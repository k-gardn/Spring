package com.care.ajaxBasic;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AjaxController {

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
	
	@ResponseBody
	@PostMapping(value="ex1c", produces = "text/plain; charset=UTF-8")
	public String ex1PostC(@RequestBody String ajaxParam) {
		System.out.println("ajax 요청 도착 : " + ajaxParam);
		return "응답 데이터";
	}
	
	@GetMapping("ex2")
	public void ex2Get() {}
	
	@Autowired private AjaxService service;
	
	@ResponseBody //return을 jsp가 아닌 응답 데이터를 주는 것이다.
	@PostMapping(value="ex2", produces = "text/plain; charset=UTF-8")
	public String ex2Post(@RequestBody(required = false) String id) {
		//System.out.println("입력한 아이디 : " + id);
		return service.exists(id);
	}
	
	@GetMapping("ex3")
	public void ex3Get() {}
	
//	@ResponseBody //return을 jsp가 아닌 응답 데이터를 주는 것이다.
//	@PostMapping("ex3")
//	public void ex3Post(@RequestBody Map<String, String> reqData) {
//		System.out.println("reqData id: " + reqData.get("id"));
//		System.out.println("reqData pw: " + reqData.get("pw"));
//	}
	
	@ResponseBody //이거 안 붙이면 이상함
	@PostMapping("ex3")
	public Map<String, String> ex3Post(@RequestBody AjaxDTO reqData) {
		System.out.println("reqData : " + reqData.getId());
		System.out.println("reqData : " + reqData.getPw());
		
		Map<String, String> resData = new HashMap<String, String>();
		resData.put("idPrint", "아이디는 서버에 저장되어 있어요");
		resData.put("pwPrint", "아이디/비밀번호를 확인 후 다시 입력하세요.");
		return resData;
	}
	
}




















