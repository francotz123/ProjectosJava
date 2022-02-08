
<%@ include file="./header.jsp" %>
<%@ page import = "dao.AtraccionDao" %>
<%@ page import = "dao.UsuarioDao" %>
<%@ page import = "model.Atraccion" %>
<%@ page import = "java.util.ArrayList" %>

<%
UsuarioDao actualizar = new UsuarioDao();
user = actualizar.actualizarUsario(user);
atracciones.getAtracciones(user);
atracciones.cargarAtracciones();
%>
	<section class="container sm-4 mt-3 pt-5">
		<h1>Atracciones</h1><br>
		
		<h3>Atracciones con preferencias</h3>
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
		
		  <% for(Atraccion atraccion : atracciones.getAtraccionesConPreferencias()){ 
		  if(atraccion.isActivo()){%>
		   <tr>
			   <td><% out.println(atraccion.getNombre()); %></td>
			   <td><% out.println(atraccion.getCostoVisita() ); %></td>
			   <td><% out.println(atraccion.getPromedioTiempo() ); %></td>
			   <td><% out.println(atraccion.getCupoVisitantes() ); %></td>
			   <td><% out.println(atraccion.getTipoAtraccion() ); %></td>
			  <%if(user.puedeComprarAtraccion(atraccion) && !user.isContieneAtraccion(atraccion) && !user.isComproAtraccionEnPromo(atraccion)){%>
					<td><a class="btn btn-primary" href="../detalleAtraccion?id=<% out.println(atraccion.getId()); %>&opcion=1">Ver</a></td>
				<% }else { %>
				  	<td><a class="btn btn-secondary" href="../detalleAtraccion?id=<% out.println(atraccion.getId()); %>&opcion=1">Ver</a></td>
				<%} %>
				
		   </tr> 
		<% }
		  	} %>
		</tbody>
		</table> 
		   
		<br>
		<h3>Atracciones sin preferencias</h3>
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
		
		  <% for(Atraccion atraccion : atracciones.getAtraccionesSinPrefenecias()){ %>
		   <tr>
			   <td><% out.println(atraccion.getNombre()); %></td>
			   <td><% out.println(atraccion.getCostoVisita() ); %></td>
			   <td><% out.println(atraccion.getPromedioTiempo() ); %></td>
			   <td><% out.println(atraccion.getCupoVisitantes() ); %></td>
			   <td><% out.println(atraccion.getTipoAtraccion() ); %></td>
			   <%if(user.puedeComprarAtraccion(atraccion) && !user.isContieneAtraccion(atraccion)) {%>
					<td><a class="btn btn-primary" href="../detalleAtraccion?id=<% out.println(atraccion.getId()); %>&opcion=1">Ver</a></td>
				<% }else { %>
				  	<td><a class="btn btn-secondary" href="../detalleAtraccion?id=<% out.println(atraccion.getId()); %>&opcion=1">Ver</a></td>
				<%} %>
		   </tr> 
		<% } %>
		</tbody>
		</table>    
	</section>	
	
<%-- <% atracciones.borrarListaAtracciones(); %> --%>
 <%@ include file="./footer.jsp" %>	