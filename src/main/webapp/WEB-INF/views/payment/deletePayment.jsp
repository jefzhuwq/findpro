<%@ include file="../common.inc" %>

<html>
<head>
	<title>Home</title>
	<jsp:include page="../header.jsp"></jsp:include>
</head>
<body>

<jsp:include page="../navbar.jsp"></jsp:include>

<div class="container-fluid">
<br><br><br>

<h3>Delete Address</h3>
<hr>

<form id="addressform" class="form-horizontal" method="post">

<c:if test="${address!=null}">
	<c:out value="${address.getFirstName()}" />
	<c:out value="${address.getLastName()}" />
	<c:out value="${address.getStreet()}" />
	<c:out value="${address.getCity()}" />
	<c:out value="${address.getState()}" />
	<c:out value="${address.getZipcode()}" />

	<input class="btn btn-primary" type="submit" role="button" value="Confirm" />
	<a href="address" class="btn btn-primary" role="button">Cancel</a>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</c:if>

</form>

</div>

<jsp:include page="../footer.jsp"></jsp:include>

</body>
</html>
