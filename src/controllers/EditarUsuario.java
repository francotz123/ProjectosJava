package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UsuarioDao;
import model.Usuario;

/**
 * Servlet implementation class EditarUsuario
 */
@WebServlet("/editarUsuario")
public class EditarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditarUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String idString = request.getParameter("id");
		
		Integer id = Integer.parseInt(idString);
		UsuarioDao buscarUser = new UsuarioDao();
		
		Usuario user = buscarUser.conseguirUsuario(id);
		
		if(user != null) {
			HttpSession misession = request.getSession(true);
			misession.setAttribute("usuarioAEditar", user);
			response.sendRedirect("includes-admin/editarUsuario.jsp");
		}else {
			System.out.println("es nulo");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
