package UI;


import Main.Mail;
import Main.User;
import Network.AppServer;
import Network.Internet;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.mail.MessagingException;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.UIManager;

import Database.Database;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Color;

public class Login {

	private JFrame frame;
	private JPasswordField passwordField;
	private JTextField usernameField;
	public User user;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login(new User());
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login(User user) {
		this.user = user;
		
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Login");
		frame.setBounds(100, 100, 379, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		passwordField = new JPasswordField();
		
		JLabel password_label = new JLabel("Password");
		password_label.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel username_label = new JLabel("Email");
		username_label.setHorizontalAlignment(SwingConstants.CENTER);
		
		usernameField = new JTextField();
		usernameField.setColumns(10);
		
		JLabel failedNotification = new JLabel("");
		failedNotification.setForeground(Color.RED);
		failedNotification.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton login_button = new JButton("Login");
		login_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String username = usernameField.getText();
				String password = passwordField.getText();
				
				boolean login_value = user.login(username, password);
				
				if(login_value) {
					System.out.println("LOGIN SUCCESSFUL");
					user.setUsername(username);
					user.setPassword(password);
					
					
					
					try {
						
						Database connection = new Database();
						if(!connection.connect()) {
							DBConnectionError error = new DBConnectionError();
							
						}
						else {
							AppServer.startServer();
							Control_Panel controlPanelWindow = new Control_Panel(user);
							
							try {
								Mail.setTotalMessages(Mail.getTotalMessages());
							} catch (MessagingException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							} // Initializes Messages
							Mail.startMailThread();
							Mail.setControlPanel(controlPanelWindow);
						}
						
						
						//Mail.startMailThread(username, password);
					} catch (MessagingException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					frame.dispose();
					
					/*
					 * 
					 * Send to control Panel
					 * 
					 * */
					
				}
				else {
					System.out.println("Login Failed");
					
					failedNotification.setText("Login Failed");
					if(!Internet.isConnected()) {
						failedNotification.setText("Login Failed - No Internet");
					}
					failedNotification.repaint();
					failedNotification.revalidate();
					
					usernameField.setText("");
					passwordField.setText("");
					
					usernameField.repaint();
					usernameField.revalidate();
					
					passwordField.repaint();
					passwordField.revalidate();
				}
				
			}
			
		});
		
		
		
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
							.addComponent(usernameField, Alignment.TRAILING)
							.addComponent(username_label, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
							.addComponent(password_label, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE))
						.addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
						.addComponent(login_button, GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
						.addComponent(failedNotification, GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(35)
					.addComponent(username_label)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(usernameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(password_label)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(login_button)
					.addPreferredGap(ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
					.addComponent(failedNotification)
					.addContainerGap())
		);
		frame.getContentPane().setLayout(groupLayout);
		
		
		frame.setVisible(true);
	}
}
