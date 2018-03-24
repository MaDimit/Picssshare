package model.notification;

import model.UserBean;

public class SubscriptionNotificationBean extends NotificationBean{
	
	private UserBean causer;
	
	public SubscriptionNotificationBean(UserBean user) {
		super(user.getUsername() + " subscribed to you.");
		this.causer = user;
	}
	

}
