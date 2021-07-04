<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>

<html lang="en">

<head>

	<title>MYTravel Admin Login</title>

	<meta charset="UTF-8">

	<meta name="viewport" content="width=device-width, initial-scale=1">

<!--===============================================================================================-->

	<link rel="icon" type="image/png" href="images/icons/favicon.ico"/>

<!--===============================================================================================-->

	<link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">

<!--===============================================================================================-->

	<link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">

<!--===============================================================================================-->

	<link rel="stylesheet" type="text/css" href="fonts/iconic/css/material-design-iconic-font.min.css">

<!--===============================================================================================-->

	<link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">

<!--===============================================================================================-->

	<link rel="stylesheet" type="text/css" href="vendor/css-hamburgers/hamburgers.min.css">

<!--===============================================================================================-->

	<link rel="stylesheet" type="text/css" href="vendor/animsition/css/animsition.min.css">

<!--===============================================================================================-->

	<link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css">

<!--===============================================================================================-->

	<link rel="stylesheet" type="text/css" href="vendor/daterangepicker/daterangepicker.css">

<!--===============================================================================================-->

	<link rel="stylesheet" type="text/css" href="css/util.css">

	<link rel="stylesheet" type="text/css" href="css/main.css">

<!--===============================================================================================-->
<script type="text/javascript">

windows.setTimeout(setEnabled,30000);

funvtion setEnable(){
	var submitButton = document.getElementById('submit');
	if(submitButton){
		submitButton.disabled = false;
	}
}

function submitPoll(){
    document.getElementById("submit").disabled = true;
    setTimeout(function(){document.getElementById("submit").disabled = false;},50);
}

</script>


</head>


<body >



	<div class="limiter">

		<div class="container-login100">

			<div class="wrap-login100">

				<form class="login100-form validate-form"  action="Controller?action=login" method="post" onSubmit="submitPoll()" >

					<span class="login100-form-title p-b-26">

						Welcome

					</span>

					<span class="login100-form-title p-b-48">

						<i class="zmdi zmdi-font"></i>

					</span>

					<p style="color: #e60e0e">${NOTIFICATION}</p>

					<div class="wrap-input100 validate-input" data-validate = "Please enter name">

						<input class="input100" type="text" name="Username" placeholder="Username"${adminlog.checkdis} required>
						
					</div>

					<div class="wrap-input100 validate-input" data-validate="Enter password" >


						<input class="input100" type="password" name="pass" placeholder="Password"${adminlog.checkdis} required>


					</div>



					<div class="container-login100-form-btn">

						<div class="wrap-login100-form-btn">

							<div class="login100-form-bgbtn"></div>

							<button class="login100-form-btn" id="submit" name="submit" value="Submit" ${adminlog.checkdis}>

								Login

							</button>

						</div>

					</div>

					<br>

					<center>

					<?php

													if(isset($_REQUEST["err"]))

															echo "Invalid Username or Password";

											?>

										</center>

				</form>

			</div>

		</div>

	</div>





	<div id="dropDownSelect1"></div>



<!--===============================================================================================-->

	<script src="vendor/jquery/jquery-3.2.1.min.js"></script>

<!--===============================================================================================-->

	<script src="vendor/animsition/js/animsition.min.js"></script>

<!--===============================================================================================-->

	<script src="vendor/bootstrap/js/popper.js"></script>

	<script src="vendor/bootstrap/js/bootstrap.min.js"></script>

<!--===============================================================================================-->

	<script src="vendor/select2/select2.min.js"></script>

<!--===============================================================================================-->

	<script src="vendor/daterangepicker/moment.min.js"></script>

	<script src="vendor/daterangepicker/daterangepicker.js"></script>

<!--===============================================================================================-->

	<script src="vendor/countdowntime/countdowntime.js"></script>

<!--===============================================================================================-->

	<script src="js/main.js"></script>



</body>

</html>

