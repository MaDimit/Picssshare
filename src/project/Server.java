//package project;
//
//import java.util.Map;
//
//import model.UserBean;
//import project.content.Post;
//
//import java.util.HashMap;
//import java.util.HashSet;
//
//
//public class Server {
//	
//	private static Server server;
//	private HashMap<String,UserBean> users;
//	private HashSet<Post> posts;
//	
//	private Server() {
//		this.users = new HashMap<>();
//		this.posts = new HashSet<>();
//	}
//	
//	public static Server getInstance() {
//		if(server == null) {
//			server = new Server();
//		}
//		return server;
//	}
//	
//	public void showUsers() {
//		System.out.println("+++++++++USERS ON THE SERVER++++++++++");
//		for(Map.Entry<String, UserBean> u: this.users.entrySet()) {
//			System.out.println(u.getValue());
//		}
//		System.out.println("++++++++++++++++++++++++++++++++++++");
//	}
//	
//	public void addPost(Post p) {
//		if(p != null) {
//			this.posts.add(p);
//		}
//	}
//	
//	public HashSet<Post> getPosts() {
//		return posts;
//	}
//	
//	public HashMap<String, UserBean> getUsers() {
//		return this.users;
//	}
//
//}
