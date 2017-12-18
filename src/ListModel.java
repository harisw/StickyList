import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ListModel {
	
	public static void insert(List list) {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		
		try {
			conn = ConnectionConfiguration.getConnection();
			preparedStatement = conn.prepareStatement("INSERT INTO list (title, color, x, y)"
					+ "VALUES (?, ?, ?, ?)");
			
			System.out.println(preparedStatement);
			preparedStatement.setString(1, list.getNamaList());
			preparedStatement.setString(2, "default");
			preparedStatement.setInt(3, list.getX());
			preparedStatement.setInt(4, list.getY());
			preparedStatement.executeUpdate();
		} catch(Exception err) {
			err.printStackTrace();
		} finally {
			if(preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch(SQLException err) {
					err.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch(SQLException err) {
					err.printStackTrace();
				}
			}
		}
	}
}