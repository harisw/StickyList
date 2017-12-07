import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JEditorPane;

public class Main {

	private JFrame frame;
	private JPanel[] panel;
	
	private int flag;
	private JLabel lblTodoList;

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
		flag = 0;
		frame = new JFrame();
		
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setIconImage(new ImageIcon(ClassLoader.getSystemResource("images/logo.png")).getImage());
		
		panel = new JPanel[5];
		
		JButton btnNewList = new JButton("New Activity");
		btnNewList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblTodoList.setVisible(true);
				panel[flag].setVisible(true);
				flag++;
			}
		});
		btnNewList.setBounds(43, 30, 100, 23);
		frame.getContentPane().add(btnNewList);
	
		lblTodoList = new JLabel("To-Do List");
		//lblTodoList.setBounds(30, 64, 58, 14);
		//lblTodoList.setVisible(false);
		
		panel[0] = new JPanel();
		panel[0].setBounds(30, 64, 105, 263);
		panel[0].setBackground(Color.WHITE);
		panel[0].add(lblTodoList);
		frame.getContentPane().add(panel[0]);
		panel[0].setVisible(false);
		
		panel[1] = new JPanel();
		panel[1].setBounds(160, 64, 105, 263);
		frame.getContentPane().add(panel[1]);
		panel[1].setBackground(Color.RED);
		panel[1].setVisible(false);
		
		panel[2] = new JPanel();
		panel[2].setBackground(Color.BLUE);
		panel[2].setBounds(290, 64, 105, 263);
		frame.getContentPane().add(panel[2]);
		panel[2].setVisible(false);
		
		panel[3] = new JPanel();
		panel[3].setBackground(Color.GREEN);
		panel[3].setBounds(420, 64, 105, 263);
		frame.getContentPane().add(panel[3]);
		panel[3].setVisible(false);
		
		JButton btnDelList = new JButton("Delete Activity");
		btnDelList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(flag>=0)
				{
					panel[flag].setVisible(false);
					if(flag!=0) flag--;
				}
			}
		});
		btnDelList.setBounds(157, 30, 100, 23);
		frame.getContentPane().add(btnDelList);
		
		
	}
}
