<%@ include file="common.inc" %>
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
<h1>Checkout</h1>
<a href="cart" class="btn btn-primary" role="button">Back to cart</a>
<c:if test="${cartItemList!=null && cartItemList.size()!=0}" >
<a href="order" class="btn btn-primary" role="button">Place your order</a>
<h3><b>Order Total: $<c:out value="${total}" /></b></h3>
</c:if>
</div>
<div class="container-fluid">
	<div class="jumbotron">
	  <div class="table-responsive">
		<table class="table table-striped">
		  <thead>
			<tr>
			  <th></th>
			  <th></th>
			  <th></th>
			  <th></th>
			</tr>
		  </thead>
		  <tbody>
			<tr>
			  <td>1</td>
			  <td>Shipping Address</td>
			  <td>
			   <c:choose>
				    <c:when test="${address!=null}">
				       <c:out value="${address.getFirstName()}" />
                       <c:out value="${address.getLastName()}" />
                       <c:out value="${address.getStreet()}" />
                       <c:out value="${address.getCity()}" />
                       <c:out value="${address.getState()}" />
                       <c:out value="${address.getZipcode()}" />
				    </c:when>
				    <c:otherwise>
				       No Address
				    </c:otherwise>
				</c:choose>
			  </td>
			  <td><button type="button" class="btn btn-primary" id="btnChangeAddress" role="button" data-toggle="modal" data-target="#addressModal">change</button></td>
			</tr>
			<tr>
			  <td>2</td>
			  <td>Payment method</td>
			  <td>
                <c:choose>
                    <c:when test="${payment!=null}">
                       <c:out value="${payment.getPaymentType()}" />
                    </c:when>
                    <c:otherwise>
                       No Payment
                    </c:otherwise>
                </c:choose>
              </td>
			  <td><button type="button" class="btn btn-primary" id="btnChangePayment" role="button" data-toggle="modal" data-target="#paymentModal">change</button></td>
			</tr>
			<tr>
			  <td>3</td>
			  <td>Review items</td>
			  <td>
                <c:choose>
                    <c:when test="${cartItemList!=null && cartItemList.size()!=0}">
                       <c:forEach var="cartItem" items="${cartItemList}">
                        <img src="${cartItem.key.getImageUrl()}" class="img-responsive" style="width: 130px; height: 140px;" />
                        <c:out value="${cartItem.key.getName()}"></c:out>
                        <c:out value="${cartItem.key.getUnitPrice()}"></c:out>
                        <c:out value="${cartItem.value}"></c:out>
                        <c:out value="${cartItem.key.getUnitPrice() * cartItem.value}"></c:out>
                       </c:forEach>
                    </c:when>
                    <c:otherwise>
                       No Item in your cart
                    </c:otherwise>
                </c:choose>
              </td>
			  <td></td>
			</tr>
		  </tbody>
		</table>
	  </div>
	</div>
</div>



<div class="modal fade" id="addressModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Message</h4>
      </div>
      <div class="modal-body">
      	<div class="list-group">
      		<c:choose>
	      		<c:when test="${addressList!=null && addressList.size()>0}">
	      			<c:forEach var="address" items="${addressList}">
		      			<a href="selectAddress?id=${address.getIdaddressBook()}" class="list-group-item <c:if test="${address.getIsPrimary()}">active</c:if>">
                            ${address.getFirstName()}
                            ${address.getLastName()}
                            ${address.getStreet()}
                            ${address.getCity()}
                            ${address.getState()}
                            ${address.getZipcode()}
                        </a>
		      		</c:forEach>
	      		</c:when>
	      		<c:otherwise>
					Your don't have any address. Please click manage to create new address.
				</c:otherwise>
      		</c:choose>
		</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <a class="btn btn-primary" href="address">Manage Address</a>
      </div>
    </div>
  </div>
</div>

<div class="modal fade" id="paymentModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Message</h4>
      </div>
      <div class="modal-body">
        <div class="list-group">
            <c:choose>
                <c:when test="${paymentList!=null && paymentList.size()>0}">
                    <c:forEach var="payment" items="${paymentList}">
                        <a href="selectPayment?id=${payment.getIdpayment()}" class="list-group-item <c:if test="${payment.getIsPrimary()}">active</c:if>">
                            ${payment.getPaymentType()}
                        </a>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    Your don't have any payment. Please click manage to create new payment.
                </c:otherwise>
            </c:choose>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <a class="btn btn-primary" href="address">Manage Address</a>
      </div>
    </div>
  </div>
</div>

<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>