package controller.manager;

import model.UserBean;
import model.feed.FeedBean;
import model.feed.MainFeedBean;
import model.feed.TrendingFeedBean;
import model.post.PostBean;

public class FeedManager {
	
	public void displayPostsInfo(FeedBean feed) {
		feed.getPosts().forEach(p -> PostManager.getInstance().showInfo(p));
	}
	
	public MainFeedBean generateMainFeed(UserBean user){
		MainFeedBean feed = new MainFeedBean(user);
		for(UserBean u: user.getSubscriptions()) {
			for(PostBean p: u.getPosts()) {
				feed.addPost(p);
			}
		}
		return feed;
	}
	
	//TODO generate Trending feed from feed or post dao
	private TrendingFeedBean generateTrendingFeed() {
		TrendingFeedBean feed = new TrendingFeedBean();
		//this.posts.addAll(Server.getInstance().getPosts());
		return feed;
	}

}
