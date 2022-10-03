package utility;

/*
          This class includes static variables and methods which are needed for assignments

          int counterLS     // counter for Linear Search
          int counterBS     // counter for Binary Search
          void setCounterLS(int number)
          void resetCounterLS()
          void increaseCounterLS()
          void decreaseCounterLS()
          int getCounterLS()
          void setCounterBS(int number)
          void resetCounterBS()
          void increaseCounterBS()
          void decreaseCounterBS()
          int getCounterBS()

          Last updated: 9:00 AM, Friday, March 11, 2022
          Author: JW
*/

public class Benchmark {
	private static int counterLS = 0; // static counter for Linear Search
	private static int counterBS = 0; // static counter for Binary Search

	// set counter for Linear Search
	public static void setCounterLS(int number) {
		counterLS = number;
	}

	// reset counter for Linear Search
	public static void resetCounterLS() {
		counterLS = 0;
	}

	// count a repetition in loop for Linear Search
	public static void increaseCounterLS() {
		counterLS++;
	}

	// decrease counter for Linear Search
	// if counter is below 0, make it 0
	public static void decreaseCounterLS() {
		if (counterLS < 0)
			counterLS = 0;
		else
			counterLS--;
	}

	// get counter for Linear Search
	public static int getCounterLS() {
		return counterLS;
	}

	public static int getCounterBS() {
		return counterBS;
	}

	// set counter for Binary Search
	public static void setCounterBS(int number) {
		counterBS = number;
	}

	// reset counter for Binary Search
	public static void resetCounterBS() {
		counterBS = 0;
	}

	// count a repetition in loop for Binary Search
	public static void increaseCounterBS() {
		counterBS++;
	}

	// decrease counter for Binary Search
	// if counter is below 0, make it 0
	public static void decreaseCounterBS() {
		if (counterBS < 0)
			counterBS = 0;
		else
			counterBS--;
	}

}
