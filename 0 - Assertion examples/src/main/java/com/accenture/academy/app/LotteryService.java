package com.accenture.academy.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LotteryService {
	
	/**
	 * Generate the winning lotto number.
	 * 
	 * @return List of 6 winning number.
	 */
	public static List<Integer> drawNumber() {
		List<Integer> result = new ArrayList<Integer>();
		 while ( result.size() < 7) {
			 int randomNumber = getRandomNumber();
			 if (!result.contains(randomNumber)) {
				 result.add(randomNumber);
			 }
		 }
		return result;
	}
	
	/**
	 * Output the lotto result to the console.
	 */
	public static void printLottoResult(List<Integer> result) {
		System.out.println("Winning number: " 
				+ result.get(0) + ", "
				+ result.get(1)  + ", "
				+ result.get(2)  + ", "
				+ result.get(3)  + ", "
				+ result.get(4)  + ", "
				+ result.get(5));
	}
	
	/**
	 * Generate and return a random number between 1 and 40.
	 * 
	 * @return
	 */
	private static int getRandomNumber( ) {
		Random r = new Random();
		int Low = 1;
		int High = 41;
		return r.nextInt(High-Low) + Low;
	}

}
