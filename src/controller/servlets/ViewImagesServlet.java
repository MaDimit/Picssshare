package controller.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.UserBean;
import model.post.PostBean;

/**
 * Servlet implementation class ViewImagesServlet
 */
@WebServlet("/getimages")
public class ViewImagesServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserBean user = (UserBean)request.getSession().getAttribute("user");
		ArrayList<PostBean> posts = new ArrayList<>(user.getPosts());
		request.setAttribute("posts", posts);
		request.getRequestDispatcher("showimg.jsp").forward(request, response);
	}

}
