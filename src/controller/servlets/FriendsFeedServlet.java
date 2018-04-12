package controller.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.manager.FeedManager;
import model.UserBean;
import model.feed.FeedBean;
import model.post.PostBean;

/**
 * Servlet implementation class FeedServlet
 */
@WebServlet("/friendsfeed")
public class FriendsFeedServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			UserBean user = (UserBean)request.getSession().getAttribute("user");
			FeedBean feed = FeedManager.getInstance().generateMainFeed(user);
			List<PostBean> posts = new ArrayList<PostBean>(feed.getPosts());
			request.setAttribute("posts", posts);
			request.getRequestDispatcher("feed.jsp").forward(request, response);
		}catch(Exception e) {
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
		
	}

}
