package Main;


import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import UI.Control_Panel;
import UI.Login;

public class Driver {

	public static void main(String[] args) throws UnknownHostException, IOException {
		
		// first check to see if the app is listening on the port
		
		try{
			Socket socket = new Socket("127.0.0.1", 9897);
			System.out.println("Connected to server! Attempting to send data..");
			System.out.println("Connected to port 127.0.0.1:5050");
			//Control_Panel.frame.setVisible(true);
			OutputStream outputStream = socket.getOutputStream();
			DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
			dataOutputStream.writeUTF("start");
			dataOutputStream.flush();
		} catch (Exception e){
			User user = new User();
			
			Login loginWindow = new Login(user);
		}
		
				
	}

}
