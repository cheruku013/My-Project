<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>EBS SELLER</title>
<link href="css/book_reg.css" rel="stylesheet" type="text/css" />


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
					<li><a href="seller">HOME</a></li>
					
					<li><a href="logout">${loggedInName}[LOG OUT]</a></li>
					<li><a href="sellerProfileUpdate"><img src="img/EditProfile.png"
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
	<h3 align="center" style="color: blue">${regStat}</h3>
	
	<form action="sellerBookUpdate" method="post">

		<h1>Update Book Details</h1>
		
		

		<fieldset>
			<label for="name">Name:</label> 
			<input type="text" id="name" name="name" value ="${bookName}" required><br> 
			
			<label for="desc">Description:</label>
			<textarea name="desc">${bookDesc}</textarea><br>

			<br><label for="author">Author:</label> 
			<input type="text" name="author" value ="${bookAuthor}"><br>

			
			&nbsp<label>Old:</label><input type="radio" value="Old" name="stat" id ="oldStat" required>&nbsp&nbsp&nbsp&nbsp 
			<label>New:</label><input type="radio" value="New" name="stat" id ="newStat"><br>
			
			
			
			<br><label for="genere">Genere:</label>
			<select id="genereCmb" name="genere" >
						
				<option value="drama">Drama</option>
				<option value="action">Action</option>
				<option value="science">Science</option>
				<option value="horror">Horror</option>
				<option value="health">Health</option>
				<option value="guide"> Guide</option>
								
				<option value="travel">Travel</option>
				<option value="math">Math</option>
				<option value="art">Art</option>
				<option value="comic">Comic</option>
				
				<option value="poetry">Poetry</option>
				<option value="cook">Cook</option>
				<option value="history">History</option>
				<option value="prayer">Religious</option>
				
				<option value="fantasy">Fantasy</option>
				<option value="diaries">Diaries</option>
				<option value="biography">Biography</option>
				<option value="children">Children</option>
				
										
			</select>
			<input type="hidden" name = "bookId" value="${bookID}" />
			
			<script type="text/javascript">
				if("${bookStatus}"=="Old")
				{
				var radiobtn = document.getElementById("oldStat");
				radiobtn.checked = true;
				}
				else
				{
				var radiobtn = document.getElementById("newStat");
				radiobtn.checked = true;
				}
					
				document.getElementById("genereCmb").value = "${bookCategory}";
			</script>
		
			
			<br>
		</fieldset>
		<br><br>
		<button type="submit">Update</button>
			
	</form>
	
</body>
</html>