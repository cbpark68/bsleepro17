<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*,sec01.ex01.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctxpath" value="${pageContext.request.contextPath}" />
<%
request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member</title>
</head>
<c:choose>
	<c:when test='${msg=="addMember"}'>
		<script>
			window.onload = function() {
				alert("회원을 등록했습니다.")
			}
		</script>
	</c:when>
	<c:when test='${msg=="modified"}'>
		<script>
			window.onload = function() {
				alert("회원정보를 수정했습니다.")
			}
		</script>
	</c:when>
	<c:when test='${msg=="deleted"}'>
		<script>
			window.onload = function() {
				alert("회원을 삭제했습니다.")
			}
		</script>
	</c:when>
</c:choose>
<style>
.cls1 {
	font-size: 40px;
	text-align: center;
}

.cls2 {
	font-size: 20px;
	text-align: center;
}
</style>

<body>
	<p class="cls1">회원정보</p>
	<table align="center" border="1" width="100%">
		<tr align="center" bgcolor="lightgreen">
			<th>아이디</th>
			<th>비번</th>
			<th>이름</th>
			<th>이메일</th>
			<th>가입일</th>
			<th>수정</th>
			<th>삭제</th>
		</tr>
		<c:choose>
			<c:when test="${empty membersList}">
				<th colspan="7" align="center">회원이 없습니다.</th>
			</c:when>
			<c:when test="${not empty membersList}">
				<c:forEach var="mem" items="${membersList}">
					<tr align="center">
						<td>${mem.id}</td>
						<td>${mem.pwd}</td>
						<td>${mem.name}</td>
						<td>${mem.email}</td>
						<td>${mem.joinDate}</td>
						<td><a href="${ctxpath}/member/modMemberForm.do?id=${mem.id}">수정</a></td>
						<td><a href="${ctxpath}/member/delMember.do?id=${mem.id}">삭제</a></td>
					</tr>
				</c:forEach>
			</c:when>
		</c:choose>
	</table>
	<a href="${ctxpath}/member/memberForm.do"><p class="cls2">회원가입하기</p></a>
</body>
</html>