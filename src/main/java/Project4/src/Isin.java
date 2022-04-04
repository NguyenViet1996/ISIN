package Project4.src;

public class Isin {

    public String nameIsin;
    public Integer quantityIsin;
    public Integer priceIsin;

    /**
     * Create structure class
     * @param nameIsin
     * @param quantityIsin
     * @param priceIsin
     */

    public Isin(String nameIsin, int quantityIsin,int priceIsin){
        this.nameIsin = nameIsin;
        this.quantityIsin = quantityIsin;
        this.priceIsin = priceIsin;
    }

    public void setNameIsin(String nameIsin){
        this.nameIsin = nameIsin;
    }
    public String getNameIsin(){
        return this.nameIsin;
    }

    public void setQuantityIsin(int quantityIsin){
        this.quantityIsin = quantityIsin;
    }
    public Integer getQuantityIsin(){
        return this.quantityIsin;
    }

    public void setPriceIsin(int priceIsin){
        this.priceIsin = priceIsin;
    }
    public Integer getPriceIsin(){
        return this.priceIsin;
    }

}
