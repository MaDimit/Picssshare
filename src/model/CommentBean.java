package model;

import java.time.LocalDateTime;
import java.util.Comparator;

import model.post.PostBean;

public class CommentBean {
	public static class ComparatorByDate implements Comparator<CommentBean>{
		@Override
		public int compare(CommentBean comment1, CommentBean comment2) {
			return comment1.postTime.compareTo(comment2.postTime) > 0 ? -1 : 1;
		}
	}
	
	// Adding comments in post can be done by id for comments instead of using HashMap;
	public static class ComparatorById implements Comparator<CommentBean>{
		@Override
		public int compare(CommentBean comment1, CommentBean comment2) {
			return comment1.id - comment2.id;
		}
	}
	
	private UserBean poster;
	private LocalDateTime postTime;
	private String content;
	private int id;
	private PostBean belongedPost;
	
	public CommentBean(UserBean poster, String content, PostBean belongedPost) {
		
		this.poster = poster;
		this.postTime = LocalDateTime.now();
		this.content = content;
		this.belongedPost=belongedPost;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}

	public PostBean getBelongedPost() {
		return belongedPost;
	}
	
	public UserBean getPoster() {
		return poster;
	}

	public void setPoster(UserBean poster) {
		this.poster = poster;
	}

	public LocalDateTime getPostTime() {
		return postTime;
	}

	public void setPostTime(LocalDateTime postTime) {
		this.postTime = postTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	

}
