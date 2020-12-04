<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!doctype html>
<html lang="it">
<head>
	<jsp:include page="../header.jsp" />
	<title>Modifica User</title>
	
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
			class="alert alert-danger ${not empty userErrors?'':'d-none' }"
			role="alert">
			<button type="button" class="close" data-dismiss="alert" aria-label="Close">
		    	<span aria-hidden="true">&times;</span>
		    </button>
			<c:forEach var="errore" items="${userErrors }">
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

					<form method="post" action="${pageContext.request.contextPath}/user/UpdateUserServlet" novalidate="novalidate">
					
						<div class="form-row">
							<div class="form-group col-md-6">
								<label>Nome <span class="text-danger"></span></label>
								<input type="text" name="nome" id="nome" class="form-control" placeholder="Inserire il nome"
									<c:if test="${ userDaAggiornare.nome != null || userDaAggiornare.nome != '' }">
										value="${ userDaAggiornare.nome }"
									</c:if>
								>           
							</div>
							
							
							<div class="form-group col-md-6">
								<label>Cognome <span class="text-danger"></span></label>
								<input type="text" name="cognome" id="cognome" class="form-control" placeholder="Inserire il cognome"
									<c:if test="${ userDaAggiornare.cognome != null || userDaAggiornare.cognome != '' }">
										value="${ userDaAggiornare.cognome }"
									</c:if>
								>               
							</div>
							
							
							<div class="form-group col-md-6">
								<label>Username <span class="text-danger"></span></label>
								<input type="text" name="username" id="username" class="form-control" placeholder="Inserire l'username"
								<c:if test="${ userDaAggiornare.username != null || userDaAggiornare.username != '' }">
										value="${ userDaAggiornare.username }"
									</c:if>
								>                               
							</div>
							
							<c:if test="${ userDaAggiornare.stato == 'CREATO' }">
							<div class="form-group col-md-6">
									<label>Stato <span class="text-danger"></span></label>
									<select name="stato" id="stato" class="form-control">
										<c:forEach items="${ listaStati }" var="stato">
											<c:if test="${ stato == userDaAggiornare.stato }">
												<option value="${ userDaAggiornare.stato }" selected hidden><c:out value = "${ userDaAggiornare.stato }"/></option>
											</c:if>
											<c:if test="${stato != 'EMPTY' && stato != 'CREATO'}">
        										<option value="${stato}"><c:out value="${stato}"></c:out></option>
        									</c:if>
    									</c:forEach>
  									</select>
							</div>
							</c:if>
							
							
							
						<c:if test="${ userDaAggiornare.stato == 'CREATO' }">
							<div class="form-group col-md-6">


								<label>Ruolo <span class="text-danger"></span></label><br>
								<c:forEach items="${ listaRuoli }" var="ruolo">


									<input type="checkbox" id="ruolo" name="ruolo" value="${ ruolo.nome }"
										<c:forEach items="${ userDaAggiornare.ruoli }" var="ruoloUser">
													<c:if test="${ ruoloUser.id == ruolo.id }">
														checked
													</c:if>
										            
										</c:forEach>>
									<label for="ruolo">${ ruolo.nome }</label>
									<br>


								</c:forEach>

							</div>
						</c:if>
							
							
							<input type="hidden" id="idDaAggiornare" name="idDaAggiornare" value="<c:out value="${userDaAggiornare.id}"/>">
							
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