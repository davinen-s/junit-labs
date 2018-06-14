package com.accenture.academy.model;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class UniversityTest {
	
	@Test
	public void testIfNewUniversityObjectCurrentStudentIsNotNull() {
		University uni = new University();
		assertNotNull(uni.getCurrentStudent());
	}

}
