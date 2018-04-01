package controller.manager;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.HashMap;

import model.UserBean;
import model.dao.NotificationDao;
import model.notification.NotificationBean;

public class NotificationManager {
	
	private static NotificationManager instance;
	private HashMap<Integer, NotificationBean> notifications;
	
	private NotificationManager() {
		this.notifications = new HashMap<>();
	}
	
	public static synchronized NotificationManager getInstance() {
		if (instance == null) {
			instance = new NotificationManager();
		}
		return instance;
	}
	
	public void proceedNotification(NotificationBean n) {
		if(n!=null) {
			//add in collection
			this.notifications.put(n.getCauserID(), n);
			//add in db
			try {
				NotificationDao.getInstance().addNotificationInDB(n);
			} catch (SQLException e) {
				System.out.println("Error while trying to add notification in db.");
				e.printStackTrace();
			}
		}
	}
}
