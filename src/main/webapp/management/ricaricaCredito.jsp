<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!doctype html>
<html lang="it">
<head>
	<jsp:include page="../header.jsp" />
	<title>Ricarica Credito</title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="${pageContext.request.contextPath}/assets/css/global.css" rel="stylesheet">
  
    
</head>
<body>
	<jsp:include page="../navbar.jsp" />
	
	<main role="main" class="container">
	
	<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}" role="alert">
		  ${errorMessage}
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		    <span aria-hidden="true">&times;</span>
		  </button>
		</div>
	
		
		<div class='card'>
		    <div class='card-header'>
		        <h5>Ricarica</h5> 
		    </div>
		    <div class='card-body'>

					<h6 class="card-title">I campi con <span class="text-danger">*</span> sono obbligatori</h6>

					
					
					
					<form name='form' method="post" action="${pageContext.request.contextPath}/RicaricaCreditoServlet" >
					
						<div class="form-row">
							<div class="form-group col-md-6">
								<label>Credito <span class="text-danger">*</span></label>
								<input type="number" name="credito" id="credito" class="form-control" placeholder="Inserire il credito" required>
							</div>
						</div>
							
						
							
						<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Ricarica</button>
					

					</form>
					
					
					
		    
		    
			<!-- end card-body -->			   
		    </div>
		</div>	
	
	
	<!-- end container -->	
	</main>
	<jsp:include page="../footer.jsp" />
	
</body>
</html>