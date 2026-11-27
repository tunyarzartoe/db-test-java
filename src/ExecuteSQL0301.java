import java.util.ArrayList;

// ExecuteSQL0301クラスの定義
public class ExecuteSQL0301 {
	public static void main(String[] args) {
		OrderControlDBAccess db = new OrderControlDBAccess();
		int price = 1500;	// 検索条件の単価（任意に設定）

		ArrayList<ProductBean> list = db.findProductsByPrice(price);

		System.out.println("単価が" + price + "円以下の商品");
		for(ProductBean bean : list) {
			System.out.println("商品ID：" + bean.getId() +
					"　商品名：" + bean.getName() +
					"　単価：" + bean.getPrice());
		}
	}
}