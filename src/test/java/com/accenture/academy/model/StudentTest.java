package com.accenture.academy.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.accenture.academy.exception.BusinessException;
import com.accenture.academy.exception.ModuleNotFoundException;
import com.accenture.academy.model.Student;
import com.accenture.academy.model.University;
import com.accenture.academy.service.UniversityService;
import com.accenture.academy.utils.Module;

public class StudentTest {

	Student student;

	@Before
	public void setUpStudent() {
		student = UniversityService.registerStudent("Wasseem", "Suhabuth", LocalDate.of(1994, 10, 1), true);
	}

	@Test(expected = BusinessException.class)
	public void testToThrowBusinessExceptionWhenNoMoreVouchersRemain() throws ModuleNotFoundException, BusinessException {
		assertNotNull(student);
		student.setAcnAcademyStudent(false);
		assertFalse(student.isAcnAcademyStudent());
		List<String> listOfModules = new ArrayList<>();
		listOfModules.add(Module.WEB_DEVELOPMENT.getModuleName());
		listOfModules.add(Module.SPRING_FRAMEWORK.getModuleName());
		listOfModules.add(Module.AGILE_METHODOLOGY.getModuleName());
		listOfModules.add(Module.BUILD_AND_UNIT_TEST.getModuleName());

		UniversityService.enrollModules(student.getId(), listOfModules);
	}
	
	@Test
	public void testToCheckStudentParametersAreNotNull() {
		assertNotNull(student);
		assertEquals("1994-10-01", student.getDob().toString());
		assertNotNull(student.getId());
		assertTrue(student.isAcnAcademyStudent());
		assertTrue(student.isFunny());
		assertEquals("Wasseem", student.getFirstName());
		assertEquals("Suhabuth", student.getLastName());
		assertNotNull(student.getEnrolledModules());
	}

	@Test
	public void testModulesEnrolledByStudent() {
		List<String> lists = new ArrayList<>();
		lists.add(Module.AGILE_METHODOLOGY.getModuleName());
		lists.add(Module.WEB_DEVELOPMENT.getModuleName());
		lists.add(Module.SPRING_FRAMEWORK.getModuleName());
		lists.add(Module.BUILD_AND_UNIT_TEST.getModuleName());

		try {
			UniversityService.enrollModules(student.getId(), lists);
		} catch (ModuleNotFoundException e) {
			e.printStackTrace();
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		assertEquals("Student should be enrolled in four modules", 4, student.getEnrolledModules().size());
	}

	@Test(expected = ModuleNotFoundException.class)
	public void testModuleNotFoundException() throws ModuleNotFoundException, BusinessException {
		assertNotNull(student);
		
		List<String> listOfModules = new ArrayList<>();
		listOfModules.add(Module.WEB_DEVELOPMENT.getModuleName());
		listOfModules.add(Module.SPRING_FRAMEWORK.getModuleName());
		listOfModules.add(Module.AGILE_METHODOLOGY.getModuleName());
		listOfModules.add(Module.BUILD_AND_UNIT_TEST.getModuleName());
		listOfModules.add("Unknown module");
		UniversityService.enrollModules(student.getId(), listOfModules);
	}

	@Test
	public void testIfUniversityIsNotNull() {
		University university = new University();
		assertNotNull(university.getCurrentStudent());
	}
	
	
	
	
	@Test
	public void testAmountOfVocherLeftForNonAcademicStudent() {
		assertNotNull(student);
		student.setAcnAcademyStudent(false);
		assertFalse(student.isAcnAcademyStudent());
		List<String> moduleNames = new ArrayList<>();

		moduleNames.add(Module.BUILD_AND_UNIT_TEST.getModuleName());
		moduleNames.add(Module.AGILE_METHODOLOGY.getModuleName());
		moduleNames.add(Module.MOBILE_DEVELOPMENT.getModuleName());

		try {
			UniversityService.enrollModules(student.getId(), moduleNames);
		} catch (ModuleNotFoundException e) {
			e.printStackTrace();
		} catch (BusinessException e) {
			e.printStackTrace();
		}

		assertEquals("Must have no vouchers left", 0, student.getFreeModuleVouchers());
	}

}

