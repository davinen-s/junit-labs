package com.accenture.academy.test.main;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.accenture.academy.Main;

public class MainTest {
	
	@Test
	public void testMainInstantiated() {
		assertNotNull(new Main());
	}
	
	@Test
	public void testMainMethod() {
		Main.main(new String[] {"MyTest"});
	}
	

	

}
