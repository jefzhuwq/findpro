<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Insert title here</title>
	<jsp:include page="header.jsp"></jsp:include>
	<link rel="stylesheet" href="resources/core/css/dashboard.css" />
</head>
<body>

<jsp:include page="navbar.jsp"></jsp:include>


<div class="container-fluid">
  <div class="row">
	<div class="col-sm-3 col-md-2 sidebar">
	  <ul class="nav nav-sidebar">
		<c:forEach var="category" items="${categories}">
	      <c:choose>
			    <c:when test="${selectedcategory.getIdcategory() == category.getIdcategory()}">
			       <li class="active">
			       <a href="menu?cat=${category.getIdcategory()}"/><c:out value="${category.getCategoryName()}"/>
			            <span class="sr-only">(current)</span>
			      	</a>
			      	</li>
			    </c:when>
			    <c:otherwise>
			        <li><a href="menu?cat=${category.getIdcategory()}"/><c:out value="${category.getCategoryName()}"/>
			      	</a></li>
			    </c:otherwise>
			</c:choose>
	    </c:forEach>
	  </ul>
<!-- 	  <ul class="nav nav-sidebar"> -->
<!-- 		<li><a href="">Nav item</a></li> -->
<!-- 		<li><a href="">Nav item again</a></li> -->
<!-- 		<li><a href="">One more nav</a></li> -->
<!-- 		<li><a href="">Another nav item</a></li> -->
<!-- 		<li><a href="">More navigation</a></li> -->
<!-- 	  </ul> -->
	</div>
	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
	  <h1 class="page-header"><c:out value="${selectedcategory.getCategoryName()}"></c:out></h1>

	  <div class="row placeholders">
		
		<c:forEach var="menu" items="${menus}">
			<div class="col-xs-6 col-sm-3 placeholder">
			  <img src="${menu.getImageUrl()}" class="img-responsive" >
			  <h4><c:out value="${menu.getName()}" /></h4>
			  <p><span class="text-muted"><c:out value="${menu.getDescription()}" /></span></p>
			  <p>
			     <a href="#" class="btn btn-primary" role="button">Add to cart</a>
			  </p>
			</div>
		</c:forEach>
	 </div>
  </div>
</div>

<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>