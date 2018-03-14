package project.content;

import java.time.LocalDateTime;

import project.user.User;

public class Comment {
	 
	
	private User poster;
	private LocalDateTime postTime;
	private String content;
	
	public Comment(User poster, LocalDateTime postTime, String content) {
		
		this.poster = poster;
		this.postTime = postTime;
		this.content = content;
	}
	
	public User getPoster() {
		return poster;
	}
	
	public String getContent() {
		return content;
	}
	
	public LocalDateTime getPostTime() {
		return postTime;
	}
	
	
	
}