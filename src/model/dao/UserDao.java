package model.dao;

import java.sql.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import model.UserBean;
import model.post.PostBean;

public class UserDao extends Dao{

	public HashMap<String, UserBean> users;
	private static UserDao instance = null;

	// singleton
	private UserDao() {
		super();
		this.users = null;
	}

	// method for printing all the users ## maybe used only when testing the
	public void printCollectionInfo() {
		for (Map.Entry<String, UserBean> entry : this.users.entrySet()) {
			System.out.println(entry.getKey() + " " + entry.getValue().getPassword());
		}
	}

	// singleton instance used in usermanager
	public static synchronized UserDao getInstance() {
		if (instance == null) {
			instance = new UserDao();
		}
		return instance;
	}

	public HashMap<String, UserBean> getUsers() {
		return users;
	}
	
	public ResultSet getAllUsers() {
		Connection conn = dbManager.getConnection();
		PreparedStatement stmt;
		ResultSet rs = null;
		
		//Fetching users from DB
		String sql = "SELECT * FROM users";
		try{
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
		}catch(SQLException e) {
			System.out.println("Problem during filling users collection: " + e.getMessage());
		}
		return rs;
	}

	
	public void registerUser(UserBean user) throws SQLException{
		Connection conn = dbManager.getConnection();
		
		//Inserting into DB
		String sql = "INSERT INTO users (username, password, email) VALUES (?,?,?)";
		PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		stmt.setString(1, user.getUsername());
		stmt.setString(2, user.getPassword());
		stmt.setString(3, user.getEmail());
		stmt.executeUpdate();
		
		//Getting id for registered user
		ResultSet generatedKeys = stmt.getGeneratedKeys();
        if (generatedKeys.next()) {
        	user.setId(generatedKeys.getInt(1));
        }else {
        	throw new SQLException("Creating user failed, no ID obtained.");
        }
        
        //adding to runtime users collection
        //this.users.put(user.getUsername(), user);
	}
	
	public void addSubscription(UserBean subscriber, UserBean subscribed) throws SQLException{
		Connection conn = dbManager.getConnection();
		String sql = "INSERT INTO subscriber_subscribed (subscriber_id, subscribed_id) VALUES (?,?)";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, subscriber.getId());
		stmt.setInt(2, subscribed.getId());
		stmt.executeUpdate();
	}
		
	
}
