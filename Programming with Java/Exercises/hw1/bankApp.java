/*
	Name: Nikos Mitsakis
	Student Number: 3210122
*/

import java.util.Locale;

public class bankApp {

	public static void main(String args[]) {

		Account acct1 = new Account("Togantzi Maria", "100-00", 188.46);
		Account acct2 = new Account("Kalergis Christos", "100-01", 140.21);
		Account acct3 = new Account("Maras Petros", "100-02", 0.00);

		System.out.println("New accounts: ");

		System.out.println(); // Blank Line

		System.out.println(acct1);

		System.out.println(); // Blank Line

		System.out.println(acct2);

		System.out.println(); // Blank Line

		System.out.println(acct3);

		System.out.println(); // Blank Line

		acct1.deposit(-10.00);
		System.out.println("Deposit @ Account " + acct1.getAccountNumber());
		System.out.println("Balance " + String.format(Locale.GERMAN, "%,.2f", acct1.getBalance()));
		System.out.println("Requested: -10.00");
		System.out.println("Error: Deposit amount is invalid.");
		System.out.println("New Balance " + String.format(Locale.GERMAN, "%,.2f", acct1.getBalance()));

		System.out.println(); // Blank Line

		acct2.deposit(500.1);
		System.out.println("Deposit @ Account " + acct2.getAccountNumber());
		System.out.println("Balance " + String.format(Locale.GERMAN, "%,.2f", acct2.getBalance()));
		System.out.println("Requested: 500.1");
		System.out.println("New Balance " + String.format(Locale.GERMAN, "%,.2f", acct2.getBalance()));

		System.out.println(); // Blank Line

		acct3.withdraw(1420.75);
		System.out.println("withdraw @ Account " + acct3.getAccountNumber());
		System.out.println("Balance " + String.format(Locale.GERMAN, "%,.2f", acct3.getBalance()));
		System.out.println("Requested: 1420.75");
		System.out.println("Error: Insufficient funds.");
		System.out.println("New Balance " + String.format(Locale.GERMAN, "%,.2f", acct3.getBalance()));

		System.out.println(); // Blank Line

		acct3.withdraw(-10.00);
		System.out.println("withdraw @ Account " + acct3.getAccountNumber());
		System.out.println("Balance " + String.format(Locale.GERMAN, "%,.2f", acct3.getBalance()));
		System.out.println("Requested: -10.0");
		System.out.println("Error: Withdraw amount is invalid.");
		System.out.println("New Balance " + String.format(Locale.GERMAN, "%,.2f", acct3.getBalance()));

		System.out.println(); // Blank Line

		acct3.withdraw(420.75);
		System.out.println("withdraw @ Account " + acct3.getAccountNumber());
		System.out.println("Balance " + String.format(Locale.GERMAN, "%,.2f", acct3.getBalance()));
		System.out.println("Requested: 420.75");
		System.out.println("Error: Insufficient funds.");
		System.out.println("New Balance " + String.format(Locale.GERMAN, "%,.2f", acct3.getBalance()));

		System.out.println("\nadd interest ... ");

		acct1.addInterest();
		acct2.addInterest();
		acct3.addInterest();

		System.out.println(); // Blank Line

		System.out.println(acct1);
		System.out.println(acct2);
		System.out.println(acct3);

	}

}
