package model.post;

import model.UserBean;

public class VideoPostBean extends PostBean{
	
	private int views;
	
	public VideoPostBean(UserBean poster, String url, int uid) {
		super(poster, url, uid);
		this.views = 0;
	}
	
	public int getViews() {
		return views;
	}
	
	public void setViews(int views) {
		this.views = views;
	}

}
