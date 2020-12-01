<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!doctype html>
<html lang="it">
<head>
	<jsp:include page="../header.jsp" />
	<title>Conferma Eliminazione</title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="./assets/css/global.css" rel="stylesheet">
    
</head>
<body>
	<jsp:include page="../navbar.jsp" />
	
	<main role="main" class="container">
	
		<div class="alert alert-danger alert-dismissible fade show d-none" role="alert">
			 	Operazione fallita!
			  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
			    <span aria-hidden="true">&times;</span>
			  </button>
		</div>
		
		<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}" role="alert">
		  ${errorMessage}
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		    <span aria-hidden="true">&times;</span>
		  </button>
		</div>
		
		<c:set var = "idDaEliminare" value = "${idDaEliminare}"/>
		<p><a class="btn btn-primary btn-lg" href="DeleteUserServlet?idDaEliminare=<c:out value = "${idDaEliminare}"/>" role="button">Conferma &raquo; </a></p>
		<p><a class="btn btn-primary btn-lg" href="ListAllUsersServlet?" role="button">Annulla &raquo;</a></p>
</main>
<jsp:include page="../footer.jsp" />
</body>
</html>