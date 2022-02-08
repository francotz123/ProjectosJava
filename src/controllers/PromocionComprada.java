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
import dao.UsuarioDao;
import model.Atraccion;
import model.Promocion;
import model.Usuario;

/**
 * Servlet implementation class PromocionComprada
 */
@WebServlet("/promocionComprada")
public class PromocionComprada extends HttpServlet  {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PromocionComprada() {
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
		Promocion promocion = (Promocion) misession.getAttribute("promocion");
		AtraccionDao atracciones = (AtraccionDao) misession.getAttribute("atracciones");
	
		user.userComproPromocion(promocion, atracciones);
		
		UsuarioDao actualizar = new UsuarioDao();
		actualizar.actualizarUsuarioDB(user);
		actualizar.agregarPromocionComprada(promocion.getId(),user.getId());
		
		AtraccionDao actualizarAtraccion = new AtraccionDao();
		
		for (Atraccion atraccion : promocion.getAtracciones()) {
			atraccion.setCupoVisitantes(atraccion.getCupoVisitantes() -1);
			actualizarAtraccion.actualizarCupo(atraccion.getId(), atraccion.getCupoVisitantes());
		}
		
		PromocionesDao actualizarPromocion = new PromocionesDao();
		actualizarPromocion.getPromociones(user);
		
		response.sendRedirect("includes-user/itinerario.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
