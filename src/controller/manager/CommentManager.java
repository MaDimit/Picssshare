package controller.manager;

import model.CommentBean;
import model.UserBean;

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
	
	public CommentBean createComment(String content, UserBean poster) {
		CommentBean comment = null;
		if(content != null && !content.isEmpty() && poster != null) {
			comment = new CommentBean(poster, content);
		}
		return comment;
	}

}
