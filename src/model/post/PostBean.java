package model.post;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
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
		protected int uid;
		protected int likes;
		protected int dislikes; 
		protected LocalDateTime date;
		protected UserBean poster;
		protected String url;
		protected HashMap<Integer, CommentBean> comments;
		//commentsCounter is needed when user decide to delete comment, it is a criteria when clicking a button
		protected int commentsCounter = 0;
		public static int ID = 1;
		
		//????? comments sorting can be implemented with TreeSet with comparator by date or id?????
		protected TreeSet<CommentBean> commentsById;
		
		
		
		public PostBean(int id, int uid, int likes, int dislikes, LocalDateTime date, String url,
				HashMap<Integer, CommentBean> comments) {
			super();
			this.id = id;
			this.uid = uid;
			this.likes = likes;
			this.dislikes = dislikes;
			this.date = date;
			this.url = url;
			this.comments = comments;
		}

		public PostBean(UserBean poster, String url, int uid) {
			this.id=ID;
			ID++;
			this.uid = uid;
			this.likes = 0;
			this.dislikes=0;
			this.date = LocalDateTime.now();
			this.poster = poster;
			this.url = url;
			this.comments = new HashMap<Integer, CommentBean>();
			this.commentsById = new TreeSet<CommentBean>((c2,c1)->(c2.getPostTime().compareTo(c1.getPostTime())));
		}
		
		public void addComment(CommentBean comment) {
			this.comments.put(commentsCounter++, comment);
		}
		
		public void removeComment(int id) {
			this.comments.remove(id);
		}
		
		public void like() {
			likes++;
		}
		
		public void dislike() {
			dislikes--;
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
		
		public TreeSet<CommentBean> getCommentsById() {
			return commentsById;
		}
		
		public void setCommentsById(TreeSet<CommentBean> commentsById) {
			this.commentsById = commentsById;
		}
		

}
