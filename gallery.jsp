<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>EBS</title>
<link href="css/text_align.css" rel="stylesheet" type="text/css" />
<!--REQUIRED STYLE SHEETS-->
<!-- BOOTSTRAP CORE STYLE CSS -->
<link href="css/bootstrap.css" rel="stylesheet" />
<!-- FONTAWESOME STYLE CSS -->
<link href="css/font-awesome.min.css" rel="stylesheet" />
<!--ANIMATED FONTAWESOME STYLE CSS -->
<link href="css/font-awesome-animation.css" rel="stylesheet" />
<!--PRETTYPHOTO MAIN STYLE -->
<link href="css/prettyPhoto.css" rel="stylesheet" />
<!--        CUSTOM STYLE CSS -->
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
					<li><a href="welcome">HOME</a></li>
					<li><a href="signIn">LOGIN</a></li>
					<li><a href="register">SIGNUP</a></li>
					<li><a href="gallery">GALLERY</a></li>
				
				</ul>
			</div>

		</div>
	</div>

	<!--END NAV SECTION -->
	<br>
	<br>
	<br>
	<br>
	
	<table cellspacing="500px">
		<c:set var="numberOfRows" value="0" />
		<c:forEach var="bkRecords" items="${bookRecords}">

			<c:set var="numberOfCols" value="0" />

			<c:forEach var="eachCol" items="${bkRecords}">

				<c:choose>

					<c:when test="${numberOfCols=='0'}">
						<c:set var="numberOfCols" value="${numberOfCols+1}" />
						<c:set var="bkId" value="${eachCol}" />
					</c:when>

					<c:when test="${numberOfCols=='1'}">
						<c:set var="numberOfCols" value="${numberOfCols+1}" />
						<c:set var="bkName" value="${eachCol}" />
					</c:when>

					<c:otherwise>
						<c:set var="numberOfCols" value="0" />
						<c:set var="bkImg" value="${eachCol}" />
					</c:otherwise>

				</c:choose>

			</c:forEach>

			<c:choose>
				<c:when test="${numberOfRows=='0'}">
					<tr>
						<td>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</td>
						<td>
							<div class="image">

								<img height=200px
									width=200px src="${bkImg}" />
								<p align="center">
										<b>${bkName}</b>
									</p></a>

							</div>
						</td>
						<td>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</td>
						<c:set var="numberOfRows" value="${numberOfRows+1}" />
				</c:when>
				<c:when test="${numberOfRows<'4'}">
					<td>
						<div class="image">
							<img height=200px
								width=200px src="${bkImg}" />
								<p align="center">
									<b>${bkName}</b>
								</p></a>
						</div>
					</td>
					<td>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</td>

					<c:set var="numberOfRows" value="${numberOfRows+1}" />
				</c:when>
				<c:otherwise>
					<c:set var="numberOfRows" value="0" />
					<td>
						<div class="image">
							<img height=200px
								width=200px src="${bkImg}" />
							<p align="center">
									<b>${bkName}</b>
								</p></a>
						</div>
					</td>
					<td>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</td>
					</tr>

				</c:otherwise>

			</c:choose>

		</c:forEach>

	</table>
	

</body>
</html>