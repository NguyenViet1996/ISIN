package Project5.src;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Test {

     public static void main(String[]args) throws IOException {
         int totalToPay = 0;
        String url = "src/Project4/resource/data.txt";
        FileReader reader = new FileReader(url);
        BufferedReader bf = new BufferedReader(reader);
        System.out.println("NameIsin" + " " + "Quantity" + " " + "Price");
        String line = null;
         List<Isin> arrayList = new ArrayList<>();
        while((line=bf.readLine())!=null){
            String[] arrayLine = line.split(",");
            Isin item = new Isin(arrayLine[0],Integer.parseInt(arrayLine[1]),Integer.parseInt(arrayLine[2]));
            System.out.println(item.getNameIsin() + " " + item.getQuantityIsin() + " " +  " " + " " + " "  + " " +  item.getPriceIsin());
            arrayList.add(item);
         }

         while (true) {
             Scanner name = new Scanner(System.in);
             System.out.print("Please enter the name of ISIN that you choose :");
             String nameIsin = name.nextLine();
             Scanner amount = new Scanner(System.in);
             System.out.print("Please enter the amount money that you have : ");
             int amountHave = amount.nextInt();
             Scanner quantity = new Scanner(System.in);
             System.out.print("Please enter the quantity ISIN that you want to buy : ");
             Integer quantityHave = quantity.nextInt();

             Optional<Project5.src.Isin> found = arrayList.stream().filter(item -> item.getNameIsin().equals(nameIsin)).findFirst();
             if (found.isPresent()) {
                 totalToPay = quantityHave * found.get().getPriceIsin();
             }
             if (totalToPay < amountHave) {
                 System.out.println("The amount ISIN that you have is  : " + quantityHave);
                 break;
             } else {
                 System.out.println("Sorry you do not have enough money");
             }
         }

     }

}
