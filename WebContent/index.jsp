<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>Welcome</title>
</head>
<body>
	This webapp is an sample about spring web, spring transaction, mybatis.<br>
	Test URL:<br>
	http://localhost:8080/SI_Basic/test/json?name=kiki<br>
	http://localhost:8080/SI_Basic/test/jsp/grape<br>
	http://localhost:8080/SI_Basic/test/user/all<br>
	http://localhost:8080/SI_Basic/test/user/query<br>
	http://localhost:8080/SI_Basic/test/whoami<br>
	<p>
	Current User Status:
	<sec:authorize access="isAuthenticated()">
		<b>logined</b>
		<form action='/SI_Basic/j_spring_security_logout' method='GET'>
			<input type="submit" value="logout" />
		</form>
	</sec:authorize>
	<sec:authorize access="!isAuthenticated()">
		<b>not login</b>
	</sec:authorize>
</body>
</html>