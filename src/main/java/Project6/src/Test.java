package Project6.src;

import com.google.gson.Gson;

import java.io.*;
import java.util.*;

public class Test {

		public static final String YES = "yes";
		public static final String NO = "no";

		public static void main(String[] args) throws IOException {

				String url = "src/main/java/Project6/resource/data.json";

				// Convert json data to object data --> return list of Isins
				List<Isin> listIsin = convertJsonDataToObjectData(url);

				// Show data on the UI
				List<Isin> dataAfterProcessing = mainProcessing(listIsin);

				// Write to file data json
				writeFile(dataAfterProcessing);
		}

		/**
		 * main process programing
		 * @param listIsin
		 */
		private static List<Isin> mainProcessing(List<Isin> listIsin) {
				System.out.println("NAMEOFISIN\t\tQUANTITY\tPRICE");
				for (Isin i : listIsin ) {
						System.out.println(i);
				}

				Scanner inputFromUser = new Scanner(System.in);
				System.out.print("Do you want to invest about ISIN ? (yes/no) : ");
				String answerUser = inputFromUser.nextLine();
				if (YES.equalsIgnoreCase(answerUser)) {

						// User enters the information about the amount of money that user has
						String amountMoneyOfUser = "Please enter the amount money that you have :";
						int amountMoneyUserHave = checkDataNumberFromUser(inputFromUser,amountMoneyOfUser);

						while (amountMoneyUserHave >0) {
								// User enters the name of ISIN that user wants to invest
								System.out.print( "Please enter the name ISIN that you want to invest : " );
								String nameIsinUserSelect = inputFromUser.nextLine();

								// Find the ISIN that user choose from the name of ISIN
								Optional<Isin> foundIsin = listIsin.stream().filter(i -> i.getNameIsin().equals(nameIsinUserSelect)).findFirst();

								if  (foundIsin.isPresent()) {
										// User enters the quantity of ISIN that user wants to invest
										String quantityOfIsinUserBuy = "Please enter the quantity of Isin that you want to buy :";
										int amountIsinUserBuy = checkDataNumberFromUser(inputFromUser,quantityOfIsinUserBuy);
										// Calculate The amount of money that user has to pay
										Isin isin = foundIsin.get();
										if (isin.getQuantityIsin() > 0) {
												int amountToPay = amountIsinUserBuy * isin.getPriceIsin();
												if  (amountToPay <= amountMoneyUserHave) {
														ValueAfterBuy outputResult;
														if (amountIsinUserBuy <= isin.getQuantityIsin()) {
																outputResult = buyingIsinLessThanMaximum(amountIsinUserBuy, amountToPay, amountMoneyUserHave, isin);
														} else {
																outputResult = buyingIsinMoreThanMaximum(isin, amountToPay,amountMoneyUserHave);
														}
														amountMoneyUserHave = outputResult.getAmountMoneyUserHaveAfterBuy();
														isin.setQuantityIsin(outputResult.getQuantityIsinAfterBuy());
														if (amountMoneyUserHave > 0) {
																System.out.print("The amount of money that you have after buying is : " + amountMoneyUserHave + " . Do you want to continue to buy ? (yes/no)");
																String selectContinueOfUser = inputFromUser.nextLine();
																if (YES.equalsIgnoreCase(selectContinueOfUser)) {
																		System.out.println("Please continue to enter the information");
																} else if (NO.equalsIgnoreCase(selectContinueOfUser)) {
																		System.out.println("Thank you very much . See you again");
																		break;
																}
														} else {
																System.out.println("Thank you very much . See you again ");
																break;
														}
												} else {
														System.out.println("Sorry you do not have enough money");
												}
										} else {
												System.out.print( "The quantity of " + isin.getNameIsin() + " is expired . Do you want to continue to buy ? (yes/no) : ");
												String selectContinueOfUser = inputFromUser.nextLine();
												if (YES.equalsIgnoreCase(selectContinueOfUser)) {
														System.out.println("Please continue to enter the information");
												} else if (NO.equalsIgnoreCase(selectContinueOfUser)) {
														System.out.println("Thank you very much . See you again");
														break;
												}
										}
								} else {
										System.out.println("Do not find the name of ISIN that you choose");
								}
						}

				} else if (NO.equals(answerUser)) {
						System.out.println("See you again");
				}

				return listIsin;
		}

