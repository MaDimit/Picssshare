package controller.manager;

import java.sql.SQLException;

import model.UserBean;
import model.dao.UserDao;

public class LoggingManager {
	
	public static class RegistrationException extends Exception{
		public RegistrationException(String msg) {
			super(msg);
		}
	}

	public static class LoggingException extends Exception{
		public LoggingException(String msg) {
			super(msg);
		}
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
		
		public synchronized UserBean register(String username, String password, String email) throws RegistrationException{
			
			//Username validating
			boolean validUsername = false;
			
			validUsername = validateUsername(username);
			
			if(!validatePassword(password)) {
				throw new RegistrationException("Weak password");
			}

			if(!validateEmailAddress(email)) {
				throw new RegistrationException("Email is not valid");
			}
			
			//if data is valid user obj is created
			UserBean user = new UserBean(username, password, email);
			
			//adding to DB and collections
			try {
				UserDao.getInstance().registerUser(user);
			}catch (SQLException e) {
				System.out.println("Registering to DB problem: " + e.getMessage());
				throw new RegistrationException("Data base connection problem!");
			}
			
			System.out.println("Registration of " + user.getUsername() + " is successfull!");
			return user;
		}
		// validate username
		public boolean validateUsername(String username) throws RegistrationException {
			if(username == null || username.isEmpty()) {
				throw new RegistrationException("Username can't be empty!");
			}
			if(!username.matches("^[a-zA-Z0-9]+([_ -]?[a-zA-Z0-9])*$")) {
				throw new RegistrationException("Invalid characters in username!");
			}
			if(CollectionsManager.getInstance().alreadyExists(username)) {
				throw new RegistrationException("User with this name already exists!");
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
			return firstName.matches("[a-zA-z]+([ '-][a-zA-Z]+)*");
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
		public UserBean login(String username, String password) throws LoggingException {
			UserBean u = CollectionsManager.getInstance().getUser(username);
			if(u == null) {
				throw new LoggingException("Wrong username!");
			}
			if(!u.getPassword().equals(password)){
				throw new LoggingException("Wrong password!");
			}
			return u;
		}

}
