package controller.manager;

import java.sql.SQLException;
import java.util.TreeSet;

import controller.manager.PostManager.PostException;
import model.UserBean;
import model.dao.NotificationDao;
import model.dao.PostDao;
import model.dao.UserDao;
import model.notification.LikeNotificationBean;
import model.notification.NotificationBean;
import model.notification.SubscriptionNotificationBean;
import model.post.PostBean;

public class UserManager {
	
	public static class UserManagerException extends Exception{
		private UserManagerException(String msg) {
			super(msg);
		}
	}
	

	private static UserManager instance;

	private UserManager() {
	}

	public static synchronized UserManager getInstance() {
		if (instance == null) {
			instance = new UserManager();
		}
		return instance;
	}

	// =================FILL COLLECTIONS===============//

	public void subscribe(UserBean subscriber, UserBean subscribedTo) throws SQLException,UserManagerException {
		if (subscribedTo == null && subscriber == null) {
			throw new UserManagerException("subscription user is empty or null");
			
		}			
			NotificationBean n = new SubscriptionNotificationBean(subscriber, subscribedTo);
			NotificationManager.getInstance().proceedNotification(n);
			
			//add in DB
			
			UserDao.getInstance().addSubscription(subscriber, subscribedTo);
			
			// Adding to UserBean subscriptions collections
			subscriber.addSubscribtion(subscribedTo);
			subscribedTo.addSubscriber(subscriber);
					
			// Adding to notifications collection of subscribedTo UserBean
			SubscriptionNotificationBean notification = new SubscriptionNotificationBean(subscriber, subscribedTo);
			subscribedTo.addNotification(notification);

			System.out.println(subscriber.getUsername() + " subscribed to " + subscribedTo.getUsername());
			
	}
	
	
	//TODO adding liked post to DB
	// ????? dividing on separate methods for liking and adding to liked ?????
	public void like(UserBean liker, PostBean likedPost) throws SQLException, PostException {
		if (likedPost != null) {
			PostManager.getInstance().addLike(liker, likedPost);
			liker.addLikedPost(likedPost);
			NotificationBean n = new LikeNotificationBean(liker,likedPost.getPoster(), likedPost);
			NotificationDao.getInstance().addNotificationInDB(n);
			likedPost.getPoster().addNotification(n);
			System.out.println("Post added to liked photos in " + liker.getUsername() + " collection.");	
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
		public void updateProfileInfo(UserBean u, String password, String first_name, String last_name, String email) throws Exception {
			
			UserDao.getInstance().executeProfileUpdate(u, password, first_name, last_name, email);
			
		}


}
