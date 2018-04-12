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
import controller.manager.LoggingManager.RegisterException;
import model.UserBean;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		String password = request.getParameter("password1");
		String password2 = request.getParameter("password2");
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		if(!password.equals(password2)) {
			out.println("Password does not match!");
			return;
		}
		UserBean user;
		try {
			user = LoggingManager.getInstance().register(username, password, email);
		} catch (RegisterException e) {
			out.println(e.getMessage());
			return;

		}
		request.getSession().setAttribute("user", user);
		request.getRequestDispatcher("main.jsp").forward(request, response);
				
	}

}
