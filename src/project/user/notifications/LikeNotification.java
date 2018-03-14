package project.user.notifications;

import project.user.User;

public class LikeNotification extends Notification {
	private User causer;
	public LikeNotification(User user) {
		super(user.getUsername() + " liked your photo.");
		// TODO Auto-generated constructor stub
	}

}
