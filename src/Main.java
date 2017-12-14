import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.JTextComponent;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager; 

public class Main {

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
		initUndoRedo(textField);
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
		btnDelList.setBounds(631, 30, 100, 23);
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
		
		textField = new JTextField();
		textField.setBounds(164, 31, 164, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField.setVisible(false);
		btnConfirm.setVisible(false);
	}
	
	private static void initUndoRedo(JTextComponent tc) {
		  UndoManager manager = new UndoManager();
		  tc.getDocument().addUndoableEditListener(manager);
		  tc.getActionMap().put("undo", new UndoAction(manager));
		  InputMap imap = tc.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
		  imap.put(KeyStroke.getKeyStroke(
		    KeyEvent.VK_Z, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()), "undo");
		  imap.put(KeyStroke.getKeyStroke(
		    KeyEvent.VK_Y, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()), "redo");
		}
	
		private static class UndoAction extends AbstractAction {
		  private final UndoManager undoManager;
		  public UndoAction(UndoManager manager) {
		    super("undo");
		    this.undoManager = manager;
		  }
		  @Override public void actionPerformed(ActionEvent e) {
		    try {
		      undoManager.undo();
		    } catch (CannotUndoException cue) {
		      //cue.printStackTrace();
		      Toolkit.getDefaultToolkit().beep();
		    }
		  }
		}
		
}
