package com.care.dbQuiz.member;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class KakaoService {

	private String accessToken;
	private String scope;
	public void getAccessToken(String code) {
		
		/*
		 # 엑세스 토근 # 
		 https://developers.kakao.com/docs/latest/ko/kakaologin/rest-api
		 curl -v -X POST "https://kauth.kakao.com/oauth/token" \
		 -H "Content-Type: application/x-www-form-urlencoded" \
		 -d "grant_type=authorization_code" \
		 -d "client_id=${REST_API_KEY}" \
		 --data-urlencode "redirect_uri=${REDIRECT_URI}" \
		 -d "code=${AUTHORIZE_CODE}"
		 */
		
		String reqUrl = "https://kauth.kakao.com/oauth/token";
		String reqParam = "grant_type=authorization_code";
		reqParam += "&client_id=4e44c398b323055c3d41ede548ed454d";
		reqParam += "&redirect_uri=http://localhost:8086/dbQuiz/kakaoLogin";
		reqParam += "&code="+code;
		
		HttpURLConnection conn;
		URL url;
		try {
			url = new URL(reqUrl); // POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송.
			conn = (HttpURLConnection) url.openConnection(); // 연결을 맺음.
			conn.setRequestMethod("POST"); // POST 요청을 위해 기본값 false에서 setDoOutput을 true로 변경
			conn.setDoOutput(true);// POST 메소드를 이용해서 데이터를 전달하기 위한 설정
			
			// 기본 outputStream을 통해 문자열로 처리할 수 있는 OutPutStreamWriter 변환 후 처리속도를 빠르게 하기위한
			// BufferedWriter로 변환해서 사용한다.
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			bw.write(reqParam);
			bw.flush();

			int responseCode = conn.getResponseCode(); // 결과 코드가 200이라면 성공
			System.out.println("responseCode : " + responseCode);
			
			//요청을 통해 얻은 JSON 타입의 Response 메세지 읽어오기
			InputStreamReader isr = new InputStreamReader(conn.getInputStream());
			ObjectMapper om = new ObjectMapper();
			Map<String, String> map = null;
			map = om.readValue(isr, new TypeReference<Map<String, String>>() {});
			
			accessToken = map.get("access_token");
			scope = map.get("scope");
			System.out.println("access_token : " + map.get("access_token"));
			System.out.println("scope : " + map.get("scope"));
			/*
			 * BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream())); 
			 * //한 줄 씩 읽어서 문자열을 하나씩 더해줄 것. 
			 * String line = "", result=""; 
			 * while((line = br.readLine()) != null) { 
			 * result += line;} 
			 * System.out.println("response body : " + result);
			 */
			
			/*
			 * response body : {
			 * "access_token":"j6SgR9kzMMk-oOWcWyYFt-G1jxXAQ4MHJl2wIl0xCiolDgAAAYlm6bPb",
			 * "token_type":"bearer",
			 * "refresh_token":"1PHc3BdzkPByU5dnJumaEninhJw4LDrTG5ANA4flCiolDgAAAYlm6bPa",
			 * "expires_in":21599,
			 * "scope":"age_range birthday account_email profile_image gender profile_nickname"
			 * , "refresh_token_expires_in":5183999 }
			 */
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setNeedsAgreement() {
		/*
		 * # 추가 항목 동의 받기 #
		 * https://developers.kakao.com/docs/latest/ko/kakaologin/rest-api#request-code-additional-consent
		 */
		//https://kauth.kakao.com/oauth/authorize?client_id=${REST_API_KEY}&redirect_uri=${REDIRECT_URI}&response_type=code&scope=account_email,gender	
		
			String reqUrl = "https://kauth.kakao.com/oauth/authorize";
			String reqParam = "?client_id=af3185dbf0396a5bf08b67e29a79b42";
			reqParam += "&redirect_uri=http://localhost:8086/dbQuiz/kakaoLogin";
			reqParam += "&response_type=code&scope="+scope;
			
			HttpURLConnection conn;
			try {
				URL url = new URL(reqUrl); // POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
				conn = (HttpURLConnection) url.openConnection();
				conn.setDoOutput(true);
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
				bw.write(reqParam);
				bw.flush();

				int responseCode = conn.getResponseCode(); // 결과 코드가 200이라면 성공
				System.out.println("responseCode : " + responseCode);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	
	public void getUserInfo() {
		System.out.println("getUserInfo");
		/*
		 * 사용자 정보 가져오기
		 * https://developers.kakao.com/docs/latest/ko/kakaologin/rest-api#req-user-info
		 */		
		
		String reqUrl = "https://kapi.kakao.com/v2/user/me";
		HttpURLConnection conn;
		URL url;
		try {
			url = new URL(reqUrl); // POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송.
			conn = (HttpURLConnection) url.openConnection(); // 연결을 맺음.
			
			conn.setRequestMethod("POST"); // POST 요청을 위해 기본값 false에서 setDoOutput을 true로 변경
			conn.setRequestProperty("Authorization", "Bearer " + accessToken); // POST 요청을 위해 기본값 false에서 setDoOutput을 true로 변경
			conn.setDoOutput(true);// POST 메소드를 이용해서 데이터를 전달하기 위한 설정
			
			int responseCode = conn.getResponseCode(); // 결과 코드가 200이라면 성공
			System.out.println("responseCode : " + responseCode);
			
			ObjectMapper om = new ObjectMapper();
			JsonNode jsonTree = om.readTree(conn.getInputStream());
			
			System.out.println("jsonTree : " + jsonTree);
			System.out.println("kakao_account : " + jsonTree.get("kakao_account"));
			
			JsonNode kakaoAccount = jsonTree.get("kakao_account");
			System.out.println("profile : " + kakaoAccount.get("profile"));
			System.out.println("email : " + kakaoAccount.get("email"));
			System.out.println("age_range : " + kakaoAccount.get("age_range"));
			System.out.println("gender : " + kakaoAccount.get("gender"));
			
			System.out.println("profile : " + kakaoAccount.get("profile"));
			System.out.println("nickname : " + kakaoAccount.get("profile").get("nickname"));
			System.out.println(kakaoAccount.get("profile").get("nickname").textValue());
			 
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void unLink() {
		/*
		 * # 연결 끊기 #
		 * https://developers.kakao.com/docs/latest/ko/kakaologin/rest-api#unlink
		 */
		String reqUrl = "https://kapi.kakao.com/v1/user/unlink";
		HttpURLConnection conn;
		try {
			URL url = new URL(reqUrl); 
			conn = (HttpURLConnection) url.openConnection();

			conn.setRequestMethod("POST");
			conn.setRequestProperty("Authorization", "Bearer " + accessToken); // Authorization: Bearer ${ACCESS_TOKEN}

			int responseCode = conn.getResponseCode(); // 결과 코드가 200이라면 성공
			System.out.println("responseCode : " + responseCode);

			ObjectMapper om = new ObjectMapper();
			JsonNode jsonNode = om.readTree(conn.getInputStream());
			System.out.println("id : " + jsonNode.get("id"));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}