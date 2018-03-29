package model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
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
//	private final String SCHEMA;
	
	
	private static DbManager instance;
	private static Connection connection;
	
	private DbManager() {
		this.PROPERTIES = getProperties();
		this.DB_URL = getProperty(Key.TEST_URL);
		this.USER = getProperty(Key.TEST_USER);
		this.PASS = getProperty(Key.TEST_PASS);
//		this.SCHEMA = getProperty(Key.TEST_SCHEMA);
		
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL,USER,PASS);
		} catch (ClassNotFoundException e) {
			System.err.println("Driver loading error.");
		} catch(SQLException se) {
			System.err.println("Connection creating error: " + se.getMessage());
		}
	}
	
	public static synchronized DbManager getInstance() {
		if(instance == null) {
			instance = new DbManager();
		}
		return instance;
	}
	
	public Connection getConnection() {
		return connection;
	}

	private static Properties getProperties() {
		Properties properties = new Properties();
		try(FileReader reader = new FileReader("db.properties");) {
			properties.load(reader);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}
	
	private String getProperty(Key key) {
		String value = PROPERTIES.getProperty(key.toString());
		return value;
	}
	
	
	
	

}
