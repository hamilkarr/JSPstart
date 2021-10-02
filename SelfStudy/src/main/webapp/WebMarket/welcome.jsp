<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.*"%>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU"
	crossorigin="anonymous">
<title>Welcome</title>
</head>
<body>
	<%@ include file="menu.jsp"%>
	<%!String greeting = "웹 쇼핑몰에 오신 것을 환영합니다.";
	String tagline = "welcome to Web Market!";%>
	<div class="jumbotron">
		<div class="container">
			<h1 class="display-3"><%=greeting%></h1>
		</div>
	</div>
	<div class="container">
		<div class="text-center">
			<h3><%=tagline%></h3>
			<%
			Date today = Calendar.getInstance().getTime();
			out.println("현재 접속 시간: " + today);
			%>
		</div>
		<hr>
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>