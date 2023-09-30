/*
	Name: Nikos Mitsakis
	Student Number: 3210122
*/

import java.text.DecimalFormat;
import java.util.Scanner;

class App3 {

	public static void main(String args[]) {

		System.out.print("This program reads three numbers and returns the solutions"
				+ "\n of the quadratic equation with these numbers as coefficients.\n");
		Scanner input = new Scanner(System.in);
		System.out.print("Enter the first number: ");
		Float a = input.nextFloat();
		System.out.print("Enter the second number: ");
		Float b = input.nextFloat();
		System.out.print("Enter the third number: ");
		Float c = input.nextFloat();
		Float discriminant = (b * b - 4 * a * c); // Discriminant
		if (discriminant < 0) {
			System.out.println("There are no real values for the quadratic equation.");
		} else {
			Float sol_1 = (float) (-b + Math.sqrt(discriminant)) / (2 * a); // First Solution (using the +)
			Float sol_2 = (float) (-b - Math.sqrt(discriminant)) / (2 * a); // Second Solution (using the -)
			DecimalFormat df = new DecimalFormat("###.###");
			System.out.println(
					"The first solution is :          " + df.format(sol_1) + "\n" +
							"The second solution is :          " + df.format(sol_2));
		}
		input.close();
	}
}
