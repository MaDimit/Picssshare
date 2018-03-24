package controller.manager;

import java.util.Comparator;

import model.notification.NotificationBean;
import project.user.notifications.Notification;

public class NotificationManager {
	
	public static class ComparatorByDate implements Comparator<NotificationBean>{

		@Override
		public int compare(NotificationBean arg0, NotificationBean arg1) {
			return arg0.date.compareTo(arg1.date);
		}
		
	}

}
