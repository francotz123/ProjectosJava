<%@ include file="./header.jsp" %>
<%@ page import = "dao.UsuarioDao" %>
<%@ page import = "model.Promocion" %>
<%UsuarioDao actualizar = new UsuarioDao();
user = actualizar.actualizarUsario(user);
atracciones.getAtracciones(user); %>

	<section class="container mt-5 pt-5">
		<h1>Itinerario</h1><br>
		<h3>Atracciones compradas</h3>
	 	<table class="table">
		  <thead>
		    <tr>
		      <th scope="col">Nombre</th>
		      <th scope="col">Costo</th>
		      <th scope="col">Tiempo</th>
		      <th scope="col">Cupo</th>
		      <th scope="col">Tipo de atraccion</th>
		    </tr>
		  </thead>
		  <tbody>
		
		  <% for(Atraccion atraccion : user.getAtraccionesCompradas()){ %>
		   <tr>
			   <td><% out.println(atraccion.getNombre()); %></td>
			   <td><% out.println(atraccion.getCostoVisita() ); %></td>
			   <td><% out.println(atraccion.getPromedioTiempo() ); %></td>
			   <td><% out.println(atraccion.getCupoVisitantes() ); %></td>
			   <td><% out.println(atraccion.getTipoAtraccion() ); %></td>
		   </tr> 
		<% } %>
		</tbody>
		</table> 
		
		<h3>Promociones Compradas</h3>
	 	<table class="table">
		  <thead>
		    <tr>
		      <th scope="col">Nombre</th>
		      <th scope="col">Costo</th>
		      <th scope="col">Tiempo</th>
		      <th scope="col">Cupo</th>
		      <th scope="col">Atracciones</th>
		    </tr>
		  </thead>
		  <tbody>
		
		  <% for(Promocion promocion : user.getPromocionesCompradas()){ %>
		    <tr>
				   <td><% out.println(promocion.getNombre()); %></td>
				   <td><% out.println(promocion.getTotalPagar() ); %></td>
				   <td><% out.println(promocion.getTiempoTotal() ); %></td>
				   <td><% out.println(promocion.cupoDisponible() ); %></td>
				   <td><% for(Atraccion atraccion : promocion.getAtracciones()){
					   out.println(atraccion.getNombre()+", ");
				   } %></td>
			   </tr> 
		<% } %>
		</tbody>
		</table>    
	</section>	

	
 <%@ include file="./footer.jsp" %>	