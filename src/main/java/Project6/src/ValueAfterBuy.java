package Project6.src;

public class BuyResult {

		private int amountUserHaveAfterBuy;
		private int quantityIsinAfterBuy;

		public BuyResult(int amountUserHaveAfterBuy,int quantityIsinAfterBuy) {
				this.amountUserHaveAfterBuy = amountUserHaveAfterBuy;
				this.quantityIsinAfterBuy = quantityIsinAfterBuy;
		}

		public BuyResult() {

		}

		public int getAmountUserHaveAfterBuy() {
				return amountUserHaveAfterBuy;
		}

		public void setAmountUserHaveAfterBuy(int amountUserHaveAfterBuy) {
				this.amountUserHaveAfterBuy = amountUserHaveAfterBuy;
		}

		public int getQuantityIsinAfterBuy() {
				return quantityIsinAfterBuy;
		}

		public void setQuantityIsinAfterBuy(int quantityIsinAfterBuy) {
				this.quantityIsinAfterBuy = quantityIsinAfterBuy;
		}

}
