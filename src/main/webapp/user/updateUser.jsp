<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!doctype html>
<html lang="it">
<head>
	<jsp:include page="../header.jsp" />
	<title>Modifica User</title>
	
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

					<form method="post" action="UpdateUserServlet" novalidate="novalidate">
					
						<div class="form-row">
							<div class="form-group col-md-6">
								<label>Nome <span class="text-danger"></span></label>
								<input type="text" name="nome" id="nome" class="form-control" placeholder="Inserire il nome"
									<c:if test="${ user.nome != null || user.nome != '' }">
										value="${ user.nome }"
									</c:if>
								>           
							</div>
							
							
							<div class="form-group col-md-6">
								<label>Cognome <span class="text-danger"></span></label>
								<input type="text" name="cognome" id="cognome" class="form-control" placeholder="Inserire il cognome"
									<c:if test="${ user.cognome != null || user.cognome != '' }">
										value="${ user.cognome }"
									</c:if>
								>               
							</div>
							
							
							<div class="form-group col-md-6">
								<label>Username <span class="text-danger"></span></label>
								<input type="text" name="username" id="username" class="form-control" placeholder="Inserire l'username"
								<c:if test="${ user.username != null || user.username != '' }">
										value="${ user.username }"
									</c:if>
								>                               
							</div>
							
							<div class="form-group col-md-6">
								<label>Password <span class="text-danger"></span></label>
								<input type="password" name="password" id="password" class="form-control" placeholder="Inserire la password"
								<c:if test="${ user.password != null || user.password != '' }">
										value="${ user.password }"
									</c:if>
								>                               
							</div>
							
							<div class="form-group col-md-6">
									<label>Stato <span class="text-danger"></span></label>
									<select name="stato" id="stato" class="form-control">
										<c:forEach items="${ listaStati }" var="stato">
											<c:if test="${ stato == user.stato }">
												<option value="${ user.stato }" selected hidden><c:out value = "${ user.stato }"/></option>
											</c:if>
											<c:if test="${stato != 'EMPTY'}">
        										<option value="${stato}"><c:out value="${stato}"></c:out></option>
        									</c:if>
    									</c:forEach>
  									</select>
							</div>
							
							<div class="form-group col-md-6">
								<label>Esperienza<span class="text-danger"></span></label>
								<input type="text" name="esperienza" id="esperienza" class="form-control" placeholder="Inserire il grado di esperienza"
								<c:if test="${ user.esperienza != null || user.esperienza != '' }">
										value="${ user.esperienza}"
									</c:if>
								>                               
							</div>
							
							<div class="form-group col-md-6">
								<label>Credito<span class="text-danger"></span></label>
								<input type="text" name="credito" id="credito" class="form-control" placeholder="Inserire il credito"
								<c:if test="${ user.credito != null || user.credito != '' }">
										value="${ user.credito }"
									</c:if>
								>                               
							</div>
							
							<div class="form-group col-md-6">
								<label>Data di Registrazione<span class="text-danger"></span></label>
								<input type="date" name="dataRegistrazione" id="dataRegistrazione" class="form-control" placeholder="Inserire la data"
								<c:if test="${ user.dataRegistrazione != null || user.dataRegistrazione != '' }">
										value="${ user.dataRegistrazione }"
									</c:if>
								>                               
							</div>
							
							
							<div class="form-group col-md-6">
								
								
								<label>Ruolo <span class="text-danger"></span></label><br>
								<c:forEach items="${ listaRuoli }" var="ruolo">
									
										 
											<input type="checkbox" id="ruolo" name="ruolo" value="${ ruolo.nome }" 
												<c:forEach items="${ user.ruoli }" var="ruoloUser">
													<c:if test="${ ruoloUser.id == ruolo.id }">
														checked
													</c:if>
												</c:forEach>
											
											>
											<label for="ruolo">${ ruolo.nome }</label><br>
											
									
								</c:forEach>
							
							</div>
							
							<input type="hidden" id="idDaAggiornare" name="idDaAggiornare" value="<c:out value="${user.id}"/>">
							
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