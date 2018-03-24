package controller.manager;

public class UserManager {
	
	
	
	//=================FILL COLLECTIONS===============//
		public void subscribeTo(UserBean u) {
			if(u!=null) {
				this.subscriptions.add(u);
				u.subscribers.add(this);
				u.addNotification(new SubscriptionNotification(this));
				System.out.println(this.getUsername() + " subscribed to " + u.getUsername());
			}
			else {
				System.out.println("Problem during subscription");
			}
		}
		
		
		
		public void addPost(PostBean p) {
			if(p!=null) {
				this.posts.add(p);
				System.out.println("Post added by "+this.username);
				server.addPost(p);
			}
			else {
				System.out.println("Error with adding post.");
			}
		}
		
		public void addLiked(PostBean c) {
			if(c!=null) {
				c.addLike();
				this.likedPosts.add(c);
				c.getPoster().addNotification(new LikeNotification(this, c));
				System.out.println("Post added to liked photos in "+this.username+" collection.");
			}
			else {
				System.out.println("Problem with adding a post to liked ones.");
			}
		}
		
		
		public void addBookmark(PostBean c) {
			if(c!=null) {
				this.bookmarks.add(c);
				System.out.println("Bookmarked content by "+this.username);
			}
			else {
				System.out.println("Error with bookmark.");
			}
		}
		
		public TreeSet<Post> getBookmarks(){
			return bookmarks;
		}
		
		//==================Notifications===============//
		
		public void addNotification(NotificationBean n) {
			this.notifications.add(n);
		}
		
		public void showNotifications() {
			for(Notification n: this.notifications) {
				System.out.println(n.getDate() + " : " +n.getDescription());
				n.seen();
			}
		}
		
		public void showSubscribers() {
			System.out.println("----------People subscribed to "+this.username+"---------");
			for(User u: this.subscriptions) {
				System.out.println("==="+u.getUsername()+"====");
			}
		}
		
		//==================REGISTER/LOGIN===============//

		public void registerRequest() {
			UserLogging.register(this);
		}
		
		public void loginRequest() {
			if(UserLogging.login(this)) {
				System.out.println(this.username+" successfully logged.");
			}
			else {
				System.out.println("Login operation unsuccessfull.");
			}
		}
		
		//=====================GETTERS AND SETTERS===============//

		public TreeSet<Post> getLikedPhotos() {
			return likedPosts;
		}
		
		public Feed getFeed(Feed.Type type) {
			switch(type) {
			case MAIN_FEED: 
				this.feed = new MainFeed(this);
				break;
			case TRENDING_FEED:
				this.feed = new TrendingFeed();
				break;
			}
			return feed;
		}

}
