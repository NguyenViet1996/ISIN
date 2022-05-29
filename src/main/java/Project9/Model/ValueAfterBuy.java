package Project9.Model;

public class ValueAfterBuy {

		private final int amountMoneyUserHaveAfterBuy;
		private final int quantityIsinAfterBuy;

		@Override
		public String toString() {
				return "ValueAfterBuy{" +
						"amountMoneyUserHaveAfterBuy=" + amountMoneyUserHaveAfterBuy +
						", quantityIsinAfterBuy=" + quantityIsinAfterBuy +
						'}';
		}

		public ValueAfterBuy(Integer amountUserHaveAfterBuy, Integer quantityIsinAfterBuy) {
				this.amountMoneyUserHaveAfterBuy = amountUserHaveAfterBuy;
				this.quantityIsinAfterBuy = quantityIsinAfterBuy;
		}

		public int getAmountMoneyUserHaveAfterBuy() {
				return amountMoneyUserHaveAfterBuy;
		}

		public int getQuantityIsinAfterBuy() {
				return quantityIsinAfterBuy;
		}

}
