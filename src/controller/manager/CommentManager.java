package controller.manager;

import java.sql.SQLException;

import model.CommentBean;
import model.UserBean;
import model.dao.CommentDao;
import model.post.PostBean;

public class CommentManager {
	
	private static CommentManager instance;
	
	private CommentManager() {
	}
	
	public static synchronized CommentManager getInstance() {
		if(instance == null) {
			instance = new CommentManager();
		}
		return instance;
	}
	
	public boolean createComment(String content, UserBean poster, PostBean belongedPost) {
		if(content == null || content.isEmpty()) {
			System.out.println("Comment is empty.");
			return false;
		}
		if(poster == null) {
			System.out.println("Comment poster is null");
			return false;
		}
		if(belongedPost == null) {
			System.out.println("Comment post is null");
			return false;
		}
		
		CommentBean comment = new CommentBean(poster,content,belongedPost);
		try {
			CommentDao.getInstance().addComment(comment);
		}catch(SQLException e) {
			System.out.println("Problem during adding comment to DB: " + e.getMessage());
		}
		return true;
	}
	
	public boolean deleteComment(CommentBean comment) {
		if(comment == null) {
			System.out.println("Comment is null");
			return false;
		}
		
		try {
			CommentDao.getInstance().deleteComment(comment);
		}catch(SQLException e) {
			System.out.println("Problem during comment deletion from DB: " + e.getMessage());
		}
		
		return true;
	}

}
