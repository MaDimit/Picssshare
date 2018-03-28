package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.CommentBean;
import model.post.PostBean;
import project.DbProperties;
import project.DbProperties.Key;

//package model.dao;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.HashMap;
//import java.util.TreeSet;
//
//import model.CommentBean;
//import model.post.PostBean;
//
public class CommentDao {
	// collection holding the posts is in the FeedDao
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = DbProperties.getDbProperty(Key.TEST_URL);
	// Database credentials
	static final String USER = DbProperties.getDbProperty(Key.TEST_USER);
	static final String PASS = DbProperties.getDbProperty(Key.TEST_PASS);
	private static CommentDao instance = null;

	// singleton instance used in commentdao
	public static CommentDao getInstance() {
		if (instance == null) {
			instance = new CommentDao();

		}
		return instance;
	}

	public void addCommentInDB(PostBean p, CommentBean c) {
		// STEP 2: Register JDBC driver
		Connection conn = null;
		Statement stmt = null;
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
		

		// insert in db
		try {
			String sql;
			sql = "INSERT INTO `picssshare`.`comments` (`comment_poster_id`,`postTime`, `content`, `belonged_post_id`) VALUES ('"
					+ c.getPoster().getId() + "', '" + c.getPostTime() + "', '" + c.getContent() + "', '"
					+ c.getBelongedPost().getId() + "')";
			stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = stmt.getGeneratedKeys();
			int id = 0;
			if (rs != null && rs.next()) {
				id = rs.getInt(1);
			}
			c.setId(id);

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
		// put in collection
				if (PostDao.getInstance().getPosts().containsKey(p.getId())) {
					PostDao.getInstance().getPosts().get(p.getId()).addComment(c);
				} else {
					System.out.println("No such post!");
				}
	}

	public void deleteComment(CommentBean c) {
		Connection conn = null;
		Statement stmt = null;
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

		// remove comment from post collection
		PostDao.getInstance().getPosts().get(c.getBelongedPost().getId()).removeComment(c.getId());

		// remove from db
		try {
			String sql;
			sql = "DELETE FROM `picssshare`.`comments` WHERE id=" + c.getId();
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

}
