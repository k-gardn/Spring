<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../default/header.jsp" %>

<div align="center">
	<h1>로그인</h1>
	<table ><tr><td>
	<form action="loginProc" method="post" id="f">
		<input type="text" name="id" placeholder="아이디" id="id"><br>
		<input type="password" name="pw" placeholder="비밀번호" id="pw"><br>
		<input type="button" value="로그인" onclick="loginCheck()">
		<input type="button" value="취소" onclick="location.href='index'"><br>
	</form>
	</td></tr>
	
	<tr><td>
		<a href="https://kauth.kakao.com/oauth/authorize?response_type=code&
		client_id=4e44c398b323055c3d41ede548ed454d&
		redirect_uri=http://localhost:8086/dbQuiz/kakaoLogin">
			<img src = "https://k.kakaocdn.net/14/dn/btroDszwNrM/I6efHub1SN5KCJqLm1Ovx1/o.jpg" />
		</a>
	</td></tr>
	</table>
</div>
<%@ include file="../default/footer.jsp" %>



