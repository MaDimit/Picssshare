package project.user.notifications;

import java.time.LocalDateTime;
import java.util.Comparator;

import project.content.Post;
import project.user.User;

public abstract class Notification {
	private String description;
	private LocalDateTime date;
	private boolean seen;
	
	Notification(String description) {
		this.description = description;
		this.date = LocalDateTime.now();
		this.seen = false;
	}
	
	public LocalDateTime getDate() {
		return date;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void seen(){
		this.seen = true;
	}
	
	public static class ComparatorByDate implements Comparator<Notification>{

		@Override
		public int compare(Notification arg0, Notification arg1) {
			return arg0.date.compareTo(arg1.date);
		}
		
	}
}
