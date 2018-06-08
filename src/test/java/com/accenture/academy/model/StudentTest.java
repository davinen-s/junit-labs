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
	 * Example of assertEquals. We are verifying that once we set a value to the
	 * name field, we obtain the same value when we are fetching it from the Student
	 * object.
	 */
	@Test
	public void testAssertEquals() {
		Student student = new Student();
		student.setFirstName("Anna");

		assertEquals("Anna", student.getFirstName());
	}

	/**
	 * Example of assertNotEquals. We are verifying that the value of the 'attitude'
	 * field is not equal to the Value 'aggressive'.
	 */
	@Test
	public void testAssertNotEquals() {
		Student studentAnna = new Student();
		studentAnna.setFirstName("Anna");
		studentAnna.setAttitude("Serious");

		assertNotEquals("student should not be aggressive.", "Aggressive", studentAnna.getAttitude());
	}

	/**
	 * Example of assertTrue. We are verifying that by default a 'Student' object
	 * has 'acnAcademyStudent' attribute defaulted to true.
	 */
	@Test
	public void testAssertTrue() {
		Student studentAnna = new Student();
		studentAnna.setFirstName("Anna");
		// no need to initialize 'funny' field. funny is of type primitive boolean,
		// default value = false.

		assertTrue("By default a student must be an accenture academy student", studentAnna.isAcnAcademyStudent());
	}

	/**
	 * Example of assertFalse. We are verifying that by default a 'Student' object
	 * has 'funny' attribute defaulted to false. Best business case example is a
	 * tuitionFeePayment attribute/field.
	 */
	@Test
	public void testAssertFalse() {
		Student studentAnna = new Student();
		studentAnna.setFirstName("Anna");

		// no need to initialise 'funny' field. funny is of type primitive boolean,
		// default value = false.

		assertFalse("Anna is a serious girl, she is not funny. value must be false", studentAnna.isFunny());
	}

	/**
	 * Example of assertNotNull. We are verifying that the value of the 'firstName'
	 * field is not null.
	 */
	@Test
	public void testAssertNotNull() {
		Student studentAnna = new Student();
		studentAnna.setFirstName("Anna");
		studentAnna.setAttitude("Serious");

		assertNotNull("student first name must not be null", studentAnna.getFirstName());
	}

	/**
	 * Example of assertSame. we are verifying that two objects are the same.
	 */
	@Test
	public void testAssertSame() {
		Student studentAnna = new Student();
		studentAnna.setFirstName("Anna");

		Student studentJohn = studentAnna;

		assertSame("Anna and John are two different persons. They cannot be the same", studentAnna, studentJohn);

	}

	/**
	 * Example of assertNotSame. We are verifying that two objects are not the same.
	 */
	@Test
	public void testAssertNotSame() {
		Student studentAnna = new Student();
		studentAnna.setFirstName("Anna");

		Student studentJohn = new Student();
		studentJohn.setFirstName("John");

		assertNotSame("Anna and John are two different persons. They cannot be the same", studentAnna, studentJohn);

	}

	/**
	 * Example of assertEquals with double values. Demonstration known issues about
	 * rounding issues. The Default value for pocket money is 0.00008d which is zero
	 * to the nearest two decimal places.
	 */
	@Test
	public void testThatPocketMoneyHasDefaultValueAndIsZeroToTheNearestTwoDecimalPlace() {
		Student student = new Student();
		// assertEquals for double data type takes and additional argument named delta.
		// delta indicate to which decimal place the values being compared should be
		// rounded off.
		assertEquals(0d, student.getPocketMoney(), 0.01);
	}

}
