package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsuarioDao;

/**
 * Servlet implementation class ModificarUsuario
 */
@WebServlet("/modificarUsuario")
public class ModificarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificarUsuario() {
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
		Double tiempo = Double.parseDouble(request.getParameter("tiempo"));
		Integer presupuesto = Integer.parseInt(request.getParameter("presupuesto"));
		Integer idTipoAtraccion = Integer.parseInt(request.getParameter("tipoAtraccionId"));
		Boolean isAdmin = Boolean.parseBoolean(request.getParameter("isAdmin"));
		Integer idUser = Integer.parseInt(request.getParameter("idUser"));
		Integer id = Integer.parseInt(request.getParameter("idUser"));
		

		if(isAdmin) {
			admin = 1;
		}else {
			admin = 0;
		}
		
		UsuarioDao actualizarUser = new UsuarioDao();
		actualizarUser.actualizarUsuarioDB2(id, nombre, presupuesto, tiempo, admin, idTipoAtraccion);
		
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
