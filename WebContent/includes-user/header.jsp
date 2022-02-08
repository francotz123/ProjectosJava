<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "model.Usuario" %>
<%@ page import = "model.Atraccion" %>
<%@ page import = "dao.AtraccionDao" %>
<%@ page import = "dao.PromocionesDao" %>
<%
HttpSession misession = request.getSession(false);
Usuario user = (Usuario) misession.getAttribute("usuario");
AtraccionDao atracciones = (AtraccionDao) misession.getAttribute("atracciones");
PromocionesDao promociones = (PromocionesDao) misession.getAttribute("promociones");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<link rel="stylesheet" href="assets/css/styles.css">
<title>Inicio | Tierra Media</title>
</head>
<body>

		<nav class="navbar navbar-dark bg-dark navbar-toggleable-md">
			<button class="navbar-toggler navbar-toggleable-md" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo01" aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation">
			    <span class="navbar-toggler-icon"></span>
			  </button>
					  <a class="navbar-brand"><%out.println(user.getPresupuesto()); %> <%out.println(user.getTimpoDisponible());%> </a> 
		  <a class="navbar-brand" href="./inicio.jsp">
		    <img src="../assets/img/mapa.jpg" width="30" height="30" class="d-inline-block align-top" alt="">
		    Tierra Media
		  </a>
		 
		  <div class="collapse navbar-collapse" id="navbarTogglerDemo01">
		  <div class="navbar-nav">
		  	<a class="nav-item nav-link active" href="./inicio.jsp">Inicio </a>
		  	<a class="nav-item nav-link" href="./promociones.jsp">Promociones </a>		  	
		  	<a class="nav-item nav-link" href="./atracciones.jsp">Atracciones </a>
		  	<a class="nav-item nav-link" href="./itinerario.jsp">Itinerario </a>
		      <li class="nav-item dropdown">
		        <a class="nav-link dropdown-toggle " href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		          <% out.println(user.getNombre()); %>
		        </a>
		        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
		          <a class="dropdown-item" href="#">Mis datos</a>
		          <div class="dropdown-divider"></div>
		          <a class="dropdown-item" href="../logout">Salir</a>
		        </div>
		      </li>		
		       
		  </div>
		  </div>
   	
		</nav>
