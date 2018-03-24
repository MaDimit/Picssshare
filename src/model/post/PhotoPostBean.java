package model.post;

import model.UserBean;

public class PhotoPostBean extends PostBean{
	
	public PhotoPostBean(UserBean poster, String url, int uid) {
		super(poster, url, uid);
	}
	
	@Override
	public boolean isPhoto() {
		return true;
	}

}
