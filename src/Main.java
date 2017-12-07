import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Color;
import javax.swing.JLabel;


public class Main {

	private JFrame frame;
	private JTextField textField;
	private JButton btnNewList, btnDelList, btnConfirm;
	private List[] list;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
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
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setIconImage(new ImageIcon(ClassLoader.getSystemResource("images/logo.png")).getImage());
		
		btnNewList = new JButton("New List");
		btnNewList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField.setVisible(true);
				btnConfirm.setVisible(true);
				textField.requestFocus();
			}
		});
		btnNewList.setBounds(43, 30, 100, 23);
		frame.getContentPane().add(btnNewList);
		
		btnDelList = new JButton("Delete List");
		btnDelList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//delete last list
			}
		});
		btnDelList.setBounds(631, 30, 100, 23);
		frame.getContentPane().add(btnDelList);
		
		btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//add new list
			}
		});
		btnConfirm.setBounds(338, 30, 100, 23);
		frame.getContentPane().add(btnConfirm);
		
		textField = new JTextField();
		textField.setBounds(164, 31, 164, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField.setVisible(false);
		btnConfirm.setVisible(false);
	}
}
