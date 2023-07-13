package com.care.ajaxBasic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class AjaxController {
	@Autowired MemberService service;
	
	//이건 ajax 통신 아님!!
	@GetMapping("ex1")
	public void ex1Get() {
	}
	
	@PostMapping("ex1a")
	public void ex1PostA() {
		System.out.println("ajax 요청 도착");
	}
	
	@PostMapping("ex1b")
	public void ex1PostB(@RequestBody String ajaxParam) {
		System.out.println("ajax 요청 도착 : " + ajaxParam);
	}
	
	@ResponseBody //return값을 jsp가 아니라 응답 데이터로 달라.
	@PostMapping(value="ex1c", produces = "text/plain; charset=UTF-8")
	public String ex1Post(@RequestBody String ajaxParam) {
		System.out.println("ajax 요청 도착 : " + ajaxParam);
		return "응답 데이터";
	}
	
	@GetMapping("ex2")
	public void ex2Get() {
	}
	
	@ResponseBody 
	@PostMapping(value="ex2", produces = "text/plain; charset=UTF-8")
	public String ex2Post(@RequestBody(required = false) String id) { 
		// @RequestBody는 무조건 데이터가 있어야 해서
		// id를 입력하지 않았을 때, null이 아닌 에러가 발생.
		String result = service.idCheck(id);
		
		return result;
	}
}
