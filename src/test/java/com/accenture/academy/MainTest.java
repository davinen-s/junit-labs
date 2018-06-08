package com.accenture.academy;

import org.junit.Test;

public class MainTest {

	@Test
	public void justToGetCoverage() {
		Main main = new Main();
		main.getClass();
		String[] args = {"one"};
		Main.main(args);
	}

}
