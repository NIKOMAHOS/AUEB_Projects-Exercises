import java.text.DecimalFormat;

/*
	Name: Nikos Mitsakis
	Student Number: 3210122
*/

class Clock {

	// Data

	protected int hour;
	protected int min;
	protected int sec;

	// Methods

	void setHour(int h) {

		this.hour = h;
	}

	void setMin(int m) {

		this.min = m;
	}

	void setSec(int s) {

		this.sec = s;
	}

	void tick() {
		sec++;
		if (sec == 60) {
			sec = 0;
			min++;
			if (min == 60) {
				min = 0;
				hour++;
				if (hour == 24) {
					hour = 0;
				}
			}
		}

	}

	public String toString() {
		DecimalFormat df = new DecimalFormat("00");
		return df.format(hour) + ":" + df.format(min) + ":" + df.format(sec);
	}
}