<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8" />
    <link rel="apple-touch-icon" sizes="76x76" href="assets/img/apple-icon.png">
    <link rel="icon" type="image/png" href="assets/img/favicon.ico">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0, shrink-to-fit=no' name='viewport' />
    <!--     Fonts and icons     -->
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700,200" rel="stylesheet" />
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css" />
    <!-- CSS Files -->
    <link href="assets/css/bootstrap.min.css" rel="stylesheet" />
    <link href="assets/css/light-bootstrap-dashboard.css?v=2.0.1" rel="stylesheet" />
    <!-- CSS Just for demo purpose, don't include it in your project -->
    <link href="assets/css/demo.css" rel="stylesheet" />
       <link href="BS3/assets/css/pe-icon-7-stroke.css" rel="stylesheet" />
</head>
<title>Users Lists</title>
<style>
button,
button:focus,
button:active{
	border:none;
	background:none;
	outline:none;
	padding:0;
}
button span{
	position: relative;
}

</style>
<body>
    <div class="wrapper">
        <div class="sidebar" data-image="assets/img/sidebar-5.jpg">
            <!--
        Tip 1: You can change the color of the sidebar using: data-color="purple | blue | green | orange | red"

        Tip 2: you can also add an image using data-image tag
    -->
            <div class="sidebar-wrapper">
                <div class="logo">
                    <a  class="simple-text">
                      WELCOME ADMIN
                      <br>                
                    </a>
                </div>
                <%@ include file = "placeside.jsp" %>
           
            </div>
        </div>
        <div class="main-panel">
            <!-- Navbar -->
            <nav class="navbar navbar-expand-lg " color-on-scroll="800">
                <div class=" container-fluid  ">
  <a class="navbar-brand" href="#pablo"> LIST OF PLACES </a>

                    <div class="collapse navbar-collapse justify-content-end" id="navigation">

                        <ul class="navbar-nav ml-auto">


                            <li class="nav-item">
                                <a class="nav-link" href="index.jsp">
                                    <span class="no-icon">Log out</span>
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
            <!-- End Navbar -->
     
           <div class="content">
              <div class="container-fluid">
                  <div class="row">
                      <div class="col-md-12">
                          <div class="card strpied-tabled-with-hover">
                              <div class="card-header ">


                              </div>
                              <div class="card-body table-full-width table-responsive">
                                  <table class="table table-hover table-striped">
                                     <tr class="thead-dark">
                                     			<th>Place ID</th>
												<th>Place Name</th>
												<th>Place Address</th>
												<th>Place Contact Number</th>
												<th>Place Email Address</th>
												<th>Place Description</th>
												
											</tr>
								
											<c:forEach items="${place}" var="place">
								
												<tr>
													<td>${place.placeid}</td>
													<td>${place.placename}</td>
													<td>${place.placeaddress}</td>
													<td>${place.placenum}</td>
													<td>${place.placemail}</td>
													<td>${place.placedesc}</td>
									 
											</c:forEach>                            
                                  </table>
                              </div>
                          </div>
                      </div>

                  </div>
              </div>
          </div>

      </div>
  </div>
    
</body>


<!--   Core JS Files   -->
<script src="../assets/js/core/jquery.3.2.1.min.js" type="text/javascript"></script>
<script src="../assets/js/core/popper.min.js" type="text/javascript"></script>
<script src="../assets/js/core/bootstrap.min.js" type="text/javascript"></script>
<!--  Plugin for Switches, full documentation here: http://www.jque.re/plugins/version3/bootstrap.switch/ -->
<script src="../assets/js/plugins/bootstrap-switch.js"></script>
<!--  Google Maps Plugin    -->
<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=YOUR_KEY_HERE"></script>
<!--  Chartist Plugin  -->
<script src="../assets/js/plugins/chartist.min.js"></script>
<!--  Notifications Plugin    -->
<script src="../assets/js/plugins/bootstrap-notify.js"></script>
<!-- Control Center for Light Bootstrap Dashboard: scripts for the example pages etc -->
<script src="../assets/js/light-bootstrap-dashboard.js?v=2.0.1" type="text/javascript"></script>
<!-- Light Bootstrap Dashboard DEMO methods, don't include it in your project! -->
<script src="../assets/js/demo.js"></script>

</html>
