package model.dao;

import model.CommentBean;
import model.UserBean;
import model.post.PostBean;

public class PostDaoTest {
	public static void main(String[] args) {
		PostDao postDao = PostDao.getInstance();
		UserBean ub = new UserBean("username", "password", "email");
		UserDao u = UserDao.getInstance();
		u.registerUser(ub);
		System.out.println(u.getUsers().get(ub.getUsername()));
		PostBean post = new PostBean(u.getUsers().get(ub.getUsername()), "url", ub.getId());
		postDao.addPost(post);
		CommentBean c = new CommentBean(ub, "cool", post);
		//PostDao.commentDao.addCommentInDB(c);
	}

}
