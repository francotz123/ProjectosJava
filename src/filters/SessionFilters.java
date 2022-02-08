package filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AtraccionDao;
import model.Usuario;

/**
 * Servlet Filter implementation class SessionFilters
 */
@WebFilter(urlPatterns = {"/includes-user/*", "/includes-admin/*"})

public class SessionFilters implements Filter {


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletResponse res = (HttpServletResponse) response;
		Usuario user = (Usuario) ((HttpServletRequest)request).getSession().getAttribute("usuario");
		
		
		if(user != null) {
			// pass the request along the filter chain
			chain.doFilter(request, response);
		}else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("./errorSession.jsp");
			dispatcher.forward(request, response);
		}

	}

}
