<%@ include file="common.inc" %>

<html>
<head>
	<title>Register</title>
	<jsp:include page="header.jsp"></jsp:include>
	<%@page import="com.mediabox.findpro.controller.RegisterController"%>
	<%@page import="com.mediabox.findpro.form.RegisterForm"%>
	<link rel="stylesheet" href="http://getbootstrap.com/examples/signin/signin.css"></link>
</head>
<body>
<jsp:include page="navbar.jsp"></jsp:include>

<div class="container">
  <form class="form-signin">
    <h2 class="form-signin-heading">Register</h2>
    <label for="inputEmail" class="sr-only">Email address</label>
    <input type="email" id="inputEmail" class="form-control" placeholder="Email address" required="" autofocus="" autocomplete="off" style="cursor: auto; background-image: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAASCAYAAABSO15qAAAABmJLR0QA/wD/AP+gvaeTAAAACXBIWXMAAAsTAAALEwEAmpwYAAAAB3RJTUUH3QsPDhss3LcOZQAAAU5JREFUOMvdkzFLA0EQhd/bO7iIYmklaCUopLAQA6KNaawt9BeIgnUwLHPJRchfEBR7CyGWgiDY2SlIQBT/gDaCoGDudiy8SLwkBiwz1c7y+GZ25i0wnFEqlSZFZKGdi8iiiOR7aU32QkR2c7ncPcljAARAkgckb8IwrGf1fg/oJ8lRAHkR2VDVmOQ8AKjqY1bMHgCGYXhFchnAg6omJGcBXEZRtNoXYK2dMsaMt1qtD9/3p40x5yS9tHICYF1Vn0mOxXH8Uq/Xb389wff9PQDbQRB0t/QNOiPZ1h4B2MoO0fxnYz8dOOcOVbWhqq8kJzzPa3RAXZIkawCenHMjJN/+GiIqlcoFgKKq3pEMAMwAuCa5VK1W3SAfbAIopum+cy5KzwXn3M5AI6XVYlVt1mq1U8/zTlS1CeC9j2+6o1wuz1lrVzpWXLDWTg3pz/0CQnd2Jos49xUAAAAASUVORK5CYII=); background-attachment: scroll; background-position: 100% 50%; background-repeat: no-repeat;">
    <label for="inputPassword" class="sr-only">Password</label>
    <input type="password" id="inputPassword" class="form-control" placeholder="Password" required="" autocomplete="off" style="cursor: auto; background-image: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAASCAYAAABSO15qAAAABmJLR0QA/wD/AP+gvaeTAAAACXBIWXMAAAsTAAALEwEAmpwYAAAAB3RJTUUH3QsPDhss3LcOZQAAAU5JREFUOMvdkzFLA0EQhd/bO7iIYmklaCUopLAQA6KNaawt9BeIgnUwLHPJRchfEBR7CyGWgiDY2SlIQBT/gDaCoGDudiy8SLwkBiwz1c7y+GZ25i0wnFEqlSZFZKGdi8iiiOR7aU32QkR2c7ncPcljAARAkgckb8IwrGf1fg/oJ8lRAHkR2VDVmOQ8AKjqY1bMHgCGYXhFchnAg6omJGcBXEZRtNoXYK2dMsaMt1qtD9/3p40x5yS9tHICYF1Vn0mOxXH8Uq/Xb389wff9PQDbQRB0t/QNOiPZ1h4B2MoO0fxnYz8dOOcOVbWhqq8kJzzPa3RAXZIkawCenHMjJN/+GiIqlcoFgKKq3pEMAMwAuCa5VK1W3SAfbAIopum+cy5KzwXn3M5AI6XVYlVt1mq1U8/zTlS1CeC9j2+6o1wuz1lrVzpWXLDWTg3pz/0CQnd2Jos49xUAAAAASUVORK5CYII=); background-attachment: scroll; background-position: 100% 50%; background-repeat: no-repeat;">
    <div class="checkbox">
      <label>
        <input type="checkbox" value="remember-me"> Remember me
      </label>
    </div>
    <button class="btn btn-lg btn-primary btn-block" type="submit">Register</button>
  </form>

</div>

<%-- <c:set var="formName"><%=RegisterController.REGISTER_FORM%></c:set> --%>
<%-- <form:form action="register" commandName="${formName}"> --%>
<!-- 	<table style="width: 100%"> -->
<!-- 		<tr> -->
<!-- 			<td width="30%" align="center" style="padding: 10px"><label>Username</label></td> -->
<!-- 			<td width="70%"><input type="text" name="username" id="username"  -->
<!-- 				placeholder="Type username here..." /></td> -->
<!-- 		</tr> -->
<!-- 		<tr> -->
<!-- 			<td align="center" style="padding: 10px"><label>Password</label></td> -->
<!-- 			<td><input type="password" name="password" id="password"  placeholder="Type password here..." /></td> -->
<!-- 		</tr> -->
<!-- 		<tr> -->
<!-- 			<td></td> -->
<!-- 			<td style="padding-top: 10px"><input type="submit" -->
<!-- 				value="Register"></td> -->
<!-- 		</tr> -->
<!-- 	</table> -->
<%-- </form:form> --%>
<c:out value="${error}"/>

<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
