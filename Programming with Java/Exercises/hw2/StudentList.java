/*
	Name: Nikos Mitsakis
	Student Number: 3210122
*/

class StudentList {

	private Student[] myList = new Student[49];

	private int length = myList.length;

	void InsertStudent(Student newStudent) {
		for (int j = 0; j < length; j++) {
			if (myList[j] == null) {
				myList[j] = newStudent;
				break;
			}
		}
	}

	void LookupStudent(String RN) {

		for (int num = 0; num < length; num++) {

			if (myList[num].getRN().equals(RN)) {

				System.out.println(myList[num].getGrade());
				break;
			} else {
				System.out.println("Student not found.");
			}

		}

	}

	void DisplayList() {

		for (int i = 0; i < length; i++) {

			System.out.println(myList[i]);

		}

	}

}