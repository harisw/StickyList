import java.awt.*;
import java.awt.event.*;
import javax.swing.*; 

public class List extends JPanel{
	private String namaList;
	private int x;
	private int y;
	private int width = 100;
	private int height = 200;
	private Activity[] child = new Activity[5];
	private int currentIndex = 0;
	
	private JLabel label;
	private JButton button;
	//private JScrollPane scroll;
	//private JTextField[] set = new JTextField[5];
	
	public void setList(String nama, int xPosisi, int yPosisi) {
		namaList = nama;
		x = xPosisi;
		y = yPosisi;
				
		label = new JLabel(nama);
		button = new JButton("Add Activity");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Activity act = new Activity();
				act.setActivity("", 10, 60+(currentIndex*25), List.this);
				act.textField.setColumns(8);
				inActivity(act);
				//System.out.println(activity[currentIndex].getParent().namaList);
			}
		});
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("yo");
			}
			public void mousePressed(MouseEvent e) {
				System.out.println("i");
			}
			public void mouseReleased(MouseEvent e) {
				//System.out.println(List.this);
				Point p = ((Component) e.getSource()).getLocation();
			    e.translatePoint((int) p.getX(), (int) p.getY());
				setLocation(e.getX(), e.getY());
				setX(e.getX());
				setY(e.getY());
			    //list[flag].setVisible(false);
			    //Main.repaintComponents(e.getX(), e.getY());
			   
				System.out.println("ahah");
			}
			public void mouseDragged(MouseEvent e) {
				System.out.println("man");
			}
		});
		
		setBounds(xPosisi, yPosisi, width, height);
		setBackground(Color.WHITE);
		add(label);
		add(button);
	}
	
	public void setNamaList(String nama) {
		this.namaList = nama;
	}
	
	public String getNamaList() {
		return this.namaList;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getX() {
		return this.x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getY() {
		return this.y;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public int getHeight(int height) {
		return this.height;
	}
	
	public void outActivity(int index){
		child[index] = null;
		for(int i = index; i<currentIndex; i++) {
			child[i] = child[i+1];
			child[i].setIndex(i);
		}
		child[currentIndex] = null;
		currentIndex--;
		
	}
	
	public void inActivity(Activity act){
		child[currentIndex] = act;
		add(child[currentIndex].textField);
		child[currentIndex].textField.revalidate();
		child[currentIndex].textField.repaint();
		currentIndex++;
	}
}