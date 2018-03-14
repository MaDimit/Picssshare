package project.content;

import project.content.Post;
import project.user.User;

public class VideoPost extends Post{

	private int views;
	
	public VideoPost(User poster, String url) {
		super(poster, url);
		this.views = 0;
	}

}
