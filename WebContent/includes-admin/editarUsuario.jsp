
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ include file="./headerAdmin.jsp" %>
<%@ page import = "dao.PromocionesDao" %>
<%@ page import = "model.Promocion" %>
<%@ page import = "model.Atraccion" %>
<%@ page import = "model.TipoAtraccion" %>
<%@ page import = "dao.UsuarioDao" %>
<%
Usuario usuario = (Usuario) misession.getAttribute("usuarioAEditar");
tipoDeAtracciones.cargarTiposAtracciones();
atracciones.cargarAtracciones();
%>
	<section class="container mt-5 pt-5">
		<h1>Editar usuario: <%out.println(usuario.getNombre()); %></h1>
		
		<form action="../modificarUsuario" method="post">
		  <div class="form-group">
		    <label for="exampleInputEmail1">Nombre</label>
		    <input  name="nombre" type="text" class="form-control" id="exampleInputEmail1" value="<%out.println(usuario.getNombre());%>" >
		  </div>
		  <div class="form-group">
		    <label for="exampleInputPassword1">Tiempo disponible</label>
		    <input  name="tiempo" type="text" class="form-control" value="<%out.println(usuario.getTimpoDisponible());%>">
		  </div>
		  <div class="form-group">
		    <label for="exampleInputPassword1">Presupuesto</label>
		    <input  name="presupuesto" type="text" class="form-control" id="exampleInputPassword1" value="<% out.println(usuario.getPresupuesto());%>">
		  </div>
		  <div class="form-group">
		   <label for="exampleInputPassword">Tipos de atracciones </label>
			 <select name="tipoAtraccionId" id="cars">
			  <%for(TipoAtraccion tipoAtraccion : tipoDeAtracciones.getTiposAtraccionesList()){
			  		if(tipoAtraccion.getNombreTipoAtraccion().equals(usuario.getAtraccionFav())){%>
			  		<option selected="true" value=<%out.println(tipoAtraccion.getId());%>><% out.println(tipoAtraccion.getNombreTipoAtraccion());%></option>
			  		<%}else{ %>
			  		<option value=<%out.println(tipoAtraccion.getId());%>><% out.println(tipoAtraccion.getNombreTipoAtraccion());%></option>
			  		<%} %>
			 <% } %>
			</select>
		</div>
		 <div class="form-group">
		  <label for="exampleInputPassword1">Administrador:</label>
			  <select name="isAdmin" id="cars">
			  <%if(usuario.isAdmin()){ %>
				  <option value=true>Si</option>
					<option value=false>No</option>
				<%}else { %>
				<option value=false>No</option>
				<option value=true>Si</option>
					<%} %>
			</select>
		</div>

		<div class="d-none">
		    <input  name="idUser" value="<% out.println(usuario.getId());%>">
		 </div>
		  <button type="submit" class="btn btn-primary">Modificar</button>
		</form>
	</section>	
 <%@ include file="./footerAdmin.jsp" %>	