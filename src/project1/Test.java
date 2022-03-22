package project1;

import java.util.Scanner;
public class Test {

    public static void main(String[] args)  {

        int totalMoneyToPay = 0;

        ItemIsin Isín1 = new ItemIsin("ISIN001",1,1000);
        ItemIsin Isín2 = new ItemIsin("ISIN002",2, 2000);
        ItemIsin Isín3 = new ItemIsin("ISIN003",3,3000);

        System.out.println(  "ISIN " + "\t" + " QUANTITY " +  "\t" + "PRICE ");
        System.out.println(Isín1.getNameIsin() + "\t" + Isín1.getInputQuantity() + "\t" + Isín1.getPriceInput());
        System.out.println(Isín2.getNameIsin() + "\t" + Isín2.getInputQuantity() + "\t" + Isín2.getPriceInput());
        System.out.println(Isín3.getNameIsin() + "\t" + Isín3.getInputQuantity() + "\t" + Isín3.getPriceInput());
        while(true) {
            Scanner amount = new Scanner(System.in);
            System.out.print("Please enter the amount money that you have : ");
            int moneyUserHave = amount.nextInt();
            Scanner name = new Scanner(System.in);
            System.out.print("Please select the name ISIN that you want to buy : ");
            String nameIsin = name.nextLine();
            Scanner quantity = new Scanner(System.in);
            System.out.print("Please enter the quantity of ISIN that you wan to buy : ");
            int amountQuantity = quantity.nextInt();

            if(nameIsin.equals(Isín1.getNameIsin())){
                totalMoneyToPay = amountQuantity * Isín1.getPriceInput();
            } else if(nameIsin.equals(Isín2.getNameIsin())){
                totalMoneyToPay = amountQuantity * Isín2.getPriceInput();
            } else if(nameIsin.equals(Isín3.getNameIsin())){
                totalMoneyToPay = amountQuantity * Isín3.getPriceInput();
            }

            if(moneyUserHave < totalMoneyToPay){
                System.out.println("Sorry , you do not have enough money");
            } else {
                System.out.println("The total amount for this ISIN that you want to buy is : " + amountQuantity );
                break;
            }
            }
        }
    }

