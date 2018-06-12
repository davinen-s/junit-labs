package com.accenture.academy.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.accenture.academy.exception.BusinessException;
import com.accenture.academy.exception.ModuleNotFoundException;
import com.accenture.academy.model.Student;
import com.accenture.academy.model.University;
import com.accenture.academy.utils.AppParametersUtils;
import com.accenture.academy.utils.Module;

public class UniversityServiceTest {
	private static Student student2;
	
	@BeforeClass
	public static void createStudent() {
		UniversityService.university = new University();
		Student student = new Student();
		LocalDate dob = LocalDate.of(1994, 4, 16);
		student2 = UniversityService.registerStudent("Veeresh", "Lubrun", dob, false);
	}
	
	@Test
	public void testIfNewUniversityObjectCurrentStudentIsNotNull() {
		University u = new University();
		assertNotNull(u.getCurrentStudent());
	}
	
	@Test
	public void testDefaultFields() {
		List<Module> moduleNames = new ArrayList<>();
		moduleNames.add(Module.BUILD_AND_UNIT_TEST);
		Student student = new Student();
		student.setDob(LocalDate.of(2000, 12, 2));
		student.setEnrolledModules(moduleNames);
		student.setFirstName("Sam");
		student.setLastName("Sammy");
		student.setAcnAcademyStudent(true);
		student.setAge(18);
		student.setAttitude("Funny");
		student.setFreeModuleVouchers(0);
		student.setFunny(true);
		
		assertNotNull(student.getAge());
		assertEquals("Funny", student.getAttitude());
		assertEquals(LocalDate.of(2000, 12, 02), student.getDob());
		assertNotNull(student.getEnrolledModules());
		assertTrue(student.isFunny());
		assertFalse(student.isMinor());
		assertNotNull(student.getFirstName());
		assertNotNull(student.getLastName());
	}
	
	@Test
	public void testRegisterStudent() {
		LocalDate dob = LocalDate.of(1994, 4, 16);
		Student student = UniversityService.registerStudent("Veeresh", "Lubrun", dob, true);
		
		assertNotNull("Student object is null", student);
		assertNotNull(student.getId());
		assertEquals("Lubrun", student.getLastName());
		assertEquals(dob, student.getDob());
		assertEquals("Veeresh", student.getFirstName());
	}
	
	@Test
	public void testEnrollModules() {
		
		
		List<String> moduleNames = new ArrayList<>();
		moduleNames.add(Module.BUILD_AND_UNIT_TEST.getModuleName());
		
		try {
			UniversityService.enrollModules(student2.getId(), moduleNames);
			assertEquals(student2.getFreeModuleVouchers(), 2);
		} catch (ModuleNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	@Test(expected=ModuleNotFoundException.class)
	public void testModuleNotFound() throws ModuleNotFoundException, BusinessException {
		List<String> module = new ArrayList<>();
		
		module.add("Database Management");
		
			UniversityService.enrollModules(student2.getId(), module);	
	}
	

	@Test(expected=BusinessException.class)
	public void testBusinessException() throws ModuleNotFoundException, BusinessException {
		List<String> module = new ArrayList<>();
		
		module.add("Spring Framework");
		module.add("Agile Methodology");
		module.add("Web Development");
		module.add("Build and Unit test");
		
			UniversityService.enrollModules(student2.getId(), module);	
	}
}
