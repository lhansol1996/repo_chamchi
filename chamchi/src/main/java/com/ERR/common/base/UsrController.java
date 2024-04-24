package com.ERR.common.base;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ERR.common.util.UtilApi;
import com.ERR.common.util.UtilSetSearch;
import com.ERR.infra.code.CodeService;
import com.ERR.infra.member.MemberDto;
import com.ERR.infra.member.MemberService;
import com.ERR.infra.party.PartyDto;
import com.ERR.infra.party.PartyService;
import com.ERR.infra.party.PartyVo;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpSession;

@Controller
public class UsrController extends BaseController {
	private String UsrCommonPath = "usr/";
	private String UsrCommomMemberPath = "usr/member/";
	private String UsrCommomMyProfilePath = "usr/myprofile/";
	
	@Autowired
	PartyService partyService;
	@Autowired
	MemberService memberService;
	@Autowired
	CodeService codeService;

	@RequestMapping(value = "/userIndex")
	public String userIndex(Model model, PartyDto dto) throws Exception {
		model.addAttribute("partyRecentList", partyService.searchPartyForRecent5(dto));
		
		String apiURL = "https://open-api.bser.io/v1/rank/top/19/3";
		UtilApi.callERApi(apiURL);

		// API 호출 및 stringBuilder 받아오기
	    StringBuilder stringBuilder = UtilApi.callERApi(apiURL);
		
		//objectMapper 생성 
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = objectMapper.readValue(stringBuilder.toString(), Map.class);
        
//        System.out.println("######## Map");
//		for (String key : map.keySet()) {
//			String value = String.valueOf(map.get(key));
//			System.out.println("[key]:" + key + ", [value]:" + value);
//		}
		List<Map<String, Object>> topRanks = new ArrayList<>();
		topRanks = (List<Map<String, Object>>) map.get("topRanks");
		// "topRanks" 배열에서 0부터 9까지의 요소만 가져오기 - 실시간 10 
		List<Map<String, Object>> limitedTopRanks = new ArrayList<>(topRanks.subList(0, 10));
	
		model.addAttribute("topRanks", limitedTopRanks);
		
		return UsrCommonPath + "index";
	}

	@RequestMapping(value = "/myProfileUpdate")
	public String myProfileUpdate(Model model, MemberDto dto, HttpSession httpSession) throws Exception {
		dto.setMemberSeq((String) httpSession.getAttribute("sessMemberSeq"));
		model.addAttribute("item", memberService.selectOne(dto));

		return UsrCommomMyProfilePath + "myProfileUpdate";
	}
	
	@RequestMapping(value = "/myProfileRecord")
	public String myProfileRecord(@RequestParam("nickName") String nickName, Model model, MemberDto dto, HttpSession httpSession) throws Exception {
		dto.setMemberSeq((String) httpSession.getAttribute("sessMemberSeq"));
		model.addAttribute("item", memberService.selectOne(dto));
		// userNum 호출 API
		String getUserNumUrl = "https://open-api.bser.io/v1/user/nickname?query="
				+ URLEncoder.encode(nickName, "UTF-8");

		UtilApi.callERApi(getUserNumUrl);
		StringBuilder stringBuilder = UtilApi.callERApi(getUserNumUrl);

		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object> getUserMap = objectMapper.readValue(stringBuilder.toString(), Map.class);
		System.out.println(getUserMap);
		Map<String, Object> getUserNumMap = (Map<String, Object>) getUserMap.get("user");
		String userNum = String.valueOf(getUserNumMap.get("userNum"));
		String userName = String.valueOf(getUserNumMap.get("nickname"));
		model.addAttribute("userNameitem", userName);
		// 받은 userNum으로 해당 유저 상세 정보 호출 API
		String getUserStatUrl = "https://open-api.bser.io/v1/user/stats/" + userNum + "/23";
		UtilApi.callERApi(getUserStatUrl);
		StringBuilder stringBuilder2 = UtilApi.callERApi(getUserStatUrl);
		// objectMapper 생성
		ObjectMapper objectMapper2 = new ObjectMapper();
		Map<String, Object> getUserStat = objectMapper2.readValue(stringBuilder2.toString(), Map.class);
		List<Map<String, Object>> getUserStatList = (List<Map<String, Object>>) getUserStat.get("userStats");
		
		model.addAttribute("getUserStatList", getUserStatList);
		System.out.println("=============");
		System.out.println("=============");
		System.out.println("=============");
		System.out.println("=============");
		System.out.println(getUserStatList);

		return  UsrCommomMyProfilePath + "myProfileUpdate";
	}
	
	@RequestMapping(value = "/profileUpdt")
	public String profileUpdt(Model model, MemberDto dto, HttpSession httpSession) throws Exception {
		dto.setMemberSeq((String) httpSession.getAttribute("sessMemberSeq"));
		System.out.println(dto.getMemberSeq());
		memberService.updateProfile(dto);

		return "redirect:/myProfileUpdate";
	}

	@RequestMapping(value = "/myProfileMyPartys")
	public String myProfileMyPartys(@ModelAttribute("vo") PartyVo vo, Model model) throws Exception {
		UtilSetSearch.setSearch(vo);

		vo.setParamsPaging(partyService.selectCountProfileMyPartys(vo));

		if (vo.getTotalRows() > 0) {
			model.addAttribute("list", partyService.profilePartyListWithPaging(vo));
		}

		return UsrCommomMyProfilePath + "myProfileMyPartys";
	}

	@RequestMapping(value = "/myProfilePartyMake")
	public String myProfilePartyMake() throws Exception {

		return UsrCommomMyProfilePath + "myProfilePartyMake";
	}

	@RequestMapping(value = "/memberLoginRegister")
	public String memberLoginRegister() throws Exception {

		return UsrCommomMemberPath + "memberLoginRegister";
	}

	@RequestMapping(value = "/myProfilePartyRequest")
	public String myProfilePartyRequest(@ModelAttribute("vo") PartyVo vo, Model model) throws Exception {
		UtilSetSearch.setSearch(vo);

		vo.setParamsPaging(partyService.selectCountProfileRequest(vo));

		if (vo.getTotalRows() > 0) {
			model.addAttribute("list", partyService.profilePartyRequestListWithPaging(vo));
		}

		return UsrCommomMyProfilePath + "myProfilePartyRequest";
	}

	@RequestMapping(value = "/myProfilePartyPermit")
	public String myProfilePartyPermit(@ModelAttribute("vo") PartyVo vo, Model model) throws Exception {
		UtilSetSearch.setSearch(vo);

		vo.setParamsPaging(partyService.selectCountProfilePermit(vo));

		if (vo.getTotalRows() > 0) {
			model.addAttribute("list", partyService.profilePartyPermitListWithPaging(vo));
		}

		return UsrCommomMyProfilePath + "myProfilePartyPermit";
	}

	@RequestMapping(value = "/memberChangePwd")
	public String memberChangePwd() throws Exception {

		return UsrCommomMemberPath + "memberChangePwd";
	}

}