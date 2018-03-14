package demo;

import java.util.Random;

import project.Server;
import project.UserLogging;
import project.content.PhotoPost;
import project.content.Post;
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
		user.setServer(server);
		user1.setServer(server);
		user2.setServer(server);
		user3.setServer(server);
		
		user.registerRequest();
		user1.registerRequest();
		user2.registerRequest();
		user3.registerRequest();
		
		server.showUsers();
		
		user.loginRequest();
		user1.loginRequest();
		
		System.out.println("tigra logged " + (UserLogging.login("tigra","TigaraBe3?") ? "succsefully" : "unseccessfully"));
		System.out.println("tigra logged " + (UserLogging.login("tigra","qwerty") ? "succsefully" : "unseccessfully"));
		System.out.println("tigra logged " + (UserLogging.login("tigran","TigaraBe3?") ? "succsefully" : "unseccessfully"));


		user1.loginRequest(); 
		System.out.println("========================");
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
	}

}
