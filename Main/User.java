package Main;


public class User extends Mail{

	public String username;
	public String password;
	public static boolean connection_status = false;
	public String server = "webmail.blomand.net"; // This is the default server value
	public boolean admin = false;
	
	
	// get a username and password and attempt to make a connection
	public static boolean login(String username, String password) {
		
		if(connect(username, password)) {
			username = username;
			password = password;
			connection_status = true;
			return true;
		}
		
		return false;
	}
	
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
	
}
