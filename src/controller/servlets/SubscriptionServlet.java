package controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.manager.CollectionsManager;
import controller.manager.UserManager;
import model.UserBean;

@WebServlet("/subscription")
public class SubscriptionServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			UserBean subscriber = CollectionsManager.getInstance()
					.getUser(Integer.parseInt(request.getParameter("subscriberUserId")));

			UserBean subscribed = CollectionsManager.getInstance()
					.getUser(Integer.parseInt(request.getParameter("subscribedUserId")));

			UserManager.getInstance().subscribe(subscriber, subscribed);
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

}
