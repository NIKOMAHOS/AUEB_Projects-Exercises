/*
	Name: Nikos Mitsakis
	Student Number: 3210122
*/

import java.util.Scanner;

class StudentApp {

	public static void main(String args[]) {

		StudentList Lesson = new StudentList();

		Scanner sc = new Scanner(System.in);

		for (;;) {
			System.out.println("1. Insert Student");
			System.out.println("2. Lookup Student");
			System.out.println("3. Display List");
			System.out.println("0. Exit");
			System.out.println();// Blank Line
			System.out.println("Please choose one of the above options: ");

			int x = sc.nextInt();
			switch (x) {
				case 1: {
					Scanner sc_1 = new Scanner(System.in);
					System.out.println(
							"Please enter the student's name, RN and Grade seperated by a space between them: ");
					String name = sc_1.next();
					String rn = sc_1.next();
					int grade = sc_1.nextInt();
					Student newStudent = new Student(name, rn, grade);
					Lesson.InsertStudent(newStudent);
					System.out.println("The student was succesfully added to the list.");
					System.out.println();
					break;
				}
				case 2: {
					System.out.println("Please enter the student's RN: ");
					Scanner sc_2 = new Scanner(System.in);
					String RN_in = sc_2.next();
					Lesson.LookupStudent(RN_in);
					System.out.println();
					break;
				}
				case 3: {
					Lesson.DisplayList();
					break;
				}
				case 0: {
					System.exit(0);
					break;
				}
			}
		}
	}

}
