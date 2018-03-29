package model.dao;

import java.sql.Connection;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.TreeSet;
import model.UserBean;
import model.post.PostBean;

public class FeedDao {
	private PostDao postDao;

	static Connection conn = null;
	static Statement stmt = null;
	private static FeedDao instance = null;
	private TreeSet<PostBean> posts;

	public static FeedDao getInstance() {
		if (instance == null) {
			instance = new FeedDao();
		}
		return instance;
	}

	public FeedDao()  {
//		this.postDao = PostDao.getInstance();
//		this.posts = new TreeSet<>();
//		// STEP 2: Register JDBC driver
//			Class.forName("com.mysql.jdbc.Driver");
//			// STEP 3: Open a connection
//			System.out.println("Connecting to database...");
//			conn = DbManager.getInstance().getConnection();
//			// STEP 4: Execute a query
//			System.out.println("Creating statement...");
//			stmt = conn.createStatement();

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
