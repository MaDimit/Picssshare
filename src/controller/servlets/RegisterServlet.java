package controller.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.manager.LoggingManager;
import controller.manager.LoggingManager.RegistrationException;
import model.UserBean;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String password = request.getParameter("password1");
			String password2 = request.getParameter("password2");
			String username = request.getParameter("username");
			String email = request.getParameter("email");
			if(!password.equals(password2)) {
				throw new LoggingManager.RegistrationException("Password does not match.");
			}
			UserBean user = LoggingManager.getInstance().register(username, password, email);
			request.getSession().setAttribute("user", user);
			request.getRequestDispatcher("userfeed").forward(request, response);
		} catch (LoggingManager.RegistrationException e) {
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}catch(Exception e) {
			request.setAttribute("error", "Something went totaly wrong. Sorry.");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
				
	}

}
