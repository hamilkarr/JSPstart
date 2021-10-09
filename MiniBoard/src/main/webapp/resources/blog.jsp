<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.model.dao.*" %>
<%@ page import="com.model.dto.*" %>
<%@ page import="java.util.*" %>

<%
BoardDAO dao = new BoardDAO();
ArrayList<Board> list = dao.getList(1,5);
%>
<c:set var="list" value="<%=list%>" />
<c:forEach var="board" items="${list}">
	<div class="post_content" stye="boarder: 1px solid blue; padding 10px;">
	제목: 
	</div>
</c:forEach>