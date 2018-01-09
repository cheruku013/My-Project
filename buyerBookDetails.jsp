<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>EBS BUYER</title>

<link href="css/book_details.css" rel="stylesheet" />

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
					<li><a href="buybooks">HOME</a></li>
					
					<li><a href="logout">${name}[LOG OUT]</a></li>
					<li><a href="buyerOrderDetails"><img src="img/cart.png"
					width="40" height="40"></a></li>
					<li><a href="#"><img src="img/EditProfile.png"
					width="40" height="40"></a></li>
				</ul>
			</div>

		</div>
	</div>

	<!--END NAV SECTION -->
	<br>
	<br>
	<br>
	<br>
	<form method="post" action="buyerPayments">
	
	<img class = "bookimg" height=850px width=550px src="${bkImg}" />
	
	<h1>Name: </h1><p>${bkName}</p>
	<h1>Description: </h1><p>${bkDesc}</p>
	<h1>Author: </h1><p>${bkAuthor}</p>
	<h1>Price: </h1><p>50 USD</p>
	<h1>Status: </h1><p>${bkStat}</p>
	<h1>Genere: </h1><p>${bkGenere}</p>
	<button type="submit" value="submit" class= "button3">ORDER</button>
	<input type = "hidden" name = "bookId" value ="${bkId}">
	</form>
	
</body>
</html>