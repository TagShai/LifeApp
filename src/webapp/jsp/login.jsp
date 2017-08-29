<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<fmt:setBundle basename="i18n.login.index_${locale}" />
<c:set var="locale"
	value="${not empty param.locale ? param.locale : not empty locale ? locale : 'en'}"
	scope="session" />

<!DOCTYPE html>
<html lang="en">

<head>

	<fmt:setLocale value="${locale}" scope="session"/>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">

	<meta name="description" content="This is the login page of the application">
	<meta name="author" content="Pavel Milovidov">

	<meta name="keywords" content="Some keywords in this field">

	<title>Welcome page</title>

	<!-- Titillium font family -->
	<link href='https://fonts.googleapis.com/css?family=Titillium+Web:400,300,600' rel='stylesheet' type='text/css'>
	
	<!-- Style.css -->
	<link rel="stylesheet" href="${contextPath}/resources/css/style.css">
	
</head>

<body>

	<div class="form">

		<ul class="tab-group">
			<li class="tab active"><a href="#signup">Sign Up</a></li>
			<li class="tab"><a href="#login"><fmt:message key="login"/></a></li>
		</ul>

		<div class="tab-content">
			<div id="signup">   

				<h1>Sign Up for Free</h1>

				<form action="/" method="post">
					<div class="top-row">
						<div class="field-wrap">
							<label>
								First Name<span class="req">*</span>
							</label>
							<input type="text" required autofocus autocomplete="off" />
						</div>

						<div class="field-wrap">
							<label>
								Last Name<span class="req">*</span>
							</label>
							<input type="text"required autocomplete="off"/>
						</div>
					</div>

					<div class="field-wrap">
						<label>
							Username<span class="req">*</span>
						</label>
						<input type="text" name="username" required autocomplete="off"/>
					</div>

					<div class="field-wrap">
						<label>
							Set A Password<span class="req">*</span>
						</label>
						<input type="password" name="password" required autocomplete="off"/>
					</div>

					<button type="submit" class="button button-block">Get Started</button>
				</form>
				<div>
					<form name ="languageForm" method="POST" action="controller">
						<input type="hidden" name="command" value="RU" />
						<button name="language" value="RU" type="submit" class="button button-language"><img src="${contextPath}/resources/img/internationalization/ru.png" class="img-language" alt="">Ru</button>

						<input type="hidden" name="command" value="EN" />
						<button name="language" value="EN" type="submit" class="button button-language"><img src="${contextPath}/resources/img/internationalization/en.png" class="img-language" alt="">En</button>
					</form>
				</div>
			</div>

			<div id="login">   
				<h1>Welcome Back!</h1>

				<form name="loginForm" action="controller" method="post" >
					<input type="hidden" name="command" value="login" /> 
					<div class="field-wrap">
						<label class="${errorLoginPassMessage != null ? 'has-error' : ''}">
							Username<span class="req">*</span>
						</label>
						<input type="text" name="username" required autocomplete="off"/>
					</div>

					<div class="field-wrap">
						<label class="${errorLoginPassMessage != null ? 'has-error' : ''}">
							Password<span class="req">*</span>
						</label>
						<input type="password" name="password" required autocomplete="off"/>
					</div>

					<p class="forgot"><a href="#">Forgot Password?</a></p>

			    <span>${wrongAction} ${nullPage}</span>
			   	<c:if test="${not empty errorLoginPassMessage}">
			   		<div class="has-error has-error-text" style="font-size: 25px"><fmt:message key="message.loginerror"/></div>
          </c:if>
					<button type="submit" class="button button-block">Log In</button>
				</form>
			</div>
		</div><!-- tab-content -->

	</div> <!-- /form -->

	<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

	<script src="${contextPath}/resources/js/main.js"></script>

</body>

</html>