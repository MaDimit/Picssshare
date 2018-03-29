package model.dao;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import model.UserBean;

public class UserDao {

	public HashMap<String, UserBean> users;
	private static UserDao instance = null;
	private static DbManager dbManager;

	// singleton
	private UserDao() {
		this.users = new HashMap<>();
		dbManager = DbManager.getInstance();
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

	public void fillCollectionWithUsers() throws Exception{
		// JDBC driver name and database URL

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
			sql = "select * from users";
			ResultSet rs = stmt.executeQuery(sql);

			// Extract data from result set
			while (rs.next()) {
				// Retrieve by column name
				int id = rs.getInt("id");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				String email = rs.getString("lastName");
				UserDao.getInstance().getUsers().put(username, new UserBean().email(email).lastName(lastName).firstName(firstName).password(password).username(username).id(id));

				// Display values
//				System.out.print("ID: " + id);
//				System.out.print(", username: " + username);
//				System.out.print(", password: " + password);
//				System.out.print(", firstName: " + firstName);
//				System.out.print(", lastName: " + lastName);
//				System.out.println();
			}

	}

	public void registerUser(UserBean u) throws Exception {
		// put in collection
		this.users.put(u.getUsername(), u);
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
			sql = "INSERT INTO `picssshare_test`.`users` (`username`, `password`, `firstName`, `lastName`, `email`) VALUES ('" + u.getUsername() + "', '" + u.getPassword() + "', '" + u.getFirstName()
					+ "', '" + u.getLastName() + "', '" + u.getEmail() + "')";
			stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = stmt.getGeneratedKeys();
			int id = 0;
			if (rs != null && rs.next()) {
				id = rs.getInt(1);
			}
			System.out.println("FROM DATABASE: " + id);
			//set id to the user
			u.setId(id);
			System.out.println("FROM ALREADY SET ID: " + u.getId());
		

	}
	
	public void addSubscription(UserBean subscriber, UserBean subscribed) throws Exception{
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
			sql = "INSERT INTO `picssshare_test`.`subscriber_subscribed` (`subscriber_id`,`subscribed_id`) VALUES ('" + subscriber.getId() + "', '" + subscribed.getId() + "')";
			stmt.executeUpdate(sql);
		
	}
		
	
}
