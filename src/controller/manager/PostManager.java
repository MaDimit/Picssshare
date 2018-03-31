package controller.manager;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;

import model.CommentBean;
import model.UserBean;
import model.dao.PostDao;
import model.post.PostBean;

public class PostManager {

	/*
	 * Singleton (may be changed later) ????? using eager singleton for better
	 * performance ?????
	 */
	private static PostManager instance;
	private static int uid;

	// Coefficients for sorting posts in feed
	private static final double LIKES_COEFFICIENT = 1.0;
	private static final double COMMENTS_COEFFICIENT = 2.0;
	private static final double DATE_COEFFICIENT = 0.5;
	 
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
	public void addLike(UserBean liker, PostBean post) {
		if(post!=null) {
			System.out.println(post.getId());
			PostDao.getInstance().getPosts().get(post.getId()).like();
			try {
				PostDao.getInstance().updateLikes(post);
				PostDao.getInstance().addInLikerPostTable(liker, post);
			} catch (Exception e) {
				System.out.println("Error with updating likes in DB.");
				e.printStackTrace();
			}
		}
		else {
			System.out.println("No such post found!");
		}
	}

	// TODO validation (May be done in JS)
	public void addDislike(PostBean post) {
		if(post!=null) {
			PostDao.getInstance().getPosts().get(post.getId()).dislike();
			try {
				PostDao.getInstance().updateLikes(post);
			} catch (Exception e) {
				System.out.println("Problem with updating likes.");
				e.printStackTrace();
			}
		}
		else {
			System.out.println("No such post found!");
		}
	}

	// TODO Comments id
//	public boolean addComment(PostBean post, CommentBean comment) {
//		if (post != null && comment != null) {
//			PostDao.getInstance().
//			post.addComment(comment);
//			return true;
//		}
//		return false;
//	}

	
	public boolean addPost(UserBean user, String url) {
		if (url == null || url.isEmpty()) {
			System.out.println("Post url is empty.");
			return false;
		}
		if(user == null) {
			System.out.println("Adding post is not successfull, user is null");
			return false;
		}
		
		PostBean post = new PostBean(user, url);
		
		try {
			PostDao.getInstance().addPost(post);
		} catch (SQLException e) {
			System.out.println("Problem during adding post to DB: " + e.getMessage());
			return false;
		}
		user.addPost(post);
		System.out.println("Post added by " + user.getUsername());
		return true;	
	}
	
	public boolean deletePost(int postID) {
		if(postID <= 0) {
			System.out.println("Negative id value");
			return false;
		}
		try {
			PostDao.getInstance().deletePost(postID);
		}catch(SQLException e) {
			System.out.println("Problem during post deleting: " + e.getMessage());
			return false;
		}
		
		return true;
	}

	public void showInfo(PostBean post) {
		System.out.println("=====INFO ABOUT POST=======");
		System.out.println("Likes: " + post.getLikes());
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
