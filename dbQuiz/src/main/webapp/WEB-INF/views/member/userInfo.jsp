<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>userInfo</title>
</head>
<body>
<%@ include file="header.jsp" %>

<c:choose>
	<c:when test="${empty sessionScope.id }">
		<c:redirect url="login.jsp"/>
	</c:when>
	<c:when test="${empty param.id }">
		<c:redirect url="memberInfo.jsp"/>
	</c:when>
	<c:when test="${sessionScope.id != param.id and sessionScope.id != 'admin'}">
		<c:redirect url="memberInfo.jsp"/>
	</c:when>
	<c:otherwise>
		<jsp:useBean id="memberDao" class="session_quiz.MemberDAO"/>
		<c:set var="member" value="${memberDao.selectId(param.id) }" />
		${memberDao.disConnection() }
		<div align="center">
			<h1>개인 정보</h1>
			아이디 : ${member.id } <br> 
			비밀번호 : ${member.pw }<br>
			이름 : ${member.userName }<br>
			주소 : ${member.address } <br>
			전화번호 : ${member.mobile } <br><br>
			<button type="button" onclick="location.href='update.jsp'">회원 수정</button>
			<button type="button" onclick="location.href='delete.jsp'">회원 삭제</button>
		</div>	
	</c:otherwise>
</c:choose>
<%@ include file="footer.jsp" %>
</body>
</html>



















