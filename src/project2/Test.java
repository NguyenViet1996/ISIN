package project2;

import project2.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Test {

    public static void main(String[] args){
        int totalMoneyToPay = 0 ;
        Item item1 = new Item("ISIN001",1000,1);
        Item item2 = new Item("ISIN002",2000,2);
        Item item3 = new Item("ISIN003",3000,3);

        System.out.println(  "ISIN " + "\t" + " QUANTITY " +  "\t" + "PRICE ");
        List<Item> listIsin = new ArrayList<>();
        listIsin.add(item1);
        listIsin.add(item2);
        listIsin.add(item3);

//        solution 1 : loop in array
        for(Item i : listIsin) {
            System.out.println(i.getNameIsin() + "\t" + "\t" + i.getQuantityInput() + "\t" + "\t"  + i.getPriceInput());
        }

//        solution 2 : default in java  , when use System.out.println, and if the input is an Object -> println will use method toString in that object
//         for(Item i : listIsin){
//             System.out.println(i);
//         }

//      solution 3 : lambda
//        listIsin.forEach( is -> {
//            System.out.println(is.getNameIsin() + "\t" + "\t" + is.getQuantityInput() + "\t" + "\t" + is.getPriceInput());
//        });

        while(true) {
            Scanner name = new Scanner(System.in);
            System.out.print("Please enter the name of ISIN that you choose :");
            String nameIsin = name.nextLine();
            Scanner amount = new Scanner(System.in);
            System.out.print("Please enter the amount money that you have : ");
            int amountHave = amount.nextInt();
            Scanner quantity = new Scanner(System.in);
            System.out.print("Please enter the quantity ISIN that you want to buy : ");
            Integer quantityHave = quantity.nextInt();
            // solution 1 : calculate totalMoneyToPay
//            for(Item i : listIsin) {
//                if(nameIsin.equals(i.getNameIsin())){
//                    totalMoneyToPay = quantityHave * i.getPriceInput();
//                    break;
//                }
//            }
            // solution 2 : calculate totalMoneyToPay
//          when list use  method filter , default using stream() ;
            Optional<Item> found = listIsin.stream().filter(item ->item.getNameIsin().equals(nameIsin)).findFirst();
            // chưa xử lí trường hợp ko filter ra được loại mã chứng khoán người mua muốn mua
            if(found.isPresent()){
                totalMoneyToPay = quantityHave * found.get().getPriceInput();
            }
            if(amountHave < totalMoneyToPay){
                System.out.println("Sorry , you do not have enough money");
            } else {
                System.out.println("The total amount for this ISIN that you want to buy is : " + quantityHave );
                break;
            }
        }
    }
}
