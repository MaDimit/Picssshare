package model.post;

import model.UserBean;

public class VideoPostBean extends PostBean{
	
	private int views;
	
	public VideoPostBean(UserBean poster, String url) {
		super(poster, url);
		this.views = 0;
	}

}
