// ExecuteSQL0302クラスの定義
public class ExecuteSQL0302 {
	public static void main(String[] args) {
		OrderControlDBAccess db = new OrderControlDBAccess();

		int count = db.createSpecificProduct();

		System.out.println("登録件数：" + count + "件");
	}
}