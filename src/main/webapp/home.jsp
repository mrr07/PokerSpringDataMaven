<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!doctype html>
<html lang="it">
  <head>
    
    <jsp:include page="./header.jsp" />
    
    <!-- Custom styles for this template -->
    <link href="./assets/css/global.css" rel="stylesheet">
    <style type="text/css">
    	body {
		  padding-top: 3.5rem;
		}	
    </style>
    
    <title>Poker</title>
  </head>
  <body>
  
  	<c:set var = "user" scope = "session" value = "${ user }"/>
  	
  	<c:forEach items="${ user.ruoli }" var="ruolo">
  		<c:if test="${ ruolo.nome == 'Amministratore' }">
  			<c:set var = "admin" scope = "session" value = "1"/>
  		</c:if>
  		<c:if test="${ ruolo.nome == 'Giocatore Speciale' }">
  			<c:set var = "special" scope = "session" value = "1"/>
  		</c:if>
  		<c:if test="${ ruolo.nome == 'Giocatore Semplice' }">
  			<c:set var = "player" scope = "session" value = "1"/>
  		</c:if>
  	</c:forEach>
  	
  	<jsp:include page="./navbar.jsp"></jsp:include>
  
	<main role="main">

	  <!-- Main jumbotron for a primary marketing message or call to action -->
	  <div class="jumbotron" >
	    <div class="container">
	      <h1 class="display-3">Benvenuto a Poker per Perdenti</h1>
	      <p>This is a template for a simple marketing or informational website. It includes a large callout called a jumbotron and three supporting pieces of content. Use it as a starting point to create something more unique.</p>
	      <p>
	      <c:if test = "${admin == '1' || special == '1' || player == '1'}">
	      		<a class="btn btn-primary btn-lg" href="management/managementHome.jsp" role="button">Play Management &raquo;</a>
	      </c:if>
	      <c:if test="${ admin == '1'  || special == '1' }">
	      	<a class="btn btn-primary btn-lg" href="tavolo/gestioneTavoli.jsp" role="button">Gestione Tavoli &raquo;</a>
	      </c:if>
	      <c:if test="${ admin == '1'}">
	      	<a class="btn btn-primary btn-lg" href="user/PrepareSearchUserServlet" role="button">Gestione Amministrazione &raquo;</a>
	      </c:if>
	      </p>
	    </div>
	  </div>
	  
	  <div class="container">
	    <!-- Example row of columns -->
	    <div class="row">
	      <div class="col-md-4">
	        <h2>Heading</h2>
	        <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
	        <p><a class="btn btn-secondary" href="#" role="button">View details &raquo;</a></p>
	      </div>
	      <div class="col-md-4">
	        <h2>Heading</h2>
	        <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
	        <p><a class="btn btn-secondary" href="#" role="button">View details &raquo;</a></p>
	      </div>
	      <div class="col-md-4">
	        <h2>Heading</h2>
	        <p>Donec sed odio dui. Cras justo odio, dapibus ac facilisis in, egestas eget quam. Vestibulum id ligula porta felis euismod semper. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus.</p>
	        <p><a class="btn btn-secondary" href="#" role="button">View details &raquo;</a></p>
	      </div>
	    </div>
	
	    <hr>
	
	  </div> <!-- /container -->
	
	</main>
	
	<jsp:include page="./footer.jsp" />
  </body>
</html>