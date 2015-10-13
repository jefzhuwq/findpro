<%@ include file="common.inc" %>

<html>
<head>
	<title>Home</title>
	<jsp:include page="header.jsp"></jsp:include>
	<link href="resources/core/css/carousel.css" rel="stylesheet">
 <style> 
   .carousel-inner > .item > img, 
   .carousel-inner > .item > a > img {
       width: 100%; 
       height: 100%;
       margin: auto; 
   } 
 </style> 
</head>
<body>

<jsp:include page="navbar.jsp"></jsp:include>

<div class="container-fluid">
<br><br><br>
<div id="myCarousel" class="carousel slide" data-ride="carousel">
     <!-- Indicators -->
     <ol class="carousel-indicators">
       <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
       <li data-target="#myCarousel" data-slide-to="1"></li>
       <li data-target="#myCarousel" data-slide-to="2"></li>
     </ol>
     <div class="carousel-inner" role="listbox">
       <div class="item active">
           <a href="menu">
           	 <img src = "https://d2ln0cvn4pv5w2.cloudfront.net/unsafe/fit-in/1500x469/filters:quality(100):max_bytes(200000):fill(white)/dcmzfk78s4reh.cloudfront.net/r1500x469_1441400668088.jpg"/>
           </a>
       </div>
       <div class="item">
         <img class="second-slide" src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" alt="Second slide">
         <div class="container">
           <div class="carousel-caption">
             <h1>Another example headline.</h1>
             <p>Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>
             <p><a class="btn btn-lg btn-primary" href="#" role="button">Learn more</a></p>
           </div>
         </div>
       </div>
       <div class="item">
         <img class="third-slide" src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" alt="Third slide">
         <div class="container">
           <div class="carousel-caption">
             <h1>One more for good measure.</h1>
             <p>Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>
             <p><a class="btn btn-lg btn-primary" href="#" role="button">Browse gallery</a></p>
           </div>
         </div>
       </div>
     </div>
     <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
       <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
       <span class="sr-only">Previous</span>
     </a>
     <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
       <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
       <span class="sr-only">Next</span>
     </a>
   </div><!-- /.carousel -->
   <c:out value="${sessionScope.name}"/>
</div>
<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>
