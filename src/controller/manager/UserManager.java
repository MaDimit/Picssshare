package controller.manager;

import java.sql.SQLException;
import java.util.TreeSet;

import model.UserBean;
import model.dao.NotificationDao;
import model.dao.PostDao;
import model.dao.UserDao;
import model.notification.LikeNotificationBean;
import model.notification.NotificationBean;
import model.notification.SubscriptionNotificationBean;
import model.post.PostBean;

public class UserManager {
	
	/*
	 * Singleton (May be changed later)
	 */

	private static UserManager instance;

	private UserManager() {
		//uid = TODO get last uid from DB or collection;
	}

	public static synchronized UserManager getInstance() {
		if (instance == null) {
			instance = new UserManager();
		}
		return instance;
	}

	// =================FILL COLLECTIONS===============//
	/*
	 * TODO more validation for all insertions to UserBean collections
	 */

	public boolean subscribe(UserBean subscriber, UserBean subscribedTo) {
		if (subscribedTo == null && subscriber == null) {
			System.out.println("Subscriber or subscribed is null");
			return false;
		}	
			
			NotificationBean n = new SubscriptionNotificationBean(subscriber, subscribedTo);
			NotificationManager.getInstance().proceedNotification(n);
			
			//add in DB
			try {
				UserDao.getInstance().addSubscription(subscriber, subscribedTo);
			} catch (SQLException e) {
				System.out.println("Maybe you have already subscribed to this user!");
				System.out.println("Adding subscribtin to DB went wrong: " + e.getMessage());
				
				return false;
			}

			// Adding to UserBean subscriptions collections
			subscriber.addSubscribtion(subscribedTo);
			subscribedTo.addSubscriber(subscriber);
		
			
			//TODO add notification to DB
			
			// Adding to notifications collection of subscribedTo UserBean
			SubscriptionNotificationBean notification = new SubscriptionNotificationBean(subscriber, subscribedTo);
			subscribedTo.addNotification(notification);

			System.out.println(subscriber.getUsername() + " subscribed to " + subscribedTo.getUsername());
			
//			try {
//				NotificationDao.getInstance().addNotificationInDB(notification);
//			} catch (SQLException e) {
//				System.out.println("Error while inserting notification in DB.");
//				e.printStackTrace();
//			}
			return true;
	}
	
	
	//TODO adding liked post to DB
	// ????? dividing on separate methods for liking and adding to liked ?????
	public void like(UserBean liker, PostBean likedPost) {
		if (likedPost != null) {
			PostManager.getInstance().addLike(liker, likedPost);
			liker.addLikedPost(likedPost);
			NotificationBean n = new LikeNotificationBean(liker,likedPost.getPoster(), likedPost);
			likedPost.getPoster().addNotification(n);
			System.out.println("Post added to liked photos in " + liker.getUsername() + " collection.");
			try {
				NotificationDao.getInstance().addNotificationInDB(n);
			} catch (SQLException e) {
				System.out.println("Problem with adding notification to db.");
				e.printStackTrace();
			}
		} else {
			System.out.println("Problem with adding a post to liked ones.");
		}
	}
	
	//TODO adding bookmarked post to DB
	public void addBookmark(UserBean user, PostBean bookmarkedPost) {
		if (bookmarkedPost != null) {
			user.addBookmark(bookmarkedPost);
			System.out.println("Bookmarked content by " + user.getUsername());
		} else {
			System.out.println("Error with bookmark.");
		}
	}

	// ==================Notifications===============//
	
	//TODO adding notifications to view by small portions using ajax
	public void showNotifications(UserBean user) {
		for (NotificationBean n : user.getNotifications()) {
			System.out.println(n.getDate() + " : " + n.getDescription());
			n.seen();
		}
	}
	
	//TODO adding subscribers to view
	public void showSubscribers(UserBean user) {
		System.out.println("----------People subscribed to " + user.getUsername() + "---------");
		for (UserBean u : user.getSubscriptions()) {
			System.out.println("===" + u.getUsername() + "====");
		}
	}
	
	//username and id are supposed not to be eddited
		public void updateProfileInfo(UserBean u, String password, String first_name, String last_name, String email) {
			try {
				UserDao.getInstance().executeProfileUpdate(u, password, first_name, last_name, email);
			} catch (SQLException e) {
				System.out.println("Sorry, problem with updating your profile.");
				e.printStackTrace();
			}
			catch(Exception e) {
				System.out.println("Sorry, something went wrong!");
			}
		}


}
