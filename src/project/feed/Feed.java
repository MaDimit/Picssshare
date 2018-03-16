package project.feed;

import java.util.TreeSet;

import project.content.Post;
import project.user.User;

public abstract class Feed {
	
	public enum Type{MAIN_FEED, TRENDING_FEED}
	
	//the user to whom this feed belongs
	protected TreeSet<Post> posts;

	public Feed() {
		this.posts = new TreeSet<>(new Post.ComparatorByCoefficient());
	}
	
	protected abstract void generateFeed();
	
	public TreeSet<Post> getFeed() {
		return posts;
	}
	
	public void displayPostsInfo() {
		posts.forEach(p -> p.showInfo());
	}
		
}
