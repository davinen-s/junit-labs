package com.accenture.academy.model;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.junit.*;

import com.accenture.academy.utils.Module;
import com.accenture.academy.model.Student;

public class StudentTest {
	
	Student stud;
	Student student1;

	@Before
	public void setupStudent() {
		stud = new Student();
		stud.setId(1);
		stud.setFirstName("Mary");
		stud.setLastName("Stones");
		stud.setAttitude("Serious");
		stud.setFunny(false);
		stud.setAcnAcademyStudent(true);
		stud.setDob(LocalDate.parse("1994-05-12"));
		stud.setAge(Period.between(LocalDate.parse("1994-05-12"), LocalDate.now()).getYears());
		stud.setFreeModuleVouchers(2);

		student1 = new Student();
		student1.setDob(LocalDate.parse("1995-09-03"));
		student1.setAge((int) ChronoUnit.YEARS.between(student1.getDob(), LocalDate.now()));
	}

	
	@Test
	public void testNewStudent() {
		assertEquals("Mary", stud.getFirstName());
		assertNotEquals("Student should not be aggressive!!!", "Aggressive", stud.getAttitude());
		assertTrue("By default a student must be an accenture academy student", stud.isAcnAcademyStudent());
		assertFalse("Mary is a serious girl, she is not funny.", stud.isFunny());
		assertEquals("Stones", stud.getLastName());
		assertSame("Age should be 24", 24, stud.getAge());
		assertSame("Id should be 1", 1, stud.getId());
	}

	@Test
	public void testAssertEquals() {
		List<Module> moduleList =  new ArrayList<>();
		moduleList.add(Module.AGILE_METHODOLOGY);
		moduleList.add(Module.MOBILE_DEVELOPMENT);
		moduleList.add(Module.SPRING_FRAMEWORK);
		
		stud.setEnrolledModules(moduleList);
		
		assertEquals(3, moduleList.size());
	}
	
	@Test
	public void testAssertTrue() {
		Student stud = new Student();
		stud.setAge(13);
		assertTrue("By default, a student is a minor", stud.isMinor());
		
	}
	
	@Test
	public void testAssertFalse() {
		Student stud = new Student();
		stud.setAge(24);
			assertFalse("Student is not a minor", stud.isMinor());
		
	}

}
