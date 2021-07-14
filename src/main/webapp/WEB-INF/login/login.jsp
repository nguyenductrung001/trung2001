<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
	<link href="/login.css" rel="stylesheet" />
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->
</head>
<body>
	<form action="/j_spring_security_check" method="Post">


		<div class="wrapper fadeInDown">
			<div id="formContent">
				<!-- Tabs Titles -->

				<!-- Icon -->
				<div class="fadeIn first">
					<img src="http://danielzawadzki.com/codepen/01/icon.svg" id="icon"
						alt="User Icon" />
				</div>

				<!-- Login Form -->
				

				
				
				<input type="text"  class="fadeIn second" name="name"
					placeholder="login">
					 <input type="password"  class="fadeIn third" name="password" placeholder="password"> <br/>
					 
					<input type="checkbox" class="fadeIn fourth" value="Log In" name="remember-me">
					<input type="submit" class="fadeIn fourth" value="Log In">


				<!-- Remind Passowrd -->
				<div id="formFooter">
					<a class="underlineHover" href="#">Forgot Password?</a>
				</div>

			</div>
		</div>
	</form>
</body>
</html>