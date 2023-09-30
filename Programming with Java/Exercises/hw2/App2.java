/*
	Name: Nikos Mitsakis
	Student Number: 3210122
*/

class App2 {

	// The array's dimensions
	final static int i = 5;
	final static int j = 3;

	public static void main(String args[]) {

		// Creating and initializing a new array from Data
		int pin[][] = { //////////// ROWS - COLUMNS
				{ 182, 41, 202 }, // [0] - [0][0], [0][1], [0][2]
				{ 145, 85, 325 }, // [1] - [1][0], [1][1], [1][2]
				{ 195, 15, 115 }, // [2] - [2][0], [2][1], [2][2]
				{ 110, 24, 407 }, // [3] - [3][0], [3][1], [3][2]
				{ 255, 11, 357 } // [4] - [4][0], [4][1], [4][2]
		};

		// Printing the newly created array
		System.out.println("|   E. D.   |     A      |    B      |     C   |"); // E. D. = electoral district
		for (int lineCounter = 0; lineCounter < i; lineCounter++) {
			System.out.print("|     " + (lineCounter + 1));
			for (int columnCounter = 0; columnCounter < j; columnCounter++) {
				System.out.print("     |    " + pin[lineCounter][columnCounter]);
			}
			System.out.print("  |");
			System.out.println();
		}

		// Initializing a variable for each candidate, to store the total votes that
		// each of them got
		int candidateA = 0;
		int candidateB = 0;
		int candidateC = 0;

		// Calculating the total votes for each of the three candidates
		for (int lineCounter = 0; lineCounter < i; lineCounter++) {
			for (int columnCounter = 0; columnCounter < j; columnCounter++) {
				if (columnCounter == 0) {
					candidateA += pin[lineCounter][columnCounter];
				} else if (columnCounter == 1) {
					candidateB += pin[lineCounter][columnCounter];
				} else if (columnCounter == 2) {
					candidateC += pin[lineCounter][columnCounter];
				}
			}
		}

		// Printing the total votes of each candidate
		System.out.println(); // Blank Line
		System.out.println("The total votes that each candidate got are: ");
		System.out.println("Candidate A: " + candidateA);
		System.out.println("Candidate B: " + candidateB);
		System.out.println("Candidate C: " + candidateC);

		// Initializing a variable for each candidate, to store the percentage of votes
		// that each of them got to total votes
		double percentageA = 0;
		double percentageB = 0;
		double percentageC = 0;

		// The total votes
		int totalVotes = candidateA + candidateB + candidateC;

		// Calculating the percentage of votes that each of the 3 candidates got to
		// total votes
		percentageA = (candidateA * 100) / totalVotes;
		percentageB = (candidateB * 100) / totalVotes;
		percentageC = (candidateC * 100) / totalVotes;

		// Printing the percentage of votes that each of the 3 candidates got to total
		// votes
		System.out.println(); // Blank Line
		System.out.println("The percentage of votes that each candidate got to total votes are: ");
		System.out.println("Candidate A: " + percentageA + "%");
		System.out.println("Candidate B: " + percentageB + "%");
		System.out.println("Candidate C: " + percentageC + "%");

		// Checking to see if there is a winner or not and acting accordingly.
		if (percentageA > 50) {
			System.out.println("Candidate A is the winner!");
		} else if (percentageB > 50) {
			System.out.println("Candidate B is the winner!");
		} else if (percentageC > 50) {
			System.out.println("Candidate C is the winner!");
		} else {
			;
			System.out.println("There is no winner!");
			System.out
					.println("The voting will be done again, between the two candidates with the highest percentages.");
			if (((percentageA > percentageB) & (percentageB > percentageC))
					| ((percentageB > percentageA) & (percentageA > percentageC))) {
				System.out.println(); // Blank Line
				System.out.println("The voting will be done again, between Candidate A and Candidate B.");
				System.out.println("Candidate A: ");
				System.out.println(pin[0][0] + " " + pin[0][1] + " " + pin[0][2]);
				System.out.println("Candidate B: ");
				System.out.println(pin[1][0] + " " + pin[1][1] + " " + pin[1][2]);
				System.out.println("Candidate A: " + percentageA + "%");
				System.out.println("Candidate B: " + percentageB + "%");
			} else if ((percentageA > percentageC) & (percentageC > percentageB)
					| ((percentageC > percentageA) & (percentageA > percentageB))) {
				System.out.println(); // Blank Line
				System.out.println("The voting will be done again, between Candidate A and Candidate C.");
				System.out.println("Candidate A: ");
				System.out.println(pin[0][0] + " " + pin[0][1] + " " + pin[0][2]);
				System.out.println("Candidate C: ");
				System.out.println(pin[2][0] + " " + pin[2][1] + " " + pin[2][2]);
				System.out.println("Candidate A: " + percentageA + "%");
				System.out.println("Candidate C: " + percentageC + "%");
			} else if (((percentageB > percentageC) & (percentageC > percentageA))
					| ((percentageC > percentageB) & (percentageB > percentageC))) {
				System.out.println(); // Blank Line
				System.out.println("The voting will be done again, between Candidate B and Candidate C.");
				System.out.println("Candidate B: ");
				System.out.println(pin[1][0] + " " + pin[1][1] + " " + pin[1][2]);
				System.out.println("Candidate C: ");
				System.out.println(pin[2][0] + " " + pin[2][1] + " " + pin[2][2]);
				System.out.println("Candidate B: " + percentageB + "%");
				System.out.println("Candidate C: " + percentageC + "%");
			}
		}
	}
}
