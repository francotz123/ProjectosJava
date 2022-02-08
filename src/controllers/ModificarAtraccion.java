package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AtraccionDao;

/**
 * Servlet implementation class ModificarAtraccion
 */
@WebServlet("/modificarAtraccion")
public class ModificarAtraccion extends HttpServlet {
	private static final long serialVersionUID = 1L;
     Integer id;  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificarAtraccion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
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
		Integer idAtraccion = Integer.parseInt(request.getParameter("idAtraccion"));
		Integer id = Integer.parseInt(request.getParameter("idAtraccion"));
		

		if(activoBool) {
			 activo = 1;
		}else {
			activo = 0;
		}
		AtraccionDao actualizar = new AtraccionDao();
		actualizar.actualizarAtraccion(id, nombre, costo, tiempo, cupo, idTipoAtraccion, activo);
		
		
		response.sendRedirect("includes-admin/atracciones.jsp");
	
	}

}
