package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class Dao {
	
	public enum Tables{
		BOOKMARKS("bookmarker_post"),
		COMMENTS("comments"),
		LIKED("liker_post"),
		NOTIFICATIONS("notifications"),
		POSTS("posts"),
		SUBSCRIPTIONS("subscriber_subscribed"),
		USERS("users");
		
		private final String table;
		
		private Tables(String table) {
			this.table = table;
		}
		
		@Override
		public String toString() {
			return table;
		}
	}
	
	protected static DbManager dbManager;
	
	protected Dao() {
		dbManager = DbManager.getInstance();
	}
	
	public static ResultSet getAll(Tables table) {
		Connection conn = DbManager.getInstance().getConnection();
		PreparedStatement stmt;
		ResultSet rs = null;
		
		//Fetching users from DB
		String sql = "SELECT * FROM " + table.toString();
		try{
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
		}catch(SQLException e) {
			System.out.println("Getting all " + table.toString() + " failed: " + e.getMessage());
		}
		return rs;
	}
}
