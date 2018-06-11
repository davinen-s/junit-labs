package com.accenture.academy.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.accenture.academy.exception.BusinessException;
import com.accenture.academy.exception.ModuleNotFoundException;
import com.accenture.academy.model.Student;
import com.accenture.academy.model.University;
import com.accenture.academy.utils.AppParametersUtils;
import com.accenture.academy.utils.Module;

public class UniversityServiceTest {

	@Test
	public void testIfNewUniversityObjectCurrentStudentIsNotNull() {
		University u = new University();
		assertNotNull(u.getCurrentStudent());
	}

	@Test
	public void testRegisterStudentFieldValues() {
		Student s = UniversityService.registerStudent("yoven", "ayassamy", LocalDate.of(1993, 9, 9), false);

		assertNotNull(s);
		assertNotNull(s.getId());
		assertEquals("yoven", s.getFirstName());
		assertEquals("ayassamy", s.getLastName());
		assertEquals("1993-09-09", s.getDob().toString());
		assertFalse(s.isAcnAcademyStudent());
		assertTrue(s.isFunny());
		assertNotNull(s.getEnrolledModules());
		assertEquals(AppParametersUtils.FREE_MODULE_VOUCHER, s.getFreeModuleVouchers());
	}

	@Test
	public void testEnrollModulesWhenStudentIsAcademicStudent() {
		Student s = UniversityService.registerStudent("yoven", "ayassamy", LocalDate.of(1993, 9, 9), true);

		List<String> moduleNames = new ArrayList<>();
		moduleNames.add(Module.AGILE_METHODOLOGY.getModuleName());
		moduleNames.add(Module.BUILD_AND_UNIT_TEST.getModuleName());

		try {
			UniversityService.enrollModules(s.getId(), moduleNames);
		} catch (ModuleNotFoundException e) {
			e.printStackTrace();
		} catch (BusinessException e) {
			e.printStackTrace();
		}

		assertEquals(2, s.getEnrolledModules().size());
	}

	@Test(expected = ModuleNotFoundException.class)
	public void shouldThrowModuleNotFoundExceptionIfModuleDoesNotExist()
			throws ModuleNotFoundException, BusinessException {
		Student s = UniversityService.registerStudent("yoven", "ayassamy", LocalDate.of(1993, 9, 9), true);

		List<String> moduleNames = new ArrayList<>();
		moduleNames.add(Module.AGILE_METHODOLOGY.getModuleName());
		moduleNames.add(Module.BUILD_AND_UNIT_TEST.getModuleName());
		moduleNames.add("PhP");

		UniversityService.enrollModules(s.getId(), moduleNames);
	}

	@Test(expected = BusinessException.class)
	public void shouldThrowBusinessExceptionWhenVoucherIsFinished() throws ModuleNotFoundException, BusinessException {

		Student s = UniversityService.registerStudent("yoven", "ayassamy", LocalDate.of(1993, 9, 9), false);

		List<String> moduleNames = new ArrayList<>();
		moduleNames.add(Module.AGILE_METHODOLOGY.getModuleName());
		moduleNames.add(Module.BUILD_AND_UNIT_TEST.getModuleName());
		moduleNames.add(Module.MOBILE_DEVELOPMENT.getModuleName());
		moduleNames.add(Module.SPRING_FRAMEWORK.getModuleName());

		UniversityService.enrollModules(s.getId(), moduleNames);
	}
	
	@Test
	public void testAmountOfVocherLeftForNonAcademicStudent() {
		Student s = UniversityService.registerStudent("yoven", "ayassamy", LocalDate.of(1993, 9, 9), false);

		List<String> moduleNames = new ArrayList<>();
		moduleNames.add(Module.AGILE_METHODOLOGY.getModuleName());

		try {
			UniversityService.enrollModules(s.getId(), moduleNames);
		} catch (ModuleNotFoundException e) {
			e.printStackTrace();
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
		assertEquals(2, s.getFreeModuleVouchers());
	}
	
}
