package com.care.ajaxBasic;


import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class MemberService {
	@Autowired private MemberMapper memberMapper;
	
	public String idCheck(String id) {
		System.out.println("가입 id : " + id);
		if(id == null || id.isEmpty()){
			return "아이디를 입력하세요.";
		}
		ArrayList<String> allId =  memberMapper.idCheck(id);
		String result = "사용 가능한 아이디 입니다.";
		for(String checkId: allId) {
			if(checkId.equals(id)) {
				System.out.println("checkId " + checkId);
				result = "이미 등록된 아이디 입니다.";
				break;
			}
		}
		System.out.println("result : " + result);
		return result;
	}

}
