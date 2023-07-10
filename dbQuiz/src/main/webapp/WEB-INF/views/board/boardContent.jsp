<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:catch var="except">
	<c:set var="no" value="${Integer.parseInt(param.no) }"/>
</c:catch>

<c:if test="${except != null }">
	<c:redirect url="boardForm.jsp"/>
</c:if>
<c:set var="board" value="${boardDao.selectNo(no)}"/>

${boardDao.disConnection()}

<c:if  test="${empty board}">
	<c:redirect url="boardForm.jsp"/>
</c:if>

<script>
	function deleteCheck(){
		result = confirm('진짜로 삭제하겠습니까?');
		if(result == true){
			location.href='boardDeleteService.jsp?no=${board.no}'
		}
	}
</script>
<div align="center">
	<h1>글 내용</h1>
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
			<td onclick="location.href='boardDownload.jsp?id=${board.id }&fileName=${board.fileName }&no=${board.no }'">
			${board.fileName }
			</td>
		</tr>
		<tr>
			<th>제목</th>
			<td colspan="3">${board.title }</td>
		</tr>
		<tr>
			<th>문서내용</th>
			<td colspan="3">${board.content }</td>
		</tr>
		<tr>
			<td colspan="4">
				<button type="button" onclick="location.href='boardForm.jsp'">목록</button>
				<button type="button" onclick="location.href='boardModify.jsp?no=${board.no }'">수정</button>
				<button type="button" onclick="deleteCheck()">삭제</button> 
			</td>
		</tr>
	</table>
</div>
<%@ include file="/session_quiz_jstl/footer.jsp"%>






