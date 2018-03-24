package model;

import java.util.HashSet;
import java.util.TreeSet;

import javax.management.Notification;


import controller.manager.PostManager;
import model.feed.FeedBean;
import model.notification.NotificationBean;
import model.notification.SubscriptionNotificationBean;
import model.post.PostBean;

public class UserBean {
	
	private int id;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private HashSet<UserBean> subscriptions; // Users that this is subscribed to (Used for feed)
	private HashSet<UserBean> subscribers;  // Users that subscribed to this	(Used for notifications)
	private TreeSet<PostBean> posts;
	private FeedBean feed;
	private TreeSet<NotificationBean> notifications;
	private TreeSet<PostBean> likedPosts;
	private TreeSet<PostBean> bookmarks;
	
	// ????? Later surely will be replaced with collections from DB, do we need this ?????
	// Adding default collections
	public UserBean() {
		this.subscriptions = new HashSet<>();
		this.subscribers = new HashSet<>();
		this.posts = new TreeSet<>(new PostBean.ComparatorByDate());
		this.notifications = new TreeSet<>(new NotificationBean.ComparatorByDate());
		this.likedPosts = new TreeSet<>(new PostBean.ComparatorByDate());
		this.bookmarks = new TreeSet<>(new PostBean.ComparatorByDate());
		
	}
	
	public UserBean(int id, String username, String password, String email) {
		this();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}

	//================= Builder =================\\
	
	public UserBean id(int id) {
		this.id = id;
		return this;
	}
	
	public UserBean username(String username) {
		this.username = username;
		return this;
	}
	
	public UserBean password(String password) {
		this.password = password;
		return this;
	}
	
	public UserBean firstName(String firstName) {
		this.firstName = firstName;
		return this;
	}
	
	public UserBean lastName(String lastName) {
		this.lastName = lastName;
		return this;
	}
	
	public UserBean email(String email) {
		this.email = email;
		return this;
	}
	
	public UserBean subscriptions(HashSet<UserBean> subscriptions) {
		this.subscriptions = subscriptions;
		return this;
	}
	
	public UserBean subscribers(HashSet<UserBean> subscribers) {
		this.subscribers = subscribers;
		return this;
	}
	
	public UserBean posts(TreeSet<PostBean> posts) {
		this.posts = posts;
		return this;
	}
	
	public UserBean feed(FeedBean feed) {
		this.feed = feed;
		return this;
	}
	
	public UserBean notifications(TreeSet<NotificationBean> notifications) {
		this.notifications = notifications;
		return this;
	}
	
	public UserBean likedPosts(TreeSet<PostBean> likedPosts) {
		this.likedPosts = likedPosts;
		return this;
	}
	
	public UserBean bookmarks(TreeSet<PostBean> bookmarks) {
		this.bookmarks = bookmarks;
		return this;
	}
	
	//================= Adding to collections =================\\
	
	/*
	 * more readable than using getters for adding to collections.
	 * --- Example with getters from UserManager : "subscribedTo.getNotifications().add((new SubscriptionNotificationBean(subscribedTo)));"
	 * --- Example with add methods : "user.addNotification(new Notification())"
	 * validation will be done in UserManager
	 */
	
	public void addSubscribtion(UserBean user) {
		this.subscriptions.add(user);
	}
	
	public void addSubscriber(UserBean user) {
		this.subscribers.add(user);
	}
	
	public void addPost(PostBean post) {
		this.posts.add(post);
	}
	
	public void addLikedPost(PostBean post) {
		this.likedPosts.add(post);
	}
	
	public void addBookmark(PostBean post) {
		this.bookmarks.add(post);
	}
	
	public void addNotification(NotificationBean notificaton) {
		this.notifications.add(notificaton);
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
	
	/*
	 * Later unused getters and setters should be removed
	 */
	
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

	public HashSet<UserBean> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(HashSet<UserBean> subscriptions) {
		this.subscriptions = subscriptions;
	}

	public HashSet<UserBean> getSubscribers() {
		return subscribers;
	}

	public void setSubscribers(HashSet<UserBean> subscribers) {
		this.subscribers = subscribers;
	}

	public TreeSet<PostBean> getPosts() {
		return posts;
	}

	public void setPosts(TreeSet<PostBean> posts) {
		this.posts = posts;
	}

	public FeedBean getFeed() {
		return feed;
	}

	public void setFeed(FeedBean feed) {
		this.feed = feed;
	}

	public TreeSet<NotificationBean> getNotifications() {
		return notifications;
	}

	public void setNotifications(TreeSet<NotificationBean> notifications) {
		this.notifications = notifications;
	}

	public TreeSet<PostBean> getLikedPosts() {
		return likedPosts;
	}

	public void setLikedPosts(TreeSet<PostBean> likedPosts) {
		this.likedPosts = likedPosts;
	}

	public TreeSet<PostBean> getBookmarks() {
		return bookmarks;
	}

	public void setBookmarks(TreeSet<PostBean> bookmarks) {
		this.bookmarks = bookmarks;
	}
}
