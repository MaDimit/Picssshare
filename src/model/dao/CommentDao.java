package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.CommentBean;
import model.post.PostBean;
public class CommentDao {
	private static CommentDao instance = null;
	private DbManager dbManager;
	
	private CommentDao() {
		this.dbManager = DbManager.getInstance();
	}

	// singleton instance used in commentdao
	public static CommentDao getInstance() {
		if (instance == null) {
			instance = new CommentDao();
		}
		return instance;
	}

	public void addCommentInDB(PostBean p, CommentBean c) throws Exception {
		// STEP 2: Register JDBC driver
		Connection conn = null;
		Statement stmt = null;
		
			Class.forName("com.mysql.jdbc.Driver");
			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = dbManager.getConnection();
			// STEP 4: Execute a query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();

		

		// insert in db
		
			String sql;
			sql = "INSERT INTO `picssshare_test`.`comments` (`comment_poster_id`,`postTime`, `content`, `belonged_post_id`) VALUES ('"
					+ c.getPoster().getId() + "', '" + c.getPostTime() + "', '" + c.getContent() + "', '"
					+ c.getBelongedPost().getId() + "')";
			stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = stmt.getGeneratedKeys();
			int id = 0;
			if (rs != null && rs.next()) {
				id = rs.getInt(1);
			}
			c.setId(id);

		// put in collection
				if (PostDao.getInstance().getPosts().containsKey(p.getId())) {
					PostDao.getInstance().getPosts().get(p.getId()).addComment(c);
				} else {
					System.out.println("No such post!");
				}
	}

	public void deleteComment(CommentBean c) throws Exception {
		Connection conn = null;
		Statement stmt = null;
	
			Class.forName("com.mysql.jdbc.Driver");
			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = dbManager.getConnection();
			// STEP 4: Execute a query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();


		// remove comment from post collection
		PostDao.getInstance().getPosts().get(c.getBelongedPost().getId()).removeComment(c.getId());

		// remove from db
		
			String sql;
			sql = "DELETE FROM `picssshare_test`.`comments` WHERE id=" + c.getId();
			stmt.executeUpdate(sql);


	}

}
