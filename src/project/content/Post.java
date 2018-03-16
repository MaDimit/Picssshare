package project.content;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import project.user.User;

public abstract class Post {
	//a constant for calculating coefficient of significance for version 1
	public static final int SIGNIFICANCE_CONSTANT = 100;
	
	//coefficients for version 2
	private static final double LIKES_COEFFICIENT = 1.0;
	private static final double COMMENTS_COEFFICIENT = 2.0;
	private static final double DATE_COEFFICIENT = 0.5;

	protected int likes;
	protected int dislikes; 
	protected LocalDateTime date;
	protected int coefficient;
	

	protected User poster;
	protected String url;
	protected HashMap<Integer, Comment> comments;
	//commentsCounter is needed when user decide to delete comment, it is a criteria when clicking a button
	protected int commentsCounter = 0;
	
	public Post(User poster, String url) {
		
		this.likes = 0;
		this.dislikes=0;
		this.date = LocalDateTime.now();
		this.poster = poster;
		this.url = url;
		this.comments = new HashMap<Integer, Comment>();
		//this.coefficient = 
		
	}
	/*	
	 * Version 1:
	 * If the date of the post is within 1 week, the dominant factor for significance is Date
	 * Date = 50% / Likes - Dislikes = 30% / Comments = 20%
	 * If post date is within 1 month, the dominant factor for significance is Likes - Dislikes
	 * Date = 20% / Likes - Dislikes = 50% / Comments = 20%
	 * If post date is older than 1 month, post will not be displayed in Feed at all
	 * 
//	 */
	private int generateCoefficient(LocalDateTime currentDate) {
		//TODO:
		if(currentDate.minusWeeks(1).isBefore(this.date)) {
			
		}
		if(currentDate.minusMonths(1).isBefore(this.date)) {
			return (int)0.2*SIGNIFICANCE_CONSTANT+(int)((0.5*(this.likes-this.dislikes)))+(int)((0.3*SIGNIFICANCE_CONSTANT));
		}
		return 0;
	
	}
	
	/*
	 * Version 2:
	 * 
	 */
	public double generateCoefficient() {
		long secondsDifference = ChronoUnit.SECONDS.between(this.date, LocalDateTime.now());
		System.out.println("Seconds difference: " + secondsDifference);
		double coefficient = (likes * LIKES_COEFFICIENT + commentsCounter * COMMENTS_COEFFICIENT)/ secondsDifference * DATE_COEFFICIENT;
		System.out.println("coefificient: " + coefficient);
		return coefficient;
	}
	
	public void addLike() {
		this.likes++;
	}
	
	public void addDislike() {
		this.dislikes++;
	}
	
	public void addComment(User user, String content) {
		this.comments.put(commentsCounter, new Comment(user, LocalDateTime.now(), content));
		commentsCounter++;
	}
	
	public void deleteComment(int id) {
		this.comments.remove(id);
	}
	
	public void showInfo() {
		System.out.println("=====INFO ABOUT POST=======");
		System.out.println("Likes: "+this.likes);
		System.out.println("Dislikes: "+this.dislikes);
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
		System.out.println("================");
	}
	
	public User getPoster() {
		return poster;
	}
	
	public LocalDateTime getDate() {
		return date;
	}

	public boolean isPhoto() {
		return false;
	}
	
	public static class ComparatorByDate implements Comparator<Post>{
		@Override
		public int compare(Post p1, Post p2) {
			return p1.date.compareTo(p2.date) > 0 ? -1 : 1;
		}
	}
	
	public static class ComparatorByCoefficient implements Comparator<Post>{
		@Override
		public int compare(Post p1, Post p2) {
			return p1.generateCoefficient() - p2.generateCoefficient() > 0 ? -1 : 1;
		}
	}
	
}
