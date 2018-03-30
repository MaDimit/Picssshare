package model.notification;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Comparator;

import model.UserBean;

public class NotificationBean {
	
	public enum Type{ LIKE_NOTIFICATION, POST_NOTIFICATION, SUBSCRIPTION_NOTIFICATION}
	
	public static class ComparatorByDate implements Comparator<NotificationBean>{

		@Override
		public int compare(NotificationBean arg0, NotificationBean arg1) {
			return arg0.date.compareTo(arg1.date);
		}
		
	}
	
	private int id;
	private String description;
	private LocalDateTime date;
	private boolean seen;
	private UserBean receiver;
	private UserBean causer;
	
	NotificationBean(String description, UserBean causer, UserBean receiver) {
		this.description = description;
		this.causer = causer;
		this.receiver = receiver;
		this.date = LocalDateTime.now();
		this.seen = false;
	}
	
	public void seen(){
		this.seen = true;
	}
	
	public int getReceiverID() {
		return receiver.getId();
	}
	
	public int getCauserID() {
		return causer.getId();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public int isSeen() {
		return seen ? 1 : 0;
	}

	public void setSeen(boolean seen) {
		this.seen = seen;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	

}
