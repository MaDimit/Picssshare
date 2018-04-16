package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.TreeSet;

import controller.manager.CollectionsManager;
import model.UserBean;
import model.post.PostBean;

public class FeedDao extends Dao{
	private PostDao postDao;

	private static FeedDao instance = null;
	private TreeSet<PostBean> posts;

	public static synchronized FeedDao getInstance() {
		if (instance == null) {
			instance = new FeedDao();
		}
		return instance;
	}

	private FeedDao()  {
		super();
	}

	public TreeSet<PostBean> getAllPostsForUser(UserBean u) {
		TreeSet<PostBean> userPosts = new TreeSet<>((p1,p2)->(p1.getDate().compareTo(p2.getDate())));
		for (PostBean post : this.postDao.getPosts().values()) {
			if (post.getId() == u.getId()) {
				userPosts.add(post);
			}
		}
		return userPosts;

	}
	
	public ArrayList<Integer> getTrendingPosts(){
		
		//ArrayList used because the order from the sql query is important
		ArrayList<Integer> postsID = new ArrayList<>();
		Connection conn = dbManager.getConnection();
		PreparedStatement stmt;
		ResultSet rs = null;
		
		//GET ALL POSTS ONE WEEK FROM NOW SORTED BY LIKES
		LocalDateTime now = LocalDateTime.now();
		now=now.minusDays(7);
		String time = "'"+now.getYear()+"-"+now.getMonthValue()+"-"+now.getDayOfMonth()+" "+now.getHour()+":"+now.getMinute()+":"+now.getSecond()+"'";
		String sql = "SELECT * FROM posts WHERE date > " + time + " ORDER BY likes DESC";
		try{
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				
				postsID.add(id);	
			}
		}catch(SQLException e) {
			System.out.println("Problem during filling users collection: " + e.getMessage());
		}
		return postsID;
	}

}
