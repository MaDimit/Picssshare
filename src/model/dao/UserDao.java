package model.dao;

import java.sql.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import model.UserBean;
import model.post.PostBean;

public class UserDao extends Dao{

	private static UserDao instance = null;

	// singleton
	private UserDao() {
		super();
	}

	// singleton instance used in usermanager
	public static synchronized UserDao getInstance() {
		if (instance == null) {
			instance = new UserDao();
		}
		return instance;
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
		String sql = "INSERT INTO subscriber_subscribed (subscriber_id, subscribedto_id) VALUES (?,?)";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, subscriber.getId());
		stmt.setInt(2, subscribed.getId());
		stmt.executeUpdate();
	}
		
	
}
