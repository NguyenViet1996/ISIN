package project3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Test {

   public static void main(String[] args) throws IOException {

      int totalToPay = 0 ;

      System.out.println( "ISIN" + "\t" + "\t" + "QUANTITY" + "\t"  + "PRICE");
      // use absolute path
      // 1.lấy dữ liệu và tảo thành mảng chuỗi => tạo một hàm xử lí dữ liệu truyền vào và trả ra  mảng chuỗi
      Reader reader = new FileReader("C://HelloWorld//src//project3//data.txt");
      BufferedReader br  = new BufferedReader(reader,16384);
      String line = null;
      List<String[]> listLines = new ArrayList<>();
      while((line=br.readLine())!=null){
         String[] lines = line.split(",");
         listLines.add(lines);
      }
      // 2 . tạo từng đối tượng item và truyển dữ liệu vào từng đối tượng => tạo một hàm xử lí truyền vào mảng chuỗi và trả ra mảng các đối tượng
      List<Isin> listIsin = new ArrayList<>();
      for (String[] item: listLines) {
         Isin itemIsin = new Isin(item[0],Integer.parseInt(item[1]),Integer.parseInt(item[2]));
         listIsin.add(itemIsin);
      }

      //3 . hiển thị thông tin cho người dùng xem
      //        solution 1 : loop in array
      for(Isin i : listIsin) {
         System.out.println(i.getNameIsin() + "\t" + "\t" + i.getQuantityInput() + "\t" + "\t" + i.getPriceInput());
      }
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

         Optional<Isin> found = listIsin.stream().filter(item -> item.getNameIsin().equals(nameIsin)).findFirst();
         if(found.isPresent()){
            totalToPay = quantityHave * found.get().getPriceInput();
         }
         if(totalToPay < amountHave) {
            System.out.println("The amount ISIN that you have is  : " + quantityHave);
            break;
         } else {
            System.out.println("Sorry you do not have enough ");
         }
      }
   }

}






