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
				act.setActivity("", 10, 60+(currentIndex*25), List.this);
				act.setParentId(id);
				act.setIndex(currentIndex);
				act.textField.setColumns(8);
				inActivity(act);
				//System.out.println(activity[currentIndex].getParent().namaList);
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
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return this.id;
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
	
	public void saveChild() {
		for(Activity c: child)
		{
			System.out.println("masukin Activity");
			System.out.println(c.getContent());
			ActivityModel.insert(c);
		}
	}
}