<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!doctype html>
<html lang="it">
<head>
	<jsp:include page="./header.jsp" />
	<title>Registrazione</title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="./assets/css/global.css" rel="stylesheet">
    
    <!--  
    <script type="text/javascript" language="javascript">
        function validate() {
            
            if (document.form.codice.value == null || document.form.codice.value == "" || 
				document.form.descrizione.value == null || document.form.descrizione.value == "" ||
				document.form.prezzo.value == null || document.form.prezzo.value == "" || isNaN(document.form.prezzo.value) ||
				document.form.categoria.value == null || document.form.categoria.value == "") {
                alert("inserimento non valido");
                return false;
            }
	
			return true;
            
        } 
	</script>
    -->
    
</head>
<body>
	<jsp:include page="./navbar.jsp" />
	
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
		        <h5>Inserisci i Tuoi Dati</h5> 
		    </div>
		    <div class='card-body'>

					<h6 class="card-title">I campi con <span class="text-danger">*</span> sono obbligatori</h6>

					
					
					
					<form name='form' method="post" action="ExecuteInsertUserServlet" >
					
						<div class="form-row">
							<div class="form-group col-md-6">
								<label>Nome <span class="text-danger">*</span></label>
								<input type="text" name="nome" id="nome" class="form-control" placeholder="Inserire il nome" required>
							</div>
							
							<div class="form-group col-md-6">
								<label>Cognome <span class="text-danger">*</span></label>
								<input type="text" name="cognome" id="cognome" class="form-control" placeholder="Inserire la cognome" required>
							</div>
							
							<div class="form-group col-md-6">
								<label>Username <span class="text-danger">*</span></label>
								<input type="text" name="username" id="username" class="form-control" placeholder="Inserire la username" required>
							</div>
							
							<div class="form-group col-md-6">
								<label>Password <span class="text-danger">*</span></label>
								<input type="password" name="password" id="password" class="form-control" placeholder="Inserire la password" required>
							</div>
							
						</div>
						
							
						<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Registrati</button>
					

					</form>
					
					
					<!-- 
					<script>
						// Example starter JavaScript for disabling form submissions if there are invalid fields
						(function() {
  							'use strict';
  							window.addEventListener('load', function() {
    							// Fetch all the forms we want to apply custom Bootstrap validation styles to
    							var forms = document.getElementsByClassName('needs-validation');
    							// Loop over them and prevent submission
    							var validation = Array.prototype.filter.call(forms, function(form) {
      								form.addEventListener('submit', function(event) {
       									if (form.checkValidity() === false) {
          									event.preventDefault();
          									event.stopPropagation();
        								}
        								form.classList.add('was-validated');
      								}, false);
    							});
  							}, false);
						})();
					</script>
					-->
		    
		    
			<!-- end card-body -->			   
		    </div>
		</div>	
	
	
	<!-- end container -->	
	</main>
	<jsp:include page="./footer.jsp" />
	
</body>
</html>