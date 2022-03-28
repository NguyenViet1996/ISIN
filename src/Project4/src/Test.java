package Project4.src;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Optional;
public class Test {

    public static final String YES = "yes";
    public static final String NO = "no";

    public static void main(String[] args) throws IOException {

        System.out.println("ISIN\t\tQUANTITY\tPRICE");

        // First : get data and create array of string --> create function to resolve data and return array of string
        String url = "src/Project4/resource/data.txt";
        List<String[]> arrayString = getDataFromFileText(url);

        // Second : create each object and transfer data into every item --> create a function to transfer into  array of string and return an array of item
        List<Project4.src.Isin> arrayItem = createArrayOfIsin(arrayString);

        // Third : Show data on the UI
        mainProcessing(arrayItem);

    }

    /**
     * main process programing
     * @param arrayItem
     */
    private static void mainProcessing(List<Isin> arrayItem) {

        for (Isin i : arrayItem ) {
            System.out.println( i.getNameIsin() + " " + i.getQuantityIsin() + " " + i.getPriceIsin() );
        }

        Scanner inputFromUser = new Scanner(System.in);
        System.out.println("Do you want to invest about ISIN ? ");
        String answerUser = inputFromUser.nextLine();
        if (YES.equals(answerUser)) {

            // User enters the information about the amount of money that user has
            System.out.print("Please enter the amount money that you have : ");
            int amountMoneyUserHave = Integer.parseInt(inputFromUser.nextLine());

            while (amountMoneyUserHave >0) {

                // User enters the name of ISIN that user wants to invest
                System.out.print( "Please enter the name ISIN that you want to invest : " );
                String nameIsinUserSelect = inputFromUser.nextLine();

                // User enters the quantity of ISIN that user wants to invest
                System.out.print("Please enter the amount ISIN that you want to buy : ");
                int amountIsinUserBuy = Integer.parseInt(inputFromUser.nextLine());

                // Find the ISIN that user choose from the name of ISIN
                Optional<Isin> foundIsin = arrayItem.stream().filter(i -> i.getNameIsin().equals(nameIsinUserSelect)).findFirst();

                if  (foundIsin.isPresent()) {

                    // Calculate The amount of money that user has to pay
                    Isin isin = foundIsin.get();
                    int amountToPay = amountIsinUserBuy * isin.getPriceIsin();
                    if  (amountToPay <= amountMoneyUserHave) {

                        if  (amountIsinUserBuy <= isin.getQuantityIsin()) {
                            quantityOfBuyingLessThanMaximum(amountIsinUserBuy, amountToPay, amountMoneyUserHave, isin);
                        } else {
                            quantityOfBuyingMoreThanMaximum(isin, amountToPay);
                        }
                        amountMoneyUserHave -= amountToPay;
                    } else {
                        System.out.println("Sorry you do not have enough money");
                    }
                } else {
                    System.out.println("Do not find the name of ISIN that you choose");
                }

            }
        } else if (NO.equals(answerUser)) {
            System.out.println("See you again");
        }

    }

    private static void quantityOfBuyingLessThanMaximum(int amountIsinUserBuy, int amountToPay, int amountMoneyUserHave, Isin isin) {
        int quantityIsin = isin.getQuantityIsin();
        int price = isin.getPriceIsin();
        String nameIsin = isin.getNameIsin();

        System.out.println("The amount " + nameIsin + " that you get is : " + amountIsinUserBuy);

        // Calculate after buying
        int amountMoneyUserAfterBuy = amountMoneyUserHave - amountToPay;
        int amountQuantityISINAfterBuy = quantityIsin - amountIsinUserBuy;

        if (amountQuantityISINAfterBuy > 0 && amountMoneyUserAfterBuy > 0) {

            Scanner answerUser = new Scanner(System.in);
            System.out.println("The amount of " + nameIsin + " after buying is  : " + amountQuantityISINAfterBuy + " . Do you want to buy ? ");
            String selectUser = answerUser.nextLine();

            if (YES.equals(selectUser)) {
                System.out.print("How many of " + nameIsin + " do you buy ? ");

                int amountQuantityUserBuySecond = Integer.parseInt(answerUser.nextLine());

                if (amountQuantityUserBuySecond <= amountQuantityISINAfterBuy) {

                    int totalPaySecond = amountQuantityUserBuySecond * price;

                    if (totalPaySecond <= amountMoneyUserAfterBuy) {
                        System.out.println("The amount money of that you have to pay : " + totalPaySecond + " . See you again");
                    } else {
                        System.out.println("Sorry you do not have enough money . See you again");
                    }
                } else {
                    System.out.println("The quantity is not enough . See you again");
                }
            } else if (NO.equals(selectUser)) {
                System.out.println("See you again");
            }

        }

    }

    /**
     *
     * @param isin
     * @param totalPay
     */
    private static void quantityOfBuyingMoreThanMaximum(Isin isin, int totalPay ) {
        String nameIsin = isin.getNameIsin();
        int quantityIsin = isin.getQuantityIsin();
        int price = isin.getPriceIsin();

        Scanner answerUser = new Scanner(System.in);
        System.out.print("The maximum quantity of " + nameIsin + " is :" + quantityIsin + " . Do you want to buy ? ");
        String selectUser = answerUser.nextLine();

        if ( YES.equals(selectUser) ) {
            System.out.println("The amount money that you have to pay : " + totalPay + " . See you again");
        } else if ( NO.equals(selectUser) ) {
            int quantityUserBuySecond = 0 ;
            boolean isDemandNotValid = true;
            while(isDemandNotValid){
                System.out.print("How many of " + nameIsin + " do you want to buy ? ");
                quantityUserBuySecond = Integer.parseInt(answerUser.nextLine());
                isDemandNotValid = quantityUserBuySecond > quantityIsin ;
            }
            System.out.println("The amount of " + nameIsin + " that you have is " + quantityUserBuySecond + " . " +
                    "The total money that you have you have to pay is " + quantityUserBuySecond * price + ". See you again");
        }
    }

    /**
     * Read data and return a list of strings[]
     * @param url path to the file - RELATIVE path
     * @return list of string[]
     * @throws IOException exception
     */
    private static List<String[]> getDataFromFileText(String url) throws IOException {

        // Create variable and transfer data into variable
        BufferedReader readFile = new BufferedReader(new FileReader(url), 16384);

        /* Create a big array String*/
        List<String[]> arrayString = new ArrayList<>();
        String line = null;

        /* Get data from line by line and add into a big array string */
        while ((line = readFile.readLine()) != null ) {
            String[] oneLine = line.split(",");
            arrayString.add(oneLine);
        }

        return arrayString;

    };

    /**
     * Receive array of string and return list of Isin
     * @param arrayString array of string have data to transfer into each item
     * @return list of Isin
     */
    private static List<Project4.src.Isin> createArrayOfIsin(List<String[]> arrayString) {

        List<Isin> arrayItem = new ArrayList<>();

        for (String[] i : arrayString) {
            Isin item = new Isin(i[0],Integer.parseInt(i[1]),Integer.parseInt(i[2]));
            arrayItem.add(item);
        }

        return arrayItem;

    }

}
