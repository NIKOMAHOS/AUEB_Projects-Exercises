/*
	Name: Nikos Mitsakis
	Student Number: 3210122
*/

import java.text.DecimalFormat;

class AMPMClock extends Clock {
	private boolean type;
	private String ampm;

	AMPMClock() {
		super();
	}

	void setAPM(boolean yes) {
		type = yes;
		if (super.hour >= 12 & type == true) {
			ampm = " pm";
		} else if (super.hour < 12 & type == true) {
			ampm = " am";
		}
	}

	public String toString() {
		if (super.hour >= 12 & type == true) {
			DecimalFormat df = new DecimalFormat("00");
			String result = df.format(hour - 12) + ":" + df.format(min) + ":" + df.format(sec);
			return result + ampm;
		} else if (super.hour < 12 & type == true) {
			DecimalFormat df = new DecimalFormat("00");
			String result = df.format(hour) + ":" + df.format(min) + ":" + df.format(sec);
			return result + ampm;
		} else {
			return super.toString();
		}
	}
}
