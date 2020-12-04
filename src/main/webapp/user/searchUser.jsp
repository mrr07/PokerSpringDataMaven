<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!doctype html>
<html lang="it">
<head>
	<jsp:include page="../header.jsp" />
	<title>Cerca User</title>
	
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
		        <h5>Inserire i criteri di ricerca</h5> 
		    </div>
		    <div class='card-body'>

					<form method="post" action="${pageContext.request.contextPath}/user/SearchUserServlet" novalidate="novalidate">
					
						<div class="form-row">
							<div class="form-group col-md-6">
								<label>Nome <span class="text-danger"></span></label>
								<input type="text" name="nome" id="nome" class="form-control" 
								<c:if test="${ userCercato.nome == null || userCercato.nome == '' }">
									placeholder="Inserire il nome"
								</c:if>
								<c:if test="${ userCercato.nome != null || userCercato.nome != '' }">
									value="<c:out value = "${userCercato.nome}"/>"
								</c:if>
								>           
							</div>
							
							
							<div class="form-group col-md-6">
								<label>Cognome <span class="text-danger"></span></label>
								<input type="text" name="cognome" id="cognome" class="form-control" 
								<c:if test="${ userCercato.cognome == null || userCercato.cognome == '' }">
									placeholder="Inserire il cognome"
								</c:if>
								<c:if test="${ userCercato.cognome != null || userCercato.cognome != '' }">
									value="<c:out value = "${userCercato.cognome}"/>"
								</c:if>
								>                    
							</div>
							
							
							<div class="form-group col-md-6">
								<label>Username <span class="text-danger"></span></label>
								<input type="text" name="username" id="username" class="form-control" 
								<c:if test="${ userCercato.username == null || userCercato.username == '' }">
									placeholder="Inserire l'username"
								</c:if>
								<c:if test="${ userCercato.username != null || userCercato.username != '' }">
									value="<c:out value = "${userCercato.username}"/>"
								</c:if>
								>                         
							</div>
							
							<div class="form-group col-md-6">
								<label>Credito <span class="text-danger"></span></label>
								<input type="number" name="credito" id="credito" class="form-control" 
								<c:if test="${ userCercato.credito == null || userCercato.credito == '' }">
									placeholder="Inserire il credito"
								</c:if>
								<c:if test="${ userCercato.credito != null || userCercato.credito != '' }">
									value="<c:out value = "${userCercato.credito}"/>"
								</c:if>
								>                         
							</div>
							
							<div class="form-group col-md-6">
								<label>Esperienza <span class="text-danger"></span></label>
								<input type="number" name="esperienza" id="esperienza" class="form-control" 
								<c:if test="${ userCercato.esperienza == null || userCercato.esperienza == '' }">
									placeholder="Inserire l'esperienza"
								</c:if>
								<c:if test="${ userCercato.esperienza != null || userCercato.esperienza != '' }">
									value="<c:out value = "${userCercato.esperienza}"/>"
								</c:if>
								>                         
							</div>
							
							<div class="form-group col-md-6">
								<label>Data Registrazione <span class="text-danger"></span></label>
								<input type="date" name="dataRegistrazione" id="dataRegistrazione" class="form-control"
								<c:if test="${ userCercato.dataRegistrazione != null || userCercato.dataRegistrazione != '' }">
									value="<c:out value = "${userCercato.dataRegistrazione}"/>"
								</c:if>
								>                   
							</div>
							
							
							<div class="form-group col-md-6">
									<label>Stato <span class="text-danger"></span></label>
									<select name="stato" id="stato" class="form-control">
									<c:choose>
										<c:when test="${ userCercato.stato == null || userCercato.stato == '' }">
											<option selected disabled>Seleziona lo stato</option>
										</c:when>
										<c:otherwise>
											<option selected value="${userCercato.stato}"><c:out value="${userCercato.stato}"></c:out></option>
										</c:otherwise>
									</c:choose>
									<c:forEach items="${ listaStati }" var="stato">
										<c:if test="${stato != 'EMPTY'}">
        									<option value="${stato}"><c:out value="${stato}"></c:out></option>
        								</c:if>
    								</c:forEach>
  									</select>
							</div>
							
							<div class="form-group col-md-6">


								<label>Ruolo <span class="text-danger"></span></label><br>
								<c:choose>
								<c:when test="${ userCercato.ruoli == null || empty userCercato.ruoli }">
								<c:forEach items="${ listaRuoli }" var="ruolo">

									<input type="radio" id="ruolo" name="ruolo" value="${ ruolo.nome }">
									<label for="ruolo">${ ruolo.nome }</label>
									<br>

								</c:forEach>
								</c:when>
								
								<c:otherwise >
								<c:forEach items="${ listaRuoli }" var="ruolo">

									<input type="radio" id="ruolo" name="ruolo" value="${ ruolo.nome }"
										<c:forEach items="${ userCercato.ruoli }" var="ruoloUser">
													<c:if test="${ ruoloUser == ruolo.nome }">
														checked
													</c:if>
										            
										</c:forEach>
									>
								<label for="ruolo">${ ruolo.nome }</label><br>
								</c:forEach>
								</c:otherwise>
								</c:choose>

							</div>
							
							
							
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