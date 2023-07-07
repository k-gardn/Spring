<%@page import="session.PageService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>memberInfo</title>
</head>
<body>
	<%@ include file="../default/header.jsp" %>
	<div align="center">
		<h1>회원 목록</h1>
		<c:catch var="except">
			<c:set var="currentPage" value="${Integer.parseInt(param.currentPage) }"/>
		</c:catch>
		
		<c:if test="${except != null }">
			<c:set var="currentPage" value="${1}"/>
		</c:if>
		
		<c:set var="pageBlock" value="${3}"/>
		<c:set var="end" value="${pageBlock * currentPage}"/>
		<c:set var="begin" value="${end - pageBlock + 1}"/>
		<jsp:useBean id="memberDao" class="session_quiz.MemberDAO" />
		
		<c:choose>
			<c:when test="${empty param.select }">
				<c:set var="members" value="${memberDao.selectAll(begin, end)}"/>
				<c:set var="totalCount" value="${memberDao.count()}"/>
			</c:when>
			<c:otherwise>
				<c:set var="members" 
						value="${memberDao.selectAll(begin, end, param.select, param.search)}"/>
				<c:set var="totalCount" value="${memberDao.count(param.select, param.search)}"/>
			</c:otherwise>
		</c:choose>
		${ memberDao.disConnection()}
		<c:choose>
			<c:when test="${empty members }">
				<h1> 등록된 데이터가 존재하지 않습니다. </h1>
			</c:when>
			<c:otherwise>
				<table border=1>
					<thead>
						<tr>
							<th>아이디</th>
							<th>이름</th>
							<th>전화번호</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="member" items="${ members}">
							<tr>
								<td onclick="location.href='userInfo.jsp?id=${member.id }'">
									${member.id }
								</td>
								<td>${member.userName }</td>
								<td>${member.mobile }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div>
					<c:set var="url" value='${"memberInfo.jsp?select="}${param.select }${"&search="}${param.search }${"&currentPage=" }' />
					${PageService.printPage(url, currentPage, totalCount, pageBlock) }
				</div>
				
				<form action="memberInfo.jsp">
					<select name="select">
						<option value="">전체</option>
						<option value="id">아이디</option>
						<option value="mobile">전화번호</option>
					</select>
					<input type="text" name="search" />
					<input type="submit" value="검색" />
				</form>
		</c:otherwise>
	</c:choose>
	</div>
	<%@ include file="../default/footer.jsp" %>
</body>
</html>






























