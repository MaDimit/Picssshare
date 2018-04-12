package controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.manager.CollectionsManager;
import controller.manager.CommentManager;
import controller.manager.PostManager;
import controller.manager.PostManager.PostException;
import controller.manager.CommentManager.CommentException;
import model.UserBean;
import model.post.PostBean;

/**
 * Servlet implementation class LikeServlet
 */
@WebServlet("/like")
public class LikeServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			PostBean post = CollectionsManager.getInstance().getPost(Integer.parseInt(request.getParameter("postid")));
			UserBean user = (UserBean)request.getSession().getAttribute("user");
			PostManager.getInstance().addLike(user, post);
			response.sendRedirect(response.encodeRedirectURL(request.getHeader("Referer")));
			}catch(PostException e) {
				request.setAttribute("error", e.getMessage());
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}catch(Exception e) {
				request.setAttribute("error", "Something went wrong durring like adding.");
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
	}

}
