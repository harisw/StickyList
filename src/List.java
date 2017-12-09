import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;


public class List extends JPanel{
	private String namaList;
	private int x;
	private int y;
	private int width = 100;
	private int height = 200;
	private Activity[] activity;
	private int currentIndex = 0;
	
	private JLabel label;
	private JButton button;
	private JScrollPane scroll;
	
	public void setValue(String nama, int xPosisi, int yPosisi) {
		namaList = nama;
		x = xPosisi;
		y = yPosisi;
				
		label = new JLabel(nama);
		button = new JButton("Add Activity");
		
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
		activity[index] = null;
		for(int i = index; i<currentIndex; i++) {
			activity[i] = activity[i+1];
			activity[i].setIndex(i);
		}
		activity[currentIndex] = null;
		currentIndex--;
		
	}
	
	public void inActivity(Activity act){
		activity[currentIndex] = act;
		currentIndex++;
	}
}