package controller.manager;

import model.feed.FeedBean;

public class FeedManager {
	
	public void generateFeed(FeedBean feed) {
		//TODO generate feed depending on feed type;
	}
	
	public void displayPostsInfo(FeedBean feed) {
		feed.getPosts().forEach(p -> p.showInfo());
	}
	
	private void generateMainFeed(){
		for(User u: this.user.getSubscriptions()) {
			for(Post p: u.getPosts()) {
				this.posts.add(p);
			}
		}
	}
	
	private void generateTrendingFeed() {
		this.posts.addAll(Server.getInstance().getPosts());
	}

}
