package project.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;

import project.Server;
import project.UserLogging;
import project.content.Post;
import project.feed.Feed;
import project.user.notifications.LikeNotification;
import project.user.notifications.Notification;
import project.user.notifications.SubscriptionNotification;

public class User {
		
	private int id;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private HashSet<User> subscriptions;
	private HashSet<User> subscribers;
	private TreeSet<Post> posts;
	private Feed feed;
	private TreeSet<Notification> notifications;
	private TreeSet<Post> likedPosts;
	private TreeSet<Post> bookmarks;
	//server would proceed requests like login and register and would have info about all the users
	private Server server;
	
	

	public User(String username, String password, String firstName, String lastName, String email) {
		this(username, password,email);
		this.firstName = firstName;
		this.lastName = lastName;
		//TODO: Feed part
	}
	
	public User(String username, String password, String email) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.subscriptions = new HashSet<>();
		this.subscribers = new HashSet<>();
		this.posts = new TreeSet<>(new Post.ComparatorByDate());
		this.likedPosts = new TreeSet<>(new Post.ComparatorByDate());
		this.bookmarks = new TreeSet<>(new Post.ComparatorByDate());
	}
	
	//=================FILL COLLECTIONS===============//
	public void subscribe(User u) {
		if(u!=null) {
			this.subscribers.add(u);
			u.subscriptions.add(this);
			this.addNotification(new SubscriptionNotification());
			System.out.println(u.getUsername() + " subscribed to " + this.getUsername());
		}
		else {
			System.out.println("Problem during subscriptio");
		}
	}
	
	
	
	public void addPost(Post p) {
		if(p!=null) {
			this.posts.add(p);
			System.out.println("Post added.");
		}
		else {
			System.out.println("Error with adding post.");
		}
	}
	
	public void addLiked(Post c) {
		if(c!=null) {
			this.likedPosts.add(c);
			c.getPoster().addNotification(new LikeNotification());
			System.out.println("Post added to liked.");
		}
		else {
			System.out.println("Problem with adding a post to liked ones.");
		}
	}
	
	public TreeSet<Post> getLikedPhotos() {
		return likedPosts;
	}
	
	public void addBookmark(Post c) {
		if(c!=null) {
			this.bookmarks.add(c);
			System.out.println("Bookmarked content.");
		}
		else {
			System.out.println("Error with bookmark.");
		}
	}
	
	public TreeSet<Post> getBookmarks(){
		return bookmarks;
	}
	
	//==================Notifications===============//
	
	public void addNotification(Notification n) {
		this.notifications.add(n);
	}
	
	//==================REGISTER/LOGIN===============//

	public void registerRequest() {
		UserLogging.register(this);
	}
	
	public void loginRequest() {
		if(UserLogging.login(this)) {
			System.out.println(this.username+" successfully logged.");
		}
		else {
			System.out.println("Login operation unsuccessfull.");
		}
	}
	
	//=====================GETTERS AND SETTERS===============//
	
	public String getUsername() {
		return username;
	}
	
	
	public String getPassword() {
		return password;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setServer(Server server) {
		this.server = server;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	//=====================================================//

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
		User other = (User) obj;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	
}
