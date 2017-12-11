import java.awt.*;
import java.awt.event.*;
import javax.swing.*; 

public class Main {
	private JMenuBar menuBar;
	private JMenu menu;
	private JToolBar toolbar;
	private JFrame frame;
	private JPanel pan;
	private JTextField textField;
	private JButton btnNewList, btnDelList, btnConfirm;
	private List[] list = new List[5];
	private int flag = -1;
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
		frame.setTitle("Sticky List");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setIconImage(new ImageIcon(ClassLoader.getSystemResource("images/logo.png")).getImage());
		
		//Add new Toolbar
		menuBar = new JMenuBar();
		menu = new JMenu("Menu");
		menu.setMnemonic(KeyEvent.VK_ALT);
		menuBar.add(menu);
		
		JMenuItem menuItem = new JMenuItem("Save Workspace");
		menuItem.getAccessibleContext().setAccessibleDescription("Save all of your List and "
				+ "activites into Database");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Save Pressed");
			}
		});
		menu.add(menuItem);
		
		menuItem = new JMenuItem("Quit");
		menuItem.getAccessibleContext().setAccessibleDescription("Exit this Application");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Quit Pressed");
			}
		});
		menu.add(menuItem);
		
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
			public void actionPerformed(ActionEvent arg0) {
				//add new list
				flag++;
				list[flag] = new List();
				String txt = textField.getText();
				list[flag].setValue(txt, 30+(flag*130), 64);
				frame.getContentPane().add(list[flag]);
				list[flag].revalidate();
				list[flag].repaint();
				
				textField.setVisible(false);
				btnConfirm.setVisible(false);
				textField.setText("");
				//frame.pack();
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
		frame.setJMenuBar(menuBar);
	}
	
	class DropDownButton extends AbstractButton
	{
	   JButton actionButton;
	   JToggleButton menuButton;
	   JPopupMenu popupMenu;
	  
	   public DropDownButton(JButton _actionButton, JPopupMenu _popupMenu) {
	      this.popupMenu = _popupMenu;
	      this.actionButton = _actionButton;
	  
	      setLayout(new BorderLayout());
	      actionButton.setBorderPainted(false);
	      add(BorderLayout.CENTER, actionButton);
	      menuButton = new JToggleButton(new ImageIcon("down.jpg"));
	      menuButton.setPreferredSize(new Dimension(15, 10));
	      add(BorderLayout.EAST, menuButton);
	      menuButton.setBorderPainted(false);
	   
	      MouseAdapter ma = new MouseAdapter() {
	         public void mouseClicked(MouseEvent me) { }
	         public void mousePressed(MouseEvent me) {
	            if (me.getSource() == actionButton) {
	               menuButton.setSelected(true);
	            }
	         }
	         public void mouseReleased(MouseEvent me) {
	            if (me.getSource() == actionButton) {
	               menuButton.setSelected(false);
	            }
	         }
	         public void mouseEntered(MouseEvent me) {
	            setRolloverBorder();
	         }
	         public void mouseExited(MouseEvent me) {
	            unsetRolloverBorder();
	         }
	      };
	  
	      actionButton.addMouseListener(ma);
	      menuButton.addMouseListener(ma);
	  
	      menuButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent ae) {
	            popupMenu.show(actionButton, 0, actionButton.getSize().height);
	         }
	      });
	   } 
	  
	   protected void setRolloverBorder() {
	      actionButton.setBorderPainted(true);
	      menuButton.setBorderPainted(true);
	   }
	  
	   protected void unsetRolloverBorder() {
	      actionButton.setBorderPainted(false);
	      menuButton.setBorderPainted(false);
	   }
	}
}
