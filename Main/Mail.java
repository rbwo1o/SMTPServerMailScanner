package Main;

import java.io.IOException;
import java.security.NoSuchProviderException;
import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;

import UI.Control_Panel;
import UI.Mail_Alert;


public class Mail{
	
	private static int new_messages = 0;
	private static int low_priority_messages = 0;
	private static int medium_priority_messages = 0;
	private static int high_priority_messages = 0;
	private static int emergency_priority_messages = 0;
	
	private static int total_inbox_messages;
	private static int current_inbox_messages;
	public static String username_;
	public static String password_;
	public static Control_Panel controlPanelWindow_;
	public static ArrayList<String> from_list;
	public static ArrayList<String> subject_list;
	public static ArrayList<String> content_list;
	public static ArrayList<String> priority_list;
	public static Mail_Alert alert = new Mail_Alert();
	
	/*
	 * Attempts to make a connection to the SMPT server.
	 * It accepts a user name and password as parameters.
	 * If the connection is established, it saves the user name and password to be used in future function calls.
	 */
	public static boolean connect(String username, String password) {
		
		try {
		      //create properties field
		      Properties properties = new Properties();
		      properties.put("mail.pop3.host", "webmail.blomand.net");
		      properties.put("mail.pop3.port", "995");
		      properties.put("mail.pop3.starttls.enable", "true");
		      Session emailSession = Session.getDefaultInstance(properties);
		  
		      
		      //create the POP3 store object and connect with the pop server
		      Store store = emailSession.getStore("pop3s");
		      store.connect("webmail.blomand.net", username, password);
		      boolean connection_status = store.isConnected();
		      store.close();
		      
		      if(connection_status) {
		    	  username_ = username;
		    	  password_ = password;
		      }
		      
		      return connection_status;
		      
		      } catch (MessagingException e) {
		    	  
		      } catch (Exception e) {
		    	  
		      }
		
		return false;
	}
	
	
	
	/*
	 * Creates a pointer to the control panel object to be used in future functions
	 * Accepts a Control_Panel object as a parameter
	 */
	public static void setControlPanel(Control_Panel control) {
		controlPanelWindow_ = control;
	}
	
	
	/**/
	public static void setTotalMessages(int total) {
		total_inbox_messages = total;
	}
	
	
	
	public static int getTotalMessages() throws MessagingException {
		//create properties field
	    Properties properties = new Properties();

	    properties.put("mail.pop3.host", "webmail.blomand.net");
	    properties.put("mail.pop3.port", "995");
	    properties.put("mail.pop3.starttls.enable", "true");
	    Session emailSession = Session.getDefaultInstance(properties);
	  
	    //create the POP3 store object and connect with the pop server
	    Store store = emailSession.getStore("pop3s");
	    store.connect("webmail.blomand.net", username_, password_);
		
	    Folder emailFolder = store.getFolder("INBOX");
	    emailFolder.open(Folder.READ_ONLY);
		
	    Message[] messages = emailFolder.getMessages();
	    store.close();
	    return messages.length;
	}
	
	
	
