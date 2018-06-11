package com.accenture.academy.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.accenture.academy.exception.BusinessException;
import com.accenture.academy.exception.ModuleNotFoundException;
import com.accenture.academy.model.Student;
import com.accenture.academy.model.University;
import com.accenture.academy.utils.AppParametersUtils;
import com.accenture.academy.utils.Module;

public class UniversityServiceTest {
	
	private static Student s, s2, s3;

	@Test
	public void testNewStudentDefaultValues() {
		assertNotNull(s);
		assertTrue(s.isAcnAcademyStudent());
		assertEquals(AppParametersUtils.FREE_MODULE_VOUCHER, s.getFreeModuleVouchers());
	}
	
	@Test
	public void testStudentNotDefaultValue() {
		List<Module> modules = new ArrayList<>();
		modules.add(Module.AGILE_METHODOLOGY);
		Student student = new Student();
		student.setAcnAcademyStudent(true);
		student.setAge(18);
		student.setAttitude("Funny");
		student.setDob(LocalDate.of(1994, 03, 02));
		student.setEnrolledModules(modules);
		student.setFirstName("Dave");
		student.setLastName("Dave");
		student.setFreeModuleVouchers(0);
		student.setFunny(true);
		
		assertNotNull(student.getAge());
		assertEquals("Funny", student.getAttitude());
		assertEquals(LocalDate.of(1994, 03, 02), student.getDob());
		assertNotNull(student.getEnrolledModules());
		assertTrue(student.isFunny());
		assertFalse(student.isMinor());
		assertNotNull(student.getFirstName());
		assertNotNull(student.getLastName());
	}
	
	@BeforeClass
	public static void before() {
		UniversityService.university = new University();
		s = new Student();
		s2 = UniversityService.registerStudent("Brandon", "David", LocalDate.of(2000, 03, 03), false);
		s3 = UniversityService.registerStudent("Tom", "Oliver", LocalDate.of(2000, 03, 03), true);
	}
	
	
	@Test
	public void testRegisterStudent() {
		
		assertNotNull(s2);

	}
	
	@Test
	public void testEnrollModule() {
		List<String> modules = new ArrayList<>();
		
		modules.add("Spring Framework");
		try {
			UniversityService.enrollModules(s2.getId(), modules);
			UniversityService.enrollModules(s3.getId(), modules);
			assertEquals(s2.getFreeModuleVouchers(), 2);
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
		List<String> modules = new ArrayList<>();
		
		modules.add("SpringFramework");
		
			UniversityService.enrollModules(s2.getId(), modules);
			
	}
	
	@Test(expected=BusinessException.class)
	public void testBusinessException() throws ModuleNotFoundException, BusinessException {
		List<String> modules = new ArrayList<>();
		
		modules.add("Spring Framework");
		modules.add("Mobile Dev");
		modules.add("Agile Methodology");
		
			UniversityService.enrollModules(s2.getId(), modules);
			
	}
}
