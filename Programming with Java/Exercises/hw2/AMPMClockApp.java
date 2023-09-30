/*
	Name: Nikos Mitsakis
	Student Number: 3210122
*/

import java.util.concurrent.TimeUnit;

class AMPMClockApp {

	public static void main(String args[]) throws Exception {

		AMPMClock clock = new AMPMClock();
		clock.setHour(16);
		clock.setMin(28);
		clock.setSec(58);
		for (int i = 0; i <= 180; i++) {
			clock.setAPM(true);
			System.out.println(clock);
			clock.tick();
			TimeUnit.SECONDS.sleep(1);
		}
	}
}