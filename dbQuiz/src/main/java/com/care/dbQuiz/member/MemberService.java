package com.care.dbQuiz.member;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.care.dbQuiz.common.PageService;


@Service
public class MemberService {
	@Autowired private MemberMapper memberMapper;
	@Autowired private HttpSession session;
	
	public String loginProc(MemberDTO member) {
		if(member.getId() == null || member.getId().isEmpty()) {
			return "아이디를 입력하세요.";
		}
		
		if(member.getPw() == null || member.getPw().isEmpty()) {
			return "비밀번호를 입력하세요.";
		}
		
		MemberDTO result = memberMapper.loginProc(member.getId());
		if(result != null && result.getPw().equals(member.getPw())){
			session.setAttribute("id", result.getId());
			session.setAttribute("userName", result.getUserName());
			
//			System.out.println("MemberService loginProc() : " + result.getUserName());
//			System.out.println("MemberService loginProc() : " + result.getAddress());
			
			session.setAttribute("address", result.getAddress());
			session.setAttribute("mobile", result.getMobile());
			return "로그인 성공";
		}
		
		return "아이디/비밀번호를 확인 후 다시 시도하세요.";
	}

	public String registerProc(MemberDTO member, String confirm) {
		if(member.getId() == null || member.getId().isEmpty()) {
			return "아이디를 입력하세요.";
		}
		
		if(member.getPw() == null || member.getPw().isEmpty()) {
			return "비밀번호를 입력하세요.";
		}
		
		if(member.getPw().equals(confirm) == false) {
			return "두 비밀번호를 일치하여 입력하세요.";
		}
		
		if(member.getUserName() == null || member.getUserName().isEmpty()) {
			return "이름을 입력하세요.";
		}
		
		MemberDTO result = memberMapper.loginProc(member.getId());
		if(result == null) {
			memberMapper.registerProc(member);
			return "회원 등록 완료";
		}
		
		return "이미 가입된 아이디 입니다.";
	}

	public void memberInfo(String cp, String select, String search, Model model) {
		if(select == null){
			select = "";
		}
		
		int currentPage = 1;
		try{
			currentPage = Integer.parseInt(cp);
		}catch(Exception e){
			currentPage = 1;
		}
		
		int pageBlock = 3; // 한 페이지에 보일 데이터의 수 
		int end = pageBlock * currentPage; // 테이블에서 가져올 마지막 행번호
		int begin = end - pageBlock + 1; // 테이블에서 가져올 시작 행번호
	
		ArrayList<MemberDTO> members = memberMapper.memberInfo(begin, end, select, search);
		int totalCount = memberMapper.count(select, search);
		String url = "memberInfo?select="+select+"&search="+search+"&currentPage=";
		String result = PageService.printPage(url, currentPage, totalCount, pageBlock);
		
		model.addAttribute("members", members);
		model.addAttribute("result", result);
		model.addAttribute("currentPage", currentPage);
	}

	public MemberDTO userInfo(String id) {
		if(id == null || id.isEmpty()) {
			return null;
		}
		
		String sessionId = (String)session.getAttribute("id");
		if(sessionId.equals(id) == false && sessionId.equals("admin") == false)
			return null;
		
//		MemberDTO result = memberMapper.loginProc(id);
		return memberMapper.loginProc(id);
	}
	
	public String updateProc(MemberDTO member, String confirm) {
		
		if(member.getPw() == null || member.getPw().isEmpty()) {
			return "비밀번호를 입력하세요.";
		}
		
		if(member.getPw().equals(confirm) == false) {
			return "두 비밀번호를 일치하여 입력하세요.";
		}
		
		if(member.getUserName() == null || member.getUserName().isEmpty()) {
			return "이름을 입력하세요.";
		}
		
		int result = memberMapper.updateProc(member);
		if(result == 1)
			return "회원 정보 수정 완료";
		return "회원 정보 수정  실패";
		
	}

	public String deleteProc(String id, String pw, String confirmPw) {
		
		if(pw == null || pw.isEmpty()) {
			return "비밀번호를 입력하세요.";
		}
		
		if(pw.equals(confirmPw) == false) {
			return "두 비밀번호를 일치하여 입력하세요.";
		}
		
		MemberDTO member = memberMapper.loginProc(id);
		if(member != null && pw.equals(member.getPw())){
			memberMapper.delete(id);
			return "회원 정보 삭제 완료";
		}
		
		return "회원 삭제  실패";
		
	}
}
