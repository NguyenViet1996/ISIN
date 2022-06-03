package Project9.Model;

public class Isin {
		private final int id;
		private final String nameIsin;
		private int quantityIsin;
		private final int priceIsin;

		@Override
		public String toString() {
				return id +"\t" + "\t" +nameIsin + "\t" + "\t" + "\t" + quantityIsin + "\t" + "\t" + priceIsin;
		}

		public Isin(int id, String nameIsin,int quantityIsin,int priceIsin) {
				this.id = id;
				this.nameIsin = nameIsin;
				this.quantityIsin = quantityIsin;
				this.priceIsin = priceIsin;
		}

		public int getId() {
				return this.id;
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
