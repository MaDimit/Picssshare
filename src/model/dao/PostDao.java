package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;

import model.CommentBean;
import model.post.PostBean;
import project.DbProperties;
import project.DbProperties.Key;

public class PostDao {
	// collection holding the posts is in the FeedDao
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = DbProperties.getDbProperty(Key.TEST_URL);;
	// Database credentials
	static final String USER = DbProperties.getDbProperty(Key.TEST_USER);;
	static final String PASS = DbProperties.getDbProperty(Key.TEST_PASS);;
	// collection for storing posts
	private HashMap<Integer, PostBean> posts;
	private static PostDao instance = null;
	

	// singleton instance used in commentmanager
	public static PostDao getInstance() {
		if (instance == null) {
			instance = new PostDao();

		}
		return instance;
	}

	public PostDao() {
		this.posts = new HashMap<>();
		
		
		
	}

	public HashMap<Integer, PostBean> getPosts() {
		return posts;
	}

	public void addPost(PostBean p) {
		// add in collection

		// insert in db
		Connection conn = null;
		Statement stmt = null;
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

		try {
			String sql;
			sql = "INSERT INTO `picssshare`.`post` (`likes`,  `date`,  `poster_id`,`url`) VALUES ('"
					+ p.getLikes() + "', '"  + p.getDate() + "', '" + p.getPoster().getId()
					+ "', '" + p.getUrl() + "')";

			stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = stmt.getGeneratedKeys();
			int id = 0;
			if (rs != null && rs.next()) {
				id = rs.getInt(1);
			}
			// set the given from database id for the post
			p.setId(id);

			rs.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		} // end finally try

		// add in collection after received id
		this.posts.put(p.getId(), p);
		System.out.println("Goodbye!");

	}

	public void deletePost(PostBean p) {

		// remove from collection
		this.posts.remove(p.getId());
		// remove from db
		Connection conn = null;
		Statement stmt = null;
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

		try {
			String sql;
			sql = "Delete from `picssshare`.`post` where id=" + p.getId();

			stmt.executeUpdate(sql);

			// STEP 6: Clean-up environment

			// stmt.close();
			// conn.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
			System.out.println("Goodbye!");
		}
	}

	public void updateLikes(PostBean post) {

		// update in db
		Connection conn = null;
		Statement stmt = null;
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

		try {
			String sql;
			sql = "UPDATE `picssshare`.`post` SET `post`.`likes` =" + post.getLikes() + " WHERE id=" + post.getId();
			stmt.executeUpdate(sql);

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		} // end finally try
		System.out.println("Goodbye!");
	}

	 
}
