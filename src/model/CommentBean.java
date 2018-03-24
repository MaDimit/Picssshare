package model;

import java.time.LocalDateTime;

public class CommentBean {
	
	private UserBean poster;
	private LocalDateTime postTime;
	private String content;
	
	public CommentBean(UserBean poster, LocalDateTime postTime, String content) {
		
		this.poster = poster;
		this.postTime = postTime;
		this.content = content;
	}

	public UserBean getPoster() {
		return poster;
	}

	public void setPoster(UserBean poster) {
		this.poster = poster;
	}

	public LocalDateTime getPostTime() {
		return postTime;
	}

	public void setPostTime(LocalDateTime postTime) {
		this.postTime = postTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	

}
