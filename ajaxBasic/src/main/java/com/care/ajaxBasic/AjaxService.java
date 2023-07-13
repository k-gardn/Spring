package com.care.ajaxBasic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AjaxService {
	@Autowired private AjaxMapper mapper;
	
	public String exists(String id) {
		if(id == null || id.isEmpty())
			return "아이디를 입력하세요.";
		
		Pattern pattern = Pattern.compile("^[a-z0-9]{1}[a-z0-9_-]{4,19}$");
		Matcher matcher = pattern.matcher(id);
		if(matcher.matches() == false)
			return "5~20자의 영문 소문자, 숫자와 특수기호(_),(-)만 사용 가능합니다.";
		
		String result = mapper.exists(id);
		if(result == null)
			return "사용 가능한 아이디 입니다.";
		return "사용 할 수 없는 아이디입니다. 다른 아이디를 입력해 주세요.";
	}

}







