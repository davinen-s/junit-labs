package com.accenture.academy.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class UniversityTest {

	@Test
	public void testIfNewUniversityObjectCurrentStudentIsNotNull() {
		University u = new University();
		assertNotNull(u.getCurrentStudent());
	}

}
