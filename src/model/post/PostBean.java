package model.post;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.HashMap;

import model.CommentBean;
import model.UserBean;



public class PostBean {
	
	public static class ComparatorByDate implements Comparator<PostBean>{
		@Override
		public int compare(PostBean p1, PostBean p2) {
			return p1.date.compareTo(p2.date) > 0 ? -1 : 1;
		}
	}
	
	public static class ComparatorByCoefficient implements Comparator<PostBean>{
		@Override
		public int compare(PostBean p1, PostBean p2) {
			return p1.generateCoefficient() - p2.generateCoefficient() > 0 ? -1 : 1;
		}
	}
	
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
		

		protected UserBean poster;
		protected String url;
		protected HashMap<Integer, CommentBean> comments;
		//commentsCounter is needed when user decide to delete comment, it is a criteria when clicking a button
		protected int commentsCounter = 0;
		
		
		public PostBean(UserBean poster, String url) {
			this.likes = 0;
			this.dislikes=0;
			this.date = LocalDateTime.now();
			this.poster = poster;
			this.url = url;
			this.comments = new HashMap<Integer, CommentBean>();
			//this.coefficient = 
		}
		
		public boolean isPhoto() {
			return false;
		}

		public int getLikes() {
			return likes;
		}

		public void setLikes(int likes) {
			this.likes = likes;
		}

		public int getDislikes() {
			return dislikes;
		}

		public void setDislikes(int dislikes) {
			this.dislikes = dislikes;
		}

		public LocalDateTime getDate() {
			return date;
		}

		public void setDate(LocalDateTime date) {
			this.date = date;
		}

		public UserBean getPoster() {
			return poster;
		}

		public void setPoster(UserBean poster) {
			this.poster = poster;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public HashMap<Integer, CommentBean> getComments() {
			return comments;
		}

		public void setComments(HashMap<Integer, CommentBean> comments) {
			this.comments = comments;
		}

		public int getCommentsCounter() {
			return commentsCounter;
		}

		public void setCommentsCounter(int commentsCounter) {
			this.commentsCounter = commentsCounter;
		}
		
		public int getCoefficient() {
			return coefficient;
		}
		
		public void setCoefficient(int coefficient) {
			this.coefficient = coefficient;
		}
		
		

}
