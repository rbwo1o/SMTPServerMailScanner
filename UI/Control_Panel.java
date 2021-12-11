package UI;

import java.util.ArrayList;
import java.util.Scanner;

import Main.Local_Rules;
import Main.Mail;
import Main.User;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.mail.MessagingException;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Database.Database;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JLayeredPane;
import java.awt.CardLayout;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class Control_Panel {

	public static JFrame frame;
	private static User user_;
	private static JLabel total_message_instance_label;
	private static JLabel new_message_instance_label;
	private JTable table;
	private static DefaultTableModel model; 
	private static DefaultTableModel global_rules_model;
	private static DefaultTableModel local_rules_model;
	private JTextField from_rule_textfield;
	private JTextField subject_rule_textfield;
	private JTextField content_rule_textfield;
	private JTextField admin_user_textfield;
	private Database DB;
	private static JTable global_rules_table;
	private static JTable local_rules_table;
	private JTextField local_from_textfield;
	private JTextField local_content_textfield;
	private JTextField local_subject_textfield;
	private static JLabel low_priority_messages_instance_label;
	private static JLabel medium_priority_messages_instance_label;
	private static JLabel high_priority_messages_instance_label;
	private static JLabel emergency_priority_messages_instance_label;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					User TEST = new User();
					Control_Panel window = new Control_Panel(TEST);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws MessagingException 
	 */
	public Control_Panel(User user) throws MessagingException {
		user_ = user;
		//Mail.setTotalMessages(Mail.getTotalMessages()); // Initializes Messages
		//Mail.startMailThread();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws MessagingException 
	 */
	private void initialize() throws MessagingException {
		
		/*Frame Elements*/
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setBackground(new Color(65, 105, 225));
		frame.setTitle("Control Panel : " + user_.username);
		frame.setBounds(100, 100, 921, 621);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		/**/
		
		
		
		/*Welcome Label*/
		JLabel welcome_label = new JLabel(user_.username + "'s Control Panel");
		welcome_label.setHorizontalAlignment(SwingConstants.CENTER);
		welcome_label.setForeground(new Color(255, 255, 255));
		welcome_label.setFont(new Font("Calibri", Font.BOLD, 38));
		/**/
		
		
		
		
	
		/* Navigation */
		JPanel navigation_panel = new JPanel();
		navigation_panel.setBorder(null);
		navigation_panel.setBackground(new Color(51, 51, 51));
		/**/
		
		
		
		
		/* LAYERED PANE - CARD LAYOUT  */
		
		JLayeredPane layeredPane = new JLayeredPane();
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(navigation_panel, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(18)
							.addComponent(layeredPane, GroupLayout.DEFAULT_SIZE, 774, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(28)
							.addComponent(welcome_label, GroupLayout.DEFAULT_SIZE, 764, Short.MAX_VALUE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(navigation_panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(welcome_label, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
					.addGap(7)
					.addComponent(layeredPane)
					.addContainerGap())
		);
		layeredPane.setLayout(new CardLayout(0, 0));
		
		
		
		
		
		
		/* Home Panel */
		JPanel home_panel = new JPanel();
		home_panel.setBackground(new Color(65, 105, 225));
		layeredPane.add(home_panel, "name_11752926988500");
		
		JLabel lblNewLabel_5 = new JLabel("<html>Server: <font color = \"#000080\">" + user_.server + "</font></html>");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setForeground(Color.WHITE);
		lblNewLabel_5.setFont(new Font("Calibri", Font.BOLD, 18));
		
		JLabel lblNewLabel_1 = new JLabel("<html><u>SMTP Server Statistics</u></html>");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 20));
		
		JLabel lblNewLabel_3 = new JLabel("<html>Email: <font color = \"#000080\">"  + user_.username + "</font></html>");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("Calibri", Font.BOLD, 18));
		
		JLabel lblNewLabel_4 = new JLabel("<html>Total Inbox Messages ( at login ): <font color = \"#000080\">" + user_.getTotalMessages() + "</font></html>");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setFont(new Font("Calibri", Font.BOLD, 18));
		
		JLabel lblNewLabel_2 = new JLabel("<html><u>Current Instance Stastics</u></html>");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 20));
		
		JLabel lblNewLabel_6 = new JLabel("Total messages: ");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_6.setForeground(Color.WHITE);
		lblNewLabel_6.setFont(new Font("Calibri", Font.BOLD, 18));
		
		JLabel lblNewLabel_7 = new JLabel("New Messages: ");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_7.setForeground(Color.WHITE);
		lblNewLabel_7.setFont(new Font("Calibri", Font.BOLD, 18));
		
		JLabel lblNewLabel_8 = new JLabel("<html><u>Message Notifications</u></html>");
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8.setForeground(Color.WHITE);
		lblNewLabel_8.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 18));
		
		JLabel lblNewLabel_9 = new JLabel("<html><font color = \"#00DBFF\">Low</font> Priority: </html>");
		lblNewLabel_9.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_9.setForeground(Color.WHITE);
		lblNewLabel_9.setFont(new Font("Calibri", Font.BOLD, 18));
		
		JLabel lblNewLabel_10 = new JLabel("<html><font color = \"yellow\">Medium</font> Priority: </html>");
		lblNewLabel_10.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_10.setForeground(Color.WHITE);
		lblNewLabel_10.setFont(new Font("Calibri", Font.BOLD, 18));
		
		JLabel lblNewLabel_11 = new JLabel("<html><font color = \"orange\">High</font> Priority: </html>");
		lblNewLabel_11.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_11.setForeground(Color.WHITE);
		lblNewLabel_11.setFont(new Font("Calibri", Font.BOLD, 18));
		
		JLabel lblNewLabel_12 = new JLabel("<html><font color = \"red\">Emergency</font> Priority: </html>");
		lblNewLabel_12.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_12.setForeground(Color.WHITE);
		lblNewLabel_12.setFont(new Font("Calibri", Font.BOLD, 18));
		
		total_message_instance_label = new JLabel("");
		total_message_instance_label.setFont(new Font("Calibri", Font.BOLD, 18));
		total_message_instance_label.setForeground(new Color(0, 0, 0));
		
		new_message_instance_label = new JLabel("");
		new_message_instance_label.setFont(new Font("Calibri", Font.BOLD, 18));
		new_message_instance_label.setForeground(new Color(0, 0, 0));
		
		low_priority_messages_instance_label = new JLabel("0");
		low_priority_messages_instance_label.setFont(new Font("Calibri", Font.BOLD, 18));
		
		medium_priority_messages_instance_label = new JLabel("0");
		medium_priority_messages_instance_label.setFont(new Font("Calibri", Font.BOLD, 18));
		
		high_priority_messages_instance_label = new JLabel("0");
		high_priority_messages_instance_label.setFont(new Font("Calibri", Font.BOLD, 18));
		
		emergency_priority_messages_instance_label = new JLabel("0");
		emergency_priority_messages_instance_label.setFont(new Font("Calibri", Font.BOLD, 18));
		GroupLayout gl_home_panel = new GroupLayout(home_panel);
		gl_home_panel.setHorizontalGroup(
			gl_home_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(lblNewLabel_8, GroupLayout.DEFAULT_SIZE, 774, Short.MAX_VALUE)
				.addGroup(gl_home_panel.createSequentialGroup()
					.addGroup(gl_home_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_home_panel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_home_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_5, GroupLayout.DEFAULT_SIZE, 754, Short.MAX_VALUE)
								.addComponent(lblNewLabel_3, GroupLayout.DEFAULT_SIZE, 754, Short.MAX_VALUE)
								.addComponent(lblNewLabel_4, GroupLayout.DEFAULT_SIZE, 754, Short.MAX_VALUE)
								.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 754, Short.MAX_VALUE)))
						.addComponent(lblNewLabel_2, GroupLayout.DEFAULT_SIZE, 764, Short.MAX_VALUE))
					.addContainerGap())
				.addGroup(gl_home_panel.createSequentialGroup()
					.addGroup(gl_home_panel.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(lblNewLabel_7, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblNewLabel_6, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_home_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(new_message_instance_label, GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE)
						.addComponent(total_message_instance_label, GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE)))
				.addGroup(gl_home_panel.createSequentialGroup()
					.addGroup(gl_home_panel.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(lblNewLabel_12, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblNewLabel_11, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblNewLabel_10, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblNewLabel_9, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 436, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_home_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(medium_priority_messages_instance_label, GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
						.addComponent(low_priority_messages_instance_label, GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
						.addComponent(high_priority_messages_instance_label, GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
						.addComponent(emergency_priority_messages_instance_label, GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_home_panel.setVerticalGroup(
			gl_home_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_home_panel.createSequentialGroup()
					.addGap(8)
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblNewLabel_5, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addGap(26)
					.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_home_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_6, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(total_message_instance_label, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_home_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_7, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(new_message_instance_label))
					.addGap(18)
					.addComponent(lblNewLabel_8, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addGroup(gl_home_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_9, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(low_priority_messages_instance_label))
					.addGap(11)
					.addGroup(gl_home_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_10, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(medium_priority_messages_instance_label))
					.addGap(11)
					.addGroup(gl_home_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_11, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(high_priority_messages_instance_label))
					.addGap(11)
					.addGroup(gl_home_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_12, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(emergency_priority_messages_instance_label))
					.addContainerGap(32, Short.MAX_VALUE))
		);
		home_panel.setLayout(gl_home_panel);
		
		
		
		
		
		
		
		/* Records Panel */
		JPanel records_panel = new JPanel();
		records_panel.setBackground(new Color(65, 105, 225));
		
		layeredPane.add(records_panel, "name_12150526006600");
		
		JTextArea textArea = new JTextArea();
		JScrollPane text_scroll = new JScrollPane(textArea);
		text_scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    text_scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		model = new DefaultTableModel(); 
	    table = new JTable(model);
	    
	    // Create a couple of columns 
	    model.addColumn("Priority"); 
	    model.addColumn("Date/Time"); 
	    model.addColumn("From"); 
	    model.addColumn("Subject");
	    model.addColumn("Email Number");
	    
	    table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	            // do some actions here, for example
	            // print first column value from selected row
	            System.out.println(table.getValueAt(table.getSelectedRow(), 4).toString());
	            //textArea.setText(table.getValueAt(table.getSelectedRow(), 4).toString());
	            
	            try {
					textArea.setText(Mail.getMessageContent(Integer.valueOf((String) table.getValueAt(table.getSelectedRow(), 4))));
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	           
	        }
	    });
	 
	    
	    
	    JScrollPane scrolly = new JScrollPane(table);
	    scrolly.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
	    scrolly.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    scrolly.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
	    frame.getContentPane().add(scrolly);
	    
		table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		
		textArea.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GroupLayout gl_records_panel = new GroupLayout(records_panel);
		gl_records_panel.setHorizontalGroup(
			gl_records_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(scrolly, GroupLayout.DEFAULT_SIZE, 764, Short.MAX_VALUE)
				.addComponent(text_scroll, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 782, Short.MAX_VALUE)
		);
		gl_records_panel.setVerticalGroup(
			gl_records_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_records_panel.createSequentialGroup()
					.addComponent(scrolly, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(text_scroll, GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE))
		);
		records_panel.setLayout(gl_records_panel);
		
		
		
		
		
		
		/* Rules Panel */
		JPanel rules_panel = new JPanel();
		rules_panel.setBackground(new Color(65, 105, 225));
		
		layeredPane.add(rules_panel, "name_12572718550400");
		
		local_rules_model = new DefaultTableModel();
		local_rules_table = new JTable(local_rules_model);
		JScrollPane local_rules_scroll = new JScrollPane(local_rules_table);
		local_rules_scroll.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
	    local_rules_scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    local_rules_scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
	    local_rules_model.addColumn("Priority");
		local_rules_model.addColumn("From");
		local_rules_model.addColumn("Subject");
		local_rules_model.addColumn("Content");
		local_rules_model.addColumn("id");
		
		/* Populate Rules */
		File myFile_ = new File("local_rules_DO_NOT_EDIT.txt");
		Scanner scanner_;
		try {
			scanner_ = new Scanner(myFile_);
			String local_id_, local_priority_, local_from_, local_subject_, local_content_;
			while(scanner_.hasNextLine()) {
				local_id_ = scanner_.nextLine();
				local_priority_ = scanner_.nextLine();
				local_from_ = scanner_.nextLine();
				local_subject_ = scanner_.nextLine();
				local_content_ = scanner_.nextLine();
				
				String [] data_ = {local_priority_, local_from_, local_subject_, local_content_, local_id_};
				local_rules_model.addRow(data_);
			}
		} catch (FileNotFoundException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		
		
		
		
	    
		
		local_from_textfield = new JTextField();
		local_from_textfield.setText("From");
		local_from_textfield.setHorizontalAlignment(SwingConstants.CENTER);
		local_from_textfield.setForeground(Color.GRAY);
		local_from_textfield.setColumns(10);
		local_from_textfield.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e){
		        if(e.getClickCount()==2){
		            // your code here
		        	local_from_textfield.setForeground(Color.black);
		        	local_from_textfield.setText("");
		        }
		    }
			
		});
		
		local_content_textfield = new JTextField();
		local_content_textfield.setForeground(Color.GRAY);
		local_content_textfield.setHorizontalAlignment(SwingConstants.CENTER);
		local_content_textfield.setText("Content");
		local_content_textfield.setColumns(10);
		local_content_textfield.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e){
		        if(e.getClickCount()==2){
		            // your code here
		        	local_content_textfield.setForeground(Color.black);
		        	local_content_textfield.setText("");
		        }
		    }
			
		});
		
		local_subject_textfield = new JTextField();
		local_subject_textfield.setText("Subject");
		local_subject_textfield.setForeground(Color.GRAY);
		local_subject_textfield.setHorizontalAlignment(SwingConstants.CENTER);
		local_subject_textfield.setColumns(10);
		local_subject_textfield.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e){
		        if(e.getClickCount()==2){
		            // your code here
		        	local_subject_textfield.setForeground(Color.black);
		        	local_subject_textfield.setText("");
		        }
		    }
			
		});
		
		
		JButton add_local_rule_button = new JButton("Add New Rule");
		
		JButton delete_local_rule_button = new JButton("Delete Selected Rule");
		
		JLabel lblNewLabel_14 = new JLabel("Local Rules");
		lblNewLabel_14.setFont(new Font("Calibri", Font.BOLD, 20));
		lblNewLabel_14.setForeground(Color.WHITE);
		lblNewLabel_14.setHorizontalAlignment(SwingConstants.CENTER);
		
		String [] priorities = {"Low", "Medium", "High", "Emergency"};
		JComboBox local_rules_comboBox = new JComboBox(priorities);
		GroupLayout gl_rules_panel = new GroupLayout(rules_panel);
		gl_rules_panel.setHorizontalGroup(
			gl_rules_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_rules_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_rules_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(local_rules_scroll, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 754, Short.MAX_VALUE)
						.addComponent(local_rules_comboBox, Alignment.LEADING, 0, 754, Short.MAX_VALUE)
						.addComponent(lblNewLabel_14, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 754, Short.MAX_VALUE)
						.addComponent(delete_local_rule_button, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 754, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, gl_rules_panel.createSequentialGroup()
							.addComponent(local_from_textfield, GroupLayout.PREFERRED_SIZE, 231, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(local_subject_textfield, GroupLayout.PREFERRED_SIZE, 276, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(local_content_textfield, 227, 227, 227))
						.addComponent(add_local_rule_button, GroupLayout.DEFAULT_SIZE, 754, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_rules_panel.setVerticalGroup(
			gl_rules_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_rules_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_14)
					.addGap(13)
					.addComponent(delete_local_rule_button)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(local_rules_scroll, GroupLayout.PREFERRED_SIZE, 276, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(local_rules_comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_rules_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_rules_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(local_from_textfield, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(local_subject_textfield, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(local_content_textfield, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(add_local_rule_button)
					.addContainerGap(27, Short.MAX_VALUE))
		);
		rules_panel.setLayout(gl_rules_panel);
		
		
		
		
		
		
		
		
	
		
		JPanel settings_panel = new JPanel();
		settings_panel.setBackground(new Color(65, 105, 225));
		layeredPane.add(settings_panel, "name_12587189004300");
		
		JLabel lblNewLabel = new JLabel("Administrator Users");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 20));
		lblNewLabel.setForeground(Color.WHITE);
		
		JLabel lblNewLabel_13 = new JLabel("Global Rules");
		lblNewLabel_13.setFont(new Font("Calibri", Font.BOLD, 20));
		lblNewLabel_13.setForeground(Color.WHITE);
		lblNewLabel_13.setHorizontalAlignment(SwingConstants.CENTER);
		
		
	
		
		
		
		DefaultListModel admin_user_model = new DefaultListModel(); 
		JList admin_user_list = new JList(admin_user_model);
		DB = new Database();
		DB.connect();
		ResultSet result = DB.view();
		//admin_user_model.addElement("mathhew");
		try {

			while(result.next()) {
				String adminUsername = result.getString("email");
				if(user_.username.equals(adminUsername)) {
					user_.admin = true;
				}
				admin_user_model.addElement(adminUsername);
			}
			
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		
		
		
		
		
	
		
		JScrollPane admin_user_list_scroll = new JScrollPane(admin_user_list); ////////////
		admin_user_list_scroll.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
	    admin_user_list_scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    admin_user_list_scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
	   
	    
	   
		
		from_rule_textfield = new JTextField();
		from_rule_textfield.setHorizontalAlignment(SwingConstants.CENTER);
		from_rule_textfield.setColumns(10);
		from_rule_textfield.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e){
		        if(e.getClickCount()==2){
		            // your code here
		        	from_rule_textfield.setForeground(Color.black);
		        	from_rule_textfield.setText("");
		        }
		    }
			
		});
		
		subject_rule_textfield = new JTextField();
		subject_rule_textfield.setHorizontalAlignment(SwingConstants.CENTER);
		subject_rule_textfield.setColumns(10);
		subject_rule_textfield.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e){
		        if(e.getClickCount()==2){
		            // your code here
		        	subject_rule_textfield.setForeground(Color.black);
		        	subject_rule_textfield.setText("");
		        }
		    }
			
		});
		
		content_rule_textfield = new JTextField();
		content_rule_textfield.setHorizontalAlignment(SwingConstants.CENTER);
		content_rule_textfield.setColumns(10);
		content_rule_textfield.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e){
		        if(e.getClickCount()==2){
		            // your code here
		        	content_rule_textfield.setForeground(Color.black);
		        	content_rule_textfield.setText("");
		        }
		    }
			
		});
		
		admin_user_textfield = new JTextField();
		admin_user_textfield.setHorizontalAlignment(SwingConstants.CENTER);
		admin_user_textfield.setColumns(10);
		admin_user_textfield.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e){
		        if(e.getClickCount()==2){
		            // your code here
		        	admin_user_textfield.setForeground(Color.black);
		        	admin_user_textfield.setText("");
		        }
		    }
			
		});
		
		
		JButton add_user_button = new JButton("Add New User");
		
		JButton delete_user_button = new JButton("Delete Selected User");
		
		
		
		JComboBox global_priority_comboBox = new JComboBox(priorities);
		
		global_rules_model = new DefaultTableModel();
		global_rules_table = new JTable(global_rules_model);
		JScrollPane global_rules_scroll = new JScrollPane(global_rules_table);
		global_rules_scroll.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
	    global_rules_scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    global_rules_scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
	 // Create a couple of columns 
	    global_rules_model.addColumn("Priority"); 
	    global_rules_model.addColumn("From");  
	    global_rules_model.addColumn("Subject");
	    global_rules_model.addColumn("Content");
	    global_rules_model.addColumn("id");
		
		
	    
	    
	    // populate rows
	    result = DB.view_rules();
	    try {
	    	
	    	
	    	while(result.next()) {
	    		String id = result.getString(1);
				String priority_rule = result.getString(2);
				String subject_rule = result.getString(3);
				String from_rule = result.getString(4);
				String content_rule = result.getString(5);
				
				String [] data = {priority_rule, from_rule, subject_rule, content_rule, id};
				addGlobalRuleRow(data);
	    	}
	    	
			
			
			
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		JButton add_rule_button = new JButton("Add New Rule");
		
		JButton delete_rule_button = new JButton("Delete Selected Rule");
	    
	    
	    
	    

	    
		GroupLayout gl_settings_panel = new GroupLayout(settings_panel);
		gl_settings_panel.setHorizontalGroup(
			gl_settings_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_settings_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_settings_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_settings_panel.createSequentialGroup()
							.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(gl_settings_panel.createSequentialGroup()
							.addGroup(gl_settings_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(add_user_button, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
								.addComponent(admin_user_list_scroll, GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
								.addComponent(delete_user_button, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
								.addComponent(admin_user_textfield, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE))
							.addGap(18)))
					.addGroup(gl_settings_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(global_rules_scroll, GroupLayout.DEFAULT_SIZE, 523, Short.MAX_VALUE)
						.addGroup(gl_settings_panel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(from_rule_textfield, GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(subject_rule_textfield, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(content_rule_textfield, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE))
						.addComponent(lblNewLabel_13, GroupLayout.DEFAULT_SIZE, 523, Short.MAX_VALUE)
						.addComponent(add_rule_button, GroupLayout.DEFAULT_SIZE, 523, Short.MAX_VALUE)
						.addComponent(delete_rule_button, GroupLayout.DEFAULT_SIZE, 523, Short.MAX_VALUE)
						.addGroup(gl_settings_panel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(global_priority_comboBox, 0, 523, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_settings_panel.setVerticalGroup(
			gl_settings_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_settings_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_settings_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_13)
						.addComponent(lblNewLabel))
					.addGap(18)
					.addGroup(gl_settings_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(delete_user_button)
						.addComponent(delete_rule_button))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_settings_panel.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(gl_settings_panel.createSequentialGroup()
							.addComponent(global_rules_scroll, 0, 0, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(global_priority_comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(admin_user_list_scroll, GroupLayout.PREFERRED_SIZE, 304, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_settings_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(content_rule_textfield, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(subject_rule_textfield, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(from_rule_textfield, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(admin_user_textfield, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(17)
					.addGroup(gl_settings_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(add_user_button)
						.addComponent(add_rule_button))
					.addContainerGap())
		);
		settings_panel.setLayout(gl_settings_panel);
		
		JButton home_button = new JButton("Home");
		home_button.setBorder(null);
		home_button.setForeground(Color.WHITE);
		home_button.setFont(new Font("Calibri", Font.BOLD, 18));
		home_button.setBackground(new Color(65, 105, 225));
		home_button.setSelected(true);
		home_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(home_panel);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		
		
		
		JButton records_button = new JButton("Records");
		records_button.setBorder(null);
		records_button.setForeground(Color.WHITE);
		records_button.setFont(new Font("Calibri", Font.BOLD, 18));
		records_button.setBackground(new Color(51, 51, 51));
		records_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(records_panel);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		
		
		
		JButton settings_button = new JButton("Settings");
		settings_button.setBorder(null);
		settings_button.setForeground(Color.WHITE);
		settings_button.setFont(new Font("Calibri", Font.BOLD, 18));
		settings_button.setBackground(new Color(51, 51, 51));
		settings_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(settings_panel);
				layeredPane.repaint();
				layeredPane.revalidate();
				
				from_rule_textfield.setForeground(Color.GRAY);
				from_rule_textfield.setText("From");
				
				subject_rule_textfield.setForeground(Color.GRAY);
				subject_rule_textfield.setText("Subject");
				
				content_rule_textfield.setForeground(Color.GRAY);
				content_rule_textfield.setText("Content");
				
				admin_user_textfield.setForeground(Color.GRAY);
				admin_user_textfield.setText("New User");
			}
			
		});
		
		
		
		JButton rules_button = new JButton("Rules");
		rules_button.setBorder(null);
		rules_button.setForeground(Color.WHITE);
		rules_button.setBackground(new Color(51, 51, 51));
		rules_button.setFont(new Font("Calibri", Font.BOLD, 18));
		rules_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(rules_panel);
				layeredPane.repaint();
				layeredPane.revalidate();
				local_from_textfield.setForeground(Color.GRAY);
				local_from_textfield.setText("From");
				
				local_subject_textfield.setForeground(Color.GRAY);
				local_subject_textfield.setText("Subject");
				
				local_content_textfield.setForeground(Color.GRAY);
				local_content_textfield.setText("Content");
				
			}
			
		});
		
		
		
		
		
		home_button.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        home_button.setBackground(new Color(65, 105, 225));
		        home_button.setSelected(true);
		        
		        records_button.setBackground(new Color(51, 51, 51));
		        records_button.setSelected(false);
		        
		        rules_button.setBackground(new Color(51, 51, 51));
		        rules_button.setSelected(false);
		        
		        settings_button.setBackground(new Color(51, 51, 51));
		        settings_button.setSelected(false);
		        
		    }
		});
		
		rules_button.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        rules_button.setBackground(new Color(65, 105, 225));
		        rules_button.setSelected(true);
		        
		        records_button.setBackground(new Color(51, 51, 51));
		        records_button.setSelected(false);
		        
		        home_button.setBackground(new Color(51, 51, 51));
		        home_button.setSelected(false);
		        
		        settings_button.setBackground(new Color(51, 51, 51));
		        settings_button.setSelected(false);
		        
		    }
		});
		
		settings_button.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        settings_button.setBackground(new Color(65, 105, 225));
		        settings_button.setSelected(true);
		        
		        records_button.setBackground(new Color(51, 51, 51));
		        records_button.setSelected(false);
		        
		        rules_button.setBackground(new Color(51, 51, 51));
		        rules_button.setSelected(false);
		        
		        home_button.setBackground(new Color(51, 51, 51));
		        home_button.setSelected(false);
		    }
		});
		
		records_button.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        records_button.setBackground(new Color(65, 105, 225));
		        records_button.setSelected(true);
		        
		        home_button.setBackground(new Color(51, 51, 51));
		        home_button.setSelected(false);
		        
		        rules_button.setBackground(new Color(51, 51, 51));
		        rules_button.setSelected(false);
		        
		        settings_button.setBackground(new Color(51, 51, 51));
		        settings_button.setSelected(false);
		        
		    }
		});
		
		
		
		
		setInstanceStatistics(user_.getTotalMessages(), 0, 0, 0, 0, 0);
		
		JButton kill_Button = new JButton("Kill Thread");
		kill_Button.setBorder(null);
		kill_Button.setFont(new Font("Calibri", Font.BOLD, 18));
		kill_Button.setBackground(new Color(51, 51, 51));
		kill_Button.setForeground(Color.WHITE);
		kill_Button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(1);
			}
			
		});
		
		
		
		
		GroupLayout gl_navigation_panel = new GroupLayout(navigation_panel);
		gl_navigation_panel.setHorizontalGroup(
			gl_navigation_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(home_button, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
				.addComponent(rules_button, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
				.addComponent(settings_button, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
				.addComponent(records_button, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
				.addComponent(kill_Button, GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
		);
		gl_navigation_panel.setVerticalGroup(
			gl_navigation_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_navigation_panel.createSequentialGroup()
					.addComponent(home_button, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(records_button, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(rules_button, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(settings_button, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 261, Short.MAX_VALUE)
					.addComponent(kill_Button, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		navigation_panel.setLayout(gl_navigation_panel);
		frame.getContentPane().setLayout(groupLayout);
		
		frame.setVisible(true);
		
		
		
		/* Test User Privilege */
		if(!user_.admin) {
			settings_button.setText("");
			settings_button.setEnabled(false);;
		}
		
		
		
		
		
		/*  --- Action Listeners --- */
		
		delete_user_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				DB.delete(admin_user_list.getSelectedValue().toString());
				admin_user_model.clear();
				ResultSet result = DB.view();
				
				try {
					while(result.next()) {
						admin_user_model.addElement(result.getString("email"));
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		
		
		
		
		
		add_user_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				DB.insert(admin_user_textfield.getText());
				admin_user_model.clear();
				ResultSet result = DB.view();
				
				try {
					while(result.next()) {
						admin_user_model.addElement(result.getString("email"));
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		
		
		
		
		
		
		
		add_rule_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//DB.insert(admin_user_textfield.getText());
				String priority = global_priority_comboBox.getSelectedItem().toString();
				String from = from_rule_textfield.getText();
				String subject = subject_rule_textfield.getText();
				String content = content_rule_textfield.getText();
				
				if(content == null) {
					System.out.println("adding NULL");
				}
				
				DB.insert_rule(priority, from, subject, content);
				
				global_rules_model.getDataVector().removeAllElements();
				
				ResultSet result = DB.view_rules();
				
				try {
					
					while(result.next()) {
						String id = result.getString(1);
						String priority_rule = result.getString(2);
						String subject_rule = result.getString(3);
						String from_rule = result.getString(4);
						String content_rule = result.getString(5);
						
						String [] data = {priority_rule, from_rule, subject_rule, content_rule, id};
						addGlobalRuleRow(data);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		
		
		
		
		delete_rule_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//DB.delete_rule(Integer.valueOf(global_rules_table.getValueAt(5, global_rules_table.getSelectedRow()));
				DB.delete_rule(Integer.valueOf(global_rules_table.getValueAt(global_rules_table.getSelectedRow(), 4).toString()));
				System.out.println(global_rules_table.getValueAt(global_rules_table.getSelectedRow(), 4).toString());
				
				for(int i = 0; i < global_rules_model.getRowCount(); i++) {
					//global_rules_model.removeRow(i);
				}
				global_rules_model.getDataVector().removeAllElements();
				
				ResultSet result = DB.view_rules();
				
				try {
					
					while(result.next()) {
						String id = result.getString(1);
						String priority_rule = result.getString(2);
						String subject_rule = result.getString(3);
						String from_rule = result.getString(4);
						String content_rule = result.getString(5);
						
						String [] data = {priority_rule, from_rule, subject_rule, content_rule, id};
						addGlobalRuleRow(data);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		
		
		
		
		
		add_local_rule_button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				local_rules_comboBox.getSelectedItem().toString();
				
				
				String local_priority = local_rules_comboBox.getSelectedItem().toString();
				String local_from = local_from_textfield.getText();
				String local_subject = local_subject_textfield.getText();
				String local_content = local_content_textfield.getText();
				
				try {
					Local_Rules.addRule(local_priority, local_from, local_subject, local_content);
					
					// remove all rows
					for(int i = 0; i < local_rules_model.getRowCount(); i++) {
						//local_rules_model.removeRow(i);
					}
					local_rules_model.getDataVector().removeAllElements();
					try {
						Thread.sleep(10);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					File myFile = new File("local_rules_DO_NOT_EDIT.txt");
					Scanner scanner = new Scanner(myFile);
					
					String local_id;
					while(scanner.hasNextLine()) {
						local_id = scanner.nextLine();
						local_priority = scanner.nextLine();
						local_from = scanner.nextLine();
						local_subject = scanner.nextLine();
						local_content = scanner.nextLine();
						
						String [] data = {local_priority, local_from, local_subject, local_content, local_id};
						local_rules_model.addRow(data);
					}
					
			
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}
			
		});
		
		
		
		delete_local_rule_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Local_Rules.deleteRule(local_rules_table.getValueAt(local_rules_table.getSelectedRow(), 4).toString());
					
					local_rules_model.getDataVector().removeAllElements();
					
					String local_priority = local_rules_comboBox.getSelectedItem().toString();
					String local_from = local_from_textfield.getText();
					String local_subject = local_subject_textfield.getText();
					String local_content = local_content_textfield.getText();
					
					
					
					File myFile = new File("local_rules_DO_NOT_EDIT.txt");
					
					
					Scanner scanner = new Scanner(myFile);
					
					String local_id;
					while(scanner.hasNextLine()) {
						local_id = scanner.nextLine();
						local_priority = scanner.nextLine();
						local_from = scanner.nextLine();
						local_subject = scanner.nextLine();
						local_content = scanner.nextLine();
						
						String [] data = {local_priority, local_from, local_subject, local_content, local_id};
						local_rules_model.addRow(data);
					}
					
					//local_rules_model.getDataVector().removeAllElements();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
		});
		
			
	}
	
	
	
	public static void display() {
		frame.setVisible(true);
	}
	
	
	
	
	public static void setRules() {
		Mail.priority_list = new ArrayList<String>();
		Mail.from_list = new ArrayList<String>();
		Mail.subject_list = new ArrayList<String>();
		Mail.content_list = new ArrayList<String>();
		
		for(int i = 0; i < local_rules_model.getRowCount(); i++) {
			Mail.priority_list.add(local_rules_table.getValueAt(i, 0).toString());
			Mail.from_list.add(local_rules_table.getValueAt(i, 1).toString());
			Mail.subject_list.add(local_rules_table.getValueAt(i, 2).toString());
			Mail.content_list.add(local_rules_table.getValueAt(i, 3).toString());
			//System.out.print(local_rules_model.getValueAt(i, 1).toString() + " --- ");
			//System.out.print(local_rules_table.getValueAt(i, 2).toString() + " --- ");
			//System.out.print(local_rules_table.getValueAt(i, 3).toString() + " --- ");
			//System.out.print(global_rules_table.getValueAt(i, 3).toString() + " --- \n");
			
		}
		
		for(int i = 0; i < global_rules_model.getRowCount(); i++) {
			Mail.priority_list.add(global_rules_table.getValueAt(i, 0).toString());
			Mail.from_list.add(global_rules_table.getValueAt(i, 1).toString());
			Mail.subject_list.add(global_rules_table.getValueAt(i, 2).toString());
			Mail.content_list.add(global_rules_table.getValueAt(i, 3).toString());
			//System.out.println(global_rules_model.getValueAt(i, 1).toString() + " --- ");
			//System.out.println(global_rules_table.getValueAt(i, 2).toString() + " --- ");
			//System.out.println(global_rules_table.getValueAt(i, 3).toString() + " --- ");
			//System.out.println(global_rules_table.getValueAt(i, 3).toString() + " --- \n");
		}
		
		System.out.println("------------");
		for(String x : Mail.priority_list) {
			if(x == "") {
				System.out.println("''");
			}
			else {
				System.out.println(x);
			}
		}
		System.out.println("------------");
		for(String x : Mail.from_list) {
			if(x == "") {
				System.out.println("''");
			}
			else {
				System.out.println(x);
			}
		}
		System.out.println("------------");
		for(String x : Mail.subject_list) {
			if(x == "") {
				System.out.println("''");
			}
			else {
				System.out.println(x);
			}
		}
		System.out.println("------------");
		for(String x : Mail.content_list) {
			if(x == "") {
				System.out.println("''");
			}
			else {
				System.out.println(x);
			}
		}
		
	}
	
		
	public static void setInstanceStatistics(int totalInstanceMessage, int new_messages, int low_priority_messages, int medium_priority_messages, int high_priority_messages, int emergency_priority_messages) throws MessagingException {
		total_message_instance_label.setText(String.valueOf(totalInstanceMessage));
		total_message_instance_label.repaint();
		total_message_instance_label.revalidate();
		
		new_message_instance_label.setText(String.valueOf(new_messages));
		new_message_instance_label.repaint();
		new_message_instance_label.revalidate();
		
		
		
		low_priority_messages_instance_label.setText(String.valueOf(low_priority_messages));
		low_priority_messages_instance_label.repaint();
		low_priority_messages_instance_label.revalidate();
		
		medium_priority_messages_instance_label.setText(String.valueOf(medium_priority_messages));
		medium_priority_messages_instance_label.repaint();
		medium_priority_messages_instance_label.revalidate();
		
		
		high_priority_messages_instance_label.setText(String.valueOf(high_priority_messages));
		high_priority_messages_instance_label.repaint();
		high_priority_messages_instance_label.revalidate();
		
		
		emergency_priority_messages_instance_label.setText(String.valueOf(emergency_priority_messages));
		emergency_priority_messages_instance_label.repaint();
		emergency_priority_messages_instance_label.revalidate();
		
	}
	
	
	
	
	
	public void addTableRow(String[] data) {
		model.addRow(data);
		table.repaint();
		table.revalidate();
	}
	
	
	
	public void addGlobalRuleRow(String[] data) {
		global_rules_model.addRow(data);
		table.repaint();
		table.revalidate();
	}
}
