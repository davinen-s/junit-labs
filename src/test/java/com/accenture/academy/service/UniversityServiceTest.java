package com.accenture.academy.service;

import static org.junit.Assert.*;

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
import com.accenture.academy.utils.Module;

public class UniversityServiceTest {

	List<String> moduleNames;
	University university;
	Student student;
	Map<Integer, Student> studentList;

	@Before
	public void setup() {
		university = new University();

		student = new Student();
		student.setId(1);

		moduleNames = new ArrayList<>();
		moduleNames.add(Module.AGILE_METHODOLOGY.getModuleName());
		moduleNames.add(Module.SPRING_FRAMEWORK.getModuleName());
		moduleNames.add(Module.WEB_DEVELOPMENT.getModuleName());

		studentList = new TreeMap<>();
		studentList.put(student.getId(), student);
		university.addStudent(studentList);
		UniversityService.university = university;

	}

	@Test
	public void testIfCurrentStudentIsNotNull() {
		University u = new University();
		assertNotNull(u.getCurrentStudent());
	}

	@Test
	public void testRegisteredStudent() {
		student = UniversityService.registerStudent("Mary", "Stones", LocalDate.parse("1995-09-03"), true);
		student.setFunny(false);

		assertNotNull(student);
		assertNotNull("Id cannot be null", student.getId());
		assertEquals("Mary", student.getFirstName());
		assertEquals("Stones", student.getLastName());
		assertEquals("DOB should be 1995/9/03", LocalDate.parse("1995-09-03"), student.getDob());
		assertTrue("By default a student must be an accenture academy student", student.isAcnAcademyStudent());
		assertSame("Age should be 22", 22, student.getAge());
		assertFalse("Mary is a serious girl, she is not funny. value must be false", student.isFunny());

	}

	@Test
	public void testEnrolledModulesIfStudentisAcnAcademyStudent() {

		student = UniversityService.registerStudent("Mary", "Stones", LocalDate.parse("1995-09-03"), true);
		studentList.put(student.getId(), student);

		try {
			UniversityService.enrollModules(student.getId(), moduleNames);
		} catch (ModuleNotFoundException e) {
			e.printStackTrace();
		} catch (BusinessException e) {
			e.printStackTrace();
		}

		assertEquals(3, student.getEnrolledModules().size());

	}

	@Test
	public void testEnrolledModulesIfStudentisNotAcnAcademyStudent() {

		student = UniversityService.registerStudent("Mary", "Stones", LocalDate.parse("1995-09-03"), false);
		studentList.put(student.getId(), student);

		try {
			UniversityService.enrollModules(student.getId(), moduleNames);
		} catch (ModuleNotFoundException e) {
			e.printStackTrace();
		} catch (BusinessException e) {
			e.printStackTrace();
		}

	}

	@Test(expected = BusinessException.class)
	public void shouldThrowBusinessExceptionWhenFreeVouchersIsZero() throws ModuleNotFoundException, BusinessException {

		student = UniversityService.registerStudent("Mary", "Stones", LocalDate.parse("1995-09-03"), false);
		student.setFreeModuleVouchers(0);
		studentList.put(student.getId(), student);

		UniversityService.enrollModules(student.getId(), moduleNames);

	}

	@Test(expected = ModuleNotFoundException.class)
	public void shouldThrowModuleNotFoundExceptionWhenModuleDoesNotExist()
			throws ModuleNotFoundException, BusinessException {

		student = UniversityService.registerStudent("Mary", "Stones", LocalDate.parse("1995-09-03"), false);
		moduleNames.add("Software Engineering");
		studentList.put(student.getId(), student);

		UniversityService.enrollModules(student.getId(), moduleNames);

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
