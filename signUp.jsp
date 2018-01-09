<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>Book Store Login</title>

<link rel="stylesheet" href="css/style.css">

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

<link href='css/SignUp.css' rel='stylesheet' >

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

			<h3 align="center" style="color: red">${duplicateId}</h3>

			<div class="login-form">
				<div class="sign-up-htm">
					<form action="/register" method="post" id="signup">

						<div class="group">
							<input type="radio" value="buyer" name="role" required>Buyer
							&nbsp; <input type="radio" value="seller" name="role">Seller
						</div>

						<div class="group">
							<label for="user" class="label">ID</label> <input id="user"
								type="text" class="input" name="id" pattern="^[^-\s][\w\s-]+$"
								required>
						</div>
						<div class="group">
							<label for="pass" class="label">Password</label> <input
								id="passWrd" type="password" class="input"
								pattern="^[^-\s][\w\s-]+$" data-type="password" name="pwd"
								required>
						</div>
						<div class="group">
							<label for="user" class="label">Username</label> <input id="user"
								type="text" class="input" name="name" pattern="^[^-\s][\w\s-]+$"
								required>
						</div>
						<div class="group">
							<label for="user" class="label">Age</label> <input id="user"
								type="number" min="10" max="100" value="16" class="input"
								name="age">
						</div>
						<div class="group">
							<input type="radio" value="male" name="gender">Male
							&nbsp; <input type="radio" value="female" name="gender">Female
							&nbsp;
						</div>
						<div class="group">
							<label for="pass" class="label">Address</label>
							<textarea rows="4" class="input" cols="50" name="addr"
								form="signup" placeholder="Enter address here..." required></textarea>
						</div>
						<div class="group">
							<label for="pass" class="label">Email Address</label> <input
								id="pass" type="email" class="input" name="email"
								placeholder="example@$#..com">
						</div>
						<div class="group">
							<label for="pass" class="label">Phone No</label> <input id="pass"
								type="tel" pattern="\d{3}[\-]\d{3}[\-]\d{4}"
								placeholder="123-456-7890" class="input" name="phno" required>
						</div>
						<div class="group">
							<input type="submit" class="button" value="Sign Up">
						</div>
					</form>
					
					<div class="group">
						<form action="/signIn" method="get">
							<button type="submit" name="your_name" value="your_value"
								class="button">Are you a member?</button>
						</form>
					</div>
				</div>

			</div>
		</div>
	</div>
</body>
</html>