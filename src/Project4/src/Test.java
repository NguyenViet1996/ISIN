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
            System.out.println(i.getNameIsin() + " " + i.getQuantityIsin() + " " + i.getPriceIsin());
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
                    //1 hàm con
                    if( amountIsinUserBuy <= foundIsin.get().getQuantityIsin()){
                        System.out.println("The amount ISIN that you get is : " + amountIsinUserBuy);
                        int amountMoneyAfter = amountMoneyUserHave - amountToPay;
                        if (amountMoneyAfter > 0){
                            processAfterFirst(amountMoneyAfter , foundIsin.get().getPriceIsin());
                        }
                            break;
                    }
                    // 1 hàm con
                    else if( amountIsinUserBuy > foundIsin.get().getQuantityIsin()){
                        System.out.println("The amount maximum of " + foundIsin.get().getNameIsin() + " is " + foundIsin.get().getQuantityIsin());
                        System.out.println("Do you want to buy ? ");
                        String answerUser = inforUser.nextLine();
                        if(answerUser.equals("yes")){
                            System.out.println("The amount money that you have to pay  is :" + foundIsin.get().getQuantityIsin() * foundIsin.get().getPriceIsin());
                            break;
                        }
                        else if(answerUser.equals("no")){
                            // nếu người dùng ko muốn mua hết thì cho người dùng chọn lại số lượng mua (dùng while)
                        }
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

    private static void processAfterFirst(int moneyHave , int price ) {
        Scanner b = new Scanner(System.in);
        System.out.println("The amount money after buying is : " + moneyHave);
        System.out.println("Do you want to continue to buy ? ");
        String answer =  b.nextLine();
        if(answer.equals("yes")){
            // quantity that user want buy
            System.out.print("Please enter the amount of that you want to buy : ");
            int amountIsinUserBuy = b.nextInt();
            int totalPay = amountIsinUserBuy * price;
            if(totalPay <= moneyHave){
                System.out.println("The amount of ISIN that you have is " + amountIsinUserBuy);
            } else {
                System.out.println("Sorry you do not have enough money");
            }
        } else if(answer.equals("no")){
            System.out.println("See you again");
        }
    }

}





















