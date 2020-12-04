<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!doctype html>
<html lang="it">
<head>
	<jsp:include page="../header.jsp" />
	<title>Cerca Partita</title>

	<!-- style per le pagine diverse dalla index -->
    <link href="${pageContext.request.contextPath}/assets/css/global.css" rel="stylesheet">

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/assets/css/jqueryUI/jquery-ui.min.css" />
<style>
.ui-autocomplete-loading {
	background: white url("assets/img/favicons/anim_16x16.gif") right center
		no-repeat;
}
</style>


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

					<form name='form' method="post" action="${pageContext.request.contextPath}/SearchPartitaServlet" >
					
						<div class="form-row">
							<div class="form-group col-md-6">
								<label>Denominazione <span class="text-danger"></span></label>
								<input type="text" name="denominazione" id="denominazione" class="form-control" placeholder="Inserire la denominazione" >
							</div>
							
							<div class="form-group col-md-6">
								<label>Cifra Minima Per Entrare <span class="text-danger"></span></label>
								<input type="number" name="cifraMinima" id="cifraMinima" class="form-control" placeholder="Inserire la cifra minima per entrare in partita" >
							</div>
							
							
							<div class="form-group col-md-6">
								<label>Data di Creazione del tavolo<span class="text-danger"></span></label>
								<input type="date" name="dataCreazione" id="dataCreazione" class="form-control">                               
							</div>

						<div class="form-group col-md-6">
							<label for="utenteInputId">Giocatore:</label>
								<input class="form-control" type="text" id="utenteInputId" name="utenteInput"> 
								<input type="hidden" name="utenteId" id="utenteId">
							
						</div>

						<div class="form-group col-md-6">
							<label for="creatoreInputId">Creatore:</label>
								<input class="form-control" type="text" id="creatoreInputId" name="creatoreInput"> 
								<input type="hidden" name="creatoreId" id="creatoreId">
						</div>

					</div>
						
							
						<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Cerca Partita</button>
						
						
						
					
					<%-- FUNZIONE JQUERY UI CON AJAX PER AUTOCOMPLETE --%>
			<script>
				$( "#utenteInputId" ).autocomplete({
					 source: function(request, response) {
					        $.ajax({
					            url: "${pageContext.request.contextPath}/SearchUserAjaxServlet?filter=uno",
					            datatype: "json",
					            data: {
					                term: request.term,   
					            },
					            success: function(data) {
					                response($.map(data, function(item) {
					                    return {
						                    label: item.label,
						                    value: item.value
					                    }
					                }))
					            },
					        })
					    },
					//quando seleziono la voce nel campo deve valorizzarsi la descrizione
				    focus: function(event, ui) {
				        $("#utenteInputId").val(ui.item.label)
				        return false;
				    },
				    minLength: 2,
				    //quando seleziono la voce nel campo hidden deve valorizzarsi l'id
				    select: function( event, ui ) {
				    	$('#utenteId').val(ui.item.value);
				    	console.log($('#utenteId').val())
				        return false;
				    }
				});
			</script>
			
			<%-- FUNZIONE JQUERY UI CON AJAX PER AUTOCOMPLETE --%>
			<script>
				$( "#creatoreInputId" ).autocomplete({
					 source: function(request, response) {
					        $.ajax({
					            url: "${pageContext.request.contextPath}/SearchUserAjaxServlet?filter=due",
					            datatype: "json",
					            data: {
					                term: request.term,   
					            },
					            success: function(data) {
					                response($.map(data, function(item) {
					                    return {
						                    label: item.label,
						                    value: item.value
					                    }
					                }))
					            },
					        })
					    },
					//quando seleziono la voce nel campo deve valorizzarsi la descrizione
				    focus: function(event, ui) {
				        $("#creatoreInputId").val(ui.item.label)
				        return false;
				    },
				    minLength: 2,
				    //quando seleziono la voce nel campo hidden deve valorizzarsi l'id
				    select: function( event, ui ) {
				    	$('#creatoreId').val(ui.item.value);
				    	console.log($('#creatoreId').val())
				        return false;
				    }
				});
			</script>	
					

					</form>
					
					
					
		    
		    
			<!-- end card-body -->			   
		    </div>
		</div>	
	
	
	<!-- end container -->	
	</main>
	<jsp:include page="../footer.jsp" />
	
</body>
</html>