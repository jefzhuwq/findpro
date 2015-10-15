<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Insert title here</title>
	<jsp:include page="header.jsp"></jsp:include>
	<link rel="stylesheet" href="http://getbootstrap.com/examples/dashboard/dashboard.css" />
</head>
<body>

<jsp:include page="navbar.jsp"></jsp:include>


<div class="container-fluid">
<p><h1>Shopping Cart</h1></p>
Subtotal: $123.58
<a href="checkout" class="btn btn-primary" role="button">Proceed to checkout</a>
<a href="menu" class="btn btn-primary" role="button">Continue shopping</a>
</div>
<div class="container-fluid">
	<div class="jumbotron">
	  <div class="table-responsive">
		<table class="table table-striped">
		  <thead>
			<tr>
			  <th></th>
			  <th></th>
			  <th>Price</th>
			  <th>Quantity</th>
			</tr>
		  </thead>
		  <tbody>
		    <c:forEach var="cart" items="${cartList}">
			<tr>
			  <td><img src="${cart.key.getImageUrl()}" class="img-responsive" style="width: 130px; height: 140px;"></td>
			  <td>${cart.key.getName()}</td>
			  <td>${cart.key.getUnitPrice()}</td>
			  <td>${cart.value}</td>
			</tr>
			</c:forEach>
		  </tbody>
		</table>
	  </div>
	</div>
</div>

</body>
</html>