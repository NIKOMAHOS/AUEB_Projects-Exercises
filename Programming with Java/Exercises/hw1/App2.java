/*
	Name: Nikos Mitsakis
	Student Number: 3210122
*/

import java.util.Scanner;

class App2 {

	public static void main(String args[]) {

		int items = 0;
		double sum = 0;
		int negative = 0;
		int positive = 0;
		int maximum = 0;
		int minimum = 0;
		int temp = 0;
		Scanner input = new Scanner(System.in);
		for (;;) {
			System.out.println("Please enter an integer (Entering 0 will terminate the program): ");
			int num = input.nextInt();
			if (num == 0) {
				double average = sum / items;

				System.out.print(
						"Items   :       " + items + "\n" +
								"Average :       " + average + "\n" +
								"Negative:       " + negative + "\n" +
								"Positive:       " + positive + "\n" +
								"Max     :       " + maximum + "\n" +
								"Min     :       " + minimum);
				break;
			} else {
				sum = sum + num;
				if (items > 1) {
					maximum = Math.max(maximum, num);
					minimum = Math.min(minimum, num);
				} else if (items == 1) {
					maximum = Math.max(temp, num);
					minimum = Math.min(temp, num);
				} else {
					temp = num;
				}
				items++;
				if (num > 0)
					positive++;
				else
					negative++;
			}
		}
		input.close();
	}
}