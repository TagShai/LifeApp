<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html> 
<head>
	<title>Main page</title>
</head> 

<body>
	<h1>Hello, ${user}!</h1>

	<c:if test="${accessType == 'USER'}">
		<div>You entered like user</div>
	</c:if>

	<c:if test="${accessType == 'ADMIN'}">
		<div>You enetered like admin</div>
	</c:if>

	<form name="logoutForm" action="controller" method="post">
		<input type="hidden" name="command" value="logout"/>
		<button type="submit">Logout</button>
	</form>

</body>
</html>