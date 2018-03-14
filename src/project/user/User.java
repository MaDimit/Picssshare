package project.user;

import java.util.ArrayList;

import project.Server;
import project.UserLogging;
import project.content.Post;
import project.feed.Feed;

public class User {
		
	private int id;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	//set for unique in subscriptions
	private ArrayList<User> subscriptions;
	
	//subscribers(Observer pattern)
	private ArrayList<Post> posts;
	private Feed feed;
	
	private ArrayList<Post> likedPhotos;
	private ArrayList<Post> bookmarks;
	//server would proceed requests like login and register and would have info about all the users
	private Server server;
	
	

	public User(String username, String password, String firstName, String lastName, String email) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.subscriptions = new ArrayList<User>();
		this.posts = new ArrayList<Post>();
		this.likedPhotos = new ArrayList<Post>();
		this.bookmarks = new ArrayList<Post>();
		//TODO: Feed part
	}
	
	//=================FILL COLLECTIONS===============//
	public void addSubscription(User u) {
		if(u!=null) {
			//notify user about being subscribed
			this.subscriptions.add(u);
			System.out.println("Subscribed to "+u.getUsername());
		}
		else {
			System.out.println("Problem during subscription to user.");
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
	
	public void addLikedPhoto(Post c) {
		if(c.isPhoto() && c!=null) {
			//TODO check if not already in liked
			//notify liked photo user about like
			this.likedPhotos.add(c);
			System.out.println("Photo added to liked.");
		}
		else {
			System.out.println("Problem with adding a photo to liked ones.");
		}
	}
	
	public void addBookmark(Post c) {
		if(c!=null) {
			//TODO check if not already in liked
			this.bookmarks.add(c);
			System.out.println("Bookmarked content.");
		}
		else {
			System.out.println("Error with bookmark.");
		}
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
