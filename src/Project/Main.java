package Project;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        int sumBuy = 0;
        System.out.println("ISIN" + "\t" + "\t" + "QUANTITY" + "\t" + "PRICE");
        Item item = new Item();
        Investor investor = new Investor();
        investor.setValue(2000);
// ISIN001
        item.setInputIsin("ISIN001");
        item.setInputQuantity(1000);
        item.setPriceInput(1);
        System.out.println(item.getInputIsin() + "\t" + "\t" + item.getInputQuantity() + "\t" + "\t" + item.getPriceInput());

// ISIN002
        item.setInputIsin("ISIN002");
        item.setInputQuantity(2000);
        item.setPriceInput(2);
        System.out.println(item.getInputIsin() + "\t" + "\t" + item.getInputQuantity() + "\t" + "\t" + item.getPriceInput());

// ISIN003
        item.setInputIsin("ISIN003");
        item.setInputQuantity(3000);
        item.setPriceInput(3);
        System.out.println(item.getInputIsin() + "\t" + "\t" + item.getInputQuantity() + "\t" + "\t" + item.getPriceInput());

        System.out.println("The amount That user wan to invest : " + investor.getValue());

        while(true) {
            Scanner object = new Scanner(System.in);
            System.out.print("Please enter the name of ISIN that you want to choose: ");
            String nameIsin = object.nextLine();
            System.out.print("Please enter the amount  that you want to choose: ");
            int valueQuantity = object.nextInt();
            if(nameIsin.equals("ISIN001")){
                sumBuy = valueQuantity * 1;
                System.out.println("The sum amount that you want to buy is : " +  sumBuy);
            } else if(nameIsin.equals("ISIN002")){
                sumBuy = valueQuantity * 2;
                System.out.println("The sum amount that you want to buy is : " +  sumBuy);
            } else if(nameIsin.equals("ISIN003")){
                sumBuy = valueQuantity * 3;
                System.out.println("The sum amount that you want to buy is : " +  sumBuy);
            }
            if(sumBuy > 2000) {
                System.out.println("User do not have enough money");
            } else {
                System.out.println("The amount That you can have is : " + sumBuy);
                break;
            }
        }
        // 1 : mỗi chứng khoán có một item đại diện
        // 2 : việc kiểm tra xem người dùng nhập thông tin nào thì phải dùng các item ISIN để check
        // 3 : tính toán việc buy dùng các item để tính toán
}
}


