<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>EBS ADMIN</title>
<!-- <link href='css/SignIn.css' rel='stylesheet' > -->

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


<style>
table, th, td {
	border: 1px solid black;
	border-collapse: collapse;
}

th, td {
	padding: 5px;
	text-align: left;
}

table#t01 {
	width: 100%;
	background-color: #f1f1c1;
}
</style>


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
	<br><br>
	<br>	
	<h2 align="center" style="color: red">Customer Details</h2>
	<br>
	<table style="width: 100%">
		<tr>
			<th>ID</th>
			<th>PWD</th>
			<th>Name</th>
			<th>Age</th>
			<th>Gender</th>
			<th>Address</th>
			<th>Email-ID</th>
			<th>Phone</th>
			<th>Alter</th>
			
		</tr>

		<c:forEach var="buyRecords" items="${buyerRecords}">
			<tr>
				<c:set var="numberOfRows" value="0" />
				<c:forEach var="eachCol" items="${buyRecords}">
				
					<c:choose> 
					<c:when test="${numberOfRows=='0'}">
						<td><a name="data " href="buyerDetails?data=${eachCol}"><c:out
									value="${eachCol}" /></a></td>
						<c:set var="numberOfRows" value="${numberOfRows+1}" />
						<c:set var="buyerID" value="${eachCol}" />
					</c:when>
					<c:otherwise>
					<td><c:out value="${eachCol}" /></td>
					</c:otherwise>
					</c:choose>
				</c:forEach>
				<td>
					<a href="adminUserUpdate?userID=${buyerID}">Update</a>
							
					<a href="adminBuyerDelete?buyerID=${buyerID}">Remove</a>
				</td>
			</tr>
		</c:forEach>

	</table>
	<br>
	<h2 align="center" style="color: red">Seller Details</h2>
	<br>
	<table id="t01">
		<tr>
			<th>ID</th>
			<th>PWD</th>
			<th>Name</th>
			<th>Age</th>
			<th>Gender</th>
			<th>Address</th>
			<th>Email-ID</th>
			<th>Phone</th>
			<th>Alter</th>
		</tr>
		<c:forEach var="sellRecords" items="${sellerRecords}">
			<tr>
				<c:set var="numberOfRows" value="0" />
				<c:forEach var="eachCol" items="${sellRecords}" varStatus="theCount">
				
					<c:choose> 
					<c:when test="${numberOfRows=='0'}">
						<td><a name="data " href="sellerDetails?data=${eachCol}"><c:out
									value="${eachCol}" /></a></td>
						<c:set var="numberOfRows" value="${numberOfRows+1}" />
						<c:set var="sellerID" value="${eachCol}" />
					</c:when>
					<c:otherwise>
					<td><c:out value="${eachCol}" /></td>
					</c:otherwise>
					</c:choose>

				</c:forEach>
				<td>
				
					<a href="adminUserUpdate?userID=${sellerID}">Update</a>							
					<a href="adminSellerDelete?sellerID=${sellerID}">Remove</a>
					
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>