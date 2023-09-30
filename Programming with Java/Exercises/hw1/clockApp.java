/*
	Name: Nikos Mitsakis
	Student Number: 3210122
*/

import java.util.concurrent.TimeUnit;

public class clockApp {

	public static void main(String args[]) throws Exception {
		// Alternatively, if I had written a constructor method in my Clock class,
		// I could instantiate a Clock object (i.e. watch) with the desired values
		// instead of using the default no-parameters needed constructor and then
		// using the set methods to give the clock object the desired values.
		Clock watch = new Clock();
		watch.setHour(16);
		watch.setMin(58);
		watch.setSec(58);
		for (int i = 0; i <= 180; i++) {
			System.out.println(watch);
			watch.tick();
			TimeUnit.SECONDS.sleep(1);

		}
	}
}
