
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ include file="./headerAdmin.jsp" %>
<%@ page import = "dao.PromocionesDao" %>
<%@ page import = "model.Promocion" %>
<%@ page import = "model.Atraccion" %>
<%@ page import = "model.TipoAtraccion" %>
<%@ page import = "dao.UsuarioDao" %>
<%
Promocion promocion = (Promocion) misession.getAttribute("promocion");
%>
	<section class="container mt-5 pt-5">
		<h1>Editar promoción: <%out.println(promocion.getNombre());%> </h1>
		
		<form action="../modificarAtraccion" method="post">
		  <div class="form-group">
		    <label for="exampleInputEmail1">Nombre</label>
		    <input  name="nombre" type="text" class="form-control" id="exampleInputEmail1" value="<%out.println(promocion.getNombre());%>" >
		  </div>
		  <div class="form-group">
		    <label for="exampleInputPassword1">Costo</label>
		    <input  name="costo" type="text" class="form-control" value="">
		  </div>
		  <div class="form-group">
		    <label for="exampleInputPassword1">Tiempo</label>
		    <input name="tiempo" type="text" class="form-control" id="exampleInputPassword1" value="">
		  </div>
		  <div class="form-group">
		    <label for="exampleInputPassword1">Cupo</label>
		    <input  name="cupo" type="text" class="form-control" id="exampleInputPassword1" value="">
		  </div>
		  <div class="form-group">

			</select>
		</div>

		<div class="d-none">
		    <input  name="idAtraccion" value="">
		 </div>
		  <button type="submit" class="btn btn-primary">Modificar</button>
		</form>
	</section>	
 <%@ include file="./footerAdmin.jsp" %>