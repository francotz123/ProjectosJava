
<%@ include file="./headerAdmin.jsp" %>
<%@ page import = "dao.AtraccionDao" %>
<%@ page import = "dao.UsuarioDao" %>
<%@ page import = "model.Atraccion" %>
<%@ page import = "java.util.ArrayList" %>

<%
UsuarioDao listaUsuarios = new UsuarioDao();
user = listaUsuarios.actualizarUsario(user);
listaUsuarios.getUsuariosList();
%>
	<section class="container sm-4 mt-3 pt-5">
	
		<h1>Editar Usuario</h1>
		<a class="btn btn-primary" href="./crearUsuario.jsp">Crear usuario</a><br>
		<h3>Listado de usuarios</h3>
	 	<table class="table">
		  <thead>
		    <tr>
		      <th scope="col">Nombre</th>
		      <th scope="col">Tiempo disponible</th>
		      <th scope="col">Atraccion favorita</th>
		      <th scope="col">Presupuesto</th>
		      <th scope="col">¿Es administrador?</th>
		      <th scope="col">Editar</th>
		    </tr>
		  </thead>
		  <tbody>
		
		  <% for(Usuario usuario : listaUsuarios.getUsuariosList()){ 
		  	if(!usuario.getId().equals(user.getId())) { %>

		   <tr>
			   <td><% out.println(usuario.getNombre()) ;%></td>
			   <td><% out.println(usuario.getTimpoDisponible()); %></td>
			   <td><% out.println(usuario.getAtraccionFav()); %></td>
			   <td><% out.println( usuario.getPresupuesto()); %></td>
			   <td><% out.println( usuario.isAdmin());%></td>
			<td><a class="btn btn-primary" href="../editarUsuario?id=<% out.println(usuario.getId()); %>">Editar</a></td>
		   </tr> 
			<% }
				} %> 
		</tbody>
		</table> 
	</section>	
	
	
	
<%-- <% atracciones.borrarListaAtracciones(); %> --%>
 <%@ include file="./footerAdmin.jsp" %>	