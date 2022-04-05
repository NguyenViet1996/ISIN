package Project6.src;

public class Isin {
		private String nameIsin;
		private int quantityIsin,priceIsin;

		@Override
		public String toString() {
				return nameIsin + "\t" + "\t" + "\t" + quantityIsin + "\t" + "\t" + priceIsin;
		}

		public Isin(String nameIsin,int quantityIsin,int priceIsin){
				this.nameIsin = nameIsin;
				this.quantityIsin = quantityIsin;
				this.priceIsin = priceIsin;
		}

		public String getNameIsin() {
				return nameIsin;
		}

		public void setNameIsin(String nameIsin) {
				this.nameIsin = nameIsin;
		}

		public Integer getQuantityIsin() {
				return quantityIsin;
		}

		public void setQuantityIsin(Integer quantityIsin) {
				this.quantityIsin = quantityIsin;
		}

		public Integer getPriceIsin() {
				return priceIsin;
		}

		public void setPriceIsin(Integer priceIsin) {
				this.priceIsin = priceIsin;
		}

}
