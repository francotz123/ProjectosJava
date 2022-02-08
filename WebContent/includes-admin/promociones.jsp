<%@ include file="./headerAdmin.jsp" %>
<%@ page import = "dao.PromocionesDao" %>
<%@ page import = "model.Promocion" %>
<%@ page import = "model.Atraccion" %>
<%@ page import = "dao.UsuarioDao" %>
<%
UsuarioDao actualizar = new UsuarioDao();
user = actualizar.actualizarUsario(user);
promociones.getPromociones(user);
%>
	<section class="container mt-5 pt-5">
		<h1>Editar promociones</h1>
		
		<h3>Lista de promociones</h3>
	 	<table class="table">
		  <thead>
		    <tr>
		      <th scope="col">Nombre</th>
		      <th scope="col">Costo</th>
		      <th scope="col">Tiempo</th>
		      <th scope="col">Cupo</th>
		      <th scope="col">Atracciones</th>
		      <th scope="col">Editar</th>
		    </tr>
		  </thead>
		  <tbody>
		
		  <% for(Promocion promocion : promociones.getPromocionList()){ %>
		    <tr>
				   <td><% out.println(promocion.getNombre()); %></td>
				   <td><% out.println(promocion.getTotalPagar() ); %></td>
				   <td><% out.println(promocion.getTiempoTotal() ); %></td>
				   <td><% out.println(promocion.cupoDisponible() ); %></td>
				   <td><% for(Atraccion atraccion : promocion.getAtracciones()){
					   out.println(atraccion.getNombre()+", ");
				   } %></td>
				   	<td><a class="btn btn-primary" href="../detallePromocion?id=<% out.println(promocion.getId());%>&opcion=0">Editar</a></td>
			   </tr> 
		<% } %>
		</tbody>
		</table>    
		
		
	</section>	
 <%@ include file="./footerAdmin.jsp" %>	