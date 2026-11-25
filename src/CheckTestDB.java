import java.sql.Connection;			//DB接続管理
import java.sql.DriverManager;		//JDBCドライバ管理
import java.sql.ResultSet;			//SQL文の実行結果
import java.sql.SQLException;		//SQL関連の例外
import java.sql.Statement;
public class CheckTestDB {
	public static void main(String[] args) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/TestDB",
					"user1",
					"pass1");
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT * FROM EMPLOYEE");
			System.out.println("TABE :\tEMPLOYEE");
			while (rs.next()) {
				String id = rs.getString("ID");
				String name = rs.getString("NAME");
				System.out.println("ID :\t" + id);
				System.out.println("NAME :\t" + name);
			}
		} catch (SQLException e) {
			System.out.println("DBアクセス時にエラーが発生しました。");
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				System.out.println("DBアクセス時にエラーが発生しました。");
				e.printStackTrace();
			}
		}
	}
}