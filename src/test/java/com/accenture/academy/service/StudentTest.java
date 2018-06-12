package com.accenture.academy.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.accenture.academy.model.Student;
import com.accenture.academy.utils.Module;

public class StudentTest {
	
	Student student;

	@Before
	public void setUpStudent() {
		student = new Student();
		student.setFirstName("Anna");
	}
	
	
	@Test
	public void testAssertEquals() {
		assertEquals("Anna", student.getFirstName());
	}

	@Test
	public void testAssertNotEquals() {

		student.setAttitude("Serious");

		assertNotEquals("student should not be aggressive.", "Aggressive", student.getAttitude());
	}

	@Test
	public void testAssertTrue() {

		assertTrue("By default a student must be an accenture academy student", student.isAcnAcademyStudent());
	}


	@Test
	public void testAssertNotNull() {
		student.setAttitude("Serious");
		assertNotNull("student first name must not be null", student.getFirstName());
	}

	
	public void testStudentIsNotMinor() {
		student.setAge(18);
		assertEquals(18, student.getAge(),0);
	}
	
	@Test
	public void testAssertSame() {
		Student studentAnna = new Student();
		studentAnna.setFirstName("Anna");

		Student studentJohn = studentAnna;

		assertSame("Anna and John are two different persons. They cannot be the same", studentAnna, studentJohn);

	}
	
	@Test
	public void testStudentIsNotAcnAcademyStudent() {
		student.setAcnAcademyStudent(false);
		assertFalse("Student is not an accenture acadmemy student",student.isAcnAcademyStudent());
		
	}
	
	
	@Test
	public void testStudentAge() {
		student.setAge(20);
		assertEquals(20,student.getAge(),0);
	}

	@Test
	public void testAssertNotSame() {
		Student studentAnna = new Student();
		studentAnna.setFirstName("Anna");

		Student studentJohn = new Student();
		studentJohn.setFirstName("John");

		assertNotSame("Anna and John are two different persons. They cannot be the same", studentAnna, studentJohn);

	}
	
	@Test
	public void testDefaultFunny() {
		assertTrue("student is funny", student.isFunny());
	}
	
	@Test
	public void testNotFunny() {
		student.setFunny(false);
		assertFalse("student is not funny", student.isFunny());
	}

	@Test
	public void testIsNotMinor() {
		student.setAge(20);
		assertFalse("student is not minor", student.isMinor());
	}
	
	@Test
	public void testIsMinor() {
		student.setAge(10);
		assertTrue("student is a minor", student.isMinor());
	}
	
	@Test
	public void testEnrolledModules() {
		List<Module> modules = new ArrayList<>();
		modules.add(Module.AGILE_METHODOLOGY);
		student.setEnrolledModules(modules);
		assertEquals(1,student.getEnrolledModules().size(),0);
	}
}
