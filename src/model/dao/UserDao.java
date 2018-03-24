package model.dao;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import model.UserBean;

public class UserDao {
	 // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/picssshare";
    //  Database credentials
    static final String USER = "root";
    static final String PASS = "root";
    static Connection conn = null;
    static Statement stmt = null;

    private static HashMap<String, UserBean> users;
    private static UserDao instance = null;
    //singleton
    public UserDao() {
        this.users = new HashMap<>();
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
    //method for printing all the users ## maybe used only when testing the connection with database
    public static void printCollectionInfo() {
        for (Map.Entry<String, UserBean> entry : UserDao.users.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue().getFirstName());
        }
    }
    //singleton instance used in usermanager
    public static UserDao getInstance() {
        if (instance == null) {
            instance = new UserDao();

        }
        return instance;
    }


    public static void getAllUsersInfo() {

        try {
            String sql;
            sql = "select * from users";
            ResultSet rs = stmt.executeQuery(sql);

            //Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");

                //Display values
                System.out.print("ID: " + id);
                System.out.print(", username: " + username);
                System.out.print(", password: " + password);
                System.out.print(", firstName: " + firstName);
                System.out.print(", lastName: " + lastName);
                System.out.println();
            }
            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        
//        } finally {
//            //finally block used to close resources
//            try {
//                if (stmt != null)
//                    stmt.close();
//            } catch (SQLException se2) {
//            }// nothing we can do
//            try {
//                if (conn != null)
//                    conn.close();
//            } catch (SQLException se) {
//                se.printStackTrace();
//            }//end finally try
        }//end try
        System.out.println("Goodbye!");
    }

    public void registerUser(UserBean u) {
        //put in collection
        this.users.put(u.getUsername(), u);
        //insert in db
        try {
            String sql;
            sql = "INSERT INTO `picssshare`.`users` (`id`,`username`, `password`, `firstName`, `lastName`, `email`) VALUES ('" + u.getId() + "', '" + u.getUsername() + "', '" + u.getPassword() + "', '" + u.getFirstName() + "', '" + u.getLastName() + "', '" + u.getEmail() + "')";
            stmt.executeUpdate(sql);

            //STEP 6: Clean-up environment

//            stmt.close();
//            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        }
//        } finally {
//            //finally block used to close resources
//            try {
//                if (stmt != null)
//                    stmt.close();
//            } catch (SQLException se2) {
//            }// nothing we can do
//            try {
//                if (conn != null)
//                    conn.close();
//            } catch (SQLException se) {
//                se.printStackTrace();
//            }//end finally try
        //end try
        System.out.println("Goodbye!");
    }
}
