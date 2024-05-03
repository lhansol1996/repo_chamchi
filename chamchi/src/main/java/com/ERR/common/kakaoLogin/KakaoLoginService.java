package com.ERR.common.kakaoLogin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class KakaoLoginService {

	@Autowired
	KakaoLoginDao dao;

	// 토큰요청
	public String getAccessTokenFromKakao(String client_id, String code) throws IOException {
		// ------kakao POST 요청------
		String reqURL = "https://kauth.kakao.com/oauth/token?grant_type=authorization_code&client_id=" + client_id
				+ "&code=" + code;
		URL url = new URL(reqURL);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");

		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

		String line = "";
		String result = "";

		while ((line = br.readLine()) != null) {
			result += line;
		}

		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object> jsonMap = objectMapper.readValue(result, new TypeReference<Map<String, Object>>() {
		});

		// System.out.println("Response Body : " + result);

		String accessToken = (String) jsonMap.get("access_token");
		// String refreshToken = (String) jsonMap.get("refresh_token");
		// String scope = (String) jsonMap.get("scope");

		return accessToken;
	}

	// 사용자정보조회
	public KakaoLoginDto getUserInfo(String access_Token, KakaoLoginDto dto) throws IOException {
		// ------kakao GET 요청------
		String reqURL = "https://kapi.kakao.com/v2/user/me";
		URL url = new URL(reqURL);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Authorization", "Bearer " + access_Token);

		int responseCode = conn.getResponseCode();
		System.out.println("responseCode : " + responseCode);

		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

		String line = "";
		String result = "";

		while ((line = br.readLine()) != null) {
			result += line;
		}

		System.out.println("Response Body : " + result);

		// jackson objectmapper 객체 생성
		ObjectMapper objectMapper = new ObjectMapper();
		// JSON String -> Map
		Map<String, Object> jsonMap = objectMapper.readValue(result, new TypeReference<Map<String, Object>>() {
		});

		// 사용자 정보 추출
		// Map<String, Object> properties = (Map<String, Object>)
		// jsonMap.get("properties");
		Map<String, Object> kakao_account = (Map<String, Object>) jsonMap.get("kakao_account");

//        Long id       = (Long) jsonMap.get("id");
//        String name   = kakao_account.get("name").toString();
		String email = kakao_account.get("email").toString();
//        String gender = kakao_account.get("gender").toString();
//        String phone  = kakao_account.get("phone_number").toString();

		/*
		 * if(properties != null) { String nickname =
		 * properties.get("nickname").toString(); String profileImage =
		 * properties.get("profile_image").toString();
		 * 
		 * dto.setNickname(nickname); dto.setProfile_image(profileImage); }
		 */

		// userInfo에 넣기
//        dto.setId(id);
//        dto.setName(name);
		dto.setEmail(email);
//        dto.setPhone(phone);

		// 성별
//        if(gender.equals("male")) {
//        	dto.setGenderCd(null); // 남
//        } else {
//        	dto.setGenderCd(null); // 여
//        }

		return dto;
	}

	// 로그인 id 확인
	public KakaoLoginDto kakaoOne(KakaoLoginDto dto) {
		return dao.kakaoOne(dto);
	};

	// 회원등록
	public int kakaoInsert(KakaoLoginDto dto) {
		return dao.kakaoInsert(dto);
	};
}
