
import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionConfiguration{
	public static final String URL = "jdbc:mysql://localhost:3306/stickylist";
	
	public static final String USERNAME = "root";
	
	public static final String PASS = "";
	
	public Connection conn = null;
	public static Connection getConnection() {
		Connection conn = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(URL, USERNAME, PASS);
		} catch(Exception err) {
			err.printStackTrace();
		}
		return conn;
	}
}
