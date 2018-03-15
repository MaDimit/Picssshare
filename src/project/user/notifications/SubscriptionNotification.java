package project.user.notifications;

import project.content.Post;
import project.user.User;

public class SubscriptionNotification extends Notification {
	private User causer;
	public SubscriptionNotification(User user) {
		super(user.getUsername() + " subscribed to you.");
		this.causer = user;
	}

}
