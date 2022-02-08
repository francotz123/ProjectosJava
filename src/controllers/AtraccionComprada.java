package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AtraccionDao;
import dao.UsuarioDao;
import model.Atraccion;
import model.Usuario;

/**
 * Servlet implementation class AtraccionComprada
 */
@WebServlet("/atraccionComprada")
public class AtraccionComprada extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AtraccionComprada() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession misession = request.getSession(false);
		Usuario user = (Usuario) misession.getAttribute("usuario");
		Atraccion atraccion = (Atraccion) misession.getAttribute("atraccion");
		
		user.userCompro(atraccion);
		UsuarioDao actualizar = new UsuarioDao();
		actualizar.actualizarUsuarioDB(user);
		actualizar.agregarAtraccionComprada(atraccion.getId(),user.getId());
		
		AtraccionDao actualizarAtraccion = new AtraccionDao();
		actualizarAtraccion.actualizarCupo(atraccion.getId(), atraccion.getCupoVisitantes());
		actualizarAtraccion.getAtracciones(user); 
		
		response.sendRedirect("includes-user/itinerario.jsp");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletReq uest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
