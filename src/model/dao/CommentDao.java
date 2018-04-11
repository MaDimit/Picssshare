package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.ZoneOffset;

import controller.manager.CollectionsManager;
import model.CommentBean;
import model.post.PostBean;
public class CommentDao extends Dao{
	
	private static CommentDao instance = null;
	
	private CommentDao() {
	}

	// singleton instance used in commentdao
	public static CommentDao getInstance() {
		if (instance == null) {
			instance = new CommentDao();
		}
		return instance;
	}
	
	public void addComment(CommentBean comment) throws SQLException{
		Connection conn = dbManager.getConnection();
		String sql = "INSERT INTO comments (poster_id, date, content, post_id) VALUES (?,?,?,?)";
		PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		stmt.setInt(1, comment.getPoster().getId());
		stmt.setObject(2, comment.getPostTime());
		stmt.setString(3, comment.getContent());
		stmt.setInt(4, comment.getBelongedPost().getId());
		stmt.executeUpdate();
		
		ResultSet generatedKeys = stmt.getGeneratedKeys();
		if (generatedKeys.next()) {
			comment.setId(generatedKeys.getInt(1));
	    }else {
	       throw new SQLException("Creating comment failed, no ID obtained.");
	    }
		
		//adding comment to post
		comment.getBelongedPost().addComment(comment);
	}
	
	public void deleteComment(CommentBean comment) throws SQLException{
		Connection conn = dbManager.getConnection();
		String sql = "DELETE FROM comments WHERE id=(?)";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, comment.getId());
		stmt.executeUpdate();
		
		//removing comment from post
		//comment.getBelongedPost().removeComment(comment);
	}
	public void deleteCommentByID(int postID, int id) throws SQLException{

		
		Connection conn = dbManager.getConnection();
		String sql = "DELETE FROM comments WHERE id=(?)";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, id);
		stmt.executeUpdate();
		

		//removing comment from post
		CollectionsManager.getInstance().getPostsByID().get(postID).removeCommentByID(id);
		
		
	}

}
