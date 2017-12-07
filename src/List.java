import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;


public class List {
	private String namaList;
	private int x;
	private int y;
	private int width = 70;
	private int height = 100;
	private Activity[] activity;
	private int currentIndex = 0;
	
	JPanel panel;
	JLabel label;
	JButton button;
	JScrollPane scroll;
	
	public JPanel createList(String nama, int xPosisi, int yPosisi) {
		this.namaList = nama;
		this.x = xPosisi;
		this.y = yPosisi;
		
		scroll = new JScrollPane();
		panel = new JPanel();
		label = new JLabel(nama);
		button = new JButton("Add Activity");
		
		panel.setBounds(xPosisi, yPosisi, width, height);
		
		panel.add(button);
		panel.add(label);
		panel.add(scroll);
		return panel;
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