package com.accenture.academy.model;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

import com.accenture.academy.model.Student;
import com.accenture.academy.utils.Module;

public class StudentTest {

	Student student;
	Student student1;

	@Before
	public void setup() {
		student = new Student();
		student.setId(1);
		student.setFirstName("Anna");
		student.setLastName("DeLacour");
		student.setAttitude("Serious");
		student.setFunny(false);
		student.setAcnAcademyStudent(true);
		student.setDob(LocalDate.parse("1990-01-23"));
		student.setAge(Period.between(LocalDate.parse("1990-01-23"), LocalDate.now()).getYears());
		student.setFreeModuleVouchers(2);

		student1 = new Student();
		student1.setDob(LocalDate.parse("2012-01-23"));
		student1.setAge((int) ChronoUnit.YEARS.between(student1.getDob(), LocalDate.now()));
	}
	
	@Test
	public void testNewStudent() {
		assertEquals("Anna", student.getFirstName());
		assertNotEquals("student should not be aggressive.", "Aggressive", student.getAttitude());
		assertTrue("By default a student must be an accenture academy student", student.isAcnAcademyStudent());
		assertFalse("Anna is a serious girl, she is not funny. value must be false", student.isFunny());
		assertEquals("DeLacour", student.getLastName());
		assertSame("Age should be 28", 28, student.getAge());
		assertSame("ID should be 1", 1, student.getId());
	}

	@Test
	public void testAssertEquals() {
		List<Module> enrolledModules =  new ArrayList<>();
		enrolledModules.add(Module.AGILE_METHODOLOGY);
		enrolledModules.add(Module.BUILD_AND_UNIT_TEST);
		student.setEnrolledModules(enrolledModules);
		
		assertEquals(2, enrolledModules.size());
	}
	
	@Test
	public void testAssertTrue() {
		if (student.getAge() < 18) {
			assertTrue("By default, a student is a minor", student.isMinor());
		} else {
			assertFalse("Student is not a minor", student.isMinor());
		}
	}
	
	@Test
	public void testAssertFalse() {
		if (student1.getAge() < 18) {
			assertTrue("By default, a student is a minor", student1.isMinor());
		} else {
			assertFalse("Student is not a minor", student1.isMinor());
		}
	}

}
