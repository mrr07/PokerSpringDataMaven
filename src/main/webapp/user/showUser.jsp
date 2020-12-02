<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!doctype html>
<html lang="it">
<head>
	<jsp:include page="../header.jsp" />
	<title>Visualizza User</title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="./assets/css/global.css" rel="stylesheet">
    
</head>
<body>
	<jsp:include page="../navbar.jsp" />
	
	<main role="main" class="container">
		
		<div class='card'>
		    <div class='card-header'>
		        Visualizza User
		    </div>
		    
		
		    <div class='card-body'>
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">ID:</dt>
				  <dd class="col-sm-9"><c:out value = "${userDaVisualizzare.id}"/></dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">nome:</dt>
				  <dd class="col-sm-9"><c:out value = "${userDaVisualizzare.nome}"/></dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">cognome:</dt>
				  <dd class="col-sm-9"><c:out value = "${userDaVisualizzare.cognome}"/></dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">username:</dt>
				  <dd class="col-sm-9"><c:out value = "${userDaVisualizzare.username}"/></dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">data registrazione:</dt>
				  <dd class="col-sm-9"><c:out value = "${userDaVisualizzare.dataRegistrazione}"/></dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">stato:</dt>
				  <dd class="col-sm-9"><c:out value = "${userDaVisualizzare.stato}"/></dd>
		    	</dl>
		    	
		    	 
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">ruolo/i:</dt>
				  <c:forEach items="${ userDaVisualizzare.ruoli }" var="ruolo">
				  	<dd class="col-sm-9"><c:out value = "${ruolo.nome}"/></dd>
				  </c:forEach>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">esperienza:</dt>
				  <dd class="col-sm-9"><c:out value = "${userDaVisualizzare.esperienza}"/></dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">credito:</dt>
				  <dd class="col-sm-9"><c:out value = "${userDaVisualizzare.credito}"/></dd>
		    	</dl>
		    	
		    	
		    </div>
		    
		    <div class='card-footer'>
		        <a href="ListAllUsersServlet" class='btn btn-outline-secondary' style='width:80px'>
		            <i class='fa fa-chevron-left'></i> Back
		        </a>
		    </div>
		</div>	
	
	
	
	<!-- end main container -->	
	</main>
	<jsp:include page="../footer.jsp" />
	
</body>
</html>