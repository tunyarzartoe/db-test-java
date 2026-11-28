import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

// OrderControlDBAccessクラス（DAO）の定義
public class OrderControlDBAccess {
	// DBとの接続を確立する
	private Connection createConnection() {
		Connection con = null;
		try{ 
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/受注管理db",
					"user1",
					"pass1");
		} catch(ClassNotFoundException e) {
			System.out.println("JDBCドライバが見つかりません。");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("DB接続時にエラーが発生しました。");
			e.printStackTrace();
		}
		return con;
	}
	// DBとの接続を閉じる
	private void closeConnection(Connection con) {
		try{
			if(con != null) {
				con.close();
			}
		} catch(SQLException e) {
			System.out.println("DB切断時にエラーが発生しました。");
			e.printStackTrace();
		}
	}
	
	// 商品リストを取得する
	public ArrayList<ProductBean> findAllProducts() {
		Connection con = createConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ProductBean> list = new ArrayList<ProductBean>();
		try{
			if(con != null) {
				String sql = 	"SELECT 商品ID, 商品名, 単価 " +
								"FROM 商品マスタ;";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while(rs.next() == true) {
					String id = rs.getString("商品ID");
					String name = rs.getString("商品名");
					int price = rs.getInt("単価");
					ProductBean bean = new ProductBean(id, name, price);
					list.add(bean);
				}
			}
		} catch(SQLException e) {
			System.out.println(
					"DB切断時にエラーが発生しました（商品検索）。");
			e.printStackTrace();
		} finally {
			try{
				if(rs != null) {
					rs.close();
				}
			} catch(SQLException e) {
				System.out.println("DB切断時にエラーが発生しました。");
				e.printStackTrace();
			}
			try{
				if(pstmt != null) {
					pstmt.close();
				}
			} catch(SQLException e) {
				System.out.println("DB切断時にエラーが発生しました。");
				e.printStackTrace();
			}
		}
		closeConnection(con);
		return list;
	}
	
	// 指定した単価以下の商品リストを取得する
	public ArrayList<ProductBean> findProductsByPrice(int price) {
		Connection con = createConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ProductBean> list = new ArrayList<ProductBean>();
		try{
			if(con != null) {
				String sql = 	"SELECT 商品ID, 商品名, 単価 " +
								"FROM 商品マスタ " +
								"WHERE 単価 <= ?;";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, price);		// 1番目の?に引数priceを設定する
				rs = pstmt.executeQuery();
				while(rs.next() == true) {
					String id = rs.getString("商品ID");
					String name = rs.getString("商品名");
					int unitPrice = rs.getInt("単価");
					ProductBean bean = new ProductBean(id, name, unitPrice);
					list.add(bean);
				}
			}
		} catch(SQLException e) {
			System.out.println(
					"DB検索時にエラーが発生しました（商品検索）。");
			e.printStackTrace();
		} finally {
			try{
				if(rs != null) {
					rs.close();
				}
			} catch(SQLException e) {
				System.out.println("DB切断時にエラーが発生しました。");
				e.printStackTrace();
			}
			try{
				if(pstmt != null) {
					pstmt.close();
				}
			} catch(SQLException e) {
				System.out.println("DB切断時にエラーが発生しました。");
				e.printStackTrace();
			}
		}
		closeConnection(con);
		return list;
	}
	
	// 特定の商品情報を登録する
	public int createSpecificProduct() {
		Connection con = createConnection();
		PreparedStatement pstmt = null;
		int count = 0;		// 登録件数
		try{
			if(con != null) {
				String sql = 	"INSERT INTO 商品マスタ (商品ID, 商品名, 単価) " +
								"VALUES (?, ?, ?);";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "F-101");	// 商品ID
				pstmt.setString(2, "バナナ");	// 商品名
				pstmt.setInt(3, 1380);			// 単価
				count = pstmt.executeUpdate();
			}
		} catch(SQLException e) {
			System.out.println(
					"DB登録時にエラーが発生しました（商品登録）。");
			e.printStackTrace();
		} finally {
			try{
				if(pstmt != null) {
					pstmt.close();
				}
			} catch(SQLException e) {
				System.out.println("DB切断時にエラーが発生しました。");
				e.printStackTrace();
			}
		}
		closeConnection(con);
		return count;
	}
	
	// 引数の商品情報を登録する
	public int createProduct(String id, String name, int price) {
		Connection con = createConnection();
		PreparedStatement pstmt = null;
		int count = 0;		// 登録件数
		try{
			if(con != null) {
				String sql = 	"INSERT INTO 商品マスタ (商品ID, 商品名, 単価) " +
								"VALUES (?, ?, ?);";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, id);		// 商品ID
				pstmt.setString(2, name);	// 商品名
				pstmt.setInt(3, price);		// 単価
				count = pstmt.executeUpdate();
			}
		} catch(SQLException e) {
			System.out.println(
					"DB登録時にエラーが発生しました（商品登録）。");
			e.printStackTrace();
		} finally {
			try{
				if(pstmt != null) {
					pstmt.close();
				}
			} catch(SQLException e) {
				System.out.println("DB切断時にエラーが発生しました。");
				e.printStackTrace();
			}
		}
		closeConnection(con);
		return count;
	}
}
