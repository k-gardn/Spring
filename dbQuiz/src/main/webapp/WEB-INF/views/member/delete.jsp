<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../default/header.jsp" %>
<c:choose>
	<c:when test="${empty sessionScope.id }">
		<c:redirect url="login.jsp"/>
	</c:when>
	<c:otherwise>
		<div align="center">
		<h1>회원 탈퇴</h1>
		<table><tr><td>
			<form action="deleteService.jsp" method="post">
				<input type="text" value="${sessionScope.id }" readonly="readonly"> <br>
				<input type="password" placeholder="비밀번호" name="pw"><br>
				<input type="password" placeholder="비밀번호 확인" name="confirmPw"><br>
				<input type="submit" value="회원 탈퇴"> 
				<input type="button" value="취소" onclick="location.href='index.jsp'">
			</form>
			</td></tr></table>
		</div>
	</c:otherwise>
</c:choose>
<%@include file="../default/footer.jsp"%>








