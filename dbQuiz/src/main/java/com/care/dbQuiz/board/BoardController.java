package com.care.dbQuiz.board;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
public class BoardController {
	@Autowired private BoardService service;
	@Autowired private HttpSession session;
	
	@RequestMapping("boardForm")
	public String boardForm(
			@RequestParam(value="currentPage", required = false)String cp,
			Model model) {
		service.boardForm(cp, model);
		return "board/boardForm";
	}
	
	@GetMapping("boardWrite")
	public String boardWrite() {
		String id = (String)session.getAttribute("id");
		if(id == null || id.isEmpty()) {
			return "redirect:login";
		}
		return "board/boardWrite";
	}
	
	@PostMapping("boardWriteProc")
	public String boardWriteProc(Model model, MultipartHttpServletRequest multi) {
		String msg = service.boardWriteProc(multi);
		if(msg.equals("로그인"))
			return "redirect:login";
		
		if(msg.equals("게시글 작성 완료"))
			return "redirect:boardForm";
		
		model.addAttribute("msg", msg);
		return "board/boardWrite";
	}
	
	
}












