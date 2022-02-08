<%@ include file="./header.jsp" %>
<%
Atraccion atraccion = (Atraccion) misession.getAttribute("atraccion");
boolean control = false;
%>
	<section class="container mt-5 pt-5">
		<h1>Detalle de atracción: <%out.println(atraccion.getNombre()); %></h1><br>
		
		<div  class="card">
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
			   <tr>
				   <td><% out.println(atraccion.getNombre()); %></td>
				   <td><% out.println(atraccion.getCostoVisita() ); %></td>
				   <td><% out.println(atraccion.getPromedioTiempo() ); %></td>
				   <td><% out.println(atraccion.getCupoVisitantes() ); %></td>
				   <td><% out.println(atraccion.getTipoAtraccion() ); %></td>
					<%if(user.puedeComprarAtraccion(atraccion) && !user.isContieneAtraccion(atraccion) && !user.isComproAtraccionEnPromo(atraccion)) {%>
					  <td><a class="btn btn-primary" href="../atraccionComprada?id=<% out.println(atraccion.getId()); %>">Comprar</a></td>
				  <% }else { control = true;%>
				  	<td><button class="btn btn-secondary" disabled="disabled">Comprar</button></td>
				  	
				  <%} %>
			   </tr> 
			</tbody>
			</table>   
		</div> 
		<%if(control){
			if(user.isContieneAtraccion(atraccion)) {%>
		<div class="alert alert-warning">
			<h4>Usted ya compró esta atracción</h4>
		</div>
		<% }else if(user.isComproAtraccionEnPromo(atraccion)){ %>
			<div class="alert alert-warning">
				<h4>Usted ya incluyó esta atracción en una promoción comprada</h4>
			</div>
		<% }else{%>
			<div class="alert alert-warning">
			<h4>No se puede comprar (tiempo o dinero insuficiente)</h4>
			</div>
			<% }
			}%>
	</section>

 <%@ include file="./footer.jsp" %>	