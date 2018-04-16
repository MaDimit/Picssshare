package controller.manager;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import model.CommentBean;
import model.UserBean;
import model.dao.PostDao;
import model.notification.LikeNotificationBean;
import model.notification.NotificationBean;
import model.post.PostBean;

public class PostManager {
	
	public static class PostException extends Exception{
		public PostException(String msg) {
			super(msg);
		}
	}

	private final static PostManager instance = new PostManager();
	private static int uid;

	// Coefficients for sorting posts in feed
	private static final double LIKES_COEFFICIENT = 1.0;
	private static final double COMMENTS_COEFFICIENT = 2.0;
	private static final double DATE_COEFFICIENT = 0.5;
	 
	private PostManager() {
	
	}

	public static PostManager getInstance() {
		return instance;
	}

//	public double generateCoefficient(PostBean post) {
//		long secondsDifference = ChronoUnit.SECONDS.between(post.getDate(), LocalDateTime.now());
//		System.out.println("Seconds difference: " + secondsDifference);
//		double coefficient = (post.getLikes() * LIKES_COEFFICIENT + post.getCommentsCounter() * COMMENTS_COEFFICIENT)
//				/ secondsDifference * DATE_COEFFICIENT;
//		System.out.println("coefificient: " + coefficient);
//		return coefficient;
//	}

	public void addLike(UserBean liker, PostBean post) throws SQLException, PostException {
		if(post!=null) {
			CollectionsManager.getInstance().getPostsByID().get(post.getId()).like();
			NotificationBean n = new LikeNotificationBean(liker, post.getPoster(), post);
			NotificationManager.getInstance().proceedNotification(n);
			PostDao.getInstance().addInLikerPostTable(liker, post);
			PostDao.getInstance().updateLikes(post);
		}
		else {
			throw new PostException("Selected post does not exist");
		}
	}

	public void addDislike(PostBean post) throws SQLException,PostException {
		if(post!=null) {
			CollectionsManager.getInstance().getPostsByID().get(post.getId()).dislike();
			PostDao.getInstance().updateLikes(post);	
		}
		else {
			throw new PostException("Selected post does not exist");
		}
	}

	
	public boolean addPost(UserBean user, String url) throws SQLException {
		if (url == null || url.isEmpty()) {
			System.out.println("Post url is empty.");
			return false;
		}
		if(user == null) {
			System.out.println("Adding post is not successfull, user is null");
			return false;
		}
		
		PostBean post = new PostBean(user, url);
		PostDao.getInstance().addPost(post);

		user.addPost(post);
		System.out.println("Post added by " + user.getUsername());
		return true;	
	}
	
	public void deletePost(int postID) throws PostException, SQLException{
		if(postID <= 0) {
			throw new PostException("Invalid post");
		}		
			PostDao.getInstance().deletePost(postID);
	}

	public void showInfo(PostBean post) {
		System.out.println("=====INFO ABOUT POST=======");
		System.out.println("Likes: " + post.getLikes());
		System.out.println("Post time: " + post.getDate());
		System.out.println("Poster: " + post.getPoster().getUsername());
		System.out.println("URL: " + post.getUrl());
		System.out.println("Comments: ");
		for (Map.Entry<Integer, CommentBean> entry : post.getCommentsById().entrySet()) {
			System.out.print(entry.getValue().getPoster().getUsername());
			System.out.print(": " + entry.getValue().getContent());
			System.out.print("  --> posted on: " + entry.getValue().getPostTime());
			System.out.println();
		}
		System.out.println("================");
	}

}
