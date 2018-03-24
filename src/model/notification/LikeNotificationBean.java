package model.notification;

import model.UserBean;
import model.post.PostBean;

public class LikeNotificationBean extends NotificationBean{
	
	private UserBean causer;
	
	public LikeNotificationBean(UserBean user, PostBean post) {
		super(user.getUsername() + " liked your photo.", post);
		this.causer = user;
	}

}
