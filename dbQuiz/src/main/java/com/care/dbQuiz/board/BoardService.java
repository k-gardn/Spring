package com.care.dbQuiz.board;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.care.dbQuiz.common.PageService;

@Service
public class BoardService {
	@Autowired private BoardMapper boardMapper;
	@Autowired private HttpSession session;
	
	public void boardForm(String cp, Model model) {
		
		int currentPage = 1;
		try{
			currentPage = Integer.parseInt(cp);
		}catch(Exception e){
			currentPage = 1;
		}
		
		int pageBlock = 3; // 한 페이지에 보일 데이터의 수 
		int end = pageBlock * currentPage; // 테이블에서 가져올 마지막 행번호
		int begin = end - pageBlock + 1; // 테이블에서 가져올 시작 행번호
	
		ArrayList<BoardDTO> boards = boardMapper.boardForm(begin, end);
		int totalCount = boardMapper.count();
		String url = "boardForm?&currentPage=";
		String result = PageService.printPage(url, currentPage, totalCount, pageBlock);
		
		model.addAttribute("boards", boards);
		model.addAttribute("result", result);
		model.addAttribute("currentPage", currentPage);
	}

	public String boardWriteProc(MultipartHttpServletRequest multi) {
		
		String id = (String)session.getAttribute("id");
		if(id == null || id.isEmpty()) {
			return "로그인";
		}
		
		
		BoardDTO board = new BoardDTO();
		board.setId(id);
		board.setTitle(multi.getParameter("title"));
		board.setContent(multi.getParameter("content"));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		board.setWriteDate(sdf.format(new Date()));
		
		if(board.getTitle() == null || board.getTitle().isEmpty()) {
			return "제목을 입력하세요.";
		}
		
		MultipartFile file = multi.getFile("upfile");
		String fileName = file.getOriginalFilename();
		if(file.getSize() != 0) {
			// 파일의 중복을 해결하기 위해 시간의 데이터를 파일이름으로 구성함.
			sdf = new SimpleDateFormat("yyyyMMddHHmmss-");
			Calendar cal = Calendar.getInstance();
			fileName = sdf.format(cal.getTime()) + fileName;
			board.setFileName(fileName);
			
			// 업로드 파일 저장 경로
			String fileLocation = "C:\\javas\\upload\\";
			File save = new File(fileLocation + fileName);
			
			try {
				// 서버가 저장한 업로드 파일은 임시저장경로에 있는데 개발자가 원하는 경로로 이동
				file.transferTo(save);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			board.setFileName("파일 없음");
		}
		
		boardMapper.boardWriteProc(board);
		return "게시글 작성 완료";
	}

}









