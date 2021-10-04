<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="java.net.URLDecoder" %>
<html>
<head>
<title>Insert Title</title>
</head>
<body>
	<p> 아이디 : <%=request.getParameter("id") %>
	<% String name = request.getParameter("name"); %>
	<p> 이름 : <%=URLDecoder.decode(name,"UTF-8") %>
</body>
</html>