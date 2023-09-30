/*
	Name: Nikos Mitsakis
	Student Number: 3210122
*/

import java.util.Locale;

class Account {

	// Data
	private final double RATE = 0.015;
	private String name;
	private String acctNumber;
	private double balance;

	// Constructors
	Account(String name_1, String accNum_1, double bal) {
		name = name_1;
		acctNumber = accNum_1;
		balance = bal;
	}

	Account(String name_2, String accNum_2) {
		name = name_2;
		acctNumber = accNum_2;
		balance = 0;
	}

	// Methods
	double deposit(double dep) {
		if (dep <= 0) {
			return balance;
		} else {
			return balance = balance + dep;
		}
	}

	double withdraw(double wit) {
		if ((wit <= 0) | (wit > balance)) {
			return balance;
		} else {
			return balance = balance - wit;
		}
	}

	double addInterest() {
		return balance = balance + RATE * balance;
	}

	double getBalance() {
		return balance;
	}

	String getAccountNumber() {
		return acctNumber;
	}

	public String toString() {
		return "Account Number: " + acctNumber + "\n" + "Name: " + name + "\n" + "Balance: "
				+ String.format(
						Locale.GERMAN, "%,.2f", balance);
	}
}
