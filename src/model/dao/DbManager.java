package model.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;


public class DbManager {
	
	//Keys used to access properties file
	public enum Key{
		TEST_URL("test_url"),
		TEST_USER("test_user"),
		TEST_PASS("test_pass"),
		TEST_SCHEMA("test_schema"),
		
		URL("url"),
		USER("user"),
		PASS("pass"),
		SCHEMA("schema");
		
		private String key;
		
		Key(String key){
			this.key = key;
		}
		
		@Override
		public String toString() {
			return key;
		}
	}
	
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	
	// Database credentials
	private final Properties PROPERTIES;
	private final String DB_URL;
	private final String USER;
	private final String PASS;
	private final String SCHEMA;
	
	
	private final static DbManager instance = new DbManager();
	private static Connection connection;
	
	private DbManager() {
//		this.PROPERTIES = getProperties();
//		this.DB_URL ="jdbc:"+ getProperty(Key.URL);
//		this.USER = getProperty(Key.USER);
//		this.PASS = getProperty(Key.PASS);
//		this.SCHEMA = getProperty(Key.SCHEMA);
		this.PROPERTIES = null;

		this.DB_URL = "jdbc:mysql://95.87.227.30:30249/Picssshare";
		this.USER = "Philip";
		this.PASS = "Picssshare97?";


		this.SCHEMA = "Picssshare";
		
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL,USER,PASS);
		} catch (ClassNotFoundException e) {
			System.err.println("Driver loading error.");
		} catch(SQLException se) {
			System.err.println("Connection creating error: " + se.getMessage());
		}
		
		useStatment(this.SCHEMA);
	}
	
	public static DbManager getInstance() {
		return instance;
	}
	
	public Connection getConnection() {
		return connection;
	}

	private static Properties getProperties() {
		Properties properties = new Properties();
		try {
			properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("src/db.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}
	
	private String getProperty(Key key) {
		String value = PROPERTIES.getProperty(key.toString());
		return value;
	}
	
	private void useStatment(String schema) {
		String sql = "USE " + schema;
		PreparedStatement stmt;
		
		try {
			stmt = connection.prepareStatement(sql);
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Problem during USE statment: " + e.getMessage());
		}
	}
	
	
	
	

}
