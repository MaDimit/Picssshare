package project;

import java.util.HashMap;

import project.user.User;

public class UserLogging {
	
	public static int usersID = 100000;
	private static HashMap<String,User> users = new HashMap<>();
	
	//========================REGISTER PART===================================//
	
	public static void register(User user) {
		boolean correctUsername = false;
		boolean correctPassword = false;
		boolean correctFirstName = false;
		boolean correctLastName = false;
		boolean correctEmail = false;
		
		if(validateUsername(user.getUsername())){
			correctUsername = true;
		}
		else {
			System.out.println("Incorrect username!");
		}
		
		if(validatePassword(user.getPassword())) {
			correctPassword = true;
		}
		else {
			System.out.println("Incorrect password!");
			System.out.println("Please note that the password must contains at least one uppercase letter"
					+ ", one lowercase letter, one digit, one special character and must be at least 8 characters long!");
		}
		
		if(validateFirstName(user.getFirstName())) {
			correctFirstName = true;
		}
		else {
			System.out.println("Incorrect first name!");
		}
		
		if(validateLastName(user.getLastName())) {
			correctLastName = true;
		}
		else {
			System.out.println("Incorrect last name!");
		}
		
		if(validateEmailAddress(user.getEmail())) {
			correctEmail = true;
		}
		else {
			System.out.println("Incorrect email address!");
		}
		
		if(correctUsername && correctPassword && correctFirstName && correctLastName && correctEmail) {
			user.setId(usersID);
			usersID++;
			users.put(user.getUsername(),user);
			System.out.println(user.getUsername() + " has been successfully registered.");
			
		}
	}

	// validate username
	public static boolean validateUsername(String username) {
		return (username != null && !username.isEmpty() && username.matches("^[a-zA-Z0-9]+([_ -]?[a-zA-Z0-9])*$"))
				? true
				: false;
	}

	/*
	 * This regex will enforce these rules for password:
	 * 
	 * At least one upper case English letter, (?=.*?[A-Z])
	 * At least one lower case English letter, (?=.*?[a-z])
	 * At least one digit, (?=.*?[0-9]) 
	 * At least one special character, (?=.*?[#?!@$%^&*-]) 
	 * Minimum eight in length .{8,} (with the anchors)
	 */
	public static boolean validatePassword(String password) {
		return (password != null && !password.isEmpty()
				&& password.matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,}$")) ? true : false;
	}

	// validate first name
	public static boolean validateFirstName(String firstName) {
		return firstName.matches("[A-Z][a-zA-Z]*");
	}

	// validate last name
	public static boolean validateLastName(String lastName) {
		return lastName.matches("[a-zA-z]+([ '-][a-zA-Z]+)*");
	}

	// validate email address
	public static boolean validateEmailAddress(String email) {
		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
		java.util.regex.Matcher m = p.matcher(email);
		return m.matches();
	}

	//================================================================//
	
	//Logging by user object
	public static boolean login(User user) {
		return login(user.getUsername(), user.getPassword());
	}
	
	//Logging by username and password
	public static boolean login(String userName, String password) {
		User u = users.get(userName);
		if(u == null) {
			System.out.println("Wrong username!");
			return false;
		}
		if(!u.getPassword().equals(password)){
			System.out.println("Wrong password");
			return false;
		}
		return true;
	}
	
	
	public static HashMap<String, User> getUsers() {
		return users;
	}
	
}