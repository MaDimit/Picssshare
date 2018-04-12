package controller.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.manager.CollectionsManager;
import controller.manager.CommentManager;
import controller.manager.CommentManager.CommentException;
import model.UserBean;
import model.post.PostBean;

/**
 * Servlet implementation class CommentServlet
 */
@WebServlet("/comment")
public class CommentServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		PostBean post = CollectionsManager.getInstance().getPost(Integer.parseInt(request.getParameter("postid")));
		UserBean user = (UserBean)request.getSession().getAttribute("user");
		String content = request.getParameter("content");
		CommentManager.getInstance().createComment(content, user, post);
		
		response.sendRedirect(response.encodeRedirectURL(request.getHeader("Referer")));
		}catch(CommentException e) {
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}catch(Exception e) {
			request.setAttribute("error", "Something went wrong durring comment adding.");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

}
