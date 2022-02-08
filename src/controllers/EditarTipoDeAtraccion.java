package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.TipoDeAtraccionDao;
import model.TipoAtraccion;

/**
 * Servlet implementation class editarTipoDeAtraccion
 */
@WebServlet("/editarTipoDeAtraccion")
public class EditarTipoDeAtraccion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditarTipoDeAtraccion() {
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
		Integer id = Integer.parseInt(request.getParameter("id"));
		TipoDeAtraccionDao buscarTipo = new TipoDeAtraccionDao();
		TipoAtraccion tipoAtraccionNew = buscarTipo.conseguirTipoAtraccion(id);

		if (tipoAtraccionNew != null) {
			HttpSession misession = request.getSession(true);
			misession.setAttribute("tipoAtraccion", tipoAtraccionNew);
			response.sendRedirect("includes-admin/editarTipoAtraccion.jsp");
		} else {
			System.out.println("es nulo");
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
