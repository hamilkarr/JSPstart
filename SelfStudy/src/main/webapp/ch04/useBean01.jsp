<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="java.util.Calendar" %>
<html>
<head>
<title>Insert Title</title>
</head>
<body>
	<jsp:useBean id="today" class="java.util.Calendar.getInstance().getTime()"></jsp:useBean>
	<p><%=today %>
</body>
</html>