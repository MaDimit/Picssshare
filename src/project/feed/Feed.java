package project.feed;

import project.content.Post;
import project.user.User;

public abstract class Feed {
	//the user to whom this feed belongs
		protected User userFeed;

	public Feed(User userFeed) {
		this.userFeed = userFeed;
	}
	
	public void displayPostsInfo() {
		for(User u: this.userFeed.getSubscribers()) {
			for(Post p: u.getPosts()) {
				p.showInfo();
			}
		}
	}
		
}
