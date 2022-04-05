package Project6.src;

import com.google.gson.Gson;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Optional;

public class Test {

		public static final String YES = "yes";
		public static final String NO = "no";

		public static void main(String[] args) throws IOException {

				String url = "src/main/java/Project6/resource/data.json";

				// Convert json data to object data --> return list of Isins
				List<Isin> listIsin = convertJsonDataToObjectData(url);

				// Third : Show data on the UI
				mainProcessing(listIsin);

		}

		/**
		 * main process programing
		 * @param listIsin
		 */
		private static void mainProcessing(List<Isin> listIsin) {

				System.out.println("NAMEOFISIN\t\tQUANTITY\tPRICE");
				for (Isin i : listIsin ) {
						System.out.println(i);
				}

				Scanner inputFromUser = new Scanner(System.in);
				System.out.println("Do you want to invest about ISIN ? ");
				String answerUser = inputFromUser.nextLine();
				if (YES.equals(answerUser)) {

						// User enters the information about the amount of money that user has
						String amountMoneyOfUser = "Please enter the amount money that you have :";
						int amountMoneyUserHave = checkDataNumberFromUser(inputFromUser,amountMoneyOfUser);

						while (amountMoneyUserHave >0) {

								// User enters the name of ISIN that user wants to invest
								System.out.print( "Please enter the name ISIN that you want to invest : " );
								String nameIsinUserSelect = inputFromUser.nextLine();

								// User enters the quantity of ISIN that user wants to invest
								String quantityOfIsinUserBuy = "Please enter the quantity of Isin that you want to buy :";
								int amountIsinUserBuy = checkDataNumberFromUser(inputFromUser,quantityOfIsinUserBuy);

								// Find the ISIN that user choose from the name of ISIN
								Optional<Isin> foundIsin = listIsin.stream().filter(i -> i.getNameIsin().equals(nameIsinUserSelect)).findFirst();

								if  (foundIsin.isPresent()) {

										// Calculate The amount of money that user has to pay
										Isin isin = foundIsin.get();
										int amountToPay = amountIsinUserBuy * isin.getPriceIsin();
										if  (amountToPay <= amountMoneyUserHave) {
                        int  amountMoneyUserHaveAfterBuy = 0;
												if  (amountIsinUserBuy <= isin.getQuantityIsin()) {
														 amountMoneyUserHaveAfterBuy = quantityOfBuyingLessThanMaximum(amountIsinUserBuy, amountToPay, amountMoneyUserHave, isin);
												} else {
														    amountMoneyUserHaveAfterBuy = quantityOfBuyingMoreThanMaximum(isin, amountToPay,amountMoneyUserHave);
												}
												amountMoneyUserHave = amountMoneyUserHaveAfterBuy;

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

		/**
		 * Get and validate the data number from user enter
		 * @param inputFromUser
		 * @return the money that user have
		 */
		private static Integer checkDataNumberFromUser(Scanner inputFromUser, String demandOfUser) {

				do {
						System.out.println(demandOfUser);
						try {
								return Integer.parseInt(inputFromUser.nextLine());
						}
						catch (Exception e) {
								System.out.println(e);
						}
				} while (true);

		}

		/**
		 * The quantity of Isin that user buy is less than the maximum of Isin
		 * @param amountIsinUserBuy
		 * @param amountToPay
		 * @param amountMoneyUserHave
		 * @param isin
		 */
		private static int quantityOfBuyingLessThanMaximum(int amountIsinUserBuy, int amountToPay, int amountMoneyUserHave, Isin isin) {
				int amountMoneyUserHaveAfterBuy ;
				int quantityIsin = isin.getQuantityIsin();
				int price = isin.getPriceIsin();
				String nameIsin = isin.getNameIsin();

				System.out.println("The amount " + nameIsin + " that you get is : " + amountIsinUserBuy);

				// Calculate after buying
				amountMoneyUserHaveAfterBuy = amountMoneyUserHave - amountToPay;
				int amountQuantityISINAfterBuyFirst = quantityIsin - amountIsinUserBuy;

				if (amountQuantityISINAfterBuyFirst > 0 && amountMoneyUserHaveAfterBuy > 0) {

						Scanner answerUser = new Scanner(System.in);
						System.out.println("The amount of " + nameIsin + " after buying is  : " + amountQuantityISINAfterBuyFirst + " . Do you want to buy ? ");
						String selectUser = answerUser.nextLine();

						if (YES.equals(selectUser)) {

                String informationOfIsinToUser = "How many of " + nameIsin + " do you buy ? ";
								int amountQuantityUserBuySecond = checkDataNumberFromUser(answerUser,informationOfIsinToUser);

								if (amountQuantityUserBuySecond <= amountQuantityISINAfterBuyFirst) {

										int totalUserPaySecond = amountQuantityUserBuySecond * price;

										if (totalUserPaySecond <= amountMoneyUserHaveAfterBuy) {
												System.out.println("The amount money of that you have to pay : " + totalUserPaySecond );
												amountMoneyUserHaveAfterBuy -= totalUserPaySecond;
										} else {
												System.out.println("Sorry you do not have enough money");
										}
										return amountMoneyUserHaveAfterBuy;
								} else {
										System.out.println("The quantity is not enough . See you again");
								}
						} else if (NO.equals(selectUser)) {
								System.out.println("Thank you");
						}
				}
				return amountMoneyUserHaveAfterBuy;

		}

		/**
		 * the case that quantity of Isin that user buy is greater than the maximum of Isin
		 * @param isin
		 * @param totalPay
		 */
		private static int quantityOfBuyingMoreThanMaximum(Isin isin, int totalPay,int amountMoneyUserHave ) {
				int amountMoneyUserHaveAfterBuy = 0;
				String nameIsin = isin.getNameIsin();
				int quantityIsin = isin.getQuantityIsin();
				int price = isin.getPriceIsin();

				Scanner answerUser = new Scanner(System.in);
				System.out.print("The maximum quantity of " + nameIsin + " is :" + quantityIsin + " . Do you want to buy ? ");
				String selectUser = answerUser.nextLine();

				if ( YES.equals(selectUser) ) {
						System.out.println("The amount money that you have to pay : " + totalPay );
						amountMoneyUserHaveAfterBuy = amountMoneyUserHave - totalPay;
				} else if ( NO.equals(selectUser) ) {
						int quantityUserBuySecond = 0 ;
						boolean isDemandNotValid = true;
						while(isDemandNotValid){
								String demandOfUser = "How many of " + nameIsin + " do you want to buy ? ";
								quantityUserBuySecond = checkDataNumberFromUser(answerUser,demandOfUser);
								isDemandNotValid = quantityUserBuySecond > quantityIsin ;
						}
						System.out.println("The amount of " + nameIsin + " that you have is " + quantityUserBuySecond + " . " +
								"The total money that you have you have to pay is " + quantityUserBuySecond * price + ". See you again");
						amountMoneyUserHaveAfterBuy = amountMoneyUserHave - quantityUserBuySecond * price;
				}
				return amountMoneyUserHaveAfterBuy;

		}

		/**
		 * convert json data to object data and return list of Isin
		 * @param url
		 * @return
		 * @throws IOException
		 */
		private static List<Isin> convertJsonDataToObjectData(String url) throws IOException {
				Gson gson = new Gson();
				BufferedReader readFile = new BufferedReader(new FileReader(url), 16384);
        Isin[] arrayData = gson.fromJson(readFile,Isin[].class);
        List<Isin> listIsin = new ArrayList<>();
		    for(Isin i : arrayData) {
						Isin isin = new Isin(i.getNameIsin(),i.getQuantityIsin(),i.getPriceIsin());
						listIsin.add(isin);
				}
				return listIsin;

		};

}
