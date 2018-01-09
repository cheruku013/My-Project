<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>EBS Admin</title>

<link href="css/book_reg.css" rel="stylesheet" type="text/css" />
<!--REQUIRED STYLE SHEETS-->
<!-- BOOTSTRAP CORE STYLE CSS -->
<link href="css/seller_home.css" rel="stylesheet" type="text/css" />
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
					<li><a href="admin">HOME</a></li>
				
					<li><a href="logout">${loggedInName}[LOG OUT]</a></li>
				</ul>
			</div>

		</div>
	</div>

	<!--END NAV SECTION -->
	<br>
	<br>
	<br>
	<br>
	<br>
	
	<form action="adminUserUpdate" method="post">

		<h1>Update Your Details</h1>

		<fieldset>
			<input type="hidden" name="Id" value="${userId}" required>
			
			<label for="name">Name:</label>
			<input type="text" id="name" name="name" value="${userName}" required><br> 
				
			<label for="name">Password:</label>
			<input type="text" id="pwd" name="pwd" value="${userPwd}" required><br>
			
			<br> &nbsp<label>Male:</label>
			<input type="radio" value="male" name="gender" id="maleId" required>&nbsp&nbsp&nbsp&nbsp 
			
			<label>Female:</label>
			<input type="radio" value="female" name="gender" id="femaleId"><br>



			<br>
			<label>Age:</label> <input id="ageId" type="number" min="10"
				max="100" value="16" class="input" name="age"><br> 
				
			<label>Address</label>
			<textarea rows="4" class="input" cols="50" name="address" placeholder="Enter address here..."   required>${userAddr}</textarea>
			<br> 
	
			<label >Email Address</label> 
			<input id="pass" type="email" class="input" name="email" placeholder="example@$#..com" value="${userMail}"> 
			<label >Phone No</label> 
			<input id="pass" type="tel" pattern="\d{3}[\-]\d{3}[\-]\d{4}" placeholder="123-456-7890" class="input" name="phno" value="${userPhone}" required>



			<script type="text/javascript">
				if("${userGender}"=="male")
				{
					var radiobtn = document.getElementById("maleId");
					radiobtn.checked = true;
				}
				else
				{
					var radiobtn = document.getElementById("femaleId");
					radiobtn.checked = true;
				}
				document.getElementById("ageId").value = "${userAge}";
			</script>
			<br>
		</fieldset>
		<br>
		<br>
		<button type="submit">Update</button>

	</form>


</body>
</html>