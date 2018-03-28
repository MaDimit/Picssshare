package controller.manager;

import model.CommentBean;
import model.UserBean;
import model.dao.CommentDao;
import model.post.PostBean;

public class CommentManager {
	
	private static CommentManager instance;
	private static int uid;
	
	private CommentManager() {
		//uid = get uid from DB or collection
	}
	
	public static synchronized CommentManager getInstance() {
		if(instance == null) {
			instance = new CommentManager();
		}
		return instance;
	}
	
	public void createComment(String content, UserBean poster, PostBean belongedPost) {
		CommentBean comment = null;
		if(content != null && !content.isEmpty() && poster != null) {
			comment = new CommentBean(poster, content, belongedPost);
		}
		
		CommentDao.getInstance().addCommentInDB(belongedPost, comment);
	}

}
