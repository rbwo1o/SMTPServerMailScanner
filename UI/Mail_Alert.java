package UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.mail.MessagingException;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Main.Mail;

import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import javax.swing.border.CompoundBorder;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingWorker;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JComboBox;

public class Mail_Alert {

	public static JFrame frame;
	private static JTable table;
	private static DefaultTableModel model; 
	public static boolean exists = false;
	public static boolean has_update;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Mail_Alert window = new Mail_Alert();
					window.frame.setVisible(true);
					vibrate();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Mail_Alert() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		initialize();
		//frame.setVisible(true);
		//vibrate();
		//setTrue();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Mail Alert!");
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setBounds(100, 100, 871, 727);
		frame.setUndecorated(true);
		frame.setAlwaysOnTop(true);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		// frame.setUndecorated(true);
		
		    
	    
	    /* JTextArea */
		JTextArea textArea = new JTextArea();
		textArea.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		JScrollPane text_scroll = new JScrollPane(textArea);
		text_scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    text_scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		/* */
	    
	    
	    
	    model = new DefaultTableModel(); 
	    JTable table = new JTable(model); 
	    table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	            // do some actions here, for example
	            // print first column value from selected row
	            System.out.println(table.getValueAt(table.getSelectedRow(), 0).toString());
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
	    
	    

	    // Create a couple of columns 
	    model.addColumn("Priority"); 
	    model.addColumn("Date/Time"); 
	    model.addColumn("From"); 
	    model.addColumn("Subject");
	    model.addColumn("Email Number");


	   
	    
	  	    
	    // adding it to JScrollPane
	    JScrollPane scrolly = new JScrollPane(table);
	    scrolly.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
	    scrolly.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    scrolly.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
	    frame.getContentPane().add(scrolly);
		/* */

	    
	    
	    

		
		/* Buttons */
		JButton awknoledgeButton = new JButton("Awknoledge");
		awknoledgeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SwingWorker<Void, Void> closeThread = new SwingWorker<Void, Void>(){
					protected Void doInBackground() {
						model.getDataVector().removeAllElements();

						frame.setVisible(false);
						return null;
					}
				};
				
				closeThread.execute();
			}
			
		});
		
		/* */
		
		
		
		
		/* JComboBox */
		String [] time_combo_list = {"1 Minute", "5 Minutes", "10 Minutes", "15 Minutes", "20 Minutes", "30 Minutes"};
		JComboBox comboBox = new JComboBox(time_combo_list);
		comboBox.setOpaque(true);
		comboBox.setSelectedIndex(0);
		/* */
		
	    
		
		
		
		JButton postponeButton = new JButton("Postpone Alert");
		postponeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//frame.setVisible(false);
				
				SwingWorker<Void, Void> postponeThread = new SwingWorker<Void, Void>(){

					@Override
					protected Void doInBackground() throws Exception {
						
						//frame.setVisible(false);
						//Object alerty = Mail.alert.clone();
						frame.setVisible(false);
						
						if(comboBox.getSelectedItem().toString().equals("1 Minute")) {
							try {
								System.out.println("Sleeping 1 minute");
								TimeUnit.MINUTES.sleep(1);
							} catch (InterruptedException e1) {
								e1.printStackTrace();
							}
						}
						else if(comboBox.getSelectedItem().toString().equals("5 Minutes")) {
							try {
								System.out.println("Sleeping 5 minutes");
								TimeUnit.MINUTES.sleep(5);
							} catch (InterruptedException e1) {
								e1.printStackTrace();
							}
						}
						else if(comboBox.getSelectedItem().toString().equals("10 Minutes")) {
							try {
								System.out.println("Sleeping 10 minutes");
								TimeUnit.MINUTES.sleep(10);
							} catch (InterruptedException e1) {
								e1.printStackTrace();
							}
						}
						else if (comboBox.getSelectedItem().toString().equals("15 Minutes")) {
							try {
								System.out.println("Sleeping 15 minutes");
								TimeUnit.MINUTES.sleep(15);
							} catch (InterruptedException e1) {
								e1.printStackTrace();
							}
						}
						else if (comboBox.getSelectedItem().toString().equals("20 Minutes")) {
							try {
								System.out.println("Sleeping 20 minutes");
								TimeUnit.MINUTES.sleep(20);
							} catch (InterruptedException e1) {
								e1.printStackTrace();
							}
						}
						else {
							try {
								System.out.println("Sleeping 30 minutes");
								TimeUnit.MINUTES.sleep(30);
							} catch (InterruptedException e1) {
								e1.printStackTrace();
							}
						}
						
						

						if(model.getRowCount() > 0) {
							frame.setVisible(true);
							vibrate();
						}
						
						return null;
					}
					
				};
				
				postponeThread.execute();
					
			}
			
		});
		
		
		
		
		/* LAYOUT PLACEMENT */
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(text_scroll, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 835, Short.MAX_VALUE)
						.addComponent(scrolly, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 835, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(postponeButton, GroupLayout.DEFAULT_SIZE, 452, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(awknoledgeButton, GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE))
						.addComponent(comboBox, 0, 835, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrolly, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(text_scroll, GroupLayout.PREFERRED_SIZE, 298, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 87, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(postponeButton, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
						.addComponent(awknoledgeButton, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		frame.getContentPane().setLayout(groupLayout);
		
	}
	
	
	public void addTableRow(String[] data) {
		
		model.addRow(data);
		table.repaint();
		table.revalidate();
		
	}
	
	

	public static void vibrate() {
		final int VIBRATION_LENGTH = 30;
	    final int VIBRATION_VELOCITY = 10;
		
		
		try {/* w  ww .  j  a va2 s.  c o m*/
            final int originalX = frame.getLocationOnScreen().x;
            final int originalY = frame.getLocationOnScreen().y;
            for (int i = 0; i < VIBRATION_LENGTH; i++) {
                Thread.sleep(10);
                frame.setLocation(originalX, originalY + VIBRATION_VELOCITY);
                Thread.sleep(10);
                frame.setLocation(originalX, originalY - VIBRATION_VELOCITY);
                Thread.sleep(10);
                frame.setLocation(originalX + VIBRATION_VELOCITY, originalY);
                Thread.sleep(10);
                frame.setLocation(originalX, originalY);
            }
        } catch (Exception err) {
            err.printStackTrace();
        }
	}
	
	
	
	public static void setTrue() {
		exists = true;
	}
	
	
}
