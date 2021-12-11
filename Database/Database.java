package Database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Database {

	private Statement statement;
	
	
	/* Function connect
	 * Connects to the database
	 * Returns true if connected
	 * */      
	public boolean connect(){
	    try {
	    	Connection conn= DriverManager.getConnection("jdbc:mysql://localhost/MailSniper","root","");  // DEMO
	        statement = conn.createStatement();
	        System.out.print("Database Connected");
	        return true;
	    } catch (Exception e) {
	        System.out.print("Database Not Connected");
	        e.printStackTrace();
	        return false;
	    }
	}
	
	 
	/* Function insert
	 * inserts email into the admin_users table
	 * */
	public void insert(String email){
	    try{
	        String insertquery = "INSERT INTO `admin_users`(`email`) VALUES ('" + email + "')";
	        statement.executeUpdate(insertquery);
	        System.out.print("Inserted");
	    } catch(Exception e){
	        System.out.print("Not Inserted");
	    }
	}
	
	
	
	/* Function insert_rule
	 * inserts a new GLOBAL rule to the rules table
	 * */
	public void insert_rule(String priority, String from, String subject, String content){
		try{
			String insertquery = "INSERT INTO `rules`(`priority`, `from_rule`, `subject_rule`, `content_rule`) VALUES ('" + priority + "', '" + from + "', '" + subject + "', '" + content + "')";
		    statement.executeUpdate(insertquery);
		    System.out.print("Inserted");
		} catch(Exception e){
		    System.out.print("Not Inserted");
		}
	}
	
	
	
	
	/* Function view
	 * returns the set of admin users
	 * */
	public ResultSet view(){
	    try {
	        String insertquery = "SELECT * FROM admin_users";
	        ResultSet result = statement.executeQuery(insertquery);
	        return result; 
	    } catch (SQLException ex) {
	        System.out.println("Problem To Show Data");
	        ex.printStackTrace();
	    }
	    
		return null; // if query fails nothing will be returned
	}

	
	
	/* Function update
	 * Changes email address to a new email address
	 * */
	public void update(String newEmail, String oldEmail){
		 try {
	        String insertquery = "UPDATE admin_users SET email = '" + newEmail + "' WHERE email = '" + oldEmail + "'";
	        statement.executeUpdate(insertquery);
	        System.out.println("Updated");
	    } catch (SQLException ex) {
	        System.out.println(ex.getMessage());
	    }
	}

	
	/* Function delete
	 * Deletes a email from the admin_users table
	 * */
	public void delete(String email){
	     try {
	        String insertquery = "DELETE FROM `admin_users` WHERE email = '" + email + "'";
	        statement.executeUpdate(insertquery);
	        System.out.println("Deleted");
	     } catch (SQLException ex) {
	        System.out.println(ex.getMessage());
	     }
	}
	
	
	/* Function view_rules
	 * Returns the set of GLOBAL rules
	 * */
	public ResultSet view_rules(){
		try {
			String insertquery = "SELECT * FROM rules";
		    ResultSet result = statement.executeQuery(insertquery);
		    return result; 
		} catch (SQLException ex) {
		    System.out.println("Problem To Show Data");
		    ex.printStackTrace();
		}
		    
			return null; // if query fails nothing will be returned
	}
	
	
	
	/* Function delete_rule
	 * deletes a GLOBAL rule from the rules table
	 * */
	public void delete_rule(int id){
		try {
			String insertquery = "DELETE FROM `rules` WHERE id = '" + id + "'";
		    statement.executeUpdate(insertquery);
		    System.out.println("Deleted");
		} catch (SQLException ex) {
		    System.out.println(ex.getMessage());
		}
	}	
		
		
}
