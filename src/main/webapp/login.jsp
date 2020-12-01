<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!doctype html>
<html lang="it">
	<head>
	  <!-- Required meta tags -->
	  <meta charset="utf-8">
	  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	  <!-- Bootstrap CSS -->
	  <link rel="stylesheet" href="./assets/css/bootstrap.min.css" >
	
	  <title>Accedi</title>
	  
	  <!-- Favicons -->
	<link rel="apple-touch-icon" href="./assets/img/favicons/apple-touch-icon.png" sizes="180x180">
	<link rel="icon" href="./assets/img/favicons/favicon-32x32.png" sizes="32x32" type="image/png">
	<link rel="icon" href="./assets/img/favicons/favicon-16x16.png" sizes="16x16" type="image/png">
	<link rel="manifest" href="./assets/img/favicons/manifest.json">
	<link rel="mask-icon" href="./assets/img/favicons/safari-pinned-tab.svg" color="#563d7c">
	<link rel="icon" href="./assets/img/favicons/favicon.ico">
	<meta name="msapplication-config" content="./assets/img/favicons/browserconfig.xml">
	<meta name="theme-color" content="#563d7c">
	
	
	   <style>
	    .bd-placeholder-img {
	      font-size: 1.125rem;
	      text-anchor: middle;
	      -webkit-user-select: none;
	      -moz-user-select: none;
	      -ms-user-select: none;
	      user-select: none;
	    }
	
	    @media (min-width: 768px) {
	      .bd-placeholder-img-lg {
	        font-size: 3.5rem;
	      }
	    }
	  </style>
	  
	  <!-- Custom styles for this template -->
	  <link href="./assets/css/signin.css" rel="stylesheet">
	</head>
	<body class="text-center">
		
	   	<form class="form-signin" action="LoginServlet" method="post">
	   	
		<%-- alert con lista errori --%>
		<div
			class="alert alert-danger ${not empty userErrors?'':'d-none' }"
			role="alert">
			<c:forEach var="errore" items="${userErrors }">
				<ul>
					<li>${errore }</li>
				</ul>
			</c:forEach>
		</div>


		<img class="mb-4" src="./assets/brand/bootstrap-solid.svg" alt="" width="72" height="72">
		  <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
		  <label for="inputUsername" class="sr-only">Username</label>
		  <input type="text" id="inputUsername" name="username" class="form-control" placeholder="Username" required autofocus>
		  <label for="inputPassword" class="sr-only">Password</label>
		  <input type="password" id="inputPassword" name="password" class="form-control" placeholder="Password" required>
		  <button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>
		  <a class="btn btn-lg btn-primary btn-block" href="PrepareInsertUserServlet" role="button">Sign In</a>
		  <p class="mt-5 mb-3 text-muted">&copy; 2017-2020</p>
		</form>
		
			
	</body>
</html>