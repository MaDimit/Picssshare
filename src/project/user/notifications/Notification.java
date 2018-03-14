package project.user.notifications;

import java.time.LocalDateTime;

import project.user.User;

public abstract class Notification {
	private String description;
	private LocalDateTime date;
	
	Notification(String description) {
		this.description = description;
		this.date = LocalDateTime.now();
	}
	
	public LocalDateTime getDate() {
		return date;
	}
	
	public String getDescription() {
		return description;
	}
}
