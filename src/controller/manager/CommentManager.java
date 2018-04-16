package controller.manager;

import java.sql.SQLException;

import model.CommentBean;
import model.UserBean;
import model.dao.CommentDao;
import model.post.PostBean;

public class CommentManager {

	public static class CommentException extends Exception {
		public CommentException(String msg) {
			super(msg);
		}
	}

	private final static CommentManager instance = new CommentManager();

	private CommentManager() {
	}

	public static CommentManager getInstance() {
		return instance;
	}

	public void createComment(String content, UserBean poster, PostBean belongedPost)
			throws SQLException, CommentException {
		if (content == null || content.isEmpty()) {
			throw new CommentException("Comment is empty.");
		}
		if (poster == null) {
			throw new CommentException("Comment poster is null");
		}
		if (belongedPost == null) {
			throw new CommentException("Comment post is null");
		}

		CommentBean comment = new CommentBean(poster, content, belongedPost);

		CommentDao.getInstance().addComment(comment);

	}

	/*
	 * Not sure how the comment will be deleted by id or object. How the information
	 * from the frontend will come here
	 * 
	 */
	public void deleteComment(CommentBean comment) throws SQLException, CommentException {
		if (comment == null) {
			throw new CommentException("Comment is empty");
		}
		CommentDao.getInstance().deleteComment(comment);
	}

	public void deleteCommentById(int postID, int commentID) throws SQLException, CommentException {
		if (commentID < 0) {
			throw new CommentException("Comment id is invalid");
		}
		CommentDao.getInstance().deleteCommentByID(postID, commentID);
	}

}
