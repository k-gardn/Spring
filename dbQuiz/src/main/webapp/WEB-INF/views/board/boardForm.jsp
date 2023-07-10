<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardForm</title>
</head>
<body>
	<c:import url="/header" />
	
	<div align="center"  >
		<h1>게시판 목록</h1>
		<table border=1 style="width:80%;">
			<thead>
				<tr>
					<th>No.</th>
					<th width="200px">제목</th>
					<th>작성자</th>
					<th>작성일</th>
					<th>조회수</th>
				</tr>
			</thead>
			<tbody>
			<c:choose>
				<c:when test="${empty boards}">
					<tr align="center">
						<td colspan='5'>등록된 값이 없습니다.</td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach var="board" items="${boards}">
						<tr align="center">
							<td>${board.no}</td>
							<td onclick="location.href='boardContent?no=${board.no}'">
								${board.title}
							</td>
							<td>${board.id}</td>
							<td>${board.writeDate}</td>
							<td>${board.hits}</td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
				<tr>
					<td colspan='4'>
						${result}
					</td>
					<td>
						<button type="button" onclick="location.href='boardWrite'">글쓰기</button>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<c:import url="/footer" />
	
</body>
</html>