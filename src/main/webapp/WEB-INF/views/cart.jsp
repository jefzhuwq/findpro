<%@ include file="common.inc" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Insert title here</title>
	<jsp:include page="header.jsp"></jsp:include>
	<link rel="stylesheet" href="http://getbootstrap.com/examples/dashboard/dashboard.css" />
	<%@page import="com.mediabox.findpro.controller.CartController"%>
	<%@page import="com.mediabox.findpro.form.CartForm"%>
	<c:set var="cartFormName"><%=CartController.CART_FORM%></c:set>
</head>
<body>

<jsp:include page="navbar.jsp"></jsp:include>


<div class="container-fluid">
<p><h1>Shopping Cart</h1></p>
Subtotal: $123.58
<form:form action="updateCart" method="post" role="form" commandName="${cartFormName}">
	<a href="checkout" class="btn btn-primary" role="button">Proceed to checkout</a>
    <a href="menu" class="btn btn-primary" role="button">Continue shopping</a>
	<input type="hidden" name="menuid" value="0" />
	<input type="submit" class="btn btn-primary" role="button" value="Clear cart">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form:form>


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
			  <td>
			  	${cart.key.getName()}
			  	<form:form action="updateCart" method="post" role="form" commandName="${cartFormName}">
					<input type="hidden" name="menuid" value="${cart.key.getIdmenu()}" />
					<input type="submit" class="btn btn-primary" role="button" value="remove">
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				</form:form>
			  </td>
			  <td>${cart.key.getUnitPrice()}</td>
			  <td>
			  	<form:form action="updateCart" method="post" role="form" commandName="${cartFormName}">
					<input type="hidden" name="menuid" value="${cart.key.getIdmenu()}" />
					<div class="form-group">
					  <select class="form-control" id="sel1" name="count" width="100" style="width: 100px">
						<c:forEach var="i" begin="1" end="9">
							<option value="${i}" <c:if test="${i==cart.value}">selected</c:if>><c:out value="${i}"></c:out></option>
						</c:forEach>
					  </select>
					</div>
					<input type="submit" name="${menu.getIdmenu()}" value="update" class="btn btn-primary" id="updateCount_${menu.getIdmenu()}">
			  		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			  	</form:form>
			  </td>
			</tr>
			</c:forEach>
		  </tbody>
		</table>
	  </div>
	</div>
</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>