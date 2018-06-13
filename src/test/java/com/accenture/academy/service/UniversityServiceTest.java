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
import com.accenture.academy.utils.AppParametersUtils;
import com.accenture.academy.utils.Module;

public class UniversityServiceTest {

	@Test
	public void testRegisterStudentFieldValues() {
		Student student = UniversityService.registerStudent("hemanta", "devi", LocalDate.of(1994, 3, 22), false);

		assertNotNull(student);
		assertNotNull(student.getId());
		assertEquals("hemanta", student.getFirstName());
		assertEquals("devi", student.getLastName());
		assertEquals("1994-03-22", student.getDob().toString());
		assertFalse(student.isAcnAcademyStudent());
		assertTrue(student.isFunny());
		assertNotNull(student.getEnrolledModules());
		assertEquals(AppParametersUtils.FREE_MODULE_VOUCHER, student.getFreeModuleVouchers());
	}

	@Test
	public void testEnrollModulesWhenStudentIsAcademicStudent() {
		Student student = UniversityService.registerStudent("hemanta", "devi", LocalDate.of(1994, 3, 22), true);

		List<String> moduleNames = new ArrayList<>();
		moduleNames.add(Module.AGILE_METHODOLOGY.getModuleName());
		moduleNames.add(Module.BUILD_AND_UNIT_TEST.getModuleName());

		try {
			UniversityService.enrollModules(student.getId(), moduleNames);
		} catch (ModuleNotFoundException e) {
			e.printStackTrace();
		} catch (BusinessException e) {
			e.printStackTrace();
		}

		assertEquals(2, student.getEnrolledModules().size());
	}

	@Test(expected = ModuleNotFoundException.class)
	public void shouldThrowModuleNotFoundExceptionIfModuleDoesNotExist()
			throws ModuleNotFoundException, BusinessException {
		Student student = UniversityService.registerStudent("hemanta", "devi", LocalDate.of(1994, 3, 22), true);

		List<String> moduleNames = new ArrayList<>();
		moduleNames.add(Module.AGILE_METHODOLOGY.getModuleName());
		moduleNames.add(Module.BUILD_AND_UNIT_TEST.getModuleName());
		moduleNames.add("PhP");

		UniversityService.enrollModules(student.getId(), moduleNames);
	}

	@Test(expected = BusinessException.class)
	public void shouldThrowBusinessExceptionWhenVoucherIsFinished() throws ModuleNotFoundException, BusinessException {

		Student student = UniversityService.registerStudent("hemanta", "devi", LocalDate.of(1994, 3, 22), false);

		List<String> moduleNames = new ArrayList<>();
		moduleNames.add(Module.AGILE_METHODOLOGY.getModuleName());
		moduleNames.add(Module.BUILD_AND_UNIT_TEST.getModuleName());
		moduleNames.add(Module.MOBILE_DEVELOPMENT.getModuleName());
		moduleNames.add(Module.SPRING_FRAMEWORK.getModuleName());

		UniversityService.enrollModules(student.getId(), moduleNames);
	}

	@Test
	public void testAmountOfVocherLeftForNonAcademicStudent() {
		Student student = UniversityService.registerStudent("hemanta", "devi", LocalDate.of(1994, 3, 22), false);

		List<String> moduleNames = new ArrayList<>();
		moduleNames.add(Module.AGILE_METHODOLOGY.getModuleName());

		try {
			UniversityService.enrollModules(student.getId(), moduleNames);
		} catch (ModuleNotFoundException e) {
			e.printStackTrace();
		} catch (BusinessException e) {
			e.printStackTrace();
		}

		assertEquals(2, student.getFreeModuleVouchers());
	}

}
