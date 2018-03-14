package project.user.notifications;

import java.time.LocalDateTime;

public abstract class Notification {
	private String description;
	private LocalDateTime date;
	
	Notification(String description) {
		this.description = description;
		this.date = LocalDateTime.now();
	}
}
