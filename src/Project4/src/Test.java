package Project4.src;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Optional;
public class Test {

    public static void main(String[] args) throws IOException {

        int totalToPay=0;
        System.out.println("This is information of code ISIN : The name of ISIN,the quantity of ISIN , the price of ISIN");
        System.out.println("ISIN" + "\t" + "\t" + "QUANTITY" + "\t" + "PRICE");

        // 1.lấy dữ liệu và tảo thành mảng chuỗi => tạo một hàm xử lí dữ liệu truyền vào và trả ra  mảng chuỗi
        String url = "src/Project4/resource/data.txt";
        List<String[]> listLine = getDataFromTextFile(url);

        // 2 . tạo từng đối tượng item và truyển dữ liệu vào từng đối tượng => tạo một hàm xử lí truyền vào mảng chuỗi
        // và trả ra mảng các đối tượng
        List<Project4.src.Isin> listIsin = createArrayItem(listLine);

        //3 . hiển thị thông tin cho người dùng xem
        mainProcessing(totalToPay, listIsin);
    }

    private static void mainProcessing(int amountToPay, List<Isin> arrayItem) {
        for (Isin i : arrayItem) {
            System.out.println(i.getNameIsin() + "\t" + "\t" + i.getQuantityIsin() + "\t " + "\t" + i.getPriceIsin());
        }
        // Amount money that user have
        Scanner inforUser = new Scanner(System.in);
        System.out.print("Please enter the amount money that you have : ");
        int amountMoneyUserHave = Integer.parseInt(inforUser.nextLine());

        while(true){
            // select name ISIN
            System.out.print("Please enter the name ISIN that you want to invest : ");
            String nameIsinUserSelect = inforUser.nextLine();
            // quantity that user want buy
            System.out.print("Please enter the amount ISIN that you want to buy : ");
            int amountIsinUserBuy = Integer.parseInt(inforUser.nextLine());
            // check and filter the item
            Optional<Isin> foundIsin = arrayItem.stream().filter(i -> i.getNameIsin().equals(nameIsinUserSelect)).findFirst();
            if(foundIsin.isPresent()) {
                amountToPay = amountIsinUserBuy * foundIsin.get().getPriceIsin();
                if(amountToPay <= amountMoneyUserHave){
                    if( amountIsinUserBuy <= foundIsin.get().getQuantityIsin()){
                        caseFirst(amountIsinUserBuy,foundIsin.get().getQuantityIsin(),amountToPay,amountMoneyUserHave,foundIsin.get().getPriceIsin(),foundIsin.get().getNameIsin());
                        break;
                    }
                    else if( amountIsinUserBuy > foundIsin.get().getQuantityIsin()){
                        caseSecond(foundIsin.get().getNameIsin(),foundIsin.get().getQuantityIsin(),foundIsin.get().getPriceIsin(),amountToPay);
                        break;
                    }
                } else {
                    System.out.println("Sorry you do not have enough money");
                }
            } else {
                System.out.println("Do not find the name of ISIN that you choose");
            }
        }
    }

    private static List<String[]> getDataFromTextFile(String url) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(url), 16384);
        List<String[]> listLine = new ArrayList<>();
        String line = null;
        while((line=br.readLine())!=null){
            String[] oneLine = line.split(",");
            listLine.add(oneLine);
        }
      return listLine;
    };

    private static List<Project4.src.Isin>  createArrayItem(List<String[]> arr){
        List<Isin> arrayList = new ArrayList<>();
        for(String[] i : arr){
            Isin item = new Isin(i[0],Integer.parseInt(i[1]),Integer.parseInt(i[2]));
            arrayList.add(item);
        }
        return arrayList;
    }

    private static void caseFirst(int amountIsinUserBuy,int quantityIsin, int amountToPay,int amountMoneyUserHave,int price,String nameIsin) {
        System.out.println("The amount " + nameIsin + " that you get is : " + amountIsinUserBuy);
        int amountMoneyUserAfterBuy = amountMoneyUserHave - amountToPay;
        int amountQuantityISINAfterBuy = quantityIsin - amountIsinUserBuy;
        if (amountQuantityISINAfterBuy > 0 && amountMoneyUserAfterBuy > 0) {
            Scanner answerUser = new Scanner(System.in);
            System.out.println("The amount of " + nameIsin + " after buying is  : " + amountQuantityISINAfterBuy + " . Do you want to buy ? ");
            String selectUser = answerUser.nextLine();
            if(selectUser.equals("yes")){
                  System.out.print("How many of " + nameIsin + " do you buy ? ");
                  int amountQuantityUserBuySecond = Integer.parseInt(answerUser.nextLine());
                  if(amountQuantityUserBuySecond <= amountQuantityISINAfterBuy){
                      int totalPaySecond = amountQuantityUserBuySecond * price;
                      if(totalPaySecond <= amountMoneyUserAfterBuy){
                          System.out.println("The amount money of that you have to pay : " + totalPaySecond + " . See you again");
                      } else {
                          System.out.println("Sorry you do not have enough money . See you again");
                      }
                  } else {
                      System.out.println("The quantity is not enough . See you again");
                  }
                } else if(selectUser.equals("no")){
                System.out.println("See you again");
            }
        }
    }

    private static void caseSecond(String nameIsin, int quantityIsin,int price, int totalPay ){
        Scanner answerUser = new Scanner(System.in);
        System.out.print("The maximum quantity of " + nameIsin + " is :" + quantityIsin + " . Do you want to buy ? ");
        String selectUser = answerUser.nextLine();
        if(selectUser.equals("yes")){
            System.out.println("The amount money that you have to pay : " +totalPay + " . See you again");
        } else if(selectUser.equals("no")){
            System.out.print("How many of " + nameIsin + " do you want to buy ? ");
            int quantityUserBuySecond = Integer.parseInt(answerUser.nextLine());
            if(quantityUserBuySecond <= quantityIsin){
                System.out.println("The amount of " + nameIsin + " that you have is " + quantityUserBuySecond + " . " + "The total money that you have you have to pay is " + quantityUserBuySecond * price + ". See you again");
            } else {
                System.out.println("The amount of " + nameIsin + " is not enough . See you again");
            }
        }
    }

}





















