package model.notification;

import model.UserBean;

public class SubscriptionNotificationBean extends NotificationBean{

	
	public SubscriptionNotificationBean(UserBean causer, UserBean receiver) {
		super(causer.getUsername() + " subscribed to you.", causer, receiver);
	
	}
	
	
	

}
