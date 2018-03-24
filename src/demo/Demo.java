package demo;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.TreeSet;

import model.UserBean;
import project.Server;
import project.UserLogging;
import project.content.PhotoPost;
import project.content.Post;
import project.feed.Feed;
import project.feed.Feed.Type;
import project.feed.MainFeed;

public class Demo {
	
	public static int getRandomNumber(int range, int startPoint) {
		Random r = new Random();
		return r.nextInt(range)+startPoint;
	}
	
	public static void main(String[] args){
		Server server = Server.getInstance();
		UserBean user = new UserBean("amatrixable", "YesssssBe5!", "Philip", "Kasapov", "amatrixable@gmail.com");
		UserBean user1 = new UserBean("distmist", "Heyhey123", "Slovan", "Kaspeev", "slovko@gmail.com");
		UserBean user2 = new UserBean("peshoO91", "toughpassword123", "Petyr", "Petrov", "peshko@gmail.com");
		UserBean user3 = new UserBean("tigra", "TigaraBe3?", "Tisho", "Petrov", "tishoooooo@gmail.com");
		UserBean user4 = new UserBean("amatrixable", "qWerty123","Name", "Surname", "Email@email.com");
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
		user.subscribeTo(user1);
		user.subscribeTo(user3);
		user1.showSubscribers();
		System.out.println(user1.getUsername() + " notifications ----->");
		user1.showNotifications();

		System.out.println();
		System.out.println();
		System.out.println("===FEED SHOWING===");
		//user has been subscribed to user1 and user3 / each have posted 1 post / result: displayed 2 posts
		System.out.println();
		System.out.println();
		user.getFeed(Type.MAIN_FEED).displayPostsInfo();
		
		try {
			feedTest();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private static void feedTest() throws InterruptedException {
		UserBean user1 = new UserBean("user1","password123","email@email.com");
		UserBean user2 = new UserBean("user2","notpassword321","email2@email.com");
		user1.setServer(Server.getInstance());
		user2.setServer(Server.getInstance());
		
		user1.subscribeTo(user2);
		user2.subscribeTo(user1);
		
		Post post1 = new PhotoPost(user1, "some photo url1");
		Thread.sleep(1000);
		Post post2 = new PhotoPost(user1, "some photo url2");
		Thread.sleep(1000);
		Post post3 = new PhotoPost(user1, "some photo url3");
		Thread.sleep(1000);
		
		post1.addLike();
		post1.addLike();
		post1.addLike();
		post1.addLike();
		post2.addLike();
		
		post1.addComment(user2, "comment1");
		post1.addComment(user2, "comment2");
		post1.addComment(user2, "comment3");
		post1.addComment(user2, "comment4");
		post2.addComment(user2, "comment");
		
		user1.addPost(post1);
		user1.addPost(post2);
		user1.addPost(post3);
		
		user2.getFeed(Type.MAIN_FEED).displayPostsInfo();
		
		System.out.println("\n\nTrending posts: \n");
		user2.getFeed(Type.TRENDING_FEED).displayPostsInfo();

	}

}
