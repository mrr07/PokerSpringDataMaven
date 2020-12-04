<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!doctype html>
<html lang="it">
<head>
	<jsp:include page="../header.jsp" />
	<title>Play</title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="${pageContext.request.contextPath}/assets/css/global.css" rel="stylesheet">
    
    
    
    
</head>
<body>
	<jsp:include page="../navbar.jsp" />
	
	<main role="main" class="container">
	
	<div class="alert alert-success alert-dismissible fade show ${successMessage==null?'d-none': ''}" role="alert">
		  ${successMessage}
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		    <span aria-hidden="true">&times;</span>
		  </button>
	</div>
		<h3>Stai giocando ... </h3>
		<p><a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath}/PlayServlet" role="button">Gioca &raquo; </a></p>
		<p><a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath}/QuitServlet" role="button">Lascia Tavolo &raquo; </a></p>
		
		
</main>
<jsp:include page="../footer.jsp" />
</body>
</html>