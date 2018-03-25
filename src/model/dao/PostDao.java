package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.TreeSet;

import model.CommentBean;
import model.post.PostBean;

public class PostDao {
	// collection holding the posts is in the FeedDao
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/picssshare";
	// Database credentials
	static final String USER = "root";
	static final String PASS = "root";
	static Connection conn = null;
	static Statement stmt = null;
	private HashMap<PostBean, TreeSet<CommentBean>> post_comments;
	private static PostDao instance = null;
	public static CommentDao commentDao;

	// singleton instance used in commentmanager
	public static PostDao getInstance() {
		if (instance == null) {
			instance = new PostDao();

		}
		return instance;
	}

	public PostDao() {
		this.commentDao = new CommentDao();
		this.post_comments = new HashMap<>();
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
	}

	public HashMap<PostBean, TreeSet<CommentBean>> getPost_comments() {
		return post_comments;
	}

	public void addPost(PostBean p) {
		//add in collection
		this.post_comments.put(p, new TreeSet<CommentBean>((c2,c1)->(c2.getPostTime().compareTo(c2.getPostTime()))));
		
		try {
			String sql;
			sql = "INSERT INTO `picssshare`.`post` (`id`,`likes`, `dislikes`, `date`,  `poster_id`,`url`) VALUES ('"
					+ p.getId() + "', '" + p.getLikes() + "', '" + p.getDislikes() + "', '" + p.getDate() + "', '"
					+ p.getPoster().getId() + "', '" + p.getUrl() + "')";
			stmt.executeUpdate(sql);

			// STEP 6: Clean-up environment

			// stmt.close();
			// conn.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		}
		// } finally {
		// //finally block used to close resources
		// try {
		// if (stmt != null)
		// stmt.close();
		// } catch (SQLException se2) {
		// }// nothing we can do
		// try {
		// if (conn != null)
		// conn.close();
		// } catch (SQLException se) {
		// se.printStackTrace();
		// }//end finally try
		// end try
		System.out.println("Goodbye!");

	}
	
	public void deletePost(PostBean p) {
		//remove from collection
				this.post_comments.remove(p);
				
				try {
					String sql;
					sql = "Delete from `picssshare`.`post` where id="+p.getId();
							
					stmt.executeUpdate(sql);

					// STEP 6: Clean-up environment

					// stmt.close();
					// conn.close();
				} catch (SQLException se) {
					// Handle errors for JDBC
					se.printStackTrace();
				} catch (Exception e) {
					// Handle errors for Class.forName
					e.printStackTrace();
				}
				// } finally {
				// //finally block used to close resources
				// try {
				// if (stmt != null)
				// stmt.close();
				// } catch (SQLException se2) {
				// }// nothing we can do
				// try {
				// if (conn != null)
				// conn.close();
				// } catch (SQLException se) {
				// se.printStackTrace();
				// }//end finally try
				// end try
				System.out.println("Goodbye!");
	}
	
	

	class CommentDao {

		public CommentDao() {
			
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
		}

		public void addCommentInDB(CommentBean c) {
			// put in collection
//			if (!post_comments.containsKey(c.getBelongedPost())) {
//
//				post_comments.put(c.getBelongedPost(),
//						new TreeSet<CommentBean>((c1, c2) -> (c2.getPostTime().compareTo(c2.getPostTime()))));
//			}
			TreeSet<CommentBean> comments = post_comments.get(c.getBelongedPost());
			comments.add(c);
			post_comments.put(c.getBelongedPost(), comments);

			// insert in db
			try {
				String sql;
				sql = "INSERT INTO `picssshare`.`comments` (`id`, `comment_poster_id`, `postTime`, `content`, `belonged_post_id`) VALUES ('" + c.getId() + "', '"
						+ c.getPoster().getId() + "', '" + c.getPostTime() + "', '" + c.getContent() + "', '"
						+ c.getBelongedPost().getId() + "')";
				stmt.executeUpdate(sql);

				// STEP 6: Clean-up environment

				// stmt.close();
				// conn.close();
			} catch (SQLException se) {
				// Handle errors for JDBC
				se.printStackTrace();
			} catch (Exception e) {
				// Handle errors for Class.forName
				e.printStackTrace();
			}
			// } finally {
			// //finally block used to close resources
			// try {
			// if (stmt != null)
			// stmt.close();
			// } catch (SQLException se2) {
			// }// nothing we can do
			// try {
			// if (conn != null)
			// conn.close();
			// } catch (SQLException se) {
			// se.printStackTrace();
			// }//end finally try
			// end try
			System.out.println("Goodbye!");
		}

		public void deleteComment(CommentBean c) {

			TreeSet<CommentBean> comments = post_comments.get(c.getBelongedPost());
			comments.remove(c);
			post_comments.put(c.getBelongedPost(), comments);

			// remove from db
			try {
				String sql;
				sql = "DELETE FROM `picssshare`.`comments` WHERE id=" + c.getId();
				stmt.executeUpdate(sql);

				// STEP 6: Clean-up environment

				// stmt.close();
				// conn.close();
			} catch (SQLException se) {
				// Handle errors for JDBC
				se.printStackTrace();
			} catch (Exception e) {
				// Handle errors for Class.forName
				e.printStackTrace();
			}
			// } finally {
			// //finally block used to close resources
			// try {
			// if (stmt != null)
			// stmt.close();
			// } catch (SQLException se2) {
			// }// nothing we can do
			// try {
			// if (conn != null)
			// conn.close();
			// } catch (SQLException se) {
			// se.printStackTrace();
			// }//end finally try
			// end try
			System.out.println("Goodbye!");
		}

	}

}
