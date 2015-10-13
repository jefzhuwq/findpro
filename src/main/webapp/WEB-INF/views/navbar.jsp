<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<nav class="navbar navbar-default navbar-fixed-top">
  <div class="container-fluid">
	<div class="navbar-header">
	  <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
		<span class="sr-only">Toggle navigation</span>
		<span class="icon-bar"></span>
		<span class="icon-bar"></span>
		<span class="icon-bar"></span>
	  </button>
	  <a class="navbar-brand" href="home">My Mini Hotpot</a>
	</div>
	<div id="navbar" class="navbar-collapse collapse">
	  <ul class="nav navbar-nav navbar-right">
		<c:choose>
		    <c:when test="${sessionId!=null}">
		        <li><a href="account">My Account</a></li>
		    	<li><a href="logout">Log out</a></li>
		    </c:when>
		    <c:otherwise>
		        <li><a href="login">Sign in</a></li>
		    </c:otherwise>
		</c:choose>
		<li><a href="cart">My Cart</a></li>
	  </ul>
	  <!--
	  <form class="navbar-form navbar-right">
		<input type="text" class="form-control" placeholder="Search...">
	  </form>
	  -->
	</div>
  </div>
</nav>