	public static void startMailThread() {
		Thread mailThread = new Thread(new Runnable() {
			@Override
			public void run() {
				while(true) {
		
					try {
						current_inbox_messages = getTotalMessages();
						System.out.println("Checking " + total_inbox_messages + " =?= " + current_inbox_messages);
						//Control_Panel.setRules();
						if(current_inbox_messages > total_inbox_messages) {
							Control_Panel.setRules();
							checkMessages();
							total_inbox_messages = current_inbox_messages;
							new_messages++;
							Control_Panel.setInstanceStatistics(current_inbox_messages, new_messages, low_priority_messages, medium_priority_messages, high_priority_messages, emergency_priority_messages);
						}
						Thread.sleep(30000);
					} catch (MessagingException | InterruptedException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
								
				}
				
			}
			
		});
		
		mailThread.start();
	}
	
	
	
	
	
	
	
	
	public static void checkMessages() throws MessagingException, IOException {
		
		//create properties field
	    Properties properties = new Properties();
	    properties.put("mail.pop3.host", "webmail.blomand.net");
	    properties.put("mail.pop3.port", "995");
	    properties.put("mail.pop3.starttls.enable", "true");
	    Session emailSession = Session.getDefaultInstance(properties);
	  
	    //create the POP3 store object and connect with the pop server
	    Store store = emailSession.getStore("pop3s");
	    store.connect("webmail.blomand.net", username_, password_);
		
	    Folder emailFolder = store.getFolder("INBOX");
	    emailFolder.open(Folder.READ_ONLY);
		
	    Message[] messages = emailFolder.getMessages();
		
	    
	    //Mail_Alert alert = new Mail_Alert();
	    
	    //String [] x = {"1", "2", "3", "4", "5"};
	    //alert.addTableRow(x);
	    
		for(int i = current_inbox_messages; i > total_inbox_messages; i--) {
			Message message = messages[i-1];
	        
			
			
			// ArrayLists should all have the same length;
			// check from
			for(int j = 0; j < message.getFrom().length; j++) {
				
				String from = message.getFrom()[j].toString();
				String subject = message.getSubject().toString().trim();
				String content = message.getContent().toString().trim();
				if(message.getSubject().isBlank()) {
					subject = "";
					System.out.println("Subject empty");
				}
				
				
				
				System.out.println("?????????????????");
				if(from.isBlank()) {
					System.out.println("''");
					System.out.println("From empty");
				}
				else {
					System.out.println(from);
				}
				if(subject.isBlank()) {
					System.out.println("''");
					System.out.println("Subject empty");
				}
				else {
					System.out.println(subject);
				}
				if(content.isBlank()) {
					System.out.println("''");
					System.out.println("Content empty");
				}
				else {
					System.out.println(content);
				}
				
				
				System.out.println(from_list.size() + " " + subject_list.size() + " " + content_list.size());
				System.out.println("?????????????????");
				//System.out.println(from);
				//System.out.println(subject);
				//System.out.println(content);
				System.out.println("?????????????????");
				
				System.out.println(from_list.size());
				for(int k = 0; k < from_list.size(); k++) {
					
					//System.out.println(from_list.get(k) + " --> " + from);
					
					if( from.toLowerCase().contains(from_list.get(k).toString().toLowerCase()) && subject.toLowerCase().contains(subject_list.get(k).toString().toLowerCase()) && content.contains(content_list.get(k).toString().toLowerCase())) {
						
							String [] message_info = {priority_list.get(k).toString(), "Time", message.getFrom()[0].toString(), message.getSubject(), String.valueOf(i)};
							
							if(message_info[0].equals("Low")) {
								controlPanelWindow_.addTableRow(message_info);
								low_priority_messages++;
							}
							else if(message_info[0].equals("Medium")) {
								controlPanelWindow_.addTableRow(message_info);
								medium_priority_messages++;
							}
							else if(message_info[0].equals("High")) {
								alert.addTableRow(message_info);
								controlPanelWindow_.addTableRow(message_info);
								alert.frame.setVisible(true);
								alert.vibrate();
								high_priority_messages++;
							}
							else {
								alert.addTableRow(message_info);
								controlPanelWindow_.addTableRow(message_info);
								alert.frame.setVisible(true);
								alert.vibrate();
								emergency_priority_messages++;
							}
									
					}
					
				}
				
			}
			
		}
		
		store.close();
	}
	
	
	
	public static String getMessageContent(int messageNumber) throws MessagingException, IOException {
		System.out.println(messageNumber);
		
		StringBuilder builder = new StringBuilder();
		//create properties field
	    Properties properties = new Properties();
	    properties.put("mail.pop3.host", "webmail.blomand.net");
	    properties.put("mail.pop3.port", "995");
	    properties.put("mail.pop3.starttls.enable", "true");
	    Session emailSession = Session.getDefaultInstance(properties);
	  
	    //create the POP3 store object and connect with the pop server
	    Store store = emailSession.getStore("pop3s");  
	    store.connect("webmail.blomand.net", username_, password_);
	    
		Folder emailFolder = store.getFolder("INBOX");
	    emailFolder.open(Folder.READ_ONLY);
		
	    Message[] messages = emailFolder.getMessages();
	    
	    Message message = messages[messageNumber - 1];
		
		System.out.println(message.getContent().toString());
		
	    for(int i = 0; i < message.getFrom().length; i++) {
	    	builder.append("From: " + message.getFrom()[i] + "\n");
	    }
	         
	    builder.append("\n");
	         
	    for(int i = 0; i < message.getRecipients(Message.RecipientType.TO).length; i++) {
	    	builder.append("To: " + message.getRecipients(Message.RecipientType.TO)[i] + "\n");
	    }
	         
	    builder.append("\n");     
	    builder.append("Subject: " + message.getSubject().toString() + "\n");
	    builder.append("-----------------------\n");
	         
	    if(message.isMimeType("text/plain")) {
	    	builder.append(message.getContent());
	        return builder.toString();
	    }
	         
	    builder.append("This message contains elements that are not 'text/plain' and cannot be rendered in this text area.\nPlease view this message using an email client..");
	         
	    return builder.toString();	
	}
	
	
}
