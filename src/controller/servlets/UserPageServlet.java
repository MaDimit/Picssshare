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
import model.post.PostBean;

/**
 * Servlet implementation class UserPageServlet
 */
@WebServlet("/getuserpage")
public class UserPageServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int id = Integer.parseInt(request.getParameter("userid"));
			UserBean user = CollectionsManager.getInstance().getUser(id);
			List<PostBean> posts = new ArrayList<>(user.getPosts());
			request.setAttribute("posts", posts);
			request.setAttribute("user", user);
			request.getRequestDispatcher("user.jsp").forward(request, response);
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

}
