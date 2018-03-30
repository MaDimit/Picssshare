package controller.manager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

import model.CommentBean;
import model.UserBean;
import model.dao.Dao;
import model.dao.Dao.Tables;
import model.post.PostBean;

public class CollectionsManager {
	
	private HashSet<UserBean> users;
	private HashSet<PostBean> posts;
	
	private HashMap<Integer, UserBean> usersByID;
	private HashMap<Integer, PostBean> postsByID;
	
	
	private static CollectionsManager instance;
	
	private CollectionsManager() {
		createCollections();
	}
	
	public static synchronized CollectionsManager getInstance() {
		if(instance == null) {
			instance = new CollectionsManager();
		}
		return instance;
	}
	
	private void createCollections() {
		//Creating users without collections
		this.users = getUsers();
		createUsersById();
		
		//Creating posts without comments
		this.posts = getPosts();
		createPostsById();
		
		//Creating comments and adding them to posts
		HashSet<CommentBean> comments = getComments();
		addCommentsToPosts(comments);
		
		//adding Posts to Users
		addPostsToUsers();
		addLikedPostsToUsers();
		addBookmarkedPostsToUsers();
		
		//TODO adding notifications to users
		
		//adding subscriptions to users
		addSubscriptionsToUsers();
	}
	
	private void createUsersById() {
		this.usersByID = new HashMap<>();
		for(UserBean u : this.users) {
			this.usersByID.put(u.getId(), u);
		}
	}
	
	private void createPostsById() {
		this.postsByID = new HashMap<>();
		for(PostBean p : this.posts) {
			this.postsByID.put(p.getId(), p);
		}
	}
	
	private void addCommentsToPosts(HashSet<CommentBean> comments) {
		for(CommentBean c : comments) {
			c.getBelongedPost().addComment(c);
		}
	}
	
	private void addPostsToUsers() {
		for(PostBean post : posts) {
			post.getPoster().addPost(post);
		}
	}
	
	private void addSubscriptionsToUsers() {
		try {
			ResultSet rs = Dao.getAll(Tables.SUBSCRIPTIONS);
			int subscriberID;
			int	subscribedID;
			while(rs.next()) {
				subscriberID = rs.getInt("subscriber_id");
				subscribedID = rs.getInt("subscribed_id");
				UserManager.getInstance()
				.subscribe(usersByID.get(subscriberID), usersByID.get(subscribedID));
			}
		}catch(SQLException e) {
			System.out.println("Problem during subscriptions adding: " + e.getMessage());
		}
	}
	
	private void addLikedPostsToUsers() {
		try {
			ResultSet rs = Dao.getAll(Tables.LIKED);
			int likerID;
			int postID;
			while(rs.next()) {
				likerID = rs.getInt("liker_id");
				postID = rs.getInt("likedpost_id");
				usersByID.get(likerID).addLikedPost(postsByID.get(postID));
			}
		}catch(SQLException e) {
			System.out.println("Problem during liked posts adding: " + e.getMessage());
		}
	}
	
	private void addBookmarkedPostsToUsers() {
		try {
			ResultSet rs = Dao.getAll(Tables.BOOKMARKS);
			int bookmarkerID;
			int postID;
			while(rs.next()) {
				bookmarkerID = rs.getInt("bookmarker_id");
				postID = rs.getInt("bookmarkedpost_id");
				usersByID.get(bookmarkerID).addBookmark(postsByID.get(postID));
			}
		}catch(SQLException e) {
			System.out.println("Problem during bookmarked posts adding: " + e.getMessage());
		}
	}
	
	private HashSet<UserBean> getUsers(){
		HashSet<UserBean> users = new HashSet<>();
		try {
			ResultSet rs = Dao.getAll(Tables.USERS);
			int id;
			String username;
			String password;
			String firstName;
			String lastName;
			String email;
			while (rs.next()) {
				// Retrieve by column name
				id = rs.getInt("id");
				username = rs.getString("username");
				password = rs.getString("password");
				firstName = rs.getString("firstName");
				lastName = rs.getString("lastName");
				email = rs.getString("email");
				UserBean user = new UserBean()
						.id(id)
						.username(username)
						.password(password)
						.firstName(firstName)
						.lastName(lastName)
						.email(email);
				//TODO add user collections;
				users.add(user);
			}
		}catch(SQLException e) {
			System.out.println("Problem during filling users collection: " + e.getMessage());
		}
		return users;
	}
	
	private HashSet<PostBean> getPosts(){
		HashSet<PostBean> posts = new HashSet<>();
		try {
			ResultSet rs = Dao.getAll(Tables.POSTS);
			int id;
			int likes;
			LocalDateTime date;
			int posterID;
			String url;
			while(rs.next()) {
				id = rs.getInt("id");
				likes = rs.getInt("likes");
				date = rs.getTimestamp("date").toLocalDateTime();
				posterID = rs.getInt("poster_id");
				url = rs.getString("url");
				PostBean post = new PostBean(id,usersByID.get(posterID),url,likes,date);
				posts.add(post);
			}
		}catch(SQLException e) {
			System.out.println("Problem during filling posts collection: " + e.getMessage());
		}
		return posts;
	}
	
	private HashSet<CommentBean> getComments(){
		HashSet<CommentBean> comments = new HashSet<>();
		try {
			ResultSet rs = Dao.getAll(Tables.COMMENTS);
			int id;
			int posterID;
			LocalDateTime postTime;
			String content;
			int postID;
			while(rs.next()) {
				id = rs.getInt("id");
				posterID = rs.getInt("comment_poster_id");
				postTime = rs.getTimestamp("postTime").toLocalDateTime();
				content = rs.getString("content");
				postID = rs.getInt("belonged_post_id");
				CommentBean comment = new CommentBean(id, usersByID.get(posterID),content,postTime,postsByID.get(postID));
				comments.add(comment);
			}
		}catch(SQLException e) {
			System.out.println("Problem during filling comments collection: " + e.getMessage());
		}
		return comments;
	}

	
}
