package model.feed;

import java.util.ArrayList;
import java.util.TreeSet;

import model.post.PostBean;

public class FeedBean {
	
public enum Type{MAIN_FEED, TRENDING_FEED}
	
	//the user to whom this feed belongs
	protected TreeSet<PostBean> posts;
	protected ArrayList<PostBean> postsByComments;

	public FeedBean() {
		this.posts = new TreeSet<>(new PostBean.ComparatorByDate());
		this.postsByComments = new ArrayList<>();
	}
	
	public void addPost(PostBean post) {
		this.posts.add(post);
	}
	
	public void addPostByComment(PostBean post) {
		this.postsByComments.add(post);
	}
	
	public TreeSet<PostBean> getPosts() {
		return posts;
	}
	
	public ArrayList<PostBean> getPostsByComment() {
		return postsByComments;
	}

}
