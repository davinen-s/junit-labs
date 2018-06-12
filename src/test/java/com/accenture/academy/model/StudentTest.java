package com.accenture.academy.model;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.Test;

import com.accenture.academy.service.UniversityService;
import com.accenture.academy.utils.AppParametersUtils;


public class StudentTest {

	
	@Test
	public void testAssertEquals() {
		Student student = new Student();
		student.setFirstName("Anna");
		student.setAttitude("Aggressive");
		student.setAge(24);
		student.setLastName("Smith");
		

		assertEquals("Anna", student.getFirstName());
		assertEquals("Aggressive", student.getAttitude());
		assertTrue(student.isFunny());
		assertTrue(student.isAcnAcademyStudent());
		assertNotNull(student.getEnrolledModules());
		assertEquals(AppParametersUtils.FREE_MODULE_VOUCHER, student.getFreeModuleVouchers());
	}
	

	

	

}
