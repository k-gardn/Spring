<%@page import="java.net.URLEncoder"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	/*
		사용자에게 파일을 전달
	*/
	String id = request.getParameter("id");
	String no = request.getParameter("num");
	String fileName = request.getParameter("fileName");
	
	if(id == null || id.isEmpty()){
		response.sendRedirect("boardContent.jsp?num="+no);
		return;
	}
	if(fileName == null || fileName.isEmpty()){
		response.sendRedirect("boardContent.jsp?num="+no);
		return;
	}
	
	String saveDir = "C:\\javas\\upload\\" + id + "\\" + fileName; // 현재 작성자의 id가 필요함.
	File f = new File(saveDir);
	if(f.exists() == false){
		response.sendRedirect("boardContet.jsp?num="+no);
		return;
	}
	
	/*
	response.getOutputStream(); 을 사용해서 파일의 데이터를 클라이언트에게 전달.
	하지만 jsp를 java로 변환 후 실행 시 java에서 response.getOutputStream(); 을 사용하기에 충돌이 발생함.
	
	out.clear();
	out = pageContext.pushBody();
	위 두 코드로 out 내장객체를 초기화 후 현재페이지(pageContext)에서만 사용할 out 객체를 생성해서 
	클라이언트에게 데이터 전송
	*/
	out.clear();
	out = pageContext.pushBody();
	OutputStream os = response.getOutputStream();
	
	response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(fileName, "UTF-8"));
	// header에 붙여준다. 만약 파일 이름이 영어가 아닌 경우, utf-8인코딩을 파일 이름에 해줘야함.
	// 속성, 값+파일 이름. => 웹 브라우저가 속성에 파일이 있다면, 현재 전달해야할 내용이 파일이구나 인식하게됨.
	// 파일을 쓰고, client에 전달해준다.
	FileInputStream fis = new FileInputStream(f);
	byte[] b = new byte[1024];
	while(true){
		int currentSize = fis.read(b, 0, b.length); //1024씩 읽어가다가, 
		if(currentSize == -1)
			break;
		os.write(b, 0, currentSize); // 마지막이 1024로 떨어지지 않으면 마지막에 딱 맞게 읽도록한다..
	}
%>