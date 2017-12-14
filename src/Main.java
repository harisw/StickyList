import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main {

	private JFrame frame;
	private JTextField textField;
	private JButton btnNewList, btnDelList, btnConfirm, btnUndo, btnRedo, btnSave;
	private List[] list = new List[5];
	private int flag = -1;
	   
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
		
		frame.setBounds(100, 100, 800, 600);
		frame.setTitle("Sticky List");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setIconImage(new ImageIcon(ClassLoader.getSystemResource("images/logo.png")).getImage());
		
		textField = new JTextField();
		textField.setBounds(164, 31, 164, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField.setVisible(false);

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
				if(flag>=0)
				{
					list[flag].setVisible(false);
					list[flag] = null;
					flag--;
				}

			}
		});
		btnDelList.setBounds(640, 30, 100, 23);
		frame.getContentPane().add(btnDelList);
		
		btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
			}
		});
		btnConfirm.setBounds(338, 30, 100, 23);
		frame.getContentPane().add(btnConfirm);
		btnConfirm.setVisible(false);
		
		ButtonListener saveListener = new ButtonListener();
		ButtonListener undoListener = new ButtonListener();
		ButtonListener redoListener = new ButtonListener();
		
		btnUndo = new JButton("Undo");
		btnUndo.setBounds(500, 56, 100, 23);
		frame.getContentPane().add(btnUndo);
		btnUndo.setVisible(true);
		btnUndo.addActionListener(undoListener);
		
		btnSave = new JButton("Save");
		btnSave.setBounds(460, 30, 100, 23);
		frame.getContentPane().add(btnSave);
		btnSave.setVisible(true);
		btnSave.addActionListener(saveListener);
		
		btnRedo = new JButton("Redo");
		btnRedo.setBounds(600, 56, 100, 23);
		frame.getContentPane().add(btnRedo);
		btnRedo.setVisible(true);
		btnRedo.addActionListener(redoListener);
	}
	
	class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
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
				}	
		}	
	}
}