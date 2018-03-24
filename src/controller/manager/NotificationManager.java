package controller.manager;

import java.util.Comparator;

import model.notification.NotificationBean;

public class NotificationManager {
	
	private static NotificationManager instance;
	
	private NotificationManager() {
		
	}
	
	public static synchronized NotificationManager getInstance() {
		if (instance == null) {
			instance = new NotificationManager();
		}
		return instance;
	}
}
