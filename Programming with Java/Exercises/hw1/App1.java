/*
	Name: Nikos Mitsakis
	Student Number: 3210122
*/

import java.util.Scanner;

class App1 {

	// Factorial Method
	public int factorial(int n) {
		if (n == 0)
			return 1;
		else {
			return n * factorial(n - 1);
		}
	}

	public static void main(String args[]) {
		Scanner input = new Scanner(System.in);
		System.out.print("Please enter a non negative integer: ");
		int num = input.nextInt();
		App1 obj = new App1();
		int result = obj.factorial(num);
		System.out.println(result);
		input.close();

	}

}
