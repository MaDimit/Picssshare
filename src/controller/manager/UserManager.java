package controller.manager;

import java.util.TreeSet;

import model.UserBean;
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
		//TODO ????? constructor for singleton manager ?????
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

	// TODO adding subscription and notification to DB
	public void subscribe(UserBean subscriber, UserBean subscribedTo) {
		if (subscribedTo != null && subscriber != null) {

			// Adding to UserBean subscriptions collections
			subscriber.addSubscribtion(subscribedTo);
			subscribedTo.addSubscriber(subscriber);

			// Adding to notifications collection of subscribedTo UserBean
			subscribedTo.addNotification(new SubscriptionNotificationBean(subscriber));

			System.out.println(subscriber.getUsername() + " subscribed to " + subscribedTo.getUsername());
		} else {
			System.out.println("Problem during subscription");
		}
	}
	
	//TODO adding post to DB
	public void addPost(UserBean user, PostBean post) {
		if (post != null) {
			user.addPost(post);
			
			System.out.println("Post added by " + user.getUsername());
		} else {
			System.out.println("Error with adding post.");
		}
	}
	
	//TODO adding liked post to DB
	// ????? dividing on separate methods for liking and adding to liked ?????
	public void like(UserBean liker, PostBean likedPost) {
		if (likedPost != null) {
			PostManager.getInstance().addLike(likedPost);
			liker.addLikedPost(likedPost);
			likedPost.getPoster().addNotification(new LikeNotificationBean(liker, likedPost));
			System.out.println("Post added to liked photos in " + liker.getUsername() + " collection.");
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

	// ==================REGISTER/LOGIN===============//
	
	/*
	 * Register and login functionality is in LoggingManager
	 */

//	public void registerRequest() {
//		UserLogging.register(this);
//	}
//
//	public void loginRequest() {
//		if (UserLogging.login(this)) {
//			System.out.println(this.username + " successfully logged.");
//		} else {
//			System.out.println("Login operation unsuccessfull.");
//		}
//	}
	
	
	/*
	 * TODO decide how feed generation will work for user ????? should be handled by FeedManager ?????
	 */
	
//	public Feed showView(Feed.Type type) {
//		switch (type) {
//		case MAIN_FEED:
//			this.feed = new MainFeed(this);
//			break;
//		case TRENDING_FEED:
//			this.feed = new TrendingFeed();
//			break;
//		}
//		return feed;
//	}

}
