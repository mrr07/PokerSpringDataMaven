<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!doctype html>
<html lang="it">
<head>
	<jsp:include page="../header.jsp" />
	<title>Modifica Tavolo</title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="${pageContext.request.contextPath}/assets/css/global.css" rel="stylesheet">
    
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
		
		<%-- alert con lista errori --%>
		<div
			class="alert alert-danger ${not empty tavoloErrors?'':'d-none' }"
			role="alert">
			<button type="button" class="close" data-dismiss="alert" aria-label="Close">
		    	<span aria-hidden="true">&times;</span>
		    </button>
			<c:forEach var="errore" items="${tavoloErrors }">
				<ul>
					<li>${errore }</li>
				</ul>
			</c:forEach>
			
		</div>
		
		<div class='card'>
		    <div class='card-header'>
		        <h5>Modifica valori</h5> 
		    </div>
		    <div class='card-body'>

					<form method="post" action="${pageContext.request.contextPath}/tavolo/UpdateTavoloServlet" novalidate="novalidate">
					
						<div class="form-row">
							<div class="form-group col-md-6">
								<label>Denominazione <span class="text-danger"></span></label>
								<input type="text" name="denominazione" id="denominazione" class="form-control" placeholder="Inserire la denominazione"
									<c:if test="${ tavoloDaAggiornare.denominazione != null || tavoloDaAggiornare.denominazione != '' }">
										value="${ tavoloDaAggiornare.denominazione }"
									</c:if>
								>           
							</div>
							
							
							<div class="form-group col-md-6">
								<label>Cifra Minima <span class="text-danger"></span></label>
								<input type="number" name="cifraMinima" id="cifraMinima" class="form-control" placeholder="Inserire la cifra minima"
									<c:if test="${ tvoloDaAggiornare.cifraMinima != null || tavoloDaAggiornare.cifraMinima != '' }">
										value="${ tavoloDaAggiornare.cifraMinima }"
									</c:if>
								>               
							</div>
							
							<div class="form-group col-md-6">
								<label>Esperienza Minima <span class="text-danger"></span></label>
								<input type="number" name="esperienzaMinima" id="esperienzaMinima" class="form-control" placeholder="Inserire l'esperienza minima"
									<c:if test="${ tvoloDaAggiornare.esperienzaMinima != null || tavoloDaAggiornare.esperienzaMinima != '' }">
										value="${ tavoloDaAggiornare.esperienzaMinima }"
									</c:if>
								>               
							</div>
							
							
							<input type="hidden" id="idDaAggiornare" name="idDaAggiornare" value="<c:out value="${tavoloDaAggiornare.id}"/>">
							
						</div>
							
						<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Modifica</button>
					

					</form>

		    
		    
			<!-- end card-body -->			   
		    </div>
		</div>	
	
	
	<!-- end container -->	
	</main>
	<jsp:include page="../footer.jsp" />
	
</body>
</html>