		/**
		 * Get and validate the data number from user enter
		 * @param inputFromUser
		 * @return the money that user have
		 */
		private static Integer checkDataNumberFromUser(Scanner inputFromUser, String demandOfUser) {

				do {
						System.out.print(demandOfUser);
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
		 * return amount of money that user have after buying and amount of quantity ISIN after buy
		 */
		private static ValueAfterBuy buyingIsinLessThanMaximum(int amountIsinUserBuy, int amountToPay, int amountMoneyUserHave, Isin isin) {
				ValueAfterBuy outputValue = null;
				int amountMoneyUserHaveAfterFirstBuy ;
				int quantityIsin = isin.getQuantityIsin();
				int priceIsin = isin.getPriceIsin();
				String nameIsin = isin.getNameIsin();

				System.out.println("The amount " + nameIsin + " that you get is : " + amountIsinUserBuy);

				// Calculate after buying
				amountMoneyUserHaveAfterFirstBuy = amountMoneyUserHave - amountToPay;
				int quantityIsinAfterFirstBuy = quantityIsin - amountIsinUserBuy;

				if (amountMoneyUserHaveAfterFirstBuy > 0) {

						if (quantityIsinAfterFirstBuy > 0) {
								Scanner answerUser = new Scanner(System.in);
								System.out.println("The amount of " + nameIsin + " after buying is : " + quantityIsinAfterFirstBuy + " . Do you want to continue to buy ? (yes/no) : ");
								String selectUser = answerUser.nextLine();

								if (YES.equalsIgnoreCase(selectUser)) {
										String demandOfUserAfterFirstBuy = "How many of " + nameIsin + " do you want to buy ? ";
										int quantityIsinUserSecondBuy = checkDataNumberFromUser(answerUser,demandOfUserAfterFirstBuy);

										if (quantityIsinUserSecondBuy <= quantityIsinAfterFirstBuy) {
												int totalUserMustPaySecond = quantityIsinUserSecondBuy * priceIsin;

												if (totalUserMustPaySecond <= amountMoneyUserHaveAfterFirstBuy) {
														System.out.println("The amount money of that you have to pay : " + totalUserMustPaySecond );
														amountMoneyUserHaveAfterFirstBuy -= totalUserMustPaySecond;
														System.out.println("The amount money that you have after buying is : " + amountMoneyUserHaveAfterFirstBuy);
												} else {
														System.out.println("Sorry you do not have enough money . The amount of money that you have only is : " + amountMoneyUserHaveAfterFirstBuy);
												}
												outputValue = new ValueAfterBuy(amountMoneyUserHaveAfterFirstBuy,quantityIsinAfterFirstBuy - quantityIsinUserSecondBuy);
										} else {
												System.out.println("The quantity of ISIN is not enough");
												outputValue = new ValueAfterBuy(amountMoneyUserHaveAfterFirstBuy,quantityIsinAfterFirstBuy);
										}
								} else if (NO.equalsIgnoreCase(selectUser)) {
										outputValue = new ValueAfterBuy(amountMoneyUserHaveAfterFirstBuy,quantityIsinAfterFirstBuy);
								}
						} else {
								System.out.println("the quantity of " + nameIsin + " is over");
								outputValue = new ValueAfterBuy(amountMoneyUserHaveAfterFirstBuy,0);
						}

				} else {
						System.out.println("Please inform that your money is expired . ");
						outputValue= new ValueAfterBuy(0,quantityIsinAfterFirstBuy);
				}

				return outputValue;

		}

		/**
		 * the case that quantity of Isin that user buy is greater than the maximum of Isin
		 * @param isin
		 * @param totalPay
		 * return amount of money that user have after buying and amount of quantity ISIN after buy
		 */
		private static ValueAfterBuy buyingIsinMoreThanMaximum(Isin isin, int totalPay, int amountMoneyUserHave ) {
				ValueAfterBuy outputValue = null;
				String nameIsin = isin.getNameIsin();
				int quantityIsin = isin.getQuantityIsin();
				int priceIsin = isin.getPriceIsin();

				Scanner answerUser = new Scanner(System.in);
				System.out.print("The maximum quantity of " + nameIsin + " is :" + quantityIsin + " . Do you want to buy ? ");
				String selectUser = answerUser.nextLine();

				if ( YES.equalsIgnoreCase(selectUser) ) {
						totalPay = quantityIsin * priceIsin;
						System.out.println("The amount money that you have to pay : " + totalPay );
						outputValue = new ValueAfterBuy(amountMoneyUserHave - totalPay,0);
				} else if ( NO.equalsIgnoreCase(selectUser) ) {
						int quantityUserBuySecond = 0 ;
						boolean isDemandNotValid = true;
						while(isDemandNotValid) {
								String demandOfUser = "How many of " + nameIsin + " do you want to continue to buy ? ";
								quantityUserBuySecond = checkDataNumberFromUser(answerUser,demandOfUser);
								isDemandNotValid = quantityUserBuySecond > quantityIsin ;
						}
						totalPay = quantityUserBuySecond * priceIsin;
						System.out.println("The amount of " + nameIsin + " that you have is " + quantityUserBuySecond + " . " +
								"The total money that you have you have to pay is " + totalPay );
						outputValue = new ValueAfterBuy(amountMoneyUserHave - totalPay,quantityIsin - quantityUserBuySecond);
				}
				return outputValue;

		}

		/**
		 * convert json data to object data
		 * @param url:relative path json data
		 * @return
		 * @throws IOException
		 */
		private static List<Isin> convertJsonDataToObjectData(String url) throws IOException {
				Gson gson = new Gson();
				BufferedReader readFile = new BufferedReader(new FileReader(url), 16384);
        Isin[] arrayData = gson.fromJson(readFile,Isin[].class);
        List<Isin> listIsin = new ArrayList<>();
				Collections.addAll(listIsin, arrayData);

				return listIsin;
		};

		/**
		 * convert object data to json data and write to file new data.json
		 * @param data
		 */
		private static void writeFile(List<Isin> data) {
				Gson gson = new Gson();
				String inputData = gson.toJson(data);
				try {
						FileWriter myWiter = new FileWriter("src/main/java/Project6/resource/data.json");
						myWiter.write(inputData);
						myWiter.close();
				} catch (IOException e) {
						e.printStackTrace();
				}
		}

}
