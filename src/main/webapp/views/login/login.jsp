<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>Login V1</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->	
	<link rel="icon" type="image/png" href="/SANGTM_PH17730_ASM/images/icons/favicon.ico"/>
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="/SANGTM_PH17730_ASM/vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="/SANGTM_PH17730_ASM/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="/SANGTM_PH17730_ASM/vendor/animate/animate.css">
<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="/SANGTM_PH17730_ASM/vendor/css-hamburgers/hamburgers.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="/SANGTM_PH17730_ASM/vendor/select2/select2.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="/SANGTM_PH17730_ASM/css/util.css">
	<link rel="stylesheet" type="text/css" href="/SANGTM_PH17730_ASM/css/main.css">
	<!-- CSS only -->
<!--===============================================================================================-->
</head>
<body>
	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100">
				<div class="login100-pic js-tilt" data-tilt>
					<img src="/SANGTM_PH17730_ASM/images/img-01.png" alt="IMG">
				</div>
				<form class="login100-form validate-form" action="/SANGTM_PH17730_ASM/login" method="post">
					<span class="login100-form-title">
						Member Login
					</span>
					<c:if test="${!empty sessionScope.error }">
						<div class="alert alert-danger">
							${ sessionScope.error }
							<c:remove var="error" scope="session"/>
						</div>
					</c:if>
					<div class="wrap-input100">
						<input class="input100" type="text" name="sdt" placeholder="Phone number">
						<span class="focus-input100"></span>
						<span class="symbol-input100">
							<i class="fa fa-mobile fs-22" aria-hidden="true"></i>
						</span>
					</div>

					<div class="wrap-input100" data-validate = "Password is required">
						<input class="input100" type="password" name="pass" placeholder="Password">
						<span class="focus-input100"></span>
						<span class="symbol-input100">
							<i class="fa fa-lock fs-17" aria-hidden="true"></i>
						</span>
					</div>
					<div class="container-login100-form-btn">
						<button class="login100-form-btn">
							Login
						</button>
					</div>
					<div class="text-center p-t-12">
						<span class="txt1">
							Forgot
						</span>
						<a class="txt2" href="#">
							Username / Password?
						</a>
					</div>
				</form>
			</div>
		</div>
	</div>
	
	

	
<!--===============================================================================================-->	
	<script src="/SANGTM_PH17730_ASM/vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
	<script src="/SANGTM_PH17730_ASM/vendor/bootstrap/js/popper.js"></script>
	<script src="/SANGTM_PH17730_ASM/vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
	<script src="/SANGTM_PH17730_ASM/vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
	<script src="/SANGTM_PH17730_ASM/vendor/tilt/tilt.jquery.min.js"></script>
	<script >
		$('.js-tilt').tilt({
			scale: 1.1
		})
	</script>
<!--===============================================================================================-->
	<script src="/SANGTM_PH17730_ASM/js/main.js"></script>

</body>
</html>