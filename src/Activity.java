import java.awt.*;
import java.awt.event.*;
import javax.swing.*; 

public class Activity {
	private String content;
	private int x;
	private int y;
	private int width = 80;
	private int height = 20;
	private List parentList;
	private int myIndex;
	
	JTextField textField = new JTextField();
	
	public void setActivity(String isi, int xPosisi, int yPosisi, List listen) {
		this.content = isi;
		this.x = xPosisi;
		this.y = yPosisi;
		this.parentList = listen;
			
		textField.setBounds(xPosisi, yPosisi, width, height);
		
	}
	
	public void setContent(String isi) {
		this.content = isi;
	}
	
	public String getContent() {
		return this.content;
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
	
	public void setIndex(int index) {
		this.myIndex = index;
	}
	
	public int getIndex() {
		return this.myIndex;
	}
	
	public void setParent(List listen) {
		this.parentList = listen;
	}
	
	public List getParent() {
		return this.parentList;
	}
}
