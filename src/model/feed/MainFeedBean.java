package model.feed;

import model.UserBean;

public class MainFeedBean extends FeedBean{
	
	private UserBean user;
	
	public MainFeedBean(UserBean user) {
		this.user = user;
	}
	
	public UserBean getUser() {
		return user;
	}
	
	public void setUser(UserBean user) {
		this.user = user;
	}
}
