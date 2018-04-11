package controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.manager.LoggingManager;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String password = request.getParameter("password1");
		String password2 = request.getParameter("password2");
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		if(password != password2) {
			//TODO password not match
		}
		
		//LoggingManager.getInstance().register(username, password, email);
		response.getWriter().println("LALALALAL");
		
				
	}

}
