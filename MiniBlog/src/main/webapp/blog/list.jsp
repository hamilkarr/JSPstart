<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.model.dto.Blog" %>
<%
ArrayList<Blog> list = (ArrayList<Blog>)request.getAttribute("list");
String siteURL = (String)request.getAttribute("siteURL");
%>
<c:set var="list" value="<%=list%>" />
<h1>게시판 목록</h1>
<ul>
<c:forEach var="blog" items="${list}">
	<li>
		<a href='<%=siteURL%>/blog/view?idx=${blog.idx}'>
			 ${blog.subject} / ${blog.regDt}
		</a>
	</li>
</c:forEach>
</ul>
<a href='post'>게시글 작성</a>