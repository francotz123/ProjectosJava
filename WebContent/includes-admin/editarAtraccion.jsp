
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
		<h1>Editar atracción: <%out.println(atraccion.getNombre()); %></h1>
		
		<form action="../modificarAtraccion" method="post">
		  <div class="form-group">
		    <label for="exampleInputEmail1">Nombre</label>
		    <input  name="nombre" type="text" class="form-control" id="exampleInputEmail1" value="<%out.println(atraccion.getNombre());%>" >
		  </div>
		  <div class="form-group">
		    <label for="exampleInputPassword1">Costo</label>
		    <input  name="costo" type="text" class="form-control" value="<%out.println(atraccion.getCostoVisita());%>">
		  </div>
		  <div class="form-group">
		    <label for="exampleInputPassword1">Tiempo</label>
		    <input name="tiempo" type="text" class="form-control" id="exampleInputPassword1" value="<% out.println(atraccion.getPromedioTiempo()); %>">
		  </div>
		  <div class="form-group">
		    <label for="exampleInputPassword1">Cupo</label>
		    <input  name="cupo" type="text" class="form-control" id="exampleInputPassword1" value="<% out.println(atraccion.getCupoVisitantes() );%>">
		  </div>
		  <div class="form-group">
		   <label for="exampleInputPassword">Tipos de atracciones </label>
			 <select name="tipoAtraccionId" id="cars">
			  <%for(TipoAtraccion tipoAtraccion : tipoDeAtracciones.getTiposAtraccionesList()){
			  		if(tipoAtraccion.getNombreTipoAtraccion().equals(atraccion.getTipoAtraccion())){%>
			  		<option selected="true" value=<%out.println(tipoAtraccion.getId());%>><% out.println(tipoAtraccion.getNombreTipoAtraccion());%></option>
			  		<%}else{ %>
			  		<option value=<%out.println(tipoAtraccion.getId());%>><% out.println(tipoAtraccion.getNombreTipoAtraccion());%></option>
			  		<%} %>
			 <% } %>
			</select>
		</div>
		 <div class="form-group">
		  <label for="exampleInputPassword1">Activo:</label>
			  <select name="isActivo" id="cars">
			  <%if(atraccion.isActivo()){ %>
				  <option value=true>Activo</option>
					<option value=false>Inactivo</option>
				<%}else { %>
				<option value=false>Inactivo</option>
				<option value=true>Activo</option>
					<%} %>
			</select>
		</div>

		<div class="d-none">
		    <input  name="idAtraccion" value="<% out.println(atraccion.getId());%>">
		 </div>
		  <button type="submit" class="btn btn-primary">Modificar</button>
		</form>
	</section>	
 <%@ include file="./footerAdmin.jsp" %>	