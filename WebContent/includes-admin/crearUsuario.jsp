
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ include file="./headerAdmin.jsp" %>
<%@ page import = "dao.PromocionesDao" %>
<%@ page import = "model.Promocion" %>
<%@ page import = "model.Atraccion" %>
<%@ page import = "model.TipoAtraccion" %>
<%@ page import = "dao.UsuarioDao" %>
<%
Atraccion atraccion = (Atraccion) misession.getAttribute("atraccion");
tipoDeAtracciones.cargarTiposAtracciones();


atracciones.cargarAtracciones();
%>
	<section class="container mt-5 pt-5">
		<h1>Crear usuario</h1>
		
		<form action="../crearUsuario" method="post">
		  <div class="form-group">
		    <label for="exampleInputEmail1">Nombre</label>
		    <input  name="nombre" type="text" class="form-control" id="exampleInputEmail1" required>
		  </div>
		   <div class="form-group">
		    <label for="exampleInputPassword1">Contraseña</label>
		    <input  name="password" type="text" class="form-control" value="media" required>
		  </div>
		  <div class="form-group">
		    <label for="exampleInputPassword1">Presupuesto</label>
		    <input  name="presupuesto" type="text" class="form-control" required>
		  </div>
		  <div class="form-group">
		    <label for="exampleInputPassword1">Tiempo disponible</label>
		    <input name="tiempo" type="text" class="form-control" id="exampleInputPassword1" required>
		  </div>
		  <div class="form-group">
		   <label for="exampleInputPassword">Tipos de atracción favorita </label required>
			 <select name="tipoAtraccionId" id="cars">
			  <%for(TipoAtraccion tipoAtraccion : tipoDeAtracciones.getTiposAtraccionesList()){%>
			  		<option  value=<%out.println(tipoAtraccion.getId());%>><% out.println(tipoAtraccion.getNombreTipoAtraccion());%></option>
			 <% } %>
			</select>
		</div>
		 <div class="form-group">
		  <label for="exampleInputPassword1">Administrador:</label>
			  <select name="isActivo" id="cars">
				 <option value=false>No</option>
				<option value=true>Si</option>
			</select>
		</div>
		
		  <button type="submit" class="btn btn-primary">Crear</button>
		</form>
	</section>	
 <%@ include file="./footerAdmin.jsp" %>	