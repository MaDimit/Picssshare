package model.post;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeMap;
import java.util.TreeSet;

import controller.manager.PostManager;
import model.CommentBean;
import model.UserBean;



public class PostBean {
	
	public enum Type{ PHOTO_POST, VIDEO_POST }
	
	public static class ComparatorByDate implements Comparator<PostBean>{
		@Override
		public int compare(PostBean p1, PostBean p2) {
			return p1.date.compareTo(p2.date) > 0 ? -1 : 1;
		}
	}
	
	public static class ComparatorByCoefficient implements Comparator<PostBean>{
		@Override
		public int compare(PostBean p1, PostBean p2) {
			return PostManager.getInstance().generateCoefficient(p1) - PostManager.getInstance().generateCoefficient(p1) > 0 ? -1 : 1;
		}
	}
		protected int id;
		protected int likes; 
		protected LocalDateTime date;
		protected UserBean poster;
		protected String url;
		
		//????? comments sorting can be implemented with TreeSet with comparator by date or id?????
		//protected TreeSet<CommentBean> comments;
		protected TreeMap<Integer, CommentBean> commentsById;
		
		//Constructor for adding new post
		public PostBean(UserBean poster, String url) {
			this.poster = poster;
			this.url = url;
			this.likes = 0;
			this.date = LocalDateTime.now();
		//	this.comments = new TreeSet<>(new CommentBean.ComparatorByDate());
			this.commentsById = new TreeMap<>();
		}
		
		//Constructor for creating post from DB
		public PostBean(int id, UserBean poster, String url, int likes, LocalDateTime date) {
			this(poster, url);
			this.likes = likes;
			this.id = id;
			this.date = date;
		}
		
		public void addComment(CommentBean comment) {
			this.commentsById.put(comment.getId(), comment);
		}
		
//		public void removeComment(CommentBean comment) {
//			this.comments.remove(comment);
//		}
		
		public void removeCommentByID(int id) {
			this.commentsById.remove(id);
		}
		
		public void like() {
			likes++;
		}
		
		public void dislike() {
			likes--;
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
		
		public void setId(int id) {
			this.id = id;
		}


		public LocalDateTime getDate() {
			return date;
		}

		public void setDate(LocalDateTime date) {
			this.date = date;
		}
		
		public int getId() {
			return id;
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
		
		public TreeMap<Integer, CommentBean> getCommentsById() {
			return commentsById;
		}


		

}
