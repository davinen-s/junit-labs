package com.accenture.academy.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.accenture.academy.utils.Module;

public class StudentTest {
	
	@Test
	public void testAssertEqualsMinor() {
		Integer age = 11;
		Student student = new Student();
		student.setAge(age);

		assertEquals(true, student.isMinor());
	}

	@Test
	public void testAssertEqualsNotMinor() {
		Integer age = 19;
		Student student = new Student();
		student.setAge(age);

		assertEquals(false, student.isMinor());
	}
	
	@Test
	public void testAssertEqualsAge() {
		Integer age = 31;
		Student student = new Student();
		student.setAge(age);

		assertEquals(age, student.getAge());
	}
	
	@Test
	public void testAssertEqualsFunny() {
		
		Student student = new Student();
		student.setFunny(true);

		assertEquals(true, student.isFunny());
	}
	
	@Test
	public void testAssertEqualsAttitude() {
		
		Student student = new Student();
		student.setAttitude("Serious");

		assertEquals("Serious", student.getAttitude());
	}
	
	@Test
	public void testAssertEqualsEnroll() {
		
		Student student = new Student();
		List<Module> enrolledModules = new ArrayList<>();
		student.setEnrolledModules(enrolledModules);

		assertEquals(enrolledModules, student.getEnrolledModules());
	}

}
