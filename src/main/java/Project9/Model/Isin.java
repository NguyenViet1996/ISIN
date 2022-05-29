package Project9.Model;

public class Isin {
		private final String nameIsin;
		private int quantityIsin;
		private final int priceIsin;

		@Override
		public String toString() {
				return nameIsin + "\t" + "\t" + "\t" + quantityIsin + "\t" + "\t" + priceIsin;
		}

		public Isin(String nameIsin,int quantityIsin,int priceIsin) {
				this.nameIsin = nameIsin;
				this.quantityIsin = quantityIsin;
				this.priceIsin = priceIsin;
		}

		public String getNameIsin() {
				return nameIsin;
		}

		public int getQuantityIsin() {
				return quantityIsin;
		}

		public void setQuantityIsin(int quantityIsin) {
				this.quantityIsin = quantityIsin;
		}

		public int getPriceIsin() {
				return priceIsin;
		}

}
