package com.ERR.common.kakaoLogin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class KakaoLoginController {
	private String UsrCommonPath = "usr/";
	private String UsrCommomMemberPath = "usr/member/";
	
	@Value("${javascript_key}")
	private String javascriptKey;

	@Value("${kakao_rest_key}")
	private String kakaoRestKey;

	@Value("${kakao_redirect_uri}")
	private String kakaoRedirectUri;
	
	@Value("${kakao_location}")
	private String location;

	@Autowired
	KakaoLoginService service;

	// 로그인화면
//	@RequestMapping(value = "/memberLoginRegister")
//	public String kakaoLogin(Model model) throws Exception {
//		model.addAttribute("location", location);
//		model.addAttribute("javascriptKey", javascriptKey);
//		model.addAttribute("kakaoRestKey", kakaoRestKey);
//		model.addAttribute("kakaoRedirectUri", kakaoRedirectUri);
//		// System.out.println(location);
//		return UsrCommomMemberPath + "memberLoginRegister";
//	}

	@RequestMapping(value = "/redirectKakao")
	public String loginKakaoRedirect(KakaoLoginDto dto, KakaoLoginDto isDto, Model model) throws Exception {
		System.out.println("dto.getCode()================" + dto.getCode());

		// 토큰 받기
		String accessToken = service.getAccessTokenFromKakao(kakaoRestKey, dto.getCode());

		dto = service.getUserInfo(accessToken, dto);

		// 회원존재확인
//        isDto = service.selectOneLogin(dto);

//        	service.insert(dto);

		model.addAttribute("info", dto);

		return UsrCommonPath + "index";
	}

}
