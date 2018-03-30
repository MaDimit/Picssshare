package model.dao;

import java.sql.Connection;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.TreeSet;
import model.UserBean;
import model.post.PostBean;

public class FeedDao extends Dao{
	private PostDao postDao;

	private static FeedDao instance = null;
	private TreeSet<PostBean> posts;

	public static synchronized FeedDao getInstance() {
		if (instance == null) {
			instance = new FeedDao();
		}
		return instance;
	}

	private FeedDao()  {
		super();
	}

	public TreeSet<PostBean> getAllPostsForUser(UserBean u) {
		TreeSet<PostBean> userPosts = new TreeSet<>((p1,p2)->(p1.getDate().compareTo(p2.getDate())));
		for (PostBean post : this.postDao.getPosts().values()) {
			if (post.getId() == u.getId()) {
				userPosts.add(post);
			}
		}
		return userPosts;

	}

}
