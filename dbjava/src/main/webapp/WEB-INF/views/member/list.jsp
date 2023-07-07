<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>list</title>
<style type="text/css">
	a {text-decoration: none; color:black}
</style>
</head>
<body>
	<c:choose>
		<c:when test="${members.isEmpty() }">
			<h3>등록 후 이용하세요.</h3>
		</c:when>
		<c:otherwise>
			
			<table border=1>
				<tr>
					<th>아이디</th>
					<th>비밀번호</th>
					<th>이름</th>
					<th>이메일</th>
				</tr>
				<c:forEach var="member" items="${members }">
					<tr>
						<td>${member.id }</td>
						<td>${member.pw }</td>
						<td>${member.name }</td>
						<td>${member.email }</td>
					</tr>
				</c:forEach>
			</table>
			
			<div>${result }</div>
			
			<form action="list">
				<select name="select">
					<option value="">전체</option>
					<option value="id">아이디</option>
					<option value="email">이메일</option>
				</select>
				<input type="text" name="search" />
				<input type="submit" value="검색" />
			</form>
		</c:otherwise>
	</c:choose>

</body>
</html>







