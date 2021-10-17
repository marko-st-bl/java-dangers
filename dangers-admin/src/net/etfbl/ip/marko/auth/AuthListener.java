package net.etfbl.ip.marko.auth;

import java.io.IOException;

import javax.faces.application.ResourceHandler;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthListener implements Filter{

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws ServletException, IOException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession(false);
		String loginURI = request.getContextPath() + "/login.xhtml";
		 boolean resourceRequest = request.getRequestURI().startsWith(request.getContextPath() + ResourceHandler.RESOURCE_IDENTIFIER + "/");
		

		// smatramo da ako postoji user u sesiji onda moze pristupiti aplikaciji. Za
		// ovaj primjer nisu obradjeni dodatni sigurnosni elementi
		if (loginURI.equals(request.getRequestURI().toString()) 
				|| (session!=null && session.getAttribute("admin") != null)
				|| resourceRequest) {
			if(!resourceRequest) {				
				response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
				response.setHeader("Pragma", "no-cache");
				response.setDateHeader("Expires", 0);
			}
			chain.doFilter(request, response);
		} else {
			System.out.print("Invalid");
			response.sendRedirect(loginURI);
		}

	}
}
