/*
	Name: Nikos Mitsakis
	Student Number: 3210122
*/

import java.util.Scanner;

class App1_2 {

	// Factorial Method
	public static int factorial(int n) {
		if (n == 0)
			return 1;
		else {
			return n * factorial(n - 1);
		}
	}

	public static void main(String args[]) {

		Scanner input = new Scanner(System.in);
		System.out.print("Please enter a non negative integer: ");
		int fac = input.nextInt();
		int result = factorial(fac);
		System.out.println(result);
		input.close();
	}

}
