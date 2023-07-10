<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/session_quiz_jstl/header.jsp"%>
<c:catch var="except">
	<c:set var="no" value="${Integer.parseInt(param.no) }" />
</c:catch>
<c:if test="${except != null }">
	<c:redirect url="boardForm.jsp"/>
</c:if>
<jsp:useBean id="boardDao" class="session_board.BoardDAO" />
<c:set var="board" value="${boardDao.selectNo(param.no)}"/>
${boardDao.disConnection()}
<c:if test="${empty board }">
	<c:redirect url="boardForm.jsp"/>
</c:if>

<div align="center">
<form action="boardModifyService.jsp" method="post">
	<input type="hidden" name="no" value="${board.no }" />
	<h1>글 수정</h1>
	<table border='1'>
		<tr>
			<th width="100">작성자</th>
			<td width="200">${board.id }</td>
			<th width="100">조회수</th>
			<td width="200">${board.hits }</td>
		</tr>
		<tr>
			<th>작성일</th>
			<td>${board.writeDate }</td>
			<th>다운로드</th>
			<td>${board.fileName }</td>
		</tr>
		<tr>
			<th>제목</th>
			<td colspan="3">
				<input style="width:100%;" type="text" name="title" value="${board.title }" />
			</td>
		</tr>
		<tr>
			<th>문서내용 수정</th>
			<td colspan="3">
				<textarea rows="10" cols="30" style="width:100%" name="content">${board.content }</textarea>
			</td>
		</tr>
		<tr>
			<td colspan="4">
				<button type="button" onclick="location.href='boardForm.jsp'">목록</button>
				<input type="submit" value="수정">
				<button type="button" onclick="history.back()">이전</button> 
			</td>
		</tr>
	</table>
</form>
</div>
<%@ include file="/session_quiz_jstl/footer.jsp"%>