<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
	<title>Login</title>
	<link rel="stylesheet"
		href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
	<script src="//code.jquery.com/jquery-1.10.2.js"></script>
	<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
	<script>
	$(function() {
	  $( "input[type=submit], a, button" )
	    .button()
	    .click(function( event ) {
	      //event.preventDefault();
	    });
	});
	</script>
	<%@page import="com.mediabox.findpro.controller.LoginController"%>
	<%@page import="com.mediabox.findpro.form.LoginForm"%>
</head>
<body>
<h1>
	Login to an existing account
</h1>
<c:set var="formName"><%=LoginController.LOGIN_FORM%></c:set>
<form:form action="login" commandName="${formName}">
	<table style="width: 100%">
		<tr>
			<td width="30%" align="center" style="padding: 10px"><label>Username</label></td>
			<td width="70%"><input type="text" name="username" id="username" 
				placeholder="Type username here..." /></td>
		</tr>
		<tr>
			<td align="center" style="padding: 10px"><label>Password</label></td>
			<td><input type="password" name="password" id="password" placeholder="Type password here..." /></td>
		</tr>
		<tr>
			<td></td>
			<td style="padding-top: 10px"><input type="submit"
				value="Login"> <a href="register">Sign up</a></td>
		</tr>
	</table>
</form:form>
<c:out value="${error}"/>


<div id="login-box">

		<h3>Login with Username and Password</h3>

		<c:if test="${not empty error}">
			<div class="error">${error}</div>
		</c:if>
		<c:if test="${not empty msg}">
			<div class="msg">${msg}</div>
		</c:if>

		<form name='loginForm'
			action="<c:url value='/login' />" method='POST'>

			<table>
				<tr>
					<td>User:</td>
					<td><input type='text' name='username'></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type='password' name='password' /></td>
				</tr>
				<tr>
					<td colspan='2'><input name="submit" type="submit"
						value="submit" /></td>
				</tr>
			</table>

			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />

		</form>
	</div>
	
	
</body>
</html>
