package model.notification;

import model.UserBean;
import model.post.PostBean;

public class LikeNotificationBean extends NotificationBean{
	
	private UserBean causer;
	private PostBean post;
	
	public LikeNotificationBean(UserBean user, PostBean post) {
		super(user.getUsername() + " liked your photo.");
		this.causer = user;
		this.post = post;
	}

}
