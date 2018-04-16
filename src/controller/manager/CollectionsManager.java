package controller.manager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import model.CommentBean;
import model.UserBean;
import model.dao.Dao;
import model.dao.Dao.Tables;
import model.post.PostBean;

public class CollectionsManager {

	private HashSet<UserBean> users;
	private HashSet<PostBean> posts;

	private HashMap<Integer, UserBean> usersByID;
	private HashMap<String, UserBean> usersByUsername;
	private HashMap<String, UserBean> usersByEmail;
	private HashMap<Integer, PostBean> postsByID;

	private static CollectionsManager instance = new CollectionsManager();

	// CollectionsManager is singleton. Collections are loading during first
	// creation of CollectionManager instance.
	private CollectionsManager() {
		createCollections();
	}

	public static CollectionsManager getInstance() {
		return instance;
	}

	// ================ Creating runtime collections ================ //

	// Starting point for creating collections. Method calls should be in exact
	// order.

	// ============== Collections creation ============ //

	// Starting point. Method calls should be in exact order.
	private void createCollections() {
		try {
			// Creating users without collections
			this.users = getUsers();
			createUsersById();
			createUsersByUsername();
			createUsersByEmail();
			// Creating posts without comments
			this.posts = getPosts();
			createPostsById();

			// Creating comments and adding them to posts
			HashMap<Integer, CommentBean> comments = getComments();
			addCommentsToPosts(comments);

			// adding Posts to Users
			addPostsToUsers();
			addLikedPostsToUsers();
			addBookmarkedPostsToUsers();

			// TODO adding notifications to users

			// adding subscriptions to users
			addSubscriptionsToUsers();
		} catch (Exception e) {
			throw new Error("Problem during filling runtime collection. App execution stopped", e);
		}
	}

	private void createUsersById() {
		this.usersByID = new HashMap<>();
		for (UserBean u : this.users) {
			this.usersByID.put(u.getId(), u);
		}
	}

	private void createUsersByEmail() {
		this.usersByEmail = new HashMap<>();
		for (UserBean u : this.users) {
			this.usersByEmail.put(u.getEmail(), u);
		}
	}

	private void createUsersByUsername() {
		this.usersByUsername = new HashMap<>();
		for (UserBean u : this.users) {
			this.usersByUsername.put(u.getUsername(), u);
		}
	}

	private void createPostsById() {
		this.postsByID = new HashMap<>();
		for (PostBean p : this.posts) {
			this.postsByID.put(p.getId(), p);
		}
	}

	private void addCommentsToPosts(HashMap<Integer, CommentBean> comments) {
		// for (CommentBean c : comments) {
		// c.getBelongedPost().addComment(c);
		// }
		for (Map.Entry<Integer, CommentBean> entry : comments.entrySet()) {
			entry.getValue().getBelongedPost().addComment(entry.getValue());
		}
	}

	private void addPostsToUsers() {
		for (PostBean post : posts) {
			post.getPoster().addPost(post);
		}
	}

	private void addSubscriptionsToUsers() throws SQLException {
		ResultSet rs = Dao.getAll(Tables.SUBSCRIPTIONS);
		int subscriberID;
		int subscribedtoID;
		while (rs.next()) {
			subscriberID = rs.getInt("subscriber_id");
			subscribedtoID = rs.getInt("subscribedto_id");
			UserBean subscriber = usersByID.get(subscriberID);
			UserBean subscribedto = usersByID.get(subscribedtoID);
			subscriber.addSubscribtion(subscribedto);
			subscribedto.addSubscriber(subscriber);
		}

	}

	private void addLikedPostsToUsers() throws SQLException {
		ResultSet rs = Dao.getAll(Tables.LIKED);
		int likerID;
		int postID;
		while (rs.next()) {
			likerID = rs.getInt("liker_id");
			postID = rs.getInt("likedpost_id");
			usersByID.get(likerID).addLikedPost(postsByID.get(postID));
		}

	}

	private void addBookmarkedPostsToUsers() throws SQLException {

		ResultSet rs = Dao.getAll(Tables.BOOKMARKS);
		int bookmarkerID;
		int postID;
		while (rs.next()) {
			bookmarkerID = rs.getInt("bookmarker_id");
			postID = rs.getInt("bookmarkedpost_id");
			usersByID.get(bookmarkerID).addBookmark(postsByID.get(postID));
		}
	}

