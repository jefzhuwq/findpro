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

<a href="editPayment?id=" class="btn btn-primary btn-lg" role="button">Enter a new payment option</a>

<h3>Primary Payment</h3>
<hr>
<c:if test="${payment!=null}">
    <c:out value="${payment.getPaymentType()}" />
    <p>
	<a href="editPayment?id=${payment.getIdpayment()}" class="btn btn-primary" role="button">Edit</a>
	<a href="deletePayment?id=${payment.getIdpayment()}" class="btn btn-primary" role="button">Delete</a>	
	</p>
</c:if>

<h3>Other Payment</h3>
<hr>

<c:if test="${paymentList!=null}">
	<c:forEach var="payment" items="${paymentList}" >
		<c:if test="${!payment.getIsPrimary()}" >
			<c:out value="${payment.getPaymentType()}" />
			<p>
			<a href="editPayment?id=${payment.getIdpayment()}" class="btn btn-primary" role="button">Edit</a>
			<a href="deletePayment?id=${payment.getIdpayment()}" class="btn btn-primary" role="button">Delete</a>
			<button class="btn btn-primary" role="button">Set as default payment</button>	
			</p>
		</c:if>
	</c:forEach>
</c:if>

</div>
<jsp:include page="../footer.jsp"></jsp:include>

</body>
</html>
