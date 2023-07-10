<%@page import="java.io.File"%>
<%@page import="session_board.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:requestEncoding value="utf-8" />
<c:if test="${empty sessionScope.id }">
	<c:redirect url="../session_quiz_jstl/login.jsp"/>
</c:if>  

<c:catch var="except">
	<c:set var="no" value="${Integer.parseInt(param.no) }" />
</c:catch>
<c:if test="${except != null }">
	<c:redirect url="boardForm.jsp"/>
</c:if>
  
<jsp:useBean id="boardDao" class="session_board.BoardDAO" />
<c:set var="board" value="${boardDao.selectNo(param.no) }" />
<c:if test="${empty board}">
	<c:redirect url="boardContent.jsp?no=${no }"/>
</c:if>
<c:if test="${sessionScope.id != board.id}">
	<c:redirect url="boardContent.jsp?no=${no }"/>
</c:if>

${boardDao.delete(param.no)}
${boardDao.disConnection()}

<%
	BoardDTO board = (BoardDTO)pageContext.getAttribute("board");
	String id = (String)session.getAttribute("id");
	String saveDir = "C:\\javas\\upload\\" + id + "\\" + board.getFileName();
	File f = new File(saveDir);
	if(f.exists() == true){
		f.delete();	
	}
%>
<c:redirect url="boardForm.jsp"/>




