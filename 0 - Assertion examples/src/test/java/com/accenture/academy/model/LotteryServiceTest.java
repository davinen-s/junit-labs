package com.accenture.academy.model;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.accenture.academy.app.LotteryService;

/**
 * Test class to test a student Java Bean.
 * 
 * @author davinen.s.curoopen
 */
public class LotteryServiceTest {

	
	private List<Integer> lotteryResult;

	/**
	 * Test that lotto result is drawn and printed to the console successfully.
	 */
	@Test
	public void testPrintLottoResultSuccess() {
		lotteryResult = LotteryService.drawNumber();
		LotteryService.printLottoResult(lotteryResult);
	}
	
	/**
	 * Asserting that an exception is thrown.
	 */
	@Test(expected=IndexOutOfBoundsException.class)
	public void testPrintLottoResultFailNullResultList() {
		ArrayList<Integer> result = new ArrayList<Integer>();
		LotteryService.printLottoResult(result);
	}
	
}
