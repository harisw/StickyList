import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main {

	private JMenuBar menuBar;
	private JFrame frame;
	private JTextField textField;
	private JButton btnNewList, btnDelList, btnConfirm, btnUndo, btnRedo, btnSave;
	private List[] list = new List[5];
	private int flag = -1;
	private int oldChar = 0;
	private int newChar;
   Caretaker caretaker = new Caretaker();
   Originator originator = new Originator();
   int saveFiles = 0, currentArticle = 0;
      
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
		frame.getContentPane().setBackground(new Color(153, 204, 153));
		
		frame.setSize(800, 600);
		frame.setTitle("Sticky List");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setIconImage(new ImageIcon(ClassLoader.getSystemResource("images/logo.png")).getImage());
		//frame.setLayout(new BorderLayout());
		
		/*create menu bar*/
		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		//file - exit
		ImageIcon iconExit = new ImageIcon(ClassLoader.getSystemResource("images/exit.png"));
		Image imageExit = iconExit.getImage();
		Image newImgExit = imageExit.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH); 
	    iconExit = new ImageIcon(newImgExit);
		
		JMenu file = new JMenu("File");
		file.setMnemonic(KeyEvent.VK_F);
		menuBar.add(file);
		JMenuItem exit = new JMenuItem("Exit", iconExit);
		exit.setMnemonic(KeyEvent.VK_E);
		exit.setToolTipText("Exit application");
		file.add(exit);
		exit.addActionListener((ActionEvent event) -> {
            System.exit(0);
        });
		
		//edit - undo - redo
		ImageIcon iconUndo = new ImageIcon(ClassLoader.getSystemResource("images/undo.png"));
		Image imageUndo = iconUndo.getImage();
		Image newImgUndo = imageUndo.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH); 
	    iconUndo = new ImageIcon(newImgUndo);
	    
	    ImageIcon iconRedo = new ImageIcon(ClassLoader.getSystemResource("images/redo.png"));
		Image imageRedo = iconRedo.getImage();
		Image newImgRedo = imageRedo.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH); 
	    iconRedo = new ImageIcon(newImgRedo);
		
		JMenu edit = new JMenu("Edit");
		menuBar.add(edit);
		JMenuItem undo = new JMenuItem("Undo", iconUndo);
		JMenuItem redo = new JMenuItem("Redo", iconRedo);
		edit.add(undo);
		edit.add(redo);
		
		//help - about
		ImageIcon iconAbout = new ImageIcon(ClassLoader.getSystemResource("images/logo.png"));
		Image imageAbout = iconAbout.getImage();
		Image newImgAbout = imageAbout.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH); 
	    iconAbout = new ImageIcon(newImgAbout);
	    
		JMenu help = new JMenu("Help");
		menuBar.add(help);
		JMenuItem about = new JMenuItem("About", iconAbout);
		help.add(about);
		/*end of create menu bar*/
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(102, 153, 153));
		panel.setBounds(0, 0, 138, 43);
		frame.getContentPane().add(panel);
		btnNewList = new JButton("New List");
		panel.add(btnNewList);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(102, 153, 153));
		panel_1.setBounds(129, 0, 213, 43);
		frame.getContentPane().add(panel_1);
		
		textField = new JTextField();
		panel_1.add(textField);
		textField.setColumns(10);
		btnConfirm = new JButton("Confirm");
		panel_1.add(btnConfirm);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(102, 153, 153));
		panel_2.setBounds(333, 0, 451, 43);
		frame.getContentPane().add(panel_2);
		
		btnDelList = new JButton("Delete List");
		panel_2.add(btnDelList);
		
		btnSave = new JButton("Save");
		panel_2.add(btnSave);
		
		btnRedo = new JButton("Redo");
		panel_2.add(btnRedo);
		
		btnUndo = new JButton("Undo");
		panel_2.add(btnUndo);
		
		/*button confirm button new list*/
		
		ButtonListener saveListener = new ButtonListener();
		ButtonListener undoListener = new ButtonListener();
		ButtonListener redoListener = new ButtonListener();
		ButtonListener newListener = new ButtonListener();
		ButtonListener delListener = new ButtonListener();
		ButtonListener confListener = new ButtonListener();
		
		btnUndo.setVisible(true);
		btnUndo.addActionListener(undoListener);
		btnRedo.setVisible(true);
		btnRedo.addActionListener(redoListener);
		btnSave.setVisible(true);
		btnSave.addActionListener(saveListener);
		btnDelList.addActionListener(delListener);
		btnNewList.addActionListener(newListener);
		btnConfirm.addActionListener(confListener);
		
		btnConfirm.setVisible(false);
		textField.setVisible(false);
	}
	
	class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("Button pressed is " +e);
			if(e.getSource() == btnSave){
				// Get text in JTextField
				String textInTextArea = textField.getText();
				
				// Set the value for the current memento
				originator.set(textInTextArea);
				
				// Add new article to the ArrayList
				caretaker.addMemento( originator.storeInMemento() );
				
				// saveFiles monitors how many articles are saved
				// currentArticle monitors the current article displayed
				
				saveFiles++;
				currentArticle++;
				
				System.out.println("Save Files " + saveFiles);
				// Make undo clickable
				btnUndo.setEnabled(true);
				
			} else if(e.getSource() == btnUndo){
				if(currentArticle >= 1){
					
					// Decrement to the current article displayed
					currentArticle--;
						
					// Get the older article saved and display it in JTextArea
					String textBoxString = originator.restoreFromMemento( caretaker.getMemento(currentArticle) );
					textField.setText(textBoxString);
						
					// Make Redo clickable
					btnRedo.setEnabled(true);
				} else {
					// Don't allow user to click Undo						
					btnUndo.setEnabled(false);
				}
				
			} else if(e.getSource() == btnRedo){
					if((saveFiles - 1) > currentArticle){
						
						// Increment to the current article displayed
						currentArticle++;
						
						// Get the newer article saved and display it in JTextArea
						String textBoxString = originator.restoreFromMemento( caretaker.getMemento(currentArticle) );			
						textField.setText(textBoxString);
					
						// Make undo clickable
						btnUndo.setEnabled(true);
					} else {
						// Don't allow user to click Redo
						btnRedo.setEnabled(false);
					}
			} else if(e.getSource() == btnDelList){
				//delete last list
				if(flag>=0)
				{
					list[flag].setVisible(false);
					list[flag] = null;
					flag--;
				}	
			} else if(e.getSource() == btnConfirm){
				//add new list
				flag++;
				list[flag] = new List();
				String txt = textField.getText();
				list[flag].setList(txt, 30+(flag*130), 64);
				frame.getContentPane().add(list[flag]);
				list[flag].revalidate();
				list[flag].repaint();
				
				textField.setVisible(false);
				btnConfirm.setVisible(false);
				textField.setText("");
				//frame.pack();	
			} else if(e.getSource() == btnNewList){
				System.out.println("masuk");
				textField.setVisible(true);
				btnConfirm.setVisible(true);
				textField.requestFocus();	
			}	
		}	
	}
}