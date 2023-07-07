package com.care.cv;


import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller //Controller로 저장하겠다.
public class MemberController {
	/*@RequestMapping(value = "index") get 또는 post로 요청시 모두 매칭됨*/
	
	/*
	@RequestMapping(value = "index", method = RequestMethod.GET)
	@RequestMapping(value = "index", method = RequestMethod.POST)
	@RequestMapping(value = "index", method = RequestMethod.PUT)
	@RequestMapping(value = "index", method = RequestMethod.DELETE)
	
	@GetMapping(value = "index")
	@PostMapping(value = "index")
	@PutMapping(value = "index")
	@DeleteMapping(value = "index")
	
	@GetMapping("index")
	@PostMapping("index")
	*/
	
	
	// http://localhost:8086/cv/index
	@RequestMapping("index") //get 또는 post로 요청 시 모두 매칭됨
	public String index() { 
		// return "jsp이름";(보편적으로 이렇게 씀)
		
		return "index"; //index.jsp를 요청
	}
	
	// http://localhost:8086/cv/login
	// 1. controller의 mapping을 찾는다. 2. jsp를 찾는다.
	@GetMapping("login")
	public String login() {
		System.out.println("@GetMapping(\"login\") 메서드 실행");
		return "login";
	}
	
	@PostMapping("login")
	public String loginProc(String pw, String id,  String name, 
							Model model, RedirectAttributes ra) {
		System.out.println("아이디 : "+id );
		System.out.println("비밀번호 : "+pw );
		System.out.println("이름: "+name );
		
		if("admin".equals(id) && "1234".equals(pw)) {
			ra.addFlashAttribute("msg", "로그인 성공");
			return "redirect:index";
		}else {
			model.addAttribute("msg", "로그인 실패");
			return "login";
		}
		/*
		 * return "list";
		 *  - ViewResolver를 거쳐 경로를 완성하고, 완성된 경로를 찾아가 xxx.jsp를 실행해서 응답함.
		 *  
		 *  return "forward:index"; 
		 *  return "redirect:index";
		 *  - @xxxMapping을 찾아가 method를 호출하는 방식
		 *  - forward : 서버가 직접 찾아가 호출함.
		 *  - redirect : 서버가 클라이언트에게 경로를 전달하여 클라이언트가 찾아가 호출함.
		 */
	}
	
	@GetMapping("list")
	public String list(Model model) { //JSP에 데이터를 넘겨주는 방법.
		System.out.println("list() 호출됨.");
		// 데이터를 담아 JSP로 전달하는 방법
		ArrayList<MemberDTO> members = new ArrayList<>();
		MemberDTO member = new MemberDTO();
		member.setId("admin");
		member.setPw("1234");
		member.setName("관리자");
		members.add(member);
		
		member = new MemberDTO();
		member.setId("user1");
		member.setPw("1111");
		member.setName("유저일");
		members.add(member);

		model.addAttribute("members", members);
		return "list";
	}
	
//	public String loginProc(HttpServletRequest req) {}
	
	//http://localhost:8086/cv/register
	@GetMapping("register")
	public void register() {}
	
	@PostMapping("register")
	public void register(MemberDTO member, String confirm, String id) {
		System.out.println("아이디 : " + member.getId());
		System.out.println("아이디 : " + id);
		System.out.println("비밀번호 : " + member.getPw());
		System.out.println("이름 : " + member.getName());
		System.out.println("비밀번호 : " + confirm);
	}
//	public void register(HttpServletRequest req) {
//		System.out.println("아이디 : " + req.getParameter("id"));
//		System.out.println("비밀번호 : " + req.getParameter("pw"));
//		System.out.println("이름 : " + req.getParameter("name"));
//	}
//	public void register(@RequestParam("id")String i, 
//			@RequestParam("pw")String p, 
//			@RequestParam("name")String n) {
//		System.out.println("아이디 : " + i);
//		System.out.println("비밀번호 : " + p);
//		System.out.println("이름 : " + n);
//	}
//	public void register(MemberDTO member) {
//		System.out.println("아이디 : " + member.getId());
//		System.out.println("비밀번호 : " + member.getPw());
//		System.out.println("이름 : " + member.getName());
//	}
//	public void register(String id, String pw, String name) {
//		System.out.println("아이디 : " + id);
//		System.out.println("비밀번호 : " + pw);
//		System.out.println("이름 : " + name);
//	}
}

