<%@ page contentType="text/html; charset=utf-8" %>
<html>
<head>
<title>Insert Title</title>
</head>
<body>
	<h3>이 파일은 first.jsp 입니다.</h3>
	<jsp:include page="second.jsp" >
		<jsp:param value="<%=java.util.Calendar.getInstance().getTime()%>" name="date"/>
	</jsp:include>
	<p>Java Server Page</p>
	
</body>
</html>