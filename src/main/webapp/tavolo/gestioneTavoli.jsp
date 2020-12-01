<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!doctype html>
<html lang="it">
<head>
	<jsp:include page="../header.jsp" />
	<title>Gestione Tavoli</title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="../assets/css/global.css" rel="stylesheet">
    
</head>
<body>
	<jsp:include page="../navbar.jsp" />
	
	<main role="main" class="container">
		
		<p><a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath}/PrepareInsertTavoloServlet" role="button">Crea Nuovo Tavolo &raquo; </a></p>
		<p><a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath}/PrepareSearchTavoloServlet" role="button">Ricerca Tavolo &raquo;</a></p>
</main>
<jsp:include page="../footer.jsp" />
</body>
</html>