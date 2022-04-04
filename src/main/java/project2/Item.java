package project2;

public class Item {

    private String nameIsin;
    private Integer quantityInput;
    private Integer priceInput;

    public Item(String nameIsin, int quantityInput, int priceInput){
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
        this.priceInput=priceInput;
    }
    public Integer getPriceInput(){
        return this.priceInput;
    }
    /**
     * When you create a new class in java. The new class will inherit all the methods in Object.java
     * You can override this method to provide different string representation.
     * @return
     */
    @Override
    public String toString() {
        return getNameIsin() + "\t" + "\t" + getQuantityInput() + "\t" + "\t" + getPriceInput();
    }

}
