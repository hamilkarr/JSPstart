<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Insert Title</title>
</head>
<body>
<%
Cookie[] cookies = request.getCookies();

for (Cookie cookie : cookies) {
	cookie.setMaxAge(0);
	response.addCookie(cookie);
}
%>
<c:import var="url" url="ex02.jsp"  />
${url}

</body>
</html>

