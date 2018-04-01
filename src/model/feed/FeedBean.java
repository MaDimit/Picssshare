package model.feed;

import java.util.TreeSet;

import model.post.PostBean;

public class FeedBean {
	
public enum Type{MAIN_FEED, TRENDING_FEED}
	
	//the user to whom this feed belongs
	protected TreeSet<PostBean> posts;

	public FeedBean() {
		this.posts = new TreeSet<>(new PostBean.ComparatorByDate());
	}
	
	public void addPost(PostBean post) {
		this.posts.add(post);
	}
	
	public TreeSet<PostBean> getPosts() {
		return posts;
	}

}
