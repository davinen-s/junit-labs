package com.accenture.academy.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Before;
import org.junit.Test;

import com.accenture.academy.exception.BusinessException;
import com.accenture.academy.exception.ModuleNotFoundException;
import com.accenture.academy.model.Student;
import com.accenture.academy.model.University;
import com.accenture.academy.service.UniversityService;
import com.accenture.academy.utils.Module;

public class UniversityServiceTest {

	List<String> moduleNames;
	University university;
	Student student;
	Map<Integer,Student> map;

	@Before
	public void setup() {
		university = new University();
		
		student = new Student();
		student.setId(1);
		
		moduleNames = new ArrayList<>();
		moduleNames.add(Module.AGILE_METHODOLOGY.getModuleName());
		moduleNames.add(Module.BUILD_AND_UNIT_TEST.getModuleName());
		moduleNames.add(Module.MOBILE_DEVELOPMENT.getModuleName());
		
		map = new TreeMap<>();
		map.put(student.getId(), student);
		university.addStudent(map);
		UniversityService.university = university;

	}

	@Test
	public void testIfCurrentStudentIsNotNull() {
		University u = new University();
		assertNotNull(u.getCurrentStudent());
	}

	@Test
	public void testRegisteredStudent() {
		student = UniversityService.registerStudent("Tom", "Lambart", LocalDate.parse("1996-01-23"), true);
		student.setFunny(false);

		assertNotNull(student);
		assertNotNull("ID must not be null", student.getId());
		assertEquals("Tom", student.getFirstName());
		assertEquals("Lambart", student.getLastName());
		assertEquals("DOB should be 1996/1/23", LocalDate.parse("1996-01-23"), student.getDob());
		assertTrue("By default a student must be an accenture academy student", student.isAcnAcademyStudent());
		assertSame("Age should be 22", 22, student.getAge());
		assertFalse("Anna is a serious girl, she is not funny. value must be false", student.isFunny());

	}

	@Test
	public void testEnrolledModulesIfStudentisAcnAcademyStudent() {

		student = UniversityService.registerStudent("Tom", "Lambart", LocalDate.parse("1996-01-23"), true);
		map.put(student.getId(), student);

		try {
			UniversityService.enrollModules(student.getId(), moduleNames);
		} catch (ModuleNotFoundException | BusinessException e) {
			e.printStackTrace();
		}

		assertEquals(3, student.getEnrolledModules().size());

	}

	@Test
	public void testEnrolledModulesIfStudentisNotAcnAcademyStudent() {

		student = UniversityService.registerStudent("Tom", "Lambart", LocalDate.parse("1996-01-23"), false);
		map.put(student.getId(), student);

		try {
			UniversityService.enrollModules(student.getId(), moduleNames);
		} catch (ModuleNotFoundException | BusinessException e) {
			e.printStackTrace();
		}

	}
	
	@Test
	public void shouldThrowBusinessExceptionWhenFreeVouchersIsZero() {

		student = UniversityService.registerStudent("Tom", "Lambart", LocalDate.parse("1996-01-23"), false);
		student.setFreeModuleVouchers(0);
		map.put(student.getId(), student);

		try {
			UniversityService.enrollModules(student.getId(), moduleNames);
		} catch (ModuleNotFoundException | BusinessException e) {
			e.getMessage();
		}

	}
	
	@Test
	public void shouldThrowModuleNotFoundExceptionWhenModuleDoesNotExist() {

		student = UniversityService.registerStudent("Tom", "Lambart", LocalDate.parse("1996-01-23"), false);
		moduleNames.add("Citizenship Education");
		map.put(student.getId(), student);

		try {
			UniversityService.enrollModules(student.getId(), moduleNames);
		} catch (ModuleNotFoundException | BusinessException e) {
			e.getMessage();
		}

	}
	
	@Test
	public void testIfUniversityServiceIsNotNull() {
		assertNotNull(new UniversityService());
	}
	
	@Test
	public void testIfBuinessExceptionIsNotNull() {
		assertNotNull(new BusinessException());
	}

}
