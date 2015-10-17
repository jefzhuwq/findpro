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
			  	<button type="button" name="${menu.getIdmenu()}" class="btn btn-primary" id="btnAddCart_${menu.getIdmenu()}" data-toggle="modal" data-target="#myModal">Add to cart</button>
			  </p>
			</div>
		</c:forEach>
	 </div>
  </div>
</div>

<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Message</h4>
      </div>
      <div class="modal-body">
        Item is successfully added to cart
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Continue shopping</button>
        <a class="btn btn-primary" href="cart">Go to cart</a>
      </div>
    </div>
  </div>
</div>

<jsp:include page="footer.jsp"></jsp:include>

<script>




$(document).ready(function() {
	$.ajaxSetup({ 
	    beforeSend: function(xhr, settings) {
	        function getCookie(name) {
	            var cookieValue = null;
	            if (document.cookie && document.cookie != '') {
	                var cookies = document.cookie.split(';');
	                for (var i = 0; i < cookies.length; i++) {
	                    var cookie = jQuery.trim(cookies[i]);
	                    // Does this cookie string begin with the name we want?
	                    if (cookie.substring(0, name.length + 1) == (name + '=')) {
	                        cookieValue = decodeURIComponent(cookie.substring(name.length + 1));
	                        break;
	                    }
	                }
	            }
	            return cookieValue;
	        }
	        if (!(/^http:.*/.test(settings.url) || /^https:.*/.test(settings.url))) {
	            // Only send the token to relative URLs i.e. locally.
	            xhr.setRequestHeader("X-CSRFToken", getCookie('csrftoken'));
	        }
	    } 
	});
	
	 $(":button").click(function(event){
		 var menuId = $(this).prop("name");
		 
		 if($(this).prop("id").indexOf("btnAddCart_") == 0) {
			 var url = 'addToCart?menuId=' + menuId;
			 $.get(url, function( data ) {
				 alert(data);
				});
 		 }
	 });
	});
</script>

</body>
</html>