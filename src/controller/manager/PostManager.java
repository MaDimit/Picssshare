package controller.manager;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;

import org.apache.jasper.compiler.Node.Comment;

import model.post.Comparator;
import model.post.Post;
import model.post.PostBean;
import project.user.User;

public class PostManager {
	
	private static PostManager instance;
	
	private PostManager() {
		
	}
	
	public static synchronized PostManager getInstance() {
		if(instance == null) {
			instance = new PostManager();
		}
		return instance;
	}
	
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
	
	public void addLike(PostBean post) {
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

}
