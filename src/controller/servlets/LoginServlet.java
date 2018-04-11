package controller.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.manager.CollectionsManager;
import controller.manager.CommentManager;
import controller.manager.LoggingManager;
import controller.manager.PostManager;
import controller.manager.UserManager;
import model.UserBean;
import controller.manager.LoggingManager.LoggingException;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String name = request.getParameter("username");
		String password = request.getParameter("password");
		boolean caughtException = false;
		UserBean user = null;
		try {
			user = LoggingManager.getInstance().login(name, password);
		} catch (LoggingException e) {
			caughtException = true;
			response.sendRedirect("http://fs5.directupload.net/images/151026/j63kh5fn.png");
		}
		if(!caughtException) {
			request.getSession().setAttribute("user", user);
			request.getRequestDispatcher("main.jsp").forward(request, response);
		}
		
	}

}
