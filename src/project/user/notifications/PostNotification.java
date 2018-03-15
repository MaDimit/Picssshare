package project.user.notifications;

import project.content.Post;

public abstract class PostNotification extends Notification {
	protected Post post;
	PostNotification(String description, Post post) {
		super(description);
		this.post = post;
	}
	
	public Post getPost() {
		return post;
	}
}
