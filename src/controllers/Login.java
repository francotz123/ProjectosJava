package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AtraccionDao;
import dao.PromocionesDao;
import dao.TipoDeAtraccionDao;
import dao.UsuarioDao;
import model.Usuario;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
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
		String username = request.getParameter("username");
        String password = request.getParameter("password");
        int r;
        Usuario usuario = new Usuario();
        UsuarioDao dao = new UsuarioDao();
        
        TipoDeAtraccionDao tipoDeAtracciones = new TipoDeAtraccionDao();

        usuario.setNombre(username);
        usuario.setPassword(password);
        r = dao.validar(usuario);
     
        if(r==1) {
        	System.out.println(usuario.isAdmin());
        	AtraccionDao atracciones = new AtraccionDao();
        	atracciones.cargarAtracciones();
        	
        	PromocionesDao promociones = new PromocionesDao();
        	promociones.cargarPromociones();
        	 
        	HttpSession misession = request.getSession(true);
        	misession.setAttribute("atracciones", atracciones);
			misession.setAttribute("usuario", usuario);
			misession.setAttribute("promociones", promociones);
			misession.setAttribute("tipoDeAtracciones", tipoDeAtracciones);
			
        	//request.getRequestDispatcher("/index.jsp").forward(request, response);
			
			if(usuario.isAdmin()) {
				response.sendRedirect("includes-admin/inicioAdmin.jsp");
			}else {
				response.sendRedirect("includes-user/inicio.jsp");
			}
			
        }else {
        	RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);
        }
        
        
	}

}
