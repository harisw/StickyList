import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeListener;

import javax.swing.*;

public class Main {

	private JMenuBar menuBar;
	public static JFrame frame;
	private JTextField textField;
	private JButton btnNewList, btnDelList, btnConfirm, btnUndo, btnRedo, btnSave;
	private JMenuItem jmUndo, jmRedo, jmExit;
	private List[] list = new List[5];
	private int flag = -1;
	private int confirmCount = 0;
	private int newConfirmCount;
	Caretaker caretaker = new Caretaker();
	Originator originator = new Originator();
	int saveFiles = 0, currentArticle = 0;
      
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Main window = new Main();
					Main.frame.setVisible(true);
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
		frame.getContentPane().setBackground(new Color(75, 191, 107));
		
		frame.setSize(1366, 768);
		frame.setTitle("Sticky List");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setIconImage(new ImageIcon(ClassLoader.getSystemResource("images/logo.png")).getImage());
		frame.setExtendedState(Frame.MAXIMIZED_BOTH );
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
		jmExit = new JMenuItem("Exit", iconExit);
		jmExit.setMnemonic(KeyEvent.VK_E);
		
		KeyStroke keyStrokeToOpen = KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK);
		jmExit.setAccelerator(keyStrokeToOpen);

		jmExit.setToolTipText("Exit application");
		file.add(jmExit);
		jmExit.addActionListener((ActionEvent event) -> {
			System.out.println("Exit...");
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
		jmUndo = new JMenuItem("Undo", iconUndo);
		jmRedo = new JMenuItem("Redo", iconRedo);
		edit.add(jmUndo);
		edit.add(jmRedo);
		jmUndo.addActionListener(buttonListener);		
		jmRedo.addActionListener(buttonListener);
		
		jmUndo.setMnemonic(KeyEvent.VK_Z);
		KeyStroke keyStrokeToUndo = KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK);
		jmUndo.setAccelerator(keyStrokeToUndo);
		jmUndo.setToolTipText("Undo");
		edit.add(jmUndo);
		jmUndo.addActionListener(buttonListener);
		
		jmRedo.setMnemonic(KeyEvent.VK_Y);
		KeyStroke keyStrokeToRedo = KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.CTRL_DOWN_MASK);
		jmRedo.setAccelerator(keyStrokeToRedo);
		jmRedo.setToolTipText("Redo");
		edit.add(jmRedo);
		jmRedo.addActionListener(buttonListener);

				
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
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(64, 163, 91));
		panel_2.setBounds(0, 0, 1371, 43);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		btnNewList = new JButton("New List");
		btnNewList.setBounds(10, 11, 94, 23);
		//frame.getContentPane().add(panel_2);
		panel_2.add(btnNewList);
		
		textField = new JTextField();
		textField.setBounds(114, 12, 113, 20);
		panel_2.add(textField);
		textField.setColumns(10);
		
		btnConfirm = new JButton("Confirm");
		btnConfirm.setBounds(237, 11, 88, 23);
		panel_2.add(btnConfirm);
		
		btnUndo = new JButton("Undo");
		btnUndo.setBounds(1256, 11, 70, 23);
		panel_2.add(btnUndo);
		
		btnRedo = new JButton("Redo");
		btnRedo.setBounds(1166, 11, 80, 23);
		panel_2.add(btnRedo);
		
		btnSave = new JButton("Save");
		btnSave.setBounds(1084, 11, 72, 23);
		panel_2.add(btnSave);

		/*button confirm button new list*/
		
		/*ButtonListener saveListener = new ButtonListener();
		ButtonListener undoListener = new ButtonListener();
		ButtonListener redoListener = new ButtonListener();
		ButtonListener newListener = new ButtonListener();
		ButtonListener delListener = new ButtonListener();
		ButtonListener confListener = new ButtonListener();*/
		
		btnDelList = new JButton("Delete List");
		btnDelList.setBounds(967, 11, 107, 23);
		panel_2.add(btnDelList);
		//btnDelList.addActionListener(delListener);
		
		btnSave.setVisible(true);
		//btnSave.addActionListener(saveListener);
		
		btnRedo.setVisible(true);
		//btnRedo.addActionListener(redoListener);
		
		btnUndo.setVisible(true);
		//btnUndo.addActionListener(undoListener);
		
		//btnConfirm.addActionListener(confListener);
		btnConfirm.setVisible(false);
		
		btnUndo.addActionListener(buttonListener);
		btnRedo.addActionListener(buttonListener);
		btnSave.setVisible(true);
		btnSave.addActionListener(buttonListener);
		btnDelList.addActionListener(buttonListener);
		btnNewList.addActionListener(buttonListener);
		btnConfirm.addActionListener(buttonListener);
		
		//textField = new JTextField();
		//textField.setColumns(10);
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				JTextField aTextField = (JTextField)e.getSource();
				String word = aTextField.getText();
				int charCount = word.length();
				if(Math.abs((confirmCount - charCount)) >= 1) {
					confirmCount = charCount;
					// Set the value for the current memento
					originator.set(word);
					
					// Add new article to the ArrayList
					caretaker.addMemento( originator.storeInMemento() );
					
					// saveFiles monitors how many articles are saved
					// currentArticle monitors the current article displayed
					
					saveFiles++;
					currentArticle++;
					
					System.out.println("Save Files " + saveFiles);
					// Make undo clickable
					btnUndo.setEnabled(true);
				}
				System.out.println("Char Entered: " + charCount);
			}
		});
		textField.setVisible(false);
		//btnNewList.addActionListener(newListener);
	}
	
	Action buttonListener = new Action() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
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
				
			} else if(e.getSource() == btnUndo || e.getSource()== jmUndo){
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
				
			} else if(e.getSource() == btnRedo || e.getSource() == jmRedo){
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
				System.out.println("masuk btn confirm");
				flag++;
				System.out.println("ini flag" + flag);
				list[flag] = new List();
				String txt = textField.getText();
				list[flag].setList(txt, flag);
				frame.getContentPane().add(list[flag]);
				list[flag].revalidate();
				list[flag].repaint();
				
				textField.setVisible(false);
				btnConfirm.setVisible(false);
				btnUndo.setEnabled(false);
				btnRedo.setEnabled(false);
				textField.setText("");
				
				String textInTextArea = textField.getText();
				originator.set(textInTextArea);
				caretaker.setNull(originator.storeInMemento());
				currentArticle = 0;
				saveFiles = 0;				
			} else if(e.getSource() == btnNewList){
				System.out.println("masuk");
				textField.setVisible(true);
				btnConfirm.setVisible(true);
				textField.requestFocus();
			}
		}
		
		@Override
		public void setEnabled(boolean b) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void removePropertyChangeListener(PropertyChangeListener listener) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void putValue(String key, Object value) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public boolean isEnabled() {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public Object getValue(String key) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public void addPropertyChangeListener(PropertyChangeListener listener) {
			// TODO Auto-generated method stub
			
		}
	};
}