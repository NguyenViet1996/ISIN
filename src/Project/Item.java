package Project;
public class Item {
    private String inputIsin ;
    private int inputQuantity;
    private int priceInput;

    public String getInputIsin(){
        return inputIsin;
    };
    public Integer getInputQuantity(){
        return  inputQuantity;
    };
    public Integer getPriceInput(){
        return priceInput;
    }

    public void setInputIsin(String newInputIsin){
        this.inputIsin = newInputIsin;
    }
    public void setInputQuantity(int newInputQuantity){
        this.inputQuantity = newInputQuantity;
    }
    public void setPriceInput(int newPriceInput){
        this.priceInput = newPriceInput;
    }

}

