<%@page import="java.util.List"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!doctype html>
<html lang="it">
<head>
	<jsp:include page="../header.jsp" />
	<title>Gestione Tavoli</title>
	
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
		<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}" role="alert">
		  ${errorMessage}
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
								<th>Denominazione</th>
								<th>Cifra Minima</th>
								<th>Esperienza Minima</th>
								<th>Data Creazione</th>
								<th>Azioni</th>
							</tr>
		                </thead>
		                <tbody>
		                	
		                	<c:forEach items="${listaTavoliUser}" var="tavolo" >
         						
         					<tr>
		                        <td>${ tavolo.id }</td>
		                        <td>${ tavolo.denominazione }</td>
		                        <td>${ tavolo.cifraMinima }</td>
		                        <td>${ tavolo.esperienzaMinima }</td>
		                        <td>${ tavolo.dataCreazione }</td>
		                        <td>
										<a class="btn  btn-sm btn-outline-secondary" href="${pageContext.request.contextPath}/tavolo/ShowTavoloServlet?idDaVisualizzare=<c:out value = "${tavolo.id}"/>">Visualizza</a>
										<a class="btn  btn-sm btn-outline-primary ml-2 mr-2" href="${pageContext.request.contextPath}/tavolo/PrepareUpdateTavoloServlet?idDaAggiornare=<c:out value = "${tavolo.id}"/>">Edit</a>
										<a class="btn btn-outline-danger btn-sm" href="${pageContext.request.contextPath}/tavolo/PrepareDeleteTavoloServlet?idDaEliminare=<c:out value = "${tavolo.id}"/>">Delete</a>	
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