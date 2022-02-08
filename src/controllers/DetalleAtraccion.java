package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AtraccionDao;
import model.Atraccion;

/**
 * Servlet implementation class DetalleAtraccion
 */
@WebServlet("/detalleAtraccion")
public class DetalleAtraccion extends HttpServlet  {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DetalleAtraccion() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String idString = request.getParameter("id");
		Integer opcString = Integer.parseInt(request.getParameter("opcion"));
		
		Integer id = Integer.parseInt(idString);
		AtraccionDao buscarAtraccion = new AtraccionDao();
		Atraccion atraccionConseguida = buscarAtraccion.conseguirAtraccion(id);
		
		if (atraccionConseguida != null) {
			HttpSession misession = request.getSession(true);
			misession.setAttribute("atraccion", atraccionConseguida);
			if(opcString == 1) {
				response.sendRedirect("includes-user/atraccionDetalle.jsp");
			}else if(opcString == 0){
				response.sendRedirect("includes-admin/editarAtraccion.jsp");
			}
			
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