	// search user method
	public List<UserBean> searchUser(String user) {
		ArrayList<UserBean> users = new ArrayList<>();
		for (Map.Entry<String, UserBean> u : this.usersByUsername.entrySet()) {
			if (u.getKey().toLowerCase().contains(user.toLowerCase())) {
				users.add(u.getValue());
			}
		}
		return users;
	}

	private HashSet<UserBean> getUsers() throws SQLException {
		HashSet<UserBean> users = new HashSet<>();
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
			firstName = rs.getString("first_name");
			lastName = rs.getString("last_name");
			email = rs.getString("email");
			UserBean user = new UserBean().id(id).username(username).password(password).firstName(firstName)
					.lastName(lastName).email(email);

			users.add(user);
		}

		return users;
	}

	private HashSet<PostBean> getPosts() throws SQLException {
		HashSet<PostBean> posts = new HashSet<>();

		ResultSet rs = Dao.getAll(Tables.POSTS);
		int id;
		int likes;
		LocalDateTime date;
		int posterID;
		String url;
		while (rs.next()) {
			id = rs.getInt("id");
			likes = rs.getInt("likes");
			date = rs.getTimestamp("date").toLocalDateTime();
			posterID = rs.getInt("poster_id");
			url = rs.getString("url");
			PostBean post = new PostBean(id, usersByID.get(posterID), url, likes, date);
			posts.add(post);
		}
		return posts;
	}

	private HashMap<Integer, CommentBean> getComments() throws SQLException {
		HashSet<CommentBean> comments = new HashSet<>();
		HashMap<Integer, CommentBean> commentsById = new HashMap<>();
		ResultSet rs = Dao.getAll(Tables.COMMENTS);
		int id;
		int posterID;
		LocalDateTime postTime;
		String content;
		int postID;
		while (rs.next()) {
			id = rs.getInt("id");
			posterID = rs.getInt("poster_id");
			postTime = rs.getTimestamp("date").toLocalDateTime();
			content = rs.getString("content");
			postID = rs.getInt("post_id");
			CommentBean comment = new CommentBean(id, usersByID.get(posterID), content, postTime,
					postsByID.get(postID));
			// comments.add(comment);
			commentsById.put(id, comment);
			// this.postsByID.get(postID).addComment(comment);
		}

		return commentsById;
	}

	public HashMap<Integer, UserBean> getUsersByID() {
		return usersByID;
	}

	public HashMap<Integer, PostBean> getPostsByID() {
		return postsByID;
	}


	// Reloading all collections from DB. May be slow, use carefully.
	public void updateCollections() {
		createCollections();
	}

	public void addUser(UserBean user) {
		this.users.add(user);
		this.usersByID.put(user.getId(), user);
		this.usersByUsername.put(user.getUsername(), user);
	}

	public void addPost(PostBean post) {
		this.posts.add(post);
		this.postsByID.put(post.getId(), post);
	}

	public void removeUser(int id) {
		removeUser(usersByID.get(id));
	}

	public void removeUser(String username) {
		removeUser(usersByUsername.get(username));
	}

	public void removeUser(UserBean user) {
		this.users.remove(user);
		this.usersByID.remove(user.getId());
		this.usersByUsername.remove(user.getUsername());
	}

	public void removePost(int id) {
		removePost(postsByID.get(id));
	}

	public void removePost(PostBean post) {
		this.posts.remove(post);
		this.postsByID.remove(post.getId());
	}

	public UserBean getUser(int userID) {
		return usersByID.get(userID);
	}

	public UserBean getUser(String username) {
		return usersByUsername.get(username);
	}

	public PostBean getPost(int postID) {
		return postsByID.get(postID);
	}

	public HashSet<UserBean> getAllUsers() {
		return this.users;
	}

	public HashSet<PostBean> getAllPosts() {
		return this.posts;
	}

	public boolean alreadyExists(String username) {
		return usersByUsername.containsKey(username);
	}

	public boolean alreadyExistsEmail(String email) {
		return this.usersByEmail.containsKey(email);
	}
}
