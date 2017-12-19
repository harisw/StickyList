import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ActivityModel {
	public static void insert(Activity activity) {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		
		try {
			conn = ConnectionConfiguration.getConnection();
			preparedStatement = conn.prepareStatement("INSERT INTO activity (title, index, x, y, list_id)"
					+ "VALUES (?, ?, ?, ?, ?)");
			
			System.out.println(activity.getContent());
			System.out.println(activity.getIndex());
			preparedStatement.setString(1, activity.getContent());
			preparedStatement.setInt(2, activity.getIndex());
			preparedStatement.setInt(3, activity.getX());
			preparedStatement.setInt(4, activity.getY());
			preparedStatement.setInt(5, activity.getParentId());
			preparedStatement.executeUpdate();
		} catch(Exception err) {
//			err.printStackTrace();
		} finally {
			if(preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch(SQLException err) {
//					err.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch(SQLException err) {
//					err.printStackTrace();
				}
			}
		}
	}
}
