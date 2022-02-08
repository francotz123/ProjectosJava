package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsuarioDao;

/**
 * Servlet implementation class CrearUsuario
 */
@WebServlet("/crearUsuario")
public class CrearUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CrearUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Integer admin;
		String nombre = request.getParameter("nombre");
		String password = request.getParameter("password");
		Double tiempo = Double.parseDouble(request.getParameter("tiempo"));
		Integer presupuesto = Integer.parseInt(request.getParameter("presupuesto"));
		Integer idTipoAtraccion = Integer.parseInt(request.getParameter("tipoAtraccionId"));
		Boolean isAdmin = Boolean.parseBoolean(request.getParameter("isAdmin"));
		

		if(isAdmin) {
			admin = 1;
		}else {
			admin = 0;
		}
		
		UsuarioDao crearUser = new UsuarioDao();
		
		crearUser.crearUsuario(nombre, presupuesto, tiempo, admin, idTipoAtraccion, password);
		
		response.sendRedirect("includes-admin/altaUsuario.jsp");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
