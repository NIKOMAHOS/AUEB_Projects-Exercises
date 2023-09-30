/*
	Name: Nikos Mitsakis
	Student Number: 3210122
*/

class App4 {
	public static void main(String[] args) {
		int fibnum_1 = 0;
		int fibnum_2 = 1;
		int nextnum = 0;
		if ((Integer.parseInt(args[0]) == 0) | (Integer.parseInt(args[0]) == 1)) {
			System.out.println(args[0] + " is a fibonacci number.");
		} else if (Integer.parseInt(args[0]) < 0) {
			System.out.println("The are no negative Fibonacci numbers.");
		} else {
			System.out.println("Fibonacci number = " + fibnum_1);
			System.out.println("Fibonacci number = " + fibnum_2);
			for (;;) {
				if (nextnum > Integer.parseInt(args[0])) {
					System.out.println(args[0] + " is not a fibonacci number");
					break;
				} else if (nextnum == Integer.parseInt(args[0])) {
					System.out.println(args[0] + " is a fibonacci number.");
					break;
				}
				nextnum = fibnum_1 + fibnum_2;
				System.out.println("Fibonacci number = " + nextnum);
				fibnum_1 = fibnum_2;
				fibnum_2 = nextnum;
			}
		}
	}
}
