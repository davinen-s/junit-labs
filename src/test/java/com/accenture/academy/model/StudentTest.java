package com.accenture.academy.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.accenture.academy.utils.AppParametersUtils;

public class StudentTest {

	@Test
	public void testNewStudentObjectDefaultFields() {
		Student s = new Student();

		assertTrue(s.isAcnAcademyStudent());
		assertTrue(s.isFunny());
		assertNotNull(s.getEnrolledModules());
		assertEquals(AppParametersUtils.FREE_MODULE_VOUCHER, s.getFreeModuleVouchers());
	}

}
