import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Arrays.*;
public class ListModel {
	
	public static void insert(List list) {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		
		try {
			conn = ConnectionConfiguration.getConnection();
			preparedStatement = conn.prepareStatement("INSERT INTO list (title, color, x, y)"
					+ "VALUES (?, ?, ?, ?)");
			
			preparedStatement.setString(1, list.getNamaList());
			preparedStatement.setString(2, "default");
			preparedStatement.setInt(3, list.getX());
			preparedStatement.setInt(4, list.getY());
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
	
	public static void deleteAll() {
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		try {
			conn = ConnectionConfiguration.getConnection();
			preparedStatement = conn.prepareStatement("TRUNCATE TABLE list");
			preparedStatement.executeUpdate();
		} catch(Exception err) {
//			err.printStackTrace();
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
	
	public static List[] getAll() {
		int count = 0;
		List[] list = new List[10];
		Connection conn = null;
		Statement statement = null;
		ResultSet result = null;
		try {
			conn = ConnectionConfiguration.getConnection();
			statement = conn.createStatement();
			result = statement.executeQuery("SELECT * FROM list");
			
			while(result.next()) {
				list[count] = new List();
				list[count].setId(result.getInt("id"));
				list[count].setNamaList(result.getString("title"));
				list[count].setX(result.getInt("x"));
				list[count].setY(result.getInt("y"));
				count++;
//				list = append(list, newList);
			}
		} catch(Exception err) {
			err.printStackTrace();
		} finally {
			if(result != null) {
				try {
					result.close();
				} catch(SQLException err) {
					err.printStackTrace();
				}
			}
			if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
		}
		System.out.println(list.length);
		return list;
	}
	
	private static <T> T[] append(T[] arr, T element) {
		final int N = arr.length;
		arr = Arrays.copyOf(arr, N + 1);
		arr[N] = element;
		return arr;
	}
}