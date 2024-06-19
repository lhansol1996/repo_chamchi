package com.ERR.infra.party;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ERR.common.base.BaseController;
import com.ERR.common.util.UtilSetSearch;
import com.ERR.infra.code.CodeService;
import com.ERR.infra.memberParty.MemberPartyDto;

import jakarta.servlet.http.HttpSession;

@Controller
public class PartyController extends BaseController {
	private String XdmPartyCommomPath = "xdm/party/";
	private String UsrPartyCommonPath = "usr/party/";

	@Autowired
	PartyService partyservice;
	@Autowired
	CodeService codeService;

	@RequestMapping(value = "/partyXdmList")
	public String partyXdmList(@ModelAttribute("vo") PartyVo vo, Model model) throws Exception {
		UtilSetSearch.setSearch(vo);

		vo.setParamsPaging(partyservice.selectCount(vo));

		if (vo.getTotalRows() > 0) {
			model.addAttribute("list", partyservice.selectListWithPaging(vo));
		}

		return XdmPartyCommomPath + "partyXdmList";
	}

	@RequestMapping(value = "/partyXdmView")
	public String partyXdmView(PartyDto dto, Model model) throws Exception {

		model.addAttribute("item", partyservice.selectOne(dto));

		return XdmPartyCommomPath + "partyXdmView";
	}

	@RequestMapping(value = "/partyXdmUpdateForm")
	public String partyXdmUpdateForm(Model model, PartyDto dto) throws Exception {

		model.addAttribute("item", partyservice.selectOne(dto));
		return XdmPartyCommomPath + "partyXdmUpdateForm";
	}

	@RequestMapping(value = "/partyXdmInsertForm")
	public String partyXdmInsertForm(Model model) throws Exception {

		return XdmPartyCommomPath + "partyXdmInsertForm";
	}

	@RequestMapping(value = "/partyXdmInsert")
	public String partyXdmInsert(PartyDto partyDto, MemberPartyDto memberPartyDto, HttpSession httpSession, Model model)
			throws Exception {
		memberPartyDto.setMemberSeqF((String.valueOf(httpSession.getAttribute("sessMemberSeq"))));

		partyservice.insert(partyDto, memberPartyDto);
		return "redirect:/partyXdmList";
	}

	@RequestMapping(value = "/partyXdmUpdt")
	public String partyXdmUpdt(PartyDto dto, Model model) throws Exception {

		partyservice.update(dto);
		return "redirect:/partyXdmList";
	}

	@RequestMapping(value = "/partyXdmUelete")
	public String partyXdmUelete(PartyDto dto, Model model) throws Exception {

		partyservice.uelete(dto);
		return "redirect:/partyXdmList";
	}

	@RequestMapping(value = "/partyXdmMultiUelete")
	public String partyXdmMultiUelete(PartyDto dto) throws Exception {
		String[] checkboxSeqArray = dto.getCheckboxSeqArray();
		for (String checkboxSeq : checkboxSeqArray) {
			dto.setPartySeq(checkboxSeq);
			partyservice.uelete(dto);
		}

		return "redirect:/partyXdmList";
	}

	@RequestMapping(value = "/partyXdmDelete")
	public String partyXdmDelete(PartyDto dto, Model model) throws Exception {

		partyservice.delete(dto);
		return "redirect:/partyXdmList";
	}

	@RequestMapping(value = "/userPartySearchList")
	public String userPartySearchList(@ModelAttribute("vo") PartyVo vo, Model model) throws Exception {
		UtilSetSearch.setSearch(vo);
		vo.setParamsPaging(partyservice.selectCount(vo));

		if (vo.getTotalRows() > 0) {
			model.addAttribute("list", partyservice.selectListWithPaging(vo));
		}

		return UsrPartyCommonPath + "userPartySearchList";
	}

	@RequestMapping(value = "/userPartyDetail")
	public String userPartyDetail(PartyDto dto, Model model) throws Exception {

		model.addAttribute("item", partyservice.selectOneDetail(dto));
		model.addAttribute("list", partyservice.selectPartyMember(dto.getPartySeq()));

		return UsrPartyCommonPath + "userPartyDetail";
	}

	@RequestMapping(value = "/insertReq")
	public String insertReq(PartyDto partyDto, MemberPartyDto memberPartyDto, HttpSession httpSession, Model model)
			throws Exception {
		memberPartyDto.setMemberSeqF((String.valueOf(httpSession.getAttribute("sessMemberSeq"))));

		partyservice.insert(partyDto, memberPartyDto);
		return "redirect:/myProfilePartyRequest";
	}

	@RequestMapping(value = "/partyUsrInsert")
	public String partyUsrInsert(PartyDto partyDto, MemberPartyDto memberPartyDto, HttpSession httpSession, Model model)
			throws Exception {
		memberPartyDto.setMemberSeqF((String.valueOf(httpSession.getAttribute("sessMemberSeq"))));

		partyservice.insert(partyDto, memberPartyDto);
		return "redirect:/myProfileMyPartys";
	}

	@RequestMapping(value = "/insertRequest")
	public String insertRequest(PartyDto partyDto, MemberPartyDto memberPartyDto, HttpSession httpSession, Model model)
			throws Exception {
		memberPartyDto.setMemberSeqF((String.valueOf(httpSession.getAttribute("sessMemberSeq"))));

		partyservice.insertRequest(partyDto, memberPartyDto);
		return "redirect:/userPartySearchList";
	}

	@RequestMapping(value = "/updatePermit")
	public String updatePermit(PartyDto partyDto, MemberPartyDto memberPartyDto, HttpSession httpSession, Model model)
			throws Exception {
		System.out.println(memberPartyDto.getMemberSeqF());

		partyservice.updatePermit(partyDto, memberPartyDto);
		return "redirect:/myProfilePartyPermit";
	}

}
