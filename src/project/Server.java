package project;

import java.util.Map;

import project.user.User;

import java.util.HashSet;


public class Server {

//	public Server() {
//		this.users = new ArrayList<User>();
//	}
	
	public void showUsers() {
		for(Map.Entry<String, User> u: UserLogging.getUsers().entrySet()) {
			System.out.println(u.getValue());
		}
	}

}
