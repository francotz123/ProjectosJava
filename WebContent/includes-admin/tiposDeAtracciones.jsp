<%@ include file="./headerAdmin.jsp" %>
<%@ page import = "dao.PromocionesDao" %>
<%@ page import = "model.Promocion" %>
<%@ page import = "model.Atraccion" %>
<%@ page import = "dao.UsuarioDao" %>
<%@ page import = "model.TipoAtraccion" %>
<%
UsuarioDao actualizar = new UsuarioDao();
user = actualizar.actualizarUsario(user);
tipoDeAtracciones.cargarTiposAtracciones();
%>
	<section class="container mt-5 pt-5">
		<h1>Editar Tipos de atracciones</h1>
		<a class="btn btn-primary" href="./crearTipoAtraccion.jsp">Crear Tipo </a><br>
		<h3>Lista de Tipos de atracciones</h3>
	 	<table class="table">
		  <thead>
		    <tr>
		      <th scope="col">Nombre del tipo</th>
		      <th scope="col">Editar</th>
		    </tr>
		  </thead>
		  <tbody>
		
		  <% for(TipoAtraccion tipoAtraccion : tipoDeAtracciones.getTiposAtraccionesList()){ %>
		    <tr>
				<td><% out.println(tipoAtraccion.getNombreTipoAtraccion()); %></td>
				<td><a class="btn btn-primary" href="../editarTipoDeAtraccion?id=<% out.println(tipoAtraccion.getId()); %>">Editar</a></td>
			</tr>
		<% } %>
		</tbody>
		</table>    
		
		
	</section>	
 <%@ include file="./footerAdmin.jsp" %>	