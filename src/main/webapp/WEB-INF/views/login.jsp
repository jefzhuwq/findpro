<%@ include file="common.inc" %>

<html>
<head>
	<title>Login</title>
	<jsp:include page="header.jsp"></jsp:include>
	<%@page import="com.mediabox.findpro.controller.LoginController"%>
	<%@page import="com.mediabox.findpro.form.LoginForm"%>
	<link rel="stylesheet" href="resources/core/css/signin.css"></link>
	
</head>
<body>

<jsp:include page="navbar.jsp"></jsp:include>
<c:set var="loginFormName"><%=LoginController.LOGIN_FORM%></c:set>
<div class="container">
	<div id="loginbox" style="margin-top: 50px;"
		class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
		<div class="panel panel-info">
			<div class="panel-heading">
				<div class="panel-title">Sign In</div>
				<div
					style="float: right; font-size: 80%; position: relative; top: -10px">
					<a href="#">Forgot password?</a>
				</div>
			</div>
			
			<div style="padding-top: 30px" class="panel-body">
				<div style="display: none" id="login-alert" class="alert alert-danger col-sm-12"></div>
				<form:form id="loginform" class="form-horizontal" role="form" method="post" commandName="${loginFormName}">
					<div style="margin-bottom: 25px" class="input-group">
						<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
						<input id="login-username"
							type="text" class="form-control" name="username" value=""
							placeholder="username or email">
					</div>

					<div style="margin-bottom: 25px" class="input-group">
						<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span> 
						<input id="login-password" type="password" class="form-control" name="password" placeholder="password">
					</div>

					<div class="input-group">
						<div class="checkbox">
							<label> <input id="login-remember" type="checkbox"
								name="remember" value="1"> Remember me
							</label>
						</div>
					</div>

					<div style="margin-top: 10px" class="form-group">
						<!-- Button -->
						<div class="col-sm-12 controls">
							<input id="btn-login" type="submit" class="btn btn-success" value="Login" />
							<a id="btn-fblogin" href="#" class="btn btn-primary">Login with Facebook</a>
						</div>
					</div>

					<div class="form-group">
						<div class="col-md-12 control">
							<div
								style="border-top: 1px solid #888; padding-top: 15px; font-size: 85%">
								Don't have an account! 
								<a href="signup">Sign Up Here </a>
							</div>
						</div>
					</div>
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				</form:form>
			</div>
		</div>
	</div>
	<div id="signupbox" style="display: none; margin-top: 50px"
		class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
		<div class="panel panel-info">
			<div class="panel-heading">
				<div class="panel-title">Sign Up</div>
				<div
					style="float: right; font-size: 85%; position: relative; top: -10px">
					<a id="signinlink" href="#"
						onclick="$('#signupbox').hide(); $('#loginbox').show()">Sign
						In</a>
				</div>
			</div>
			<div class="panel-body">
				<form:form id="registerform" class="form-horizontal" role="form" method="post">
					<div id="signupalert" style="display: none"
						class="alert alert-danger">
						<p>Error:</p>
						<span></span>
					</div>

					<div class="form-group">
						<label for="email" class="col-md-3 control-label">Email</label>
						<div class="col-md-9">
							<input type="text" class="form-control" name="email"
								placeholder="Email Address">
						</div>
					</div>

					<div class="form-group">
						<label for="firstname" class="col-md-3 control-label">First
							Name</label>
						<div class="col-md-9">
							<input type="text" class="form-control" name="firstname"
								placeholder="First Name">
						</div>
					</div>
					<div class="form-group">
						<label for="lastname" class="col-md-3 control-label">Last
							Name</label>
						<div class="col-md-9">
							<input type="text" class="form-control" name="lastname"
								placeholder="Last Name">
						</div>
					</div>
					<div class="form-group">
						<label for="password" class="col-md-3 control-label">Password</label>
						<div class="col-md-9">
							<input type="password" class="form-control" name="passwd"
								placeholder="Password">
						</div>
					</div>

					<div class="form-group">
						<label for="icode" class="col-md-3 control-label">Invitation
							Code</label>
						<div class="col-md-9">
							<input type="text" class="form-control" name="icode"
								placeholder="">
						</div>
					</div>

					<div class="form-group">
						<!-- Button -->
						<div class="col-md-offset-3 col-md-9">
							<input id="btn-signup" type="submit" class="btn btn-info" value="Sign Up">
							<span style="margin-left: 8px;">or</span>
						</div>
					</div>

					<div style="border-top: 1px solid #999; padding-top: 20px"
						class="form-group">

						<div class="col-md-offset-3 col-md-9">
							<button id="btn-fbsignup" type="button" class="btn btn-primary">
								<i class="icon-facebook"></i>   Sign Up with Facebook
							</button>
						</div>
					</div>
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				</form:form>
			</div>
		</div>
	</div>
</div>

<c:out value="${error}"/>

<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>
