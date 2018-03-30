package model.notification;

import model.UserBean;
import model.post.PostBean;

public class LikeNotificationBean extends NotificationBean{

	private UserBean causer;
	private UserBean receiver;
	private PostBean post;
	
	public LikeNotificationBean(UserBean causer, UserBean receiver, PostBean post) {
		super(causer.getUsername() + " liked your photo.", causer, receiver);
		
		this.post = post;
	}

}
