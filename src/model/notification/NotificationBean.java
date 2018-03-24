package model.notification;

import java.time.LocalDateTime;

public class NotificationBean {
	
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
