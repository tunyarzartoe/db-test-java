// ExecuteSQL0303クラスの定義
public class ExecuteSQL0303 {
	public static void main(String[] args) {
		OrderControlDBAccess db = new OrderControlDBAccess();

		// 登録する商品情報（任意に設定）
		String id = "F-102";
		String name = "リンゴ";
		int price = 200;

		int count = db.createProduct(id, name, price);

		System.out.println("登録件数：" + count + "件");
	}
}