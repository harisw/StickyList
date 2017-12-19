import java.awt.*;
import java.awt.event.*;
import javax.swing.*; 

public class Activity extends JPanel{
	private String content;
	private int x;
	private int y;
	private int width = 182;
	private int height = 25;
	private List parentList;
	private int myIndex;
	private int myParentId;
	
	JButton btnDelAct;
	JTextField textField = new JTextField();
	
	public void setActivity(String isi, int xPosisi, int yPosisi, List listen) {
		this.content = isi;
		this.x = xPosisi;
		this.y = yPosisi;
		this.parentList = listen;
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("yaa");
			}
			public void mousePressed(MouseEvent e) {
				System.out.println("i");
			}
			public void mouseReleased(MouseEvent e) {
				//Move to target list
				Point p = ((Component) e.getSource()).getLocation();
			    e.translatePoint((int) p.getX(), (int) p.getY());
			    Component tile = Main.frame.getContentPane().getComponentAt(e.getX(), e.getY());
			    List target = (List)tile;
			    System.out.println(myIndex);
			    parentList.outActivity(myIndex);
			    target.inActivity(Activity.this);
//				panel.setLocation(e.getX(), e.getY());
//				setX(e.getX());
//				setY(e.getY());
			    //list[flag].setVisible(false);
			    //Main.repaintComponents(e.getX(), e.getY());
			   
				//System.out.println(target.getNamaList());
			}
			public void mouseDragged(MouseEvent e) {
				System.out.println("man");
			}
		});
		
		btnDelAct = new JButton("x");
		btnDelAct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("ini delete activity");
				parentList.outActivity(myIndex);
				
			}
		});
		
		setBackground(new Color(255, 255, 255));
		textField.setColumns(10);
		textField.setBounds(2,3,135,20);
		btnDelAct.setBounds(140,3,40,20);
		btnDelAct.setFont(new Font("Arial", Font.PLAIN, 10));
		btnDelAct.setForeground(Color.WHITE);
		btnDelAct.setBackground(new Color(217, 83, 79));
		setLayout(null);

		setBounds(xPosisi, yPosisi, width, height);
		add(textField);
		add(btnDelAct);
		
	}
	
	public void setContent(String isi) {
		this.content = isi;
	}
	
	public String getContent() {
		return this.textField.getText();
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
	
	public void update(int index) {
		this.myIndex = index;
		setLocation(x, 80 + (index*30));
		this.x = x;
		this.y = 80 + (index*30);
		//revalidate();
		repaint();
	}
	
	public void setParentId(int id) {
		this.myParentId = id;
	}
	
	public int getParentId() {
		return this.myParentId;

	}
}
