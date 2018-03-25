package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.TreeSet;

import model.CommentBean;
import model.post.PostBean;

public class CommentDao {
	 static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	    static final String DB_URL = "jdbc:mysql://localhost/picssshare";
	    //  Database credentials
	    static final String USER = "root";
	    static final String PASS = "root";
	    static Connection conn = null;
	    static Statement stmt = null;
	    
	    private static CommentDao instance = null;
	    //singleton instance used in commentmanager
	    public static CommentDao getInstance() {
	        if (instance == null) {
	            instance = new CommentDao();

	        }
	        return instance;
	    }
	    //????????
	    HashMap<PostBean, TreeSet<CommentBean>> post_comments;
		public CommentDao() {
			this.post_comments = new HashMap<>();
			 //STEP 2: Register JDBC driver
	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	            //STEP 3: Open a connection
	            System.out.println("Connecting to database...");
	            conn = DriverManager.getConnection(DB_URL, USER, PASS);
	            conn = DriverManager.getConnection(DB_URL, USER, PASS);
	            //STEP 4: Execute a query
	            System.out.println("Creating statement...");
	            stmt = conn.createStatement();

	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		}
		
		public void addCommentInDB(CommentBean c) {
			 //put in collection
			if(this.post_comments.containsKey(c.getBelongedPost())) {
				
				this.post_comments.put(c.getBelongedPost(), new TreeSet<CommentBean>((c1,c2)->(c2.getPostTime().compareTo(c2.getPostTime()))));
			}
			TreeSet<CommentBean> comments = this.post_comments.get(c.getBelongedPost());
			comments.add(c);
	        this.post_comments.put(c.getBelongedPost(), comments);
	        
	        //insert in db
	        try {
	            String sql;
	            sql = "INSERT INTO `picssshare`.`comments` (`comment_poster_id`, `postTime`, `content`, `belonged_post_id`) VALUES ('" + c.getPoster().getId() + "', '" + c.getPostTime()+ "', '" + c.getContent()+ "', '" + c.getBelongedPost().getId() + "')";
	            stmt.executeUpdate(sql);

	            //STEP 6: Clean-up environment

//	            stmt.close();
//	            conn.close();
	        } catch (SQLException se) {
	            //Handle errors for JDBC
	            se.printStackTrace();
	        } catch (Exception e) {
	            //Handle errors for Class.forName
	            e.printStackTrace();
	        }
//	        } finally {
//	            //finally block used to close resources
//	            try {
//	                if (stmt != null)
//	                    stmt.close();
//	            } catch (SQLException se2) {
//	            }// nothing we can do
//	            try {
//	                if (conn != null)
//	                    conn.close();
//	            } catch (SQLException se) {
//	                se.printStackTrace();
//	            }//end finally try
	        //end try
	        System.out.println("Goodbye!");
	    }
		
		public void deleteComment(CommentBean c) {
			
			TreeSet<CommentBean> comments = this.post_comments.get(c.getBelongedPost());
			comments.remove(c);
	        this.post_comments.put(c.getBelongedPost(), comments);
	        
	        //remove from db
	        try {
	            String sql;
	            sql = "DELETE FROM `picssshare`.`comments` WHERE id="+c.getId();
	            stmt.executeUpdate(sql);

	            //STEP 6: Clean-up environment

//	            stmt.close();
//	            conn.close();
	        } catch (SQLException se) {
	            //Handle errors for JDBC
	            se.printStackTrace();
	        } catch (Exception e) {
	            //Handle errors for Class.forName
	            e.printStackTrace();
	        }
//	        } finally {
//	            //finally block used to close resources
//	            try {
//	                if (stmt != null)
//	                    stmt.close();
//	            } catch (SQLException se2) {
//	            }// nothing we can do
//	            try {
//	                if (conn != null)
//	                    conn.close();
//	            } catch (SQLException se) {
//	                se.printStackTrace();
//	            }//end finally try
	        //end try
	        System.out.println("Goodbye!");
		}
		
		public HashMap<PostBean, TreeSet<CommentBean>> getPost_comments() {
			return post_comments;
		}
}
