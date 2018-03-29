package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

import model.post.PostBean;

public class PostDao {
	// collection for storing posts
	private HashMap<Integer, PostBean> posts;
	private static PostDao instance = null;
	private DbManager dbManager;
	

	// singleton instance used in commentmanager
	public static PostDao getInstance() {
		if (instance == null) {
			instance = new PostDao();
		}
		return instance;
	}

	private PostDao() {
		this.posts = new HashMap<>();
		this.dbManager = DbManager.getInstance();
	}

	public HashMap<Integer, PostBean> getPosts() {
		return posts;
	}

	public void addPost(PostBean p) throws Exception {
		// add in collection

		// insert in db
		Connection conn = null;
		Statement stmt = null;
		// STEP 2: Register JDBC driver
		
			Class.forName("com.mysql.jdbc.Driver");
			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = dbManager.getConnection();
			// STEP 4: Execute a query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();

	

		
			String sql;
			sql = "INSERT INTO `picssshare_test`.`post` (`likes`,  `date`,  `poster_id`,`url`) VALUES ('"
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

		this.posts.put(p.getId(), p);

	}

	public void deletePost(PostBean p) throws Exception{

		// remove from collection
		this.posts.remove(p.getId());
		// remove from db
		Connection conn = null;
		Statement stmt = null;
		// STEP 2: Register JDBC driver

			Class.forName("com.mysql.jdbc.Driver");
			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = dbManager.getConnection();
			// STEP 4: Execute a query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();


		
			String sql;
			sql = "Delete from `picssshare_test`.`post` where id=" + p.getId();

			stmt.executeUpdate(sql);

	}

	public void updateLikes(PostBean post) throws Exception{

		// update in db
		Connection conn = null;
		Statement stmt = null;
		// STEP 2: Register JDBC driver

			Class.forName("com.mysql.jdbc.Driver");
			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = dbManager.getConnection();
			// STEP 4: Execute a query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();


			String sql;
			sql = "UPDATE `picssshare_test`.`post` SET `post`.`likes` =" + post.getLikes() + " WHERE id=" + post.getId();
			stmt.executeUpdate(sql);

	}

//	public HashSet<PostBean> getUserPostsById(int id) throws SQLException{
//		HashSet<PostBean> posts = new HashSet<>();
//		Connection conn = dbManager.getConnection();
//		String sql = "SELECT * FROM posts WHERE id=(?)";
//		PreparedStatement stmt = conn.prepareStatement(sql);
//		stmt.setInt(1, id);
//		ResultSet rs = stmt.executeQuery();
//		
//		while(rs.next()) {
//			PostBean post = new PostBean(poster, url, uid)
//		}
//		
//		return posts;
//	}
}
