package model;

import java.util.HashSet;
import java.util.TreeSet;

import project.Server;
import project.content.Post;
import project.feed.Feed;
import project.user.User;
import project.user.notifications.Notification;

public class UserBean {
	
	private int id;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private HashSet<User> subscriptions; // Users that this is subscribed to (Used for feed)
	private HashSet<User> subscribers;  // Users that subscribed to this	(Used for notifications)
	private TreeSet<Post> posts;
	private Feed feed;
	private TreeSet<Notification> notifications;
	private TreeSet<Post> likedPosts;
	private TreeSet<Post> bookmarks;
	//server would proceed requests like login and register and would have info about all the users
	
	public UserBean(int id, String username, String password, String email) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	//================= Object methods =================\\
	
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserBean other = (UserBean) obj;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	
	//================= Getters and Setters =================\\
	
	

	public String getFirstName() {
		return firstName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public HashSet<User> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(HashSet<User> subscriptions) {
		this.subscriptions = subscriptions;
	}

	public HashSet<User> getSubscribers() {
		return subscribers;
	}

	public void setSubscribers(HashSet<User> subscribers) {
		this.subscribers = subscribers;
	}

	public TreeSet<Post> getPosts() {
		return posts;
	}

	public void setPosts(TreeSet<Post> posts) {
		this.posts = posts;
	}

	public Feed getFeed() {
		return feed;
	}

	public void setFeed(Feed feed) {
		this.feed = feed;
	}

	public TreeSet<Notification> getNotifications() {
		return notifications;
	}

	public void setNotifications(TreeSet<Notification> notifications) {
		this.notifications = notifications;
	}

	public TreeSet<Post> getLikedPosts() {
		return likedPosts;
	}

	public void setLikedPosts(TreeSet<Post> likedPosts) {
		this.likedPosts = likedPosts;
	}

	public TreeSet<Post> getBookmarks() {
		return bookmarks;
	}

	public void setBookmarks(TreeSet<Post> bookmarks) {
		this.bookmarks = bookmarks;
	}
}
