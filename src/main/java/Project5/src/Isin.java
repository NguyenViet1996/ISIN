package Project5.src;

public class Isin {

    private String nameIsin;
    private Integer quantityIsin;
    private Integer priceIsin;

    public Isin(String nameIsin, int quantityIsin, int priceIsin){
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
