<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">

<title>EBS</title>
<link href='css/SignIn.css' rel='stylesheet' >

<!--REQUIRED STYLE SHEETS-->
<!-- BOOTSTRAP CORE STYLE CSS -->
<link href="css/bootstrap.css" rel="stylesheet" /> 
<!-- FONTAWESOME STYLE CSS -->
<link href="css/font-awesome.min.css" rel="stylesheet" />
<!--ANIMATED FONTAWESOME STYLE CSS -->
<link href="css/font-awesome-animation.css" rel="stylesheet" />
<!--PRETTYPHOTO MAIN STYLE -->
<link href="assets/css/prettyPhoto.css" rel="stylesheet" />
<!-- CUSTOM STYLE CSS -->
<link href="css/style.css" rel="stylesheet" />

<!-- GOOGLE FONT -->
<link href='http://fonts.googleapis.com/css?family=Open+Sans'
	rel='stylesheet' type='text/css'>



<!-- <link rel="stylesheet" href="css/style.css"> -->

</head>

<body>

	<!-- NAV SECTION -->
	<div class="navbar navbar-inverse navbar-fixed-top">

		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#"><img src="img/ebook.jpg"
					width="40" height="40"> EBS</a>
			</div>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav navbar-right">			
					<li><a href="welcome">HOME</a></li>					
					<li><a href="gallery">GALLERY</a></li>
				
				</ul>
			</div>

		</div>
	</div>

	<!--END NAV SECTION -->


	
	<div class="login-wrap">
	
		<div class="login-html">
			<h3 align="center" style="color: blue">${success}</h3>
			<h3 align="center" style="color: red">${invalid}</h3>

			<div class="login-form">
				<div class="sign-in-htm">
					<form action="/login.do" method="post">

						<div class="group">
							<label for="user" class="label">UserID</label> <input id="user"
								type="text" class="input" name="id" pattern="^[^-\s][\w\s-]+$"
								required>
						</div>
						<div class="group">
							<label for="pass" class="label">Password</label> <input id="pass"
								type="password" class="input" data-type="password" name="pwd"
								pattern="^[^-\s][\w\s-]+$" required>
						</div>
						<div class="group">
							<input type="submit" class="button" value="Sign In">
						</div>

					</form>
					<div class="hr"></div>
					<div class="group">
						<form action="/register" method="get">
							<button type="submit" name="your_name" value="your_value"
								class="btn-link">Not a member?</button>
						</form>
					</div>
				</div>
			</div>



		</div>

	</div>

</body>
</html>