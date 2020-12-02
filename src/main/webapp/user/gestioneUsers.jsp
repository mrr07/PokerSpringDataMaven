<%@page import="java.util.List"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!doctype html>
<html lang="it">
<head>
	<jsp:include page="../header.jsp" />
	<title>Gestione Users</title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="./assets/css/global.css" rel="stylesheet">
    
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
		<div class="alert alert-danger alert-dismissible fade show d-none" role="alert">
		  Esempio di operazione fallita!
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		    <span aria-hidden="true">&times;</span>
		  </button>
		</div>
		<div class="alert alert-info alert-dismissible fade show d-none" role="alert">
		  Aggiungere d-none nelle class per non far apparire
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		    <span aria-hidden="true">&times;</span>
		  </button>
		</div>
		
		<div class='card'>
		    <div class='card-header'>
		        <h5>Lista dei risultati</h5> 
		    </div>
		    <div class='card-body'>
		 
		        <div class='table-responsive'>
		            <table class='table table-striped ' >
		                <thead>
		                    <tr>
								<th>Id</th>
								<th>Nome</th>
								<th>Cognome</th>
								<th>Username</th>
								<th>Data Registrazione</th>
								<th>Stato</th>
								<th>Ruolo/i</th>
								<th>Esperienza</th>
								<th>Credito</th>
								<th>Azioni</th>
							</tr>
		                </thead>
		                <tbody>
		                	
		                	<c:forEach items="${listaUsers}" var="user" >
         						
         					<tr>
		                        <td>${ user.id }</td>
		                        <td>${ user.nome }</td>
		                        <td>${ user.cognome }</td>
		                        <td>${ user.username }</td>
		                        <td>${ user.dataRegistrazione }</td>
		                        <td>${ user.stato }</td>
		                        <td>
		                        	<c:forEach items="${ user.ruoli }" var="ruolo">
		                        		<c:out value="${ ruolo.nome }"></c:out>
		                        	</c:forEach>
		                        </td>
		                        <td>${ user.esperienza }</td>
		                        <td>${ user.credito }</td>
		                        <td>
										<a class="btn  btn-sm btn-outline-secondary" href="ShowUserServlet?idDaVisualizzare=<c:out value = "${user.id}"/>">Visualizza</a>
										<c:if test="${ user.stato == 'DISABILITATO' }">
											<a class="btn  btn-sm btn-outline-primary ml-2 mr-2" href="EnableUserServlet?idDaAttivare=<c:out value = "${user.id}"/>">Attiva</a>
										</c:if>
										<c:if test="${ user.stato == 'ATTIVO' }">
											<a class="btn  btn-sm btn-outline-primary ml-2 mr-2" href="PrepareDeleteUserServlet?idDaEliminare=<c:out value = "${user.id}"/>">Disattiva</a>
										</c:if>
										<a class="btn  btn-sm btn-outline-primary ml-2 mr-2" href="PrepareUpdateUserServlet?idDaAggiornare=<c:out value = "${user.id}"/>">Edit</a>	
								</td>
		                    </tr>
         						
     						 </c:forEach>
		              
		                </tbody>
		            </table>
		        </div>
		   
			<!-- end card-body -->			   
		    </div>
		</div>	
	
	
	
	
	
	
	<!-- end container -->	
	</main>
	<jsp:include page="../footer.jsp" />
	
</body>
</html>