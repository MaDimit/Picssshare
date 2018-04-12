package controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.manager.UserManager;
import model.UserBean;

/**
 * Servlet implementation class UpdateProfileServlet
 */
@WebServlet("/editProfile")
public class UpdateProfileServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String newPassword = request.getParameter("newPassword");
			String repeatedNewPassword = request.getParameter("rNewPassword");
			if (newPassword.equalsIgnoreCase(repeatedNewPassword)) {
				
					UserManager.getInstance().updateProfileInfo((UserBean) request.getSession().getAttribute("user"),
							request.getParameter("newPassword"), request.getParameter("firstName"), request.getParameter("lastName"), request.getParameter("email"));
					
					request.getRequestDispatcher("user.jsp").forward(request, response);
				
			} else {
				request.setAttribute("error", "New passwords don't match");
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
		} catch (Exception e) {
			request.setAttribute("error", "Sorry! Something went completely wrong!");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

}
