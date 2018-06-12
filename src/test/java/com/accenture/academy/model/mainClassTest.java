package com.accenture.academy.model;

import org.junit.Test;

import com.accenture.academy.Main;

public class mainClassTest {

	@Test
	public void test() {
		Main main =  new  Main();
		
		String[] args = {"x"};
		Main.main(args);
	}
}
