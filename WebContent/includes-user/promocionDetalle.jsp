<%@ include file="./header.jsp" %>
<%@ page import = "model.Promocion" %>
<%
Promocion promocion = (Promocion) misession.getAttribute("promocion");
boolean control = false;
%>
	<section class="container mt-5 pt-5">
		<h1>Detalle de promoción: <%out.println(promocion.getNombre()); %></h1><br>
		
		<div  class="card">
			<table class="table">
		  <thead>
		    <tr>
		      <th scope="col">Nombre</th>
		      <th scope="col">Costo</th>
		      <th scope="col">Tiempo</th>
		      <th scope="col">Cupo</th>
		      <th scope="col">Atracciones</th>
		      <th scope="col">Comprar</th>
		    </tr>
		  </thead>
		  <tbody>
		    <tr>
				   <td><% out.println(promocion.getNombre()); %></td>
				   <td><% out.println(promocion.getTotalPagar() ); %></td>
				   <td><% out.println(promocion.getTiempoTotal() ); %></td>
				   <td><% out.println(promocion.cupoDisponible() ); %></td>
				   <td><% for(Atraccion atraccion : promocion.getAtracciones()){
					   out.println(atraccion.getNombre()+", ");
				   		} %>
				   	</td>
				    <%if(user.puedeComprarPromocion(promocion) && !user.isContienePromocion(promocion)) {%>
				   	<td><a class="btn btn-primary" href="../promocionComprada?id=<% out.println(promocion.getId());%>&opcion=0">Comprar</a></td>
				   <%}else{ control = true;%>
				   	<td><button class="btn btn-secondary" disabled="disabled">Comprar</button></td>
				   <%} %>
			   </tr> 
		</tbody>
		</table>      
		</div> 
		<%if(control){
			if(user.isContienePromocion(promocion)) {%>
		<div class="alert alert-warning">
			<h4>Usted ya compró esta promoción</h4>
		</div>
		<% }else{%>
			<div class="alert alert-warning">
			<h4>No se puede comprar (tiempo o dinero insuficiente)</h4>
			</div>
			<% }
			}%>
	</section>

 <%@ include file="./footer.jsp" %>	