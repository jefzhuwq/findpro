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

<a href="editAddress?id=" class="btn btn-primary btn-lg" role="button">Enter a new address</a>

<h3>Primary Address</h3>
<hr>
<c:if test="${address!=null}">
    <c:out value="${address.getFirstName()}" /> <c:out value="${address.getLastName()}" />
	<c:out value="${address.getStreet()}" />
    <c:out value="${address.getCity()}" /> <c:out value="${address.getState()}" /> <c:out value="${address.getZipcode()}" />
    <p>
	<a href="editAddress?id=${address.getIdaddressBook()}" class="btn btn-primary" role="button">Edit</a>
	<a href="deleteAddress?id=${address.getIdaddressBook()}" class="btn btn-primary" role="button">Delete</a>	
	</p>
</c:if>

<h3>Other Addresses</h3>
<hr>

<c:if test="${addressList!=null}">
	<c:forEach var="address" items="${addressList}" >
		<c:if test="${!address.getIsPrimary()}" >
			<c:out value="${address.getFirstName()}" /> <c:out value="${address.getLastName()}" />
        	<c:out value="${address.getStreet()}" />
            <c:out value="${address.getCity()}" /> <c:out value="${address.getState()}" /> <c:out value="${address.getZipcode()}" />
			<p>
			<a href="editAddress?id=${address.getIdaddressBook()}" class="btn btn-primary" role="button">Edit</a>
			<a href="deleteAddress?id=${address.getIdaddressBook()}" class="btn btn-primary" role="button">Delete</a>
			<button class="btn btn-primary" role="button">Set as default address</button>	
			</p>
		</c:if>
	</c:forEach>
</c:if>

</div>
<jsp:include page="../footer.jsp"></jsp:include>

</body>
</html>
