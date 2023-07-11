package com.care.dbQuiz.board;

import javax.servlet.http.HttpServletResponse;
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
			@RequestParam(value="currentPage", required = false)String cp, //currentPage 파라미터가 없으면 null을 넣어줘라.
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
	
	@RequestMapping("boardContent")
	public String boardContent(
			@RequestParam(value="no", required = false)String n, Model model) {
		BoardDTO board = service.boardContent(n);
		if(board == null)
			return "redirect:boardForm";
	
		model.addAttribute("board", board);
		return "board/boardContent";
	}
	
	@RequestMapping("boardDownload")
	public String boardDownload(
			@RequestParam(value="no", required = false)String n, 
			HttpServletResponse res){
		
		boolean result = service.boardDownload(n, res);
		if(result == false)
			return "redirect:boardForm";
		
		return "forward:boardContent?no="+n;
	}
	
	@GetMapping("boardModify")
	public String boardModify(
			@RequestParam(value="no", required = false)String n,
			Model model) {
		
		String id = (String)session.getAttribute("id");
		if(id == null || id.isEmpty()) {
			return "redirect:login";
		}
		BoardDTO board = service.boardModify(n);
		if(board == null)
			return "redirect:boardForm";
		
		
		model.addAttribute("board",board);
		return "board/boardModify";
	}
	
	@PostMapping("boardModifyProc")
	public String boardModifyProc(BoardDTO board) {
		String id = (String)session.getAttribute("id");
		if(id == null || id.isEmpty()) {
			return "redirect:login";
		}
		
		String msg = service.boardModifyProc(board);
		if(msg.equals("게시글 수정 완료"))
			return "forward:boardContent?no="+board.getNo();
		
		return "forward:boardModify?no="+board.getNo();
	}
	
}












