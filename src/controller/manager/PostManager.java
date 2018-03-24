package controller.manager;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;

import model.CommentBean;
import model.post.PostBean;

public class PostManager {

	/*
	 * Singleton (may be changed later) ????? using eager singleton for better
	 * performance ?????
	 */

	// Coefficients for sorting posts in feed
	private static final double LIKES_COEFFICIENT = 1.0;
	private static final double COMMENTS_COEFFICIENT = 2.0;
	private static final double DATE_COEFFICIENT = 0.5;

	private static PostManager instance;
	private static int uid;

	private PostManager() {
		// uid = TODO get uid from db or collection
	}

	public static synchronized PostManager getInstance() {
		if (instance == null) {
			instance = new PostManager();
		}
		return instance;
	}

	public double generateCoefficient(PostBean post) {
		long secondsDifference = ChronoUnit.SECONDS.between(post.getDate(), LocalDateTime.now());
		System.out.println("Seconds difference: " + secondsDifference);
		double coefficient = (post.getLikes() * LIKES_COEFFICIENT + post.getCommentsCounter() * COMMENTS_COEFFICIENT)
				/ secondsDifference * DATE_COEFFICIENT;
		System.out.println("coefificient: " + coefficient);
		return coefficient;
	}

	// TODO validation (May be done in JS)
	public void addLike(PostBean post) {
		post.like();
	}

	// TODO validation (May be done in JS)
	public void addDislike(PostBean post) {
		post.dislike();
		;
	}

	// TODO Comments id
	public boolean addComment(PostBean post, CommentBean comment) {
		if (post != null && comment != null) {
			post.addComment(comment);
			return true;
		}
		return false;
	}

	public void deleteComment(PostBean post, int id) {
		post.removeComment(id);
	}

	public void showInfo(PostBean post) {
		System.out.println("=====INFO ABOUT POST=======");
		System.out.println("Likes: " + post.getLikes());
		System.out.println("Dislikes: " + post.getDislikes());
		System.out.println("Post time: " + post.getDate());
		System.out.println("Poster: " + post.getPoster().getUsername());
		System.out.println("URL: " + post.getUrl());
		System.out.println("Comments: ");
		for (Map.Entry<Integer, CommentBean> entry : post.getComments().entrySet()) {
			System.out.print(entry.getValue().getPoster().getUsername());
			System.out.print(": " + entry.getValue().getContent());
			System.out.print("  --> posted on: " + entry.getValue().getPostTime());
			System.out.println();
		}
		System.out.println("================");
	}

}
