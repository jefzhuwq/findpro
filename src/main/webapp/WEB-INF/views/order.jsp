<%@ include file="common.inc" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Order Succeeded</title>
    <jsp:include page="header.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="navbar.jsp"></jsp:include>
    
    <h3>Thanks for your order. YOu could go to <a href="account">your account</a> to check the order status.</h3>
    
    <jsp:include page="footer.jsp"></jsp:include>
</body>
</html>