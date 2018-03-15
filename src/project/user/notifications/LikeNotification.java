package project.user.notifications;

import project.content.Post;
import project.user.User;

public class LikeNotification extends PostNotification {
	private User causer;
	
	public LikeNotification(User user, Post post) {
		super(user.getUsername() + " liked your photo.", post);
		this.causer = user;
	}

}
