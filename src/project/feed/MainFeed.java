package project.feed;

import java.util.TreeSet;

import project.content.Post;
import project.user.User;

public class MainFeed extends Feed {
	
	private User user;
	
	public MainFeed(User user) {
		this.user = user;
		System.out.println("\n\nCoeficients(generateCoefficient() method in Post): \n");
		generateFeed();
	}
	
	protected void generateFeed(){
		for(User u: this.user.getSubscriptions()) {
			for(Post p: u.getPosts()) {
				this.posts.add(p);
			}
		}
	}
	

}
