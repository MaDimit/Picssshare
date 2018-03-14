package project.user.notifications;

import project.user.User;

public class SubscriptionNotification extends Notification {
	private User causer;
	public SubscriptionNotification(User user) {
		super(user.getUsername() + " subscribed to you.");
		
	}

}
