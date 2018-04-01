package demo;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.TreeSet;

import controller.manager.CollectionsManager;
import controller.manager.CommentManager;
import controller.manager.FeedManager;
import controller.manager.LoggingManager;
import controller.manager.PostManager;
import controller.manager.UserManager;
import model.CommentBean;
import model.UserBean;
import model.dao.PostDao;
import model.dao.UserDao;
import model.post.PostBean;

public class Demo {

	public static int getRandomNumber(int range, int startPoint) {
		Random r = new Random();
		return r.nextInt(range) + startPoint;
	}

	public static void main(String[] args) throws Exception {
		demo();
	}
	
	public static void demo() {
		UserManager um = UserManager.getInstance();
		PostManager pm = PostManager.getInstance();
		CommentManager cm = CommentManager.getInstance();
		LoggingManager lm = LoggingManager.getInstance();
		CollectionsManager colm = CollectionsManager.getInstance();
		
		//Registration
//		lm.register("Maxim", "pass1234QQ", "myEmail@gmail.com");
//		lm.register("Phillip", "QWErty123", "hisEmail@gmail.com");
//		lm.register("John", "pass1234TT", "someEmail@gmail.com");
//		lm.register("Peter", "pass2321WW", "email@gmail.com");
		// -------------- successfull! ------------ //
		
		//Loggging
		UserBean u1 = lm.login("Maxim", "pass1234QQ");
		UserBean u2 = lm.login("Phillip", "Pass112233");
		UserBean u3 = lm.login("John", "pass1234TT");
		UserBean u4 = lm.login("Peter", "pass2321WW");
		// ------------ successfull! --------------//
		
		//Adding posts
//		pm.addPost(u1, "url of post 1");
//		pm.addPost(u2, "url of post 2");
//		pm.addPost(u3, "url of post 3");
//		pm.addPost(u4, "url of post 4");
//		pm.addPost(u2, "url of post 5");
//		pm.addPost(u3, "url of post 6");
//		pm.addPost(u2, "url of post 7");
//		pm.addPost(u1, "url of post 8");
//		pm.addPost(u1, "url of post 9");
//		pm.addPost(u2, "url of post 10");
//		pm.addPost(u3, "url of post 11");
//		pm.addPost(u4, "url of post 12");
		// ------------ successfull --------------//
		
		//Delete post
//		pm.deletePost(3);
		//-------------successfull---------------//
		
		//Adding like to post
//		pm.addLike(u2, colm.getPostsByID().get(7));
//		pm.addLike(u1, colm.getPostsByID().get(7));
//		pm.addLike(u2, colm.getPostsByID().get(7));
//		pm.addLike(u3, colm.getPostsByID().get(8));
//		pm.addLike(u3, colm.getPostsByID().get(11));
//		pm.addLike(u4, colm.getPostsByID().get(12));
//		pm.addLike(u1, colm.getPostsByID().get(20));
//		pm.addLike(u4, colm.getPostsByID().get(22));
//		pm.addLike(u2, colm.getPostsByID().get(7));
//		
		
		//----------successfull-----------------//
		
		//Addig dislike to post
//		pm.addDislike(colm.getPostsByID().get(2));
		
		//-------------successfull-------------//
		
		
		//Subscription
//		um.subscribe(u2, u3); 
//		um.subscribe(u2, u4); 
//		System.out.println(u1.getSubscriptions()); // Checking if collection manager successfully added subscriptions
		// ------------ successfull --------------//
		
		//Adding comments to post
//		cm.createComment("Comment 1!", u2, colm.getPost(1));
//
//		cm.createComment("Comment 2!", u3, colm.getPost(1));
//
//		cm.createComment("Comment 3!", u4, colm.getPost(1));
//		cm.createComment("Comment 4!", u1, colm.getPost(2));
//		cm.createComment("Comment 5!", u2, colm.getPost(2));
//		cm.createComment("Comment 6!", u3, colm.getPost(2));
//		cm.createComment("Comment 7!", u4, colm.getPost(2));
		
		//-------------successfull-------------//

		//Delete comment
		//parameters: postID, commentID
//		cm.deleteCommentById(4,11);
		
		//---------successfull---------///
		
		
		//UPDATE PROFILE INFO
		
		//fill the fileds with new values, the old ones stay with null
//		UserManager.getInstance().updateProfileInfo(u2, null, "Filip", "Kasapov", null);
		//-------------successfull ----------------//
		
//		FeedManager f = new FeedManager();
//		f.displayPostsInfo(f.generateMainFeed(u2));
		
		//-------------successfull-------------//
		
	}
}
