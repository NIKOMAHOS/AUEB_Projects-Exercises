/*
	Name: Nikos Mitsakis
	Student Number: 3210122
*/

class Student {

	private String Name;
	private String RN;
	private int Grade;

	Student(String Name, String RN, int Grade) {
		this.Name = Name;
		this.RN = RN;
		this.Grade = Grade;
	}

	public String getName() {
		return Name;
	}

	public int getGrade() {
		return Grade;
	}

	public String getRN() {
		return RN;
	}

	public String toString() {
		return Name + " | " + RN + " | " + Grade;
	}

}
