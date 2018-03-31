package demo;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.TreeSet;

import controller.manager.CollectionsManager;
import controller.manager.CommentManager;
import controller.manager.LoggingManager;
import controller.manager.PostManager;
import controller.manager.UserManager;
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
		// firstDemo();
		//secondDemo();
		
		thirdDemo();
	}
	
	public static void thirdDemo() {
		UserManager um = UserManager.getInstance();
		PostManager pm = PostManager.getInstance();
		CommentManager cm = CommentManager.getInstance();
		LoggingManager lm = LoggingManager.getInstance();
		CollectionsManager colm = CollectionsManager.getInstance();
		
		//Registration
		//lm.register("Maxim", "pass1234QQ", "myEmail@gmail.com");
		//lm.register("Phillip", "QWErty123", "hisEmail@gmail.com");
		// -------------- successfull! ------------ //
		
		//Loggging
		UserBean u1 = lm.login("Maxim", "pass1234QQ");
		UserBean u2 = lm.login("Phillip", "QWErty123");
		// ------------ successfull! --------------//
		
		//Adding posts
		//pm.addPost(u1, "url of post 1");
		//pm.addPost(u2, "url of post 2");
		// ------------ successfull --------------//
		
		//Subscription
		//um.subscribe(u1, u2); // adding to DB
		//System.out.println(u1.getSubscriptions()); // Checking if collection manager successfully added subscriptions
		// ------------ successfull --------------//
		
		//Adding comments to post
		cm.createComment("Comment text!", u2, colm.getPost(2));
		// Cannot add or update a child row: a foreign key constraint fails (`Picssshare`.`comments`, CONSTRAINT `belonged_post_id` FOREIGN KEY (`post_id`) REFERENCES `posts` (`id`)
	}

	private static void secondDemo() {
		// Clear collection before using
		// register();
		// UserDao.getInstance().printCollectionInfo();

		// Login testing
		 login();

		// Subscription testing
		// subscribe();

		// Post adding
		// addPosts();

		// Post deleting
		// deletePost();

		// Login testing
		// login();

		// Subscription testing
		// subscribe();

		//likeTest();

	}

	private static void register() {
		UserManager userManager = UserManager.getInstance();
		LoggingManager loggingManager = LoggingManager.getInstance();

		// Registering users
		loggingManager.register("Maxim", "Da123456", "email@email.com");
		loggingManager.register("Philip", "Ne112233", "emailhi1@email.com");

	}

	private static void likeTest() {
		UserManager um = UserManager.getInstance();
		LoggingManager lm = LoggingManager.getInstance();

		UserBean liker = lm.login("Maxim", "Da123456");
		UserBean liker2 = lm.login("Philip", "Ne112233");
		//
		PostBean p = new PostBean(liker, "asdas");
		PostBean p2 = new PostBean(liker2, "das");
		PostBean p3 = new PostBean(liker, "asqerdas");
		try {
			PostDao.getInstance().addPost(p);
			PostDao.getInstance().addPost(p2);
			PostDao.getInstance().addPost(p3);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		UserManager.getInstance().like(liker, p2);

	}

	private static void login() {
		LoggingManager lm = LoggingManager.getInstance();

		// Correct login
		UserBean user1 = lm.login("Maxim", "Da123456");
		System.out.println(user1);
		UserBean user2 = lm.login("Philip", "Ne112233");
		System.out.println(user2);

		// Wrong password login
		UserBean user4 = lm.login("Maxim", "qwerty");
		System.out.println(user4);

		// Wrong username login
		UserBean user3 = lm.login("Anonymos", "passs");
		System.out.println(user3);
	}

	private static void subscribe() {
		UserManager um = UserManager.getInstance();
		LoggingManager lm = LoggingManager.getInstance();

		UserBean subscribedTo = lm.login("Maxim", "Da123456");
		UserBean subscriber = lm.login("Philip", "Ne112233");

		um.subscribe(subscriber, subscribedTo);

		// UserBean oneMoreUser = lm.login("Chocho1", "4343jh24aS");

		um.subscribe(subscribedTo, subscriber);

	}

	private static void addPosts() {
		LoggingManager lm = LoggingManager.getInstance();
		PostManager pm = PostManager.getInstance();

		UserBean user1 = lm.login("Maxim", "Da123456");
		UserBean user2 = lm.login("Philip", "Ne112233");

		pm.addPost(user1, "some photo url");
		pm.addPost(user2, "url of post");

	}

	public static void deletePost() {
		LoggingManager lm = LoggingManager.getInstance();
		PostManager pm = PostManager.getInstance();

		UserBean user1 = lm.login("Maxim", "Da123456");
		UserBean user2 = lm.login("Philip", "Ne112233");
		pm.deletePost(1);
	}

	private static void firstDemo() throws Exception {
		// everytime the server starts the collections must be filled up with the data
		// from users table
		//UserDao.getInstance().fillCollectionWithUsers();
		/////////////////////////////////////////
		// testing registering of existing user
		// UserBean user5 = new UserBean("filippp", "FilippKasss992",
		// "asdddd@gmail.com");
		// user5.setFirstName("Filip");
		// user5.setLastName("Kasapov");
		// LoggingManager.getInstance().register(user5);
		//
		// UserBean user6 = new UserBean("filipppp", "FilippKasss992",
		// "asdddd@gmail.com");
		// user6.setFirstName("Filip");
		// user6.setLastName("Kasapov");
		// LoggingManager.getInstance().register(user6);
		///////////////////////////////////////////
		// testing subscribing
		UserBean user = UserDao.getInstance().getUsers().get("filippp");
		UserBean user2 = UserDao.getInstance().getUsers().get("filipppp");

		// UserManager.getInstance().subscribe(user, user2);
		////////////////////////////////////////////////
		// testing login
		System.out.println(LoggingManager.getInstance().login("filippp", "FilippKasss992"));
		/////////////////////////////////////////////
		// testing posting
		//PostBean post = new PostBean(user2, "url", 1);
		//	UserManager.getInstance().addPost(user2, post);
		// PostBean post2 = new PostBean(user2, "url2", 1);
		// UserManager.getInstance().addPost(user2, post2);
		PostBean post2 = PostDao.getInstance().getPosts().get(1);
		// PostManager.getInstance().addLike(post);
		//
		// PostManager.getInstance().addLike(post);
		//
		// PostManager.getInstance().addDislike(post);

		// CommentManager.getInstance().createComment("KO MEN TAR", user2, post2);
		/////////////////////////////////////////////

		// Philip: Done testing and adding here...continue with the below

		// post.showInfo();
		// post.deleteComment(1);
		// post.showInfo();
		// user.addPost(post);
		//
		//
		// //showNotifications && showSubscribers test
		// System.out.println(user.getUsername() + " notifications ------>");
		// user.showNotifications();
		// user.subscribeTo(user1);
		// user.subscribeTo(user3);
		// user1.showSubscribers();
		// System.out.println(user1.getUsername() + " notifications ----->");
		// user1.showNotifications();
		//
		// System.out.println();
		// System.out.println();
		// System.out.println("===FEED SHOWING===");
		// //user has been subscribed to user1 and user3 / each have posted 1 post /
		// result: displayed 2 posts
		// System.out.println();
		// System.out.println();
		// user.getFeed(Type.MAIN_FEED).displayPostsInfo();
		//
		// try {
		// feedTest();
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		// }
		//
		// private static void feedTest() throws InterruptedException {
		// UserBean user1 = new UserBean("user1","password123","email@email.com");
		// UserBean user2 = new UserBean("user2","notpassword321","email2@email.com");
		// user1.setServer(Server.getInstance());
		// user2.setServer(Server.getInstance());
		//
		// user1.subscribeTo(user2);
		// user2.subscribeTo(user1);
		//
		// Post post1 = new PhotoPost(user1, "some photo url1");
		// Thread.sleep(1000);
		// Post post2 = new PhotoPost(user1, "some photo url2");
		// Thread.sleep(1000);
		// Post post3 = new PhotoPost(user1, "some photo url3");
		// Thread.sleep(1000);
		//
		// post1.addLike();
		// post1.addLike();
		// post1.addLike();
		// post1.addLike();
		// post2.addLike();
		//
		// post1.addComment(user2, "comment1");
		// post1.addComment(user2, "comment2");
		// post1.addComment(user2, "comment3");
		// post1.addComment(user2, "comment4");
		// post2.addComment(user2, "comment");
		//
		// user1.addPost(post1);
		// user1.addPost(post2);
		// user1.addPost(post3);
		//
		// user2.getFeed(Type.MAIN_FEED).displayPostsInfo();
		//
		// System.out.println("\n\nTrending posts: \n");
		// user2.getFeed(Type.TRENDING_FEED).displayPostsInfo();
		//
		// }
	}
}
