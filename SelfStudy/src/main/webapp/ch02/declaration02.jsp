<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%!int sum(int num1, int num2){ 
		return num1 + num2;}%>
	<% out.println("2 + 3 = "+ sum(2,3)); %>
</body>
</html>