package Network;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.mail.MessagingException;

import UI.Control_Panel;

public class AppServer {
	
	public static void startServer() throws IOException {
		
		
		Thread serverThread = new Thread(new Runnable() {
			@Override
			public void run() {
					       
			    try {
					ServerSocket ss = new ServerSocket(9897);
					while(true) {
						Socket socket = ss.accept();
						System.out.println(socket + " has connected! Waiting for data..");
						
						
						InputStream inputStream = socket.getInputStream();
					       DataInputStream dataInputStream = new DataInputStream(inputStream);
					       
					       
					       String message;
					       
					    	   message = dataInputStream.readUTF();
					    	   
					    	   if(message.equals("start")) {
					    		   Control_Panel.frame.setVisible(true);
					    	   }
						
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    
			    
			}
			
		});
		
		serverThread.start();  
	}
	
}
