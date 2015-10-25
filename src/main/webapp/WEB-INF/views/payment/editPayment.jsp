<%@ include file="../common.inc" %>

<html>
<head>
	<title>Home</title>
	<jsp:include page="../header.jsp"></jsp:include>
    <%@page import="com.mediabox.findpro.controller.AddressController"%>
    <%@page import="com.mediabox.findpro.form.AddressForm"%>
    <c:set var="loginFormName"><%=AddressController.ADDRESS_FORM%></c:set>
</head>
<body>

<jsp:include page="../navbar.jsp"></jsp:include>

<div class="container-fluid">
<br><br><br>

<h3>Address Detail</h3>
<hr>
<form:form id="addressform" class="form-horizontal" role="form" method="post" commandName="${addressFormName}">
<c:choose>
	<c:when test="${address!=null}">
		<div class="input-group">
		  <input type="text" class="form-control" placeholder="First Name" name="firstname" aria-describedby="basic-addon1" value="${address.getFirstName()}">
		  <input type="text" class="form-control" placeholder="Last Name" name="lastname" aria-describedby="basic-addon1" value="${address.getLastName()}">
		  <input type="text" class="form-control" placeholder="Street" name="street" aria-describedby="basic-addon1" value="${address.getStreet()}">
		  <input type="text" class="form-control" placeholder="City" name="city" aria-describedby="basic-addon1" value="${address.getCity()}">
		  <input type="text" class="form-control" placeholder="State" name="state" aria-describedby="basic-addon1" value="${address.getState()}">
		  <input type="text" class="form-control" placeholder="Zip code" name="zipcode" aria-describedby="basic-addon1" value="${address.getZipcode()}">
		</div>
	</c:when>
	<c:otherwise>
		<div class="input-group">
		  <input type="text" class="form-control" placeholder="First Name" name="firstname" aria-describedby="basic-addon1">
		  <input type="text" class="form-control" placeholder="Last Name" name="lastname" aria-describedby="basic-addon1">
		  <input type="text" class="form-control" placeholder="Street" name="street" aria-describedby="basic-addon1">
		  <input type="text" class="form-control" placeholder="City" name="city" aria-describedby="basic-addon1">
		  <input type="text" class="form-control" placeholder="State" name="state" aria-describedby="basic-addon1">
		  <input type="text" class="form-control" placeholder="Zip code" name="zipcode" aria-describedby="basic-addon1">
		</div>
	</c:otherwise>
</c:choose>

<input type="submit" class="btn btn-primary" role="button" value="Save">
<a href="address" class="btn btn-primary" role="button" >Cancel</a>
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form:form>
</div>

<jsp:include page="../footer.jsp"></jsp:include>

</body>
</html>
