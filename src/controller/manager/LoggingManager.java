package controller.manager;

import java.sql.SQLException;

import model.UserBean;
import model.dao.UserDao;

public class LoggingManager {
	
	public class UsernameException extends Exception{
		
	}
	
	public class EmptyUsernameException extends UsernameException{
		
	}
	
	public class InvalidUsernameCharactersException extends UsernameException{
		
	}
	
	public class UsernameAlreadyExistsException extends UsernameException{
		
	}
	
	private static LoggingManager instance;
	
	private LoggingManager() {
		
	}
	
	public static synchronized LoggingManager getInstance() {
		if(instance == null) {
			instance = new LoggingManager();
		}
		return instance;
	}
	
	//========================REGISTER PART===================================//
	
//		public synchronized void register(UserBean user) {
//			boolean correctUsername = false;
//			boolean correctPassword = false;
//			boolean correctFirstName = false;
//			boolean correctLastName = false;
//			boolean correctEmail = false;
//			
//			if(validateUsername(user.getUsername())){
//				correctUsername = true;
//			}
//			else {
//				System.out.println("Incorrect username!");
//			}
//			
//			if(validatePassword(user.getPassword())) {
//				correctPassword = true;
//			}
//			else {
//				System.out.println("Incorrect password!");
//				System.out.println("Please note that the password must contains at least one uppercase letter"
//						+ ", one lowercase letter, one digit, one special character and must be at least 8 characters long!");
//			}
//			
//			//unnecessary fields
//			if(user.getFirstName()!=null && validateFirstName(user.getFirstName())) {
//				correctFirstName = true;
//			}
//			else {
//				System.out.println("First name error!");
//			}
//			
//			if(user.getLastName() != null && validateLastName(user.getLastName())) {
//				correctLastName = true;
//			}
//			else {
//				System.out.println("Last name error!");
//			}
//			//end of unnecessary fields
//			
//			if(validateEmailAddress(user.getEmail())) {
//				correctEmail = true;
//			}
//			else {
//				System.out.println("Incorrect email address!");
//			}
//			//check the if the input data is ok
//			if(correctUsername && correctPassword && correctEmail) {
//				//if it is ok, check if the user already exists
//				if(UserDao.getInstance().getUsers().containsKey(user.getUsername())) {
//					System.out.println("User already exists. ");
//				}
//				else {
//					//add in collection
//					UserDao.getInstance().getUsers().put(user.getUsername(),user); 
//					//add in db
//					UserDao.getInstance().registerUser(user);
//					System.out.println(user.getUsername() + " has been successfully registered.");
//				}
//			}
//		}
		
		public synchronized boolean register(String username, String password, String email){
			
			//Username validating
			boolean validUsername = false;
			try {
				validUsername = validateUsername(username);
			}catch(EmptyUsernameException empt){
				System.out.println("Username is empty.");
			}catch(InvalidUsernameCharactersException ch) {
				System.out.println("Username has illigal characters.");
			}catch(UsernameAlreadyExistsException exis) {
				System.out.println("Username already exists.");
			}catch(UsernameException e) {
				System.out.println("Username is not valid.");
			}
			if(!validUsername) {
				return false;
			}
			
			if(!validatePassword(password)) {
				System.err.println("Weak password");
				return false;
			}
			//TODO check if already exists in runtime collection
			if(!validateEmailAddress(email)) {
				System.err.println("Email is not valid");
				return false;
			}
			
			//if data is valid user obj is created
			UserBean user = new UserBean(username, password, email);
			
			//adding to DB and collections
			try {
				UserDao.getInstance().registerUser(user);
			}catch (SQLException e) {
				System.out.println("Registering to DB problem: " + e.getMessage());
				return false;
			}
			
			System.out.println("Registration of " + user.getUsername() + " is successfull!");
			return true;
		}
		// validate username
		public boolean validateUsername(String username) throws UsernameException {
			if(username == null || username.isEmpty()) {
				throw new EmptyUsernameException();
			}
			if(!username.matches("^[a-zA-Z0-9]+([_ -]?[a-zA-Z0-9])*$")) {
				throw new InvalidUsernameCharactersException();
			}
			if(UserDao.getInstance().getUsers().containsKey(username)) {
				throw new UsernameAlreadyExistsException();
			}
			return true;
		}

		/*
		 * This regex will enforce these rules for password:
		 * 
		 * At least one upper case English letter, (?=.*?[A-Z])
		 * At least one lower case English letter, (?=.*?[a-z])
		 * At least one digit, (?=.*?[0-9]) 
		 * Minimum eight in length .{8,} (with the anchors)
		 */
		public boolean validatePassword(String password) {
			return (password != null && !password.isEmpty()
					&& password.matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,}$")) ? true : false;
		}

		// validate first name
		public boolean validateFirstName(String firstName) {
			return firstName.matches("[A-Z][a-zA-Z]*");
		}

		// validate last name
		public boolean validateLastName(String lastName) {
			return lastName.matches("[a-zA-z]+([ '-][a-zA-Z]+)*");
		}

		// validate email address
		public boolean validateEmailAddress(String email) {
			String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
			java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
			java.util.regex.Matcher m = p.matcher(email);
			return m.matches();
		}

		//================================================================//
			
		//Logging by username and password
		public boolean login(String username, String password) {
			UserBean u = UserDao.getInstance().getUsers().get(username);
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

}
