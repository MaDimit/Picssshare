package demo;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.TreeSet;

import project.Server;
import project.UserLogging;
import project.content.PhotoPost;
import project.content.Post;
import project.feed.Feed;
import project.feed.MainFeed;
import project.user.User;

public class Demo {
	
	public static int getRandomNumber(int range, int startPoint) {
		Random r = new Random();
		return r.nextInt(range)+startPoint;
	}
	
	public static void main(String[] args) {
		Server server = new Server();
		User user = new User("amatrixable", "YesssssBe5!", "Philip", "Kasapov", "amatrixable@gmail.com");
		User user1 = new User("distmist", "Heyhey123", "Slovan", "Kaspeev", "slovko@gmail.com");
		User user2 = new User("peshoO91", "toughpassword123", "Petyr", "Petrov", "peshko@gmail.com");
		User user3 = new User("tigra", "TigaraBe3?", "Tisho", "Petrov", "tishoooooo@gmail.com");
		User user4 = new User("amatrixable", "qWerty123","Name", "Surname", "Email@email.com");
		user.setServer(server);
		user1.setServer(server);
		user2.setServer(server);
		user3.setServer(server);
		
		user.registerRequest();
		user1.registerRequest();
		user2.registerRequest();
		user3.registerRequest();
		user4.registerRequest();
		
		server.showUsers();
		
		user.loginRequest();
		user1.loginRequest();
		//adding post and show that deleteComment function works
		Post post = new PhotoPost(user, "C:\\Users\\Philip\\Desktop\\saf.ttxt");
		post.addLike();
		post.addLike();
		post.addLike();
		post.addComment(user3, "MNOO QK");
		post.addComment(user2, "CHESTNO");
		post.addComment(user1, "TAKA E");
		post.showInfo();
		post.deleteComment(1);
		post.showInfo();
		user.addPost(post);
		
		//add 2 more posts which are posted from different user
		Post post1 = new PhotoPost(user3, "daf.ttxt");
		post1.addLike();
		post1.addLike();
		post1.addLike();
		post1.addLike();
		post1.addLike();
		post1.addDislike();
		post1.addDislike();
		post1.addComment(user3, "buu");
		post1.addComment(user2, "bee");
		post1.addComment(user1, "eee");
		user1.addPost(post1);
		Post post3 = new PhotoPost(user1, "uaf.ttxt");
		post3.addLike();
		post3.addLike();
		post3.addLike();
		post3.addLike();
		post3.addDislike();
		post3.addComment(user3, "te");
		post3.addComment(user2, "sa");
		post3.addComment(user1, "te");
		user3.addPost(post3);
		user1.addBookmark(post);
		user1.addLiked(post);
		
		//showNotifications && showSubscribers test
		System.out.println(user.getUsername() + " notifications ------>");
		user.showNotifications();
		user.subscribe(user1);
		user.subscribe(user3);
		user1.showSubscribers();
		System.out.println(user1.getUsername() + " notifications ----->");
		user1.showNotifications();

		System.out.println();
		System.out.println();
		System.out.println("===FEED SHOWING===");
		//user has been subscribed to user1 and user3 / each have posted 1 post / result: displayed 2 posts
		System.out.println();
		System.out.println();
		user.getFeed().displayPostsInfo();
	}

}
