package com.accenture.academy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.accenture.academy.Main;

public class MainTest {

	@Test
	public void testAsserEquals() {
		String[] args = { "one", "two", "three" };
		Main.main(args);
		assertEquals(3, args.length);

	}

	@Test
	public void testAssertNotNull() {
		assertNotNull(new Main());
	}

}
