package controller.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.manager.CollectionsManager;
import model.UserBean;

/**
 * Servlet implementation class SearchUsersServlet
 */
@WebServlet("/searchUsers")
public class SearchUsersServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		List<UserBean> foundUsers = (ArrayList<UserBean>) CollectionsManager.getInstance().searchUser(request.getParameter("username"));
		for(int i = 0 ; i<foundUsers.size();i++) {
			response.getWriter().println(foundUsers.get(i).getUsername());
		}
		request.setAttribute("foundUsers", foundUsers);
		request.getRequestDispatcher("search.jsp").forward(request, response);
		}catch(Exception e) {
			request.setAttribute("error", "Something went totally wrong!");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

}
