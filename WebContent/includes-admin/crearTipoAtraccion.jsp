<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ include file="./headerAdmin.jsp" %>
<%@ page import = "dao.PromocionesDao" %>
<%@ page import = "model.Promocion" %>
<%@ page import = "model.Atraccion" %>
<%@ page import = "model.TipoAtraccion" %>
<%@ page import = "dao.UsuarioDao" %>
<%
TipoAtraccion tipoAtraccion = (TipoAtraccion) misession.getAttribute("tipoAtraccion");
tipoDeAtracciones.cargarTiposAtracciones();
atracciones.cargarAtracciones();
%>
	<section class="container mt-5 pt-5">
		<h1>Crear Tipo de atracción</h1>
		
		<form action="../crearTipoAtraccion" method="post">
		  <div class="form-group">
		    <label for="exampleInputEmail1">Nombre</label>
		    <input  name="nombre" type="text" class="form-control" id="exampleInputEmail1"  required>
		  </div>
		  <button type="submit" class="btn btn-primary">Crear</button>
		</form>
	</section>	
 <%@ include file="./footerAdmin.jsp" %>	