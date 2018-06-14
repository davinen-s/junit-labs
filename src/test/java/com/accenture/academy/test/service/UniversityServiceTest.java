package com.accenture.academy.test.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.Period;
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

	Student student;
	University university;
	LocalDate dob = LocalDate.parse("1994-10-14");
	List<String> moduleNames;
	Map<Integer, Student> map;

	@Before
	public void setup() {
		university = new University();
		student = new Student();
		student.setFirstName("Peter");
		student.setLastName("Parker");
		student.setDob(dob);
		student.setAge(Period.between(dob, LocalDate.now()).getYears());
		student.setAcnAcademyStudent(true);

		moduleNames = new ArrayList<>();
		moduleNames.add(Module.SPRING_FRAMEWORK.getModuleName());
		moduleNames.add(Module.WEB_DEVELOPMENT.getModuleName());
		
		student.setId(1);
		map = new TreeMap<>();
		map.put(student.getId(), student);
		university.addStudent(map);
		UniversityService.university = university;

	}

	@Test
	public void testAssertStudentRegistered() {

		Student stud = UniversityService.registerStudent("alpha", "beta", dob, true);

		assertNotNull(stud);
		assertNotNull(stud.getId());
		assertEquals("alpha", stud.getFirstName());
		assertEquals("beta", stud.getLastName());
		assertEquals(dob, student.getDob());
		assertTrue(stud.isAcnAcademyStudent());
		assertEquals(Period.between(dob, LocalDate.now()).getYears(), stud.getAge(), 1);

	}

	@Test
	public void testAssertEnrolledModules_AccnStud() throws ModuleNotFoundException, BusinessException {

		Student stud = UniversityService.registerStudent("alpha", "beta", dob, true);

		map.put(stud.getId(), stud);

		UniversityService.enrollModules(stud.getId(), moduleNames);
	}
	

	@Test
	public void testAssertEnrolledModules_NotAccnStud() throws ModuleNotFoundException, BusinessException {

		Student stud = UniversityService.registerStudent("alpha", "beta", dob, false);

		map.put(stud.getId(), stud);

		UniversityService.enrollModules(stud.getId(), moduleNames);
	
	}

	@Test(expected=BusinessException.class)
	public void testAssertEnrolledModules_NotAccnStud_NoVoucher() throws ModuleNotFoundException, BusinessException {

		Student stud = UniversityService.registerStudent("alpha", "beta", dob, false);

		stud.setFreeModuleVouchers(0);

		map.put(stud.getId(), stud);

		UniversityService.enrollModules(stud.getId(), moduleNames);

	}

	@Test(expected=ModuleNotFoundException.class)
	public void testAssertEnrolledModules_ModuleNotFound() throws ModuleNotFoundException, BusinessException {

		Student stud = UniversityService.registerStudent("alpha", "beta", dob, true);

		map.put(stud.getId(), stud);

		moduleNames.add("Software Engineering");

		UniversityService.enrollModules(stud.getId(), moduleNames);

	}

	@Test
	public void testAssertUniversityServiceInstantiated() {

		assertNotNull(new UniversityService());

	}

	@Test
	public void testAssertBusinessExceptionInstantiated() {

		assertNotNull(new BusinessException());

	}
	

}
