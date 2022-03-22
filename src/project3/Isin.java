package project3;


public class Isin {

    private String nameIsin;
    private Integer quantityInput;
    private Integer priceInput;

    /**
     * Create structure class
     * @param nameIsin
     * @param quantityInput
     * @param priceInput
     */

   public Isin(String nameIsin, int quantityInput,int priceInput){
       this.nameIsin = nameIsin;
       this.quantityInput = quantityInput;
       this.priceInput = priceInput;
   }

   public void setNameIsin(String nameIsin){
       this.nameIsin = nameIsin;
   }
   public String getNameIsin(){
       return this.nameIsin;
   }

    public void setQuantityInput(int quantityInput){
        this.quantityInput = quantityInput;
    }
    public Integer getQuantityInput(){
        return this.quantityInput;
    }

    public void setPriceInput(int priceInput){
        this.priceInput = priceInput;
    }
    public Integer getPriceInput(){
        return this.priceInput;
    }

}
