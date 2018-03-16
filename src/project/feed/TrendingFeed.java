package project.feed;

import project.Server;
import project.user.User;

public class TrendingFeed extends Feed{
	
	public TrendingFeed() {
		generateFeed();
	}

	@Override
	protected void generateFeed() {
		this.posts.addAll(Server.getInstance().getPosts());
	}


}
