<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="java.net.*" %>
<html>
<head>
<title>Insert Title</title>
</head>
<body>
	<h3>param 액션 태그</h3>
	<jsp:forward page="param01.-data.jsp">
		<jsp:param name="id" value="admin" />
		<jsp:param value="<%=java.net.URLEncoder.encode("관리자","UTF-8") %>" name="name"/>
	</jsp:forward>
</body>
</html>