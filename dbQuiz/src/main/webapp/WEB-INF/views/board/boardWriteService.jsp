<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page import="java.io.File"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="session_board.BoardDTO"%>
<%@page import="session_board.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String id = (String)session.getAttribute("id");
	if(id == null){
		response.sendRedirect("../session_quiz/login.jsp");
		return;
	}
	/*
		bit
		8bit -> 1byte
		1024byte -> 1kb
		1024kb -> 1mb
		1024mb -> 1gb
		2014gb -> 1tb
	*/
 	int maxPostSize = 1024 * 1024 * 10;
	
	String saveDirectory = "C:\\javas\\upload\\" + id + "\\";
	
	File file = new File(saveDirectory);
	if(file.exists() == false){
		file.mkdir();
	}
	
	MultipartRequest multiRequest = new MultipartRequest(request, saveDirectory, maxPostSize, "utf-8");

	String title = multiRequest.getParameter("title");
	String content = multiRequest.getParameter("content");
	String upfile = multiRequest.getOriginalFileName("upfile");
	
	if(title == null || title.isEmpty()){
		File f = new File(saveDirectory + upfile);
		if(f.exists() == true)
			f.delete();// 폴더, 파일 삭제
		
		response.sendRedirect("boardWrite.jsp");
		return;
	}
	
	BoardDTO boardDto = new BoardDTO();
	boardDto.setTitle(title);
	boardDto.setContent(content);
	boardDto.setFileName(upfile);
	boardDto.setHits("0");
	boardDto.setId(id);
	
	Date date = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	boardDto.setWriteDate(sdf.format(date));
	
	BoardDAO boardDao = new BoardDAO();
	
	int no = boardDao.selectMaxNo(); // 게시글의 No.를 가장 최신글보다 1증가시키기 위해.
	boardDto.setNo(Integer.toString(no)); //문자로 변환 함.
	boardDao.write(boardDto);
	
	boardDao.disConnection();
	response.sendRedirect("boardForm.jsp");
%>












