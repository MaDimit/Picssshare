package model.post;

import model.UserBean;

public class PhotoPostBean extends PostBean{
	
	public PhotoPostBean(UserBean poster, String url) {
		super(poster, url);
	}
	
	@Override
	public boolean isPhoto() {
		return true;
	}

}
