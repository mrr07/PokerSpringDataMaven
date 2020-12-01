<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!doctype html>
<html lang="it">
<head>
	<jsp:include page="../header.jsp" />
	<title>Crea Nuovo Tavolo</title>
	
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
		        <h5>Inserisci i criteri di ricerca</h5> 
		    </div>
		    <div class='card-body'>

					<h6 class="card-title">I campi con <span class="text-danger">*</span> sono obbligatori</h6>

					
					
					
					<form name='form' method="post" action="SearchTavoloServlet" >
					
						<div class="form-row">
							<div class="form-group col-md-6">
								<label>Denominazione <span class="text-danger">*</span></label>
								<input type="text" name="denominazione" id="denominazione" class="form-control" placeholder="Inserire la denominazione" >
							</div>
							
							<div class="form-group col-md-6">
								<label>Cifra Minima Per Entrare <span class="text-danger">*</span></label>
								<input type="number" name="cifraMinima" id="cifraMinima" class="form-control" placeholder="Inserire la cifra minima per entrare in partita" >
							</div>
							
							
							<div class="form-group col-md-6">
								<label>Data di Creazione del tavolo<span class="text-danger"></span></label>
								<input type="date" name="dataCreazione" id="dataRCreazione" class="form-control">                               
							</div>
							
						</div>
						
							
						<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Registrati</button>
					

					</form>
					
					
					
		    
		    
			<!-- end card-body -->			   
		    </div>
		</div>	
	
	
	<!-- end container -->	
	</main>
	<jsp:include page="../footer.jsp" />
	
</body>
</html>