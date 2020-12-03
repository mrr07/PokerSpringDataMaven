<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!doctype html>
<html lang="it">
<head>
	<jsp:include page="../header.jsp" />
	<title>Play Management</title>
	
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
		
		<p><a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath}/management/ricaricaCredito.jsp" role="button">Ricarica Credito &raquo; </a></p>
		
		<c:choose>
		<c:when test="${ user.tavolo.id == null }">
			<p><a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath}/PrepareInsertTavoloServlet" role="button">Cerca Tavolo &raquo; </a></p>
		</c:when>
		<c:otherwise>
			<p><a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath}/management/play.jsp" role="button">Vai all'ultimo Tavolo &raquo;</a></p>
		</c:otherwise>
		</c:choose>
</main>
<jsp:include page="../footer.jsp" />
</body>
</html>