<%@ page contentType="text/html; charset=utf-8" %>
<html>
<head>
<title>Insert Title</title>
</head>
<body>
<%
	Cookie[] cookies = request.getCookies();
	for (Cookie cookie : cookies) {
		String name = cookie.getName();
		String value = cookie.getValue();
		out.println("쿠키 : " + name + ", 쿠키 값 : " + value + "<br>"); 
	}
%>
</body>
</html>