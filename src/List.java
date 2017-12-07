import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JEditorPane;

import javax.swing.JPanel;

public class List {
	private String namaList;
	private int x;
	private int y;
	private int width = 70;
	private int height = 100;
	
	JPanel panel;
	JLabel label;
	JButton button;
	
	public JPanel createlist(String nama, int xPosisi, int yPosisi) {
		this.namaList = nama;
		this.x = xPosisi;
		this.y = yPosisi;
		
		panel = new JPanel();
		label = new JLabel(nama);
		button = new JButton("Add Activity");
		
		panel.setBounds(xPosisi, yPosisi, width, height);
		
		panel.add(button);
		panel.add(label);
		
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
}
