package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

import javax.print.attribute.standard.DateTimeAtCompleted;

import model.UserBean;
import model.post.PostBean;

public class PostDao extends Dao{
	// collection for storing posts
	private HashMap<Integer, PostBean> posts;
	private static PostDao instance;

	// singleton instance used in commentmanager
	public static PostDao getInstance() {
		if (instance == null) {
			instance = new PostDao();
		}
		return instance;
	}

	private PostDao() {
		super();
		this.posts = new HashMap<>();
	}

	public HashMap<Integer, PostBean> getPosts() {
		return posts;
	}
	
	public ResultSet getAllPosts() {
		Connection conn = dbManager.getConnection();
		PreparedStatement stmt;
		ResultSet rs = null;
		
		//Fetching users from DB
		String sql = "SELECT * FROM posts";
		try{
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
		}catch(SQLException e) {
			System.out.println("Problem during filling users collection: " + e.getMessage());
		}
		return rs;
	}
	
	public void addPost(PostBean post) throws SQLException{
		Connection conn = dbManager.getConnection();
		String sql = "INSERT INTO posts (likes,  date, poster_id, url) VALUES (?,?,?,?)";
		PreparedStatement stmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
		stmt.setInt(1, post.getLikes());
		stmt.setTimestamp(2,Timestamp.from(post.getDate().toInstant(ZoneOffset.ofHours(0))));
		stmt.setInt(3, post.getPoster().getId());
		stmt.setString(4, post.getUrl());
		stmt.executeUpdate();
		
		ResultSet generatedKeys = stmt.getGeneratedKeys();
		 if (generatedKeys.next()) {
			 post.setId(generatedKeys.getInt(1));
	     }else {
	        throw new SQLException("Creating user failed, no ID obtained.");
	     }
		 this.posts.put(post.getId(), post);
	}
	
	
	public void deletePost(PostBean post) throws SQLException{
		deletePost(post.getId());
	}
	
	public void deletePost(int id) throws SQLException{
		Connection conn = dbManager.getConnection();
		String sql = "DELETE FROM posts WHERE id=(?)";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, id);
		stmt.executeUpdate();
	}
	
	public void updateLikes(PostBean post) throws SQLException {
		Connection conn = dbManager.getConnection();
		String sql = "UPDATE posts SET likes = ? WHERE id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, post.getLikes());
		stmt.setInt(2, post.getId());
		stmt.executeUpdate();
	}

	public void addInLikerPostTable(UserBean liker, PostBean post) throws SQLException {
//		
		Connection conn = dbManager.getConnection();
		PreparedStatement stmt = null;
		String sql;
		sql = "INSERT INTO liker_post (`liker_id`, `likedpost_id`) VALUES (?,?)";
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, liker.getId());
		stmt.setInt(2, post.getId());
		stmt.executeUpdate();	
	}

	// public HashSet<PostBean> getUserPostsById(int id) throws SQLException{
	// HashSet<PostBean> posts = new HashSet<>();
	// Connection conn = dbManager.getConnection();
	// String sql = "SELECT * FROM posts WHERE id=(?)";
	// PreparedStatement stmt = conn.prepareStatement(sql);
	// stmt.setInt(1, id);
	// ResultSet rs = stmt.executeQuery();
	//
	// while(rs.next()) {
	// PostBean post = new PostBean(poster, url, uid)
	// }
	//
	// return posts;
	// }
}
