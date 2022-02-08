package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AtraccionDao;

/**
 * Servlet implementation class CrearAtraccion
 */
@WebServlet("/crearAtraccion")
public class CrearAtraccion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CrearAtraccion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Integer activo;
		String nombre = request.getParameter("nombre");
		Integer costo = Integer.parseInt(request.getParameter("costo"));
		Double tiempo = Double.parseDouble(request.getParameter("tiempo"));
		Integer cupo = Integer.parseInt(request.getParameter("cupo"));
		Integer idTipoAtraccion = Integer.parseInt(request.getParameter("tipoAtraccionId"));
		Boolean activoBool = Boolean.parseBoolean(request.getParameter("isActivo"));


		if(activoBool) {
			 activo = 1;
		}else {
			activo = 0;
		}
		AtraccionDao actualizar = new AtraccionDao();
		actualizar.crearAtraccion(nombre, costo, tiempo, cupo, idTipoAtraccion, activo);
		
		
		response.sendRedirect("includes-admin/atracciones.jsp");
	}

}
