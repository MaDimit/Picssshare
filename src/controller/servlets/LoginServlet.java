package controller.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.manager.LoggingManager;
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
		try {
			LoggingManager.getInstance().login(name, password);
		} catch (LoggingException e) {
			 out.println("<script type=\"text/javascript\">");
			   out.println("alert('User incorrect');");
			   out.println("location='index.html';");
			   out.println("</script>");

				response.sendRedirect("index.html");
		}
//		} catch (PasswordException e) {
//			 out.println("<script type=\"text/javascript\">");
//			   out.println("alert('Password incorrect');");
//			   out.println("location='index.html';");
//			   out.println("</script>");
//
//				response.sendRedirect("index.html");
//		}

		response.sendRedirect("main.jsp");

	}

}
