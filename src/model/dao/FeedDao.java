package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.TreeSet;

import model.CommentBean;
import model.UserBean;
import model.post.PostBean;

public class FeedDao {
	private CommentDao commentDao;

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/picssshare";
	static final String USER = "root";
	static final String PASS = "root";
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

	public FeedDao() {
		this.commentDao = CommentDao.getInstance();
		this.posts = new TreeSet<>();
		// STEP 2: Register JDBC driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			// STEP 4: Execute a query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public TreeSet<PostBean> getAllPostsForUser(UserBean u) {
		TreeSet<PostBean> userPosts = new TreeSet<>();
		for (Map.Entry<PostBean, TreeSet<CommentBean>> posts : this.commentDao.getPost_comments().entrySet()) {
			if (posts.getKey().getPoster().getId() == u.getId()) {
				PostBean post = posts.getKey();
				post.setCommentsById(posts.getValue());
				userPosts.add(post);
			}
		}
		return userPosts;

	}

}
