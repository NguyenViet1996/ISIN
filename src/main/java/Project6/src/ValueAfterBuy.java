package Project6.src;

public class ValueAfterBuy {

		private int amountMoneyUserHaveAfterBuy;
		private int quantityIsinAfterBuy;

		public ValueAfterBuy(int amountUserHaveAfterBuy, int quantityIsinAfterBuy) {
				this.amountMoneyUserHaveAfterBuy = amountUserHaveAfterBuy;
				this.quantityIsinAfterBuy = quantityIsinAfterBuy;
		}

		public ValueAfterBuy() {

		}

		public int getAmountMoneyUserHaveAfterBuy() {
				return amountMoneyUserHaveAfterBuy;
		}

		public void setAmountMoneyUserHaveAfterBuy(int amountMoneyUserHaveAfterBuy) {
				this.amountMoneyUserHaveAfterBuy = amountMoneyUserHaveAfterBuy;
		}

		public int getQuantityIsinAfterBuy() {
				return quantityIsinAfterBuy;
		}

		public void setQuantityIsinAfterBuy(int quantityIsinAfterBuy) {
				this.quantityIsinAfterBuy = quantityIsinAfterBuy;
		}

}
