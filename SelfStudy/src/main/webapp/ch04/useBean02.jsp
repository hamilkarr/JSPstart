<%@ page contentType="text/html; charset=utf-8"%>
<html>
<head>
<title>Insert Title</title>
</head>
<body>
	<jsp:useBean id="bean" class="com.dto.Calculator" />
	<%
	int m = bean.process(5);
	out.print("5의 세제곱 : " + m);
	%>
</body>
</html>