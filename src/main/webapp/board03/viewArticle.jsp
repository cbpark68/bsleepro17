<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
request.setCharacterEncoding("UTF-8");
%>
<c:set var="ctxPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script>
	function backToList(obj) {
		obj.action = "${ctxPath}/board/listArticles.do";
		obj.submit();
	}
</script>
<body>
	<h1 style="text-align: center;">수정하기</h1>
	<form name="articleForm" method="post" enctype="multipart/form-data">
		<table border="0" align="center">
			<tr>
				<th align="center">글번호</th>
				<td><input type="text" value="${article.articleNO}" disabled />
					<input type="hidden" name="articleNO" value="${article.articleNO}" />
				</td>
			</tr>
			<tr>
				<th align="center">작성자아이디</th>
				<td><input type="text" value="${article.id}" disabled />
				</td>
			</tr>
			<tr>
				<th align="center">글제목</th>
				<td ><input type="text" value="${article.title}" id="i_title"
					name="title" disabled /></td>
			</tr>
			<tr>
				<th align="center" >글내용</th>
				<td ><textarea name="content" rows="20" cols="60"
						id="i_content" disabled>${article.content}</textarea></td>
			</tr>
			<c:if test="${not empty article.imageFileName && article.imageFileName!='null'}">
			<tr>
				<th align="right" rowspan="2">이미지</th>
				<td><input type="hidden" name="originalFileName" value="${article.imageFileName}"/>
				<img src="${ctxPath}/download.do?imageFileName=${article.imageFileName}&articleNO=${article.articleNO}" id="preview"/><br/>
				</td>
			</tr>
			<tr>
				<td><input type="file" name="imageFileName"	id="i_imageFileName" disabled onchange="readURL(this);"/>
			</tr>
			</c:if>
			<tr>
				<th align="center">등록일자</th>
				<td ><input type="text" value="<fmt:formatDate value="${article.writeDate}"/>" disabled /></td>
			</tr>
			<tr id="tr_btn_modify">
				<td colspan="2" align="center">
					<button type="button" onclick="fn_modify_article(frmArticle)">수정하기</button>	
					<button type="button" onclick="backToList(frmArticle)">취소</button>
				</td>	
			</tr>
			<tr id="tr_btn">
				<td colspan="2" align="center">
				<button type="button" onclick="fn_enable(this.form)">수정하기</button>
				<button type="button" onclick="fn_remove_article('${ctxPath}/board/removeArticle.do',${article.articleNO})">삭제하기</button>
					<button type="button" onclick="backToList(frmArticle)">리스트</button>
				<button type="button" onclick="fn_reply_form('${ctxPath}/board/replyForm.do',${article.articleNO})">삭제하기</button>
			</td>
		</table>
	</form>

</body>
</html>