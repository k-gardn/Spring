<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:requestEncoding value="utf-8" />

<c:catch var="except">
	<c:set var="no" value="${Integer.parseInt(param.no) }" />
</c:catch>
<c:if test="${except != null }">
	<c:redirect url="boardForm.jsp"/>
</c:if>

<c:if test="${empty param.title }">
	<c:redirect url="boardModify.jsp?no=${no }"/>
</c:if>

<jsp:useBean id="boardDao" class="session_board.BoardDAO" />
${boardDao.updateContent(param.no, param.title, param.content)}
${boardDao.disConnection()}

<c:redirect url="boardForm.jsp"/>











