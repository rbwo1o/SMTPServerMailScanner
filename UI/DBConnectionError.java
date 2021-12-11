package UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class DBConnectionError {

	private static JFrame frame;

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
					DBConnectionError window = new DBConnectionError();
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
	public DBConnectionError() {
		initialize();
		frame.setVisible(true);
		vibrate();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Database Connection Error!");
		frame.setBounds(100, 100, 714, 218);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JLabel lblNewLabel = new JLabel("Cannot Connect To Database...");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 24));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 678, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(85)
					.addComponent(lblNewLabel)
					.addContainerGap(80, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
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
	
	
}
