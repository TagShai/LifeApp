<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
     
    <title>Uncaught error</title>
     
</head>
<body>

<center>
    <h1 style="margin-top: 70px;">Uncaught error</h1>
    <h3 style="margin-top: 70px;">Try to refresh this page later</h3>
    Request from ${pageContext.errorData.requestURI} is failed
    <br>
    Servlet name: ${pageContext.errorData.servletName}
    <br>
    Status code: ${pageContext.errorData.statusCode}
    <br>
    Exception: ${pageContext.exception}
    <br>
    Message from exception: ${pageContext.exception.message}
</center>

</body>
</html>