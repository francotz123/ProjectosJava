package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AtraccionDao;
import dao.PromocionesDao;
import model.Atraccion;
import model.Promocion;

/**
 * Servlet implementation class DetallePromocion
 */
@WebServlet("/detallePromocion")
public class DetallePromocion extends HttpServlet  {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DetallePromocion() {
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
		Integer id = Integer.parseInt(idString);
		Integer opcString = Integer.parseInt(request.getParameter("opcion"));
		PromocionesDao buscarPromo = new PromocionesDao();
		Promocion promoConseguida = buscarPromo.conseguirPromocion(id);
		
		if (promoConseguida != null) {
			HttpSession misession = request.getSession(true);
			misession.setAttribute("promocion", promoConseguida);
			
			if(opcString == 1) {
				response.sendRedirect("includes-user/promocionDetalle.jsp");
			}else if(opcString == 0){
				response.sendRedirect("includes-admin/editarPromocion.jsp");
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
