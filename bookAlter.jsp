<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>EBS SELLER</title>
<link href="css/book_alter.css" rel="stylesheet" type="text/css" />


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
	<div class="page">
		<h1 align="center">Book Details</h1>
		
		
		<table class="layout display responsive-table">
			<thead>
				<tr>
					<th>ID</th>
					<th>Name</th>
					<th>Description</th>
					<th>Author</th>
					<th>Status</th>
					<th>Category</th>
					<th>Alter</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="bkRecords" items="${bookRecords}">
					
					<tr>
					
						<c:set var="numberOfRows" value="0" />
						<c:set var="bookID" value=" " />
						<c:forEach var="eachCol" items="${bkRecords}">

							<c:choose>
								<c:when test="${numberOfRows=='0'}">
									<td class="organisationnumber">${eachCol}</td>
									<c:set var="numberOfRows" value="${numberOfRows+1}" />
									<c:set var="bookID" value="${eachCol}" />									
								</c:when>
								<c:otherwise>
								<td class="organisationnumber">${eachCol}</td>
								</c:otherwise>
																

							</c:choose>

						</c:forEach>
						<td>
							<a href="sellerBookUpdate?bookId=${bookID}">Update</a>
							
							<a href="sellerBookDelete?bookId=${bookID}">Remove</a>
						</td>
						
					</tr>
					
				</c:forEach>


			</tbody>
		</table>
		
	</div>

</body>
</html>