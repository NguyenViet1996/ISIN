package project1;

public class ItemIsin {

    private String nameIsin;
    private int priceInput;
    private int inputQuantity;

    /**
     * default constructor of class
     * @param nameIsin
     * @param priceInput
     * @param inputQuantity
     */
    public ItemIsin(String nameIsin,int priceInput,int inputQuantity){
        this.nameIsin = nameIsin;
        this.priceInput = priceInput;
        this.inputQuantity = inputQuantity;
    }

    public void setNameIsin(String nameIsin){
        this.nameIsin = nameIsin;
    }
    public String getNameIsin(){
        return this.nameIsin;
    }

    public void setInputQuantity(int inputQuantity){
        this.inputQuantity = inputQuantity;
    }
    public int getInputQuantity(){
        return this.inputQuantity;
    }

    public void setPriceInput(int priceInput){
        this.priceInput = priceInput;
    }
    public int getPriceInput(){
        return this.priceInput;
    }

}
