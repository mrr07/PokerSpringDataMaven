<!-- navbar -->

<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!doctype html>
<html lang="it">
<c:set var = "user" scope = "session" value = "${ user }"/>  <!--  -->

<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-primary">

	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
       <span class="navbar-toggler-icon"></span>
     </button>

  <div class="collapse navbar-collapse" id="navbarsExampleDefault">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
      	<c:if test="${ user != null }">
        	<a class="nav-link" href="${pageContext.request.contextPath}/home.jsp">Home <span class="sr-only">(current)</span></a>
        </c:if>
        <c:if test="${ user == null }">
        	<a class="nav-link" href="${pageContext.request.contextPath}/login.jsp">Login <span class="sr-only">(current)</span></a>
        </c:if>
        
      </li>
    </ul>
    <c:if test="${ user != null }">
    	<a class="btn btn-success" href="${pageContext.request.contextPath}/LogoutServlet">Logout</a>
    </c:if>
   
  </div>
</nav>


