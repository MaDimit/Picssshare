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
		try {
			String name = request.getParameter("username");
			String password = request.getParameter("password");
			UserBean user = LoggingManager.getInstance().login(name, password);
			request.getSession().setAttribute("user", user);
			request.getRequestDispatcher("main.jsp").forward(request, response);
		} catch (LoggingException e) {
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}catch(Exception e) {
			request.setAttribute("error", "Something went totaly wrong. Sorry.");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	
		
	}

}
