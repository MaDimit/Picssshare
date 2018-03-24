package model.notification;

import java.time.LocalDateTime;
import java.util.Comparator;

public class NotificationBean {
	
	public static class ComparatorByDate implements Comparator<NotificationBean>{

		@Override
		public int compare(NotificationBean arg0, NotificationBean arg1) {
			return arg0.date.compareTo(arg1.date);
		}
		
	}
	
	private String description;
	private LocalDateTime date;
	private boolean seen;
	
	NotificationBean(String description) {
		this.description = description;
		this.date = LocalDateTime.now();
		this.seen = false;
	}
	
	public void seen(){
		this.seen = true;
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

	public boolean isSeen() {
		return seen;
	}

	public void setSeen(boolean seen) {
		this.seen = seen;
	}
	
	

}
