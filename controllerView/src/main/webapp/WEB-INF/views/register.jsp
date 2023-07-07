<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>register</title>
</head>
<body>
	<h1>회원 가입</h1>
	<form action="register" method="post">
		<input type="text" name="id" placeholder="아이디" /><br>
		<input type="password" name="pw" placeholder="비밀번호" /><br>
		<input type="text" name="name" placeholder="이름" /><br>
		<input type="submit" value="회원 가입"/>
		<input type="button" value="회원 취소" 
				onclick="javascript:location.href='index'"/>
	</form>
</body>
</html>







