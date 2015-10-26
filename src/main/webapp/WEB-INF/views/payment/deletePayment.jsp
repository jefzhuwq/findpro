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

<h3>Delete Payment</h3>
<hr>

<form id="paymentform" class="form-horizontal" method="post">

<c:if test="${payment!=null}">
	<c:out value="${payment.getPaymentType()}" />

	<input class="btn btn-primary" type="submit" role="button" value="Confirm" />
	<a href="payment" class="btn btn-primary" role="button">Cancel</a>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</c:if>

</form>

</div>

<jsp:include page="../footer.jsp"></jsp:include>

</body>
</html>
