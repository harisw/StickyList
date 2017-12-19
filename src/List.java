import java.awt.*;
import java.awt.event.*;
import javax.swing.*; 

public class List extends JPanel{
	private String namaList;
	private int x;
	private int y;
	private int width = 200;
	private int height = 580;
	private Activity[] child = new Activity[5];
	private int currentIndex = 0;
	private int id;
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
				act.setActivity("", 10, 120+(currentIndex*40), List.this);
				act.textField.setColumns(10);
				act.setParentId(id);
				act.setIndex(currentIndex);
				inActivity(act);
				//System.out.println(activity[currentIndex].getParent().namaList);
			}
		});
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("yaa");
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
		
		label.setBounds(5, 0, 100, 40);
		button.setBounds(5, 40, 190, 30);
		setLayout(null);
		setBounds(xPosisi, yPosisi, width, height);
		setBackground(new Color(226, 228, 230));
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
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}
	public void outActivity(int index){
		currentIndex--;
		child[index] = null;
		for(int i = index; i<currentIndex; i++) {
			child[i] = child[i+1];
			child[i].setIndex(i);
		}
		child[currentIndex] = null;
	}
	
	public void inActivity(Activity act){
		child[currentIndex] = act;
		act.setActivity("", 10, 80+(currentIndex*30), List.this);
		act.setIndex(currentIndex);
		add(child[currentIndex].panel);
		child[currentIndex].panel.revalidate();
		child[currentIndex].panel.repaint();
		currentIndex++;
	}
	
	public void saveChild() {
		for(Activity c: child)
		{
			System.out.println("masukin Activity");
			System.out.println(c.getContent());
			ActivityModel.insert(c);
		}
	}
}