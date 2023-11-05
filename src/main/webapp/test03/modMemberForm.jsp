<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctxpath" value="${pageContext.request.contextPath}" />
<% request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member</title>
</head>
<body>
	<form method="post" action="${ctxpath}/member/modMember.do?id=${memInfo.id}">
		<h1 style="text-align: center;">회원정보수정</h1>
		<table align="center">
			<colgroup>
				<col width="200px" />
				<col width="400px" />
			</colgroup>
			<tr>
				<th><p align="right">아이디</p></th>
				<td><input type="text" name="id" value="${memInfo.id}" disabled /></td>
			</tr>
			<tr>
				<th><p align="right">비번</p></th>
				<td><input type="password" name="pwd" value="${memInfo.pwd}" /></td>
			</tr>
			<tr>
				<th><p align="right">이름</p></th>
				<td><input type="text" name="name" value="${memInfo.name}"/></td>
			</tr>
			<tr>
				<th><p align="right">이메일</p></th>
				<td><input type="text" name="email" value="${memInfo.email}"/></td>
			</tr>
			<tr>
				<th><p align="right">&nbsp;</p></th>
				<td><button type="submit">가입하기</button>
					<button type="reset">다시하기</button></td>
			</tr>
		</table>
	</form>
</body>
</html>