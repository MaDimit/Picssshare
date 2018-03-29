package demo;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.TreeSet;

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
		return r.nextInt(range)+startPoint;
	}
	
	public static void main(String[] args) throws Exception{
		first_demo();
	}
	
	private static void first_demo() throws Exception {
		//everytime the server starts the collections must be filled up with the data from users table
				UserDao.getInstance().fillCollectionWithUsers();
				/////////////////////////////////////////
				//testing registering of existing user
//				UserBean user5 = new UserBean("filippp", "FilippKasss992", "asdddd@gmail.com");
//				user5.setFirstName("Filip");
//				user5.setLastName("Kasapov");
//				LoggingManager.getInstance().register(user5);
//				
//				UserBean user6 = new UserBean("filipppp", "FilippKasss992", "asdddd@gmail.com");
//				user6.setFirstName("Filip");
//				user6.setLastName("Kasapov");
//				LoggingManager.getInstance().register(user6);
				///////////////////////////////////////////
				//testing subscribing
				UserBean user = UserDao.getInstance().getUsers().get("filippp");
				UserBean user2 = UserDao.getInstance().getUsers().get("filipppp");
				
				//UserManager.getInstance().subscribe(user, user2);
				////////////////////////////////////////////////
				//testing login
				System.out.println(LoggingManager.getInstance().login("filippp", "FilippKasss992"));
				/////////////////////////////////////////////
				//testing posting
				PostBean post = new PostBean(user2, "url", 1);
				UserManager.getInstance().addPost(user2, post);
//				PostBean post2 = new PostBean(user2, "url2", 1);
//				UserManager.getInstance().addPost(user2, post2);
				PostBean post2 = PostDao.getInstance().getPosts().get(1);
//				PostManager.getInstance().addLike(post);
		//
//				PostManager.getInstance().addLike(post);
		//
//				PostManager.getInstance().addDislike(post);
				
//				CommentManager.getInstance().createComment("KO MEN TAR", user2, post2);
				/////////////////////////////////////////////
				
				//Philip: Done testing and adding here...continue with the below
				
				
//				post.showInfo();
//				post.deleteComment(1);
//				post.showInfo();
//				user.addPost(post);
//				
//				
//				//showNotifications && showSubscribers test
//				System.out.println(user.getUsername() + " notifications ------>");
//				user.showNotifications();
//				user.subscribeTo(user1);
//				user.subscribeTo(user3);
//				user1.showSubscribers();
//				System.out.println(user1.getUsername() + " notifications ----->");
//				user1.showNotifications();
		//
//				System.out.println();
//				System.out.println();
//				System.out.println("===FEED SHOWING===");
//				//user has been subscribed to user1 and user3 / each have posted 1 post / result: displayed 2 posts
//				System.out.println();
//				System.out.println();
//				user.getFeed(Type.MAIN_FEED).displayPostsInfo();
//				
//				try {
//					feedTest();
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			}
		//	
//			private static void feedTest() throws InterruptedException {
//				UserBean user1 = new UserBean("user1","password123","email@email.com");
//				UserBean user2 = new UserBean("user2","notpassword321","email2@email.com");
//				user1.setServer(Server.getInstance());
//				user2.setServer(Server.getInstance());
//				
//				user1.subscribeTo(user2);
//				user2.subscribeTo(user1);
//				
//				Post post1 = new PhotoPost(user1, "some photo url1");
//				Thread.sleep(1000);
//				Post post2 = new PhotoPost(user1, "some photo url2");
//				Thread.sleep(1000);
//				Post post3 = new PhotoPost(user1, "some photo url3");
//				Thread.sleep(1000);
//				
//				post1.addLike();
//				post1.addLike();
//				post1.addLike();
//				post1.addLike();
//				post2.addLike();
//				
//				post1.addComment(user2, "comment1");
//				post1.addComment(user2, "comment2");
//				post1.addComment(user2, "comment3");
//				post1.addComment(user2, "comment4");
//				post2.addComment(user2, "comment");
//				
//				user1.addPost(post1);
//				user1.addPost(post2);
//				user1.addPost(post3);
//				
//				user2.getFeed(Type.MAIN_FEED).displayPostsInfo();
//				
//				System.out.println("\n\nTrending posts: \n");
//				user2.getFeed(Type.TRENDING_FEED).displayPostsInfo();
		//
//			}
	}
}
