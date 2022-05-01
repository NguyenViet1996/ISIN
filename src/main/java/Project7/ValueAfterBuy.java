package Project7;

public class ValueAfterBuy {

		private Integer amountMoneyUserHaveAfterBuy;
		private Integer quantityIsinAfterBuy;

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

		public ValueAfterBuy() {

		}

		public int getAmountMoneyUserHaveAfterBuy() {
				return amountMoneyUserHaveAfterBuy;
		}

		public void setAmountMoneyUserHaveAfterBuy(Integer amountMoneyUserHaveAfterBuy) {
				this.amountMoneyUserHaveAfterBuy = amountMoneyUserHaveAfterBuy;
		}

		public int getQuantityIsinAfterBuy() {
				return quantityIsinAfterBuy;
		}

		public void setQuantityIsinAfterBuy(Integer quantityIsinAfterBuy) {
				this.quantityIsinAfterBuy = quantityIsinAfterBuy;
		}

}
