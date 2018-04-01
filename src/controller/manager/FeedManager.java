package controller.manager;

import java.util.Collections;

import model.UserBean;
import model.dao.FeedDao;
import model.feed.FeedBean;
import model.feed.MainFeedBean;
import model.feed.TrendingFeedBean;
import model.post.PostBean;

public class FeedManager {

	public void displayPostsInfo(FeedBean feed) {
		feed.getPosts().forEach(p -> PostManager.getInstance().showInfo(p));
	}
	
	public void displayTrendingPostInfo(FeedBean feed) {
		Collections.sort(feed.getPostsByComment(), (p1,p2)->(p2.getCommentsById().size() - p1.getCommentsById().size()));
		feed.getPostsByComment().forEach(p -> PostManager.getInstance().showInfo(p));
	}

	public MainFeedBean generateMainFeed(UserBean user) {
		MainFeedBean feed = new MainFeedBean(user);
		for (UserBean u : user.getSubscriptions()) {
			for (PostBean p : u.getPosts()) {
				feed.addPost(p);
			}
		}
		return feed;
	}

	public TrendingFeedBean generateTrendingFeed() {
		TrendingFeedBean feed = new TrendingFeedBean();
		for (Integer id : FeedDao.getInstance().getTrendingPosts()) {
			feed.addPostByComment(CollectionsManager.getInstance().getPostsByID().get(id));
		}
		return feed;
	}

}
