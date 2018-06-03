package com.accenture.academy.model;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test class to test a student Java Bean.
 * 
 * @author davinen.s.curoopen
 */
public class StudentTest {

	/**
	 * Example of assertEquals. We are verifying that once we set a value to the name field,
	 *  we obtain the same value when we are fetching it from the Student object.
	 */
	@Test
	public void testAssertEquals() {
		Student student = new Student();
		student.setFirstName("Anna");
		
		assertEquals("Anna", student.getFirstName());
	}


	/**
	 * Example of assertNotEquals. We are verifying that the value of the 'attitude' field
	 * is not equal to the Value 'aggressive'.
	 */
	@Test
	public void testAssertNotEquals() {
		Student student = new Student();
		student.setFirstName("Anna");
		student.setAttitude("Serious");
		
		assertNotEquals("student should not be aggresive.","Aggressive", student.getAttitude());
	}
}
