<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Index</title>
</head>
<body>
	<c:if test="${not empty msg }" >
		<h3>${msg }</h3>
	</c:if>
	<a href="index">인덱스</a> | 
	<a href="login">로그인</a> | 
	<a href="register">회원가입</a> 
</body>
</html>