package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;

import com.mysql.jdbc.Statement;

import model.notification.NotificationBean;

public class NotificationDao extends Dao{
	private static NotificationDao instance = null;

	private NotificationDao() {
		super();
	}
	
	// singleton instance used in commentmanager
		public static NotificationDao getInstance() {
			if (instance == null) {
				instance = new NotificationDao();
			}
			return instance;
		}

	public void addNotificationInDB(NotificationBean n) throws SQLException {
		Connection conn = dbManager.getConnection();
		String sql = "INSERT INTO notifications (`date`, `seen`, `receiver_id`, `causer_id`, `description`) VALUES (?,?,?,?,?)";
		PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		stmt.setObject(1, Timestamp.valueOf(n.getDate()));
		stmt.setInt(2, n.isSeen());
		stmt.setInt(3, n.getReceiverID());
		stmt.setInt(4, n.getCauserID());
		stmt.setString(5, n.getDescription());
		stmt.executeUpdate();
		ResultSet rs = stmt.getGeneratedKeys();
		if (rs.next()) {
			n.setId(rs.getInt(1));
		} else {
			throw new SQLException("Setting id for the notification failed.");
		}

	}

}
