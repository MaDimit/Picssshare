package model.dao;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import model.UserBean;

public class UserDao {
	// Database credentials
	final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	final static String DB_URL = "jdbc:mysql://localhost/picssshare";
	final static String USER = "root";
	final static String PASS = "root";

	public HashMap<String, UserBean> users;
	private static UserDao instance = null;

	// singleton
	public UserDao() {
		this.users = new HashMap<>();
	}

	// method for printing all the users ## maybe used only when testing the
	public void printCollectionInfo() {
		for (Map.Entry<String, UserBean> entry : this.users.entrySet()) {
			System.out.println(entry.getKey() + " " + entry.getValue().getPassword());
		}
	}

	// singleton instance used in usermanager
	public static UserDao getInstance() {
		if (instance == null) {
			instance = new UserDao();

		}
		return instance;
	}

	public HashMap<String, UserBean> getUsers() {
		return users;
	}

	public static void fillCollectionWithUsers() {
		// JDBC driver name and database URL

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
			// STEP 6: Clean-up environment
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
			} // end finally try
		} // end try
		System.out.println("Goodbye!");
	}

	public void registerUser(UserBean u) {
		// put in collection
		this.users.put(u.getUsername(), u);
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
			sql = "INSERT INTO `picssshare`.`users` (`username`, `password`, `firstName`, `lastName`, `email`) VALUES ('" + u.getUsername() + "', '" + u.getPassword() + "', '" + u.getFirstName()
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
			//close rs resourse
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
			} // end finally try
			// end try
			System.out.println("Goodbye!");
		}
	}
	
	public void addSubscription(UserBean subscriber, UserBean subscribed) {
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
			sql = "INSERT INTO `picssshare`.`subscriber_subscribed` (`subscriber_id`,`subscribed_id`) VALUES ('" + subscriber.getId() + "', '" + subscribed.getId() + "')";
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
			} // end finally try
			// end try
			System.out.println("Goodbye!");
		}
		
		
	}
}
