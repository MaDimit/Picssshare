package model.feed;

import model.UserBean;

public class MainFeedBean extends FeedBean{
	
	private UserBean user;
	
	public MainFeedBean(UserBean user) {
		this.user = user;
		System.out.println("\n\nCoeficients(generateCoefficient() method in Post): \n");
		generateFeed();
	}
}
