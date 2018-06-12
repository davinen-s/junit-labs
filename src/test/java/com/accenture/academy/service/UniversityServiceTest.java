package com.accenture.academy.service;

import static org.junit.Assert.*;

import java.awt.Dialog.ModalExclusionType;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.accenture.academy.exception.BusinessException;
import com.accenture.academy.exception.ModuleNotFoundException;
import com.accenture.academy.model.Student;
import com.accenture.academy.service.UniversityService;
import com.accenture.academy.utils.AppParametersUtils;
import com.accenture.academy.utils.Module;


public class UniversityServiceTest {


	
@Test
	public void universityServiceRegisterStudentTest() {
		LocalDate dob = LocalDate.of(1995, 9, 24);
		Student student = UniversityService.registerStudent("Hanna", "Dowlut",dob, false);
	
		assertNotNull(student.getId());
		assertEquals("Hanna", student.getFirstName());
		assertEquals("Dowlut", student.getLastName());
		assertEquals(dob, student.getDob());
		assertFalse(student.isAcnAcademyStudent());
		assertTrue(student.isFunny());
		assertEquals(AppParametersUtils.FREE_MODULE_VOUCHER, student.getFreeModuleVouchers());

	}

@Test
public void testEnrollModulesWhenStudentIsAcademicStudent() {
	LocalDate dob = LocalDate.of(1995, 9, 24);
	Student student = UniversityService.registerStudent("Hanna", "Dowlut",dob, true);

	List<String> moduleNames = new ArrayList<>();
	moduleNames.add(Module.AGILE_METHODOLOGY.getModuleName());
	moduleNames.add(Module.BUILD_AND_UNIT_TEST.getModuleName());
	moduleNames.add(Module.MOBILE_DEVELOPMENT.getModuleName());

	try {
		UniversityService.enrollModules(student.getId(), moduleNames);
	} catch (ModuleNotFoundException e) {
		e.printStackTrace();
	} catch (BusinessException e) {
		e.printStackTrace();
	}

	assertEquals(3, student.getEnrolledModules().size());
}

	

	

	

}
