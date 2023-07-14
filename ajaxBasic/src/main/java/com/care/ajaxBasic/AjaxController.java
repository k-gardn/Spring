package com.care.ajaxBasic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.AnnotationIntrospector.ReferenceProperty.Type;
import com.fasterxml.jackson.databind.ObjectMapper;
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
	
	//jackson-databind라이브러리를 설치
	//속성의 이름과  key값이 같다고 하면 넣어줌
//	@ResponseBody //return을 jsp가 아닌 응답 데이터를 주는 것이다.
//	@PostMapping("ex3")
//	public void ex3Post(@RequestBody Map<String, String> reqData) {
//		System.out.println("reqData id: " + reqData.get("id"));
//		System.out.println("reqData pw: " + reqData.get("pw"));
//	}
	
	//jackson-data 속성의 이름과  setter의  이름이 같다고 하면 넣어줌
	@ResponseBody //이거 안 붙이면 jsp가 return됨.
	@PostMapping("ex3")
	public Map<String, String> ex3Post(@RequestBody AjaxDTO reqData) {
		System.out.println("reqData : " + reqData.getId());
		System.out.println("reqData : " + reqData.getPw());
		
		Map<String, String> resData = new HashMap<String, String>();
		resData.put("idPrint", "아이디는 서버에 저장되어 있어요");
		resData.put("pwPrint", "아이디/비밀번호를 확인 후 다시 입력하세요.");
		return resData;
	}
	
	@GetMapping("ex4")
	public void ex4Get() {}
	
	@ResponseBody
	@PostMapping(value="ex4", produces = "application/json; charset=UTF-8")
	public String ex4Post() {
		//src/main/resources가 최상위
		ClassPathResource cpr = new ClassPathResource("jsonExam.json");
		String result = "";
		try {
			File file = cpr.getFile();
			FileReader fr = new FileReader(file);
			
			//BufferedReader의 속도가 좀 더 빠르기 때문에 사용.
			BufferedReader br = new BufferedReader(fr);
			while(true) {
				String tmp = br.readLine(); // 한줄씩 읽을 것.
				if(tmp == null)
					break;
				result += tmp;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	@GetMapping("ex5")
	public void ex5Get() {}
	
	@ResponseBody
	@PostMapping(value="ex5", produces = "application/json; charset=UTF-8")
	public List<AjaxVO> ex5Post() {
		
		ClassPathResource cpr = new ClassPathResource("jsonExam2.json");
		List<AjaxVO> lists = null;
		try {
			File file = cpr.getFile();
			
			// mapper.readValue(JSON 데이터, 반환 자료형); 를 사용하여
			// JSON Array -> List<AjaxVO> 변환
			
			ObjectMapper mapper = new ObjectMapper();
			lists = mapper.readValue(file, new TypeReference<List<AjaxVO>>() {});
			//
			
			System.out.println( (new TypeReference<List<AjaxVO>>() {}).getType() );
			//java.util.List<com.care.ajaxBasic.AjaxVO>
			//TypeReference를 사용하는 이유 : 제너릭을 사용하는 클래스의 자료형을 얻고 싶을 때
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return lists;
	}
	
	/* 
	 * 한번만 수행 또는 데이터가 변경되었을 경우에만 수행함.
	 * JSON 파일의 데이터를 테이블에 모두 입력하는 기능
	 */
	@ResponseBody
	@GetMapping(value = "jsonInsert")
	public String jsonInsert() {
		String msg = service.jsonInsert();
		return msg;
	}
	
	@GetMapping("ex6")
	public void ex6Get() {}
	
	@ResponseBody
	@PostMapping(value="ex6", produces = "application/json; charset=UTF-8")
	public List<AjaxVO> ex6Post() {
		
		return service.ex6();
	}
	
	@GetMapping("quiz")
	public void quizGet() {
		
	}
	
	@ResponseBody
	@PostMapping(value="quiz", produces = "application/json; charset=UTF-8")
	public List<AjaxVO> quizPost(@RequestBody(required = false) String search) {
		/*
		 * 첫 화면 또는 input에 아무것도 입력이 안되어 있으면 모든 데이터 출력하기.
		 * input 에 입력한 글자를 포함한 데이터가 있으면 모두 출력하기.
		 */
		System.out.println("quizPost() 호출함.");
		return service.quiz(search);
	}
}




















