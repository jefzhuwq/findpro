<%@ include file="../common.inc" %>

<html>
<head>
	<title>Home</title>
	<jsp:include page="../header.jsp"></jsp:include>
    <%@page import="com.mediabox.findpro.controller.PaymentController"%>
    <%@page import="com.mediabox.findpro.form.PaymentForm"%>
    <c:set var="paymentFormName"><%=PaymentController.PAYMENT_FORM%></c:set>
</head>
<body>

<jsp:include page="../navbar.jsp"></jsp:include>

<div class="container-fluid">
<br><br><br>

<h3>Address Detail</h3>
<hr>
<form:form id="paymentform" class="form-horizontal" role="form" method="post" commandName="${paymentFormName}">
<c:choose>
	<c:when test="${payment!=null}">
		<div class="input-group">
		  <input type="text" class="form-control" placeholder="Payment Type" name="paymentType" aria-describedby="basic-addon1" value="${payment.getPaymentType()}">
		</div>
	</c:when>
	<c:otherwise>
		<div class="input-group">
		  <input type="text" class="form-control" placeholder="Payment Type" name="paymentType" aria-describedby="basic-addon1">
		</div>
	</c:otherwise>
</c:choose>

<input type="submit" class="btn btn-primary" role="button" value="Save">
<a href="payment" class="btn btn-primary" role="button" >Cancel</a>
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form:form>
</div>

<jsp:include page="../footer.jsp"></jsp:include>

</body>
</html>
