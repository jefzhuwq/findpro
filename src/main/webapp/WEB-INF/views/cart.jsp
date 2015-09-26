
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
<a href="#" class="btn btn-primary" role="button">Proceed to checkout</a>
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
			<tr>
			  <td><img src="" class="img-responsive" style="width: 130px; height: 140px;"></td>
			  <td>Description</td>
			  <td>$13.56</td>
			  <td>1</td>
			</tr>
			<tr>
			  <td><img src="" class="img-responsive" style="width: 130px; height: 140px;"></td>
			  <td>Description</td>
			  <td>$56.25</td>
			  <td>2</td>
			</tr>
			<tr>
			  <td><img src="" class="img-responsive" style="width: 130px; height: 140px;"></td>
			  <td>Description</td>
			  <td>$56.25</td>
			  <td>2</td>
			</tr>
		  </tbody>
		</table>
	  </div>
	</div>
</div>

</body>
</html>