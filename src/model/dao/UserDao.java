package model.dao;

import java.sql.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import model.UserBean;
import model.post.PostBean;

public class UserDao {

	public HashMap<String, UserBean> users;
	private static UserDao instance = null;
	private static DbManager dbManager;

	// singleton
	private UserDao() {
		dbManager = DbManager.getInstance();
		this.users = getAllUsers();
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
	
	//Initializing runtime users collection
	private HashMap<String, UserBean> getAllUsers(){
		HashMap<String, UserBean> users = new HashMap<>();
		Connection conn = dbManager.getConnection();
		PreparedStatement stmt;
		ResultSet rs;
		
		//Fetching users from DB
		String sql = "SELECT * FROM users";
		try{
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			//Adding users to runtime collection
			int id;
			String username;
			String password;
			String firstName;
			String lastName;
			String email;
			while (rs.next()) {
				// Retrieve by column name
				id = rs.getInt("id");
				username = rs.getString("username");
				password = rs.getString("password");
				firstName = rs.getString("firstName");
				lastName = rs.getString("lastName");
				email = rs.getString("email");
				UserBean user = new UserBean()
						.id(id)
						.username(username)
						.password(password)
						.firstName(firstName)
						.lastName(lastName)
						.email(email);
						//.posts(PostDao.getInstance().getUserPostsById(id));
				//TODO add user collections;
				users.put(username, user);
			}
		}catch(SQLException e) {
			System.out.println("Problem during filling users collection: " + e.getMessage());
		}
		return users;
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
        this.users.put(user.getUsername(), user);
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
