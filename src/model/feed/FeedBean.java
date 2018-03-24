package model.feed;

import java.util.TreeSet;

import project.content.Post;

public class FeedBean {
	
public enum Type{MAIN_FEED, TRENDING_FEED}
	
	//the user to whom this feed belongs
	protected TreeSet<Post> posts;

	public FeedBean() {
		this.posts = new TreeSet<>(new Post.ComparatorByCoefficient());
	}
	
	public TreeSet<Post> getPosts() {
		return posts;
	}

}
