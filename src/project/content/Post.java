package project.content;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import project.user.User;

public abstract class Post {

	protected int likes;
	protected LocalDateTime date;
	//TODO: COMMENTS -> Classes ? true : false; ------- true (should contain user info, date and text)

	protected User poster;
	protected String url;
	protected HashMap<Integer, Comment> comments;
	//commentsCounter is needed when user decide to delete comment, it is a criteria when clicking a button
	protected int commentsCounter = 0;
	
	public Post(User poster, String url) {
		
		this.likes = 0;
		this.date = LocalDateTime.now();
		this.poster = poster;
		this.url = url;
		this.comments = new HashMap<Integer, Comment>();
		
	}
	
	public void addLike() {
		this.likes++;
	}
	
	public void addComment(User user, String content) {
		this.comments.put(commentsCounter, new Comment(user, LocalDateTime.now(), content));
		commentsCounter++;
	}
	
	public void deleteComment(int id) {
		this.comments.remove(id);
	}
	
	public void showInfo() {
		System.out.println("Likes: "+this.likes);
		System.out.println("Post time: "+this.date);
		System.out.println("Poster: "+this.poster.getUsername());
		System.out.println("URL: "+this.url);
		System.out.println("Comments: ");
		for(Map.Entry<Integer, Comment> entry: this.comments.entrySet()) {
			System.out.print(entry.getValue().getPoster().getUsername());
			System.out.print(": "+entry.getValue().getContent());
			System.out.print("  --> posted on: "+entry.getValue().getPostTime());
			System.out.println();
		}
	}

	public boolean isPhoto() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
