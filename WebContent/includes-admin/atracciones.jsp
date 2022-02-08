
<%@ include file="./headerAdmin.jsp" %>
<%@ page import = "dao.AtraccionDao" %>
<%@ page import = "model.Atraccion" %>
<%@ page import = "java.util.ArrayList" %>

<%
atracciones.cargarAtracciones();
%>
	<section class="container sm-4 mt-3 pt-5">
		<h1>Modificación y creación de atracciones</h1><br>
		<a class="btn btn-primary" href="./crearAtraccion.jsp">Crear atracción</a><br>
		<h3>Listado de atracciones</h3>
	 	<table class="table">
		  <thead>
		    <tr>
		      <th scope="col">Nombre</th>
		      <th scope="col">Costo</th>
		      <th scope="col">Tiempo</th>
		      <th scope="col">Cupo</th>
		      <th scope="col">Tipo de atraccion</th>
		      <th scope="col">Comprar</th>
		    </tr>
		  </thead>
		  <tbody>
		
		  <% for(Atraccion atraccion : atracciones.getAtraccionList()){ %>
		   <tr>
			   <td><% out.println(atraccion.getNombre()); %></td>
			   <td><% out.println(atraccion.getCostoVisita() ); %></td>
			   <td><% out.println(atraccion.getPromedioTiempo() ); %></td>
			   <td><% out.println(atraccion.getCupoVisitantes() ); %></td>
			   <td><% out.println(atraccion.getTipoAtraccion() ); %></td>
			<td><a class="btn btn-primary" href="../detalleAtraccion?id=<% out.println(atraccion.getId()); %>&opcion=0">Editar</a></td>
		   </tr> 
			<% } %> 
		</tbody>
		</table> 
		
		<br>
		
	</section>	
	
<%-- <% atracciones.borrarListaAtracciones(); %> --%>
 <%@ include file="./footerAdmin.jsp" %>	