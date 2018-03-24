package model.notification;

import model.post.PostBean;
import project.content.Post;

public class PostNotificationBean extends NotificationBean{
	
	protected PostBean post;
	
	PostNotificationBean(String description, PostBean post) {
		super(description);
		this.post = post;
	}
	
	public PostBean getPost() {
		return post;
	}

}
