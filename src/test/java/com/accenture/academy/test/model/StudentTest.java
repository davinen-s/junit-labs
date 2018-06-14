package com.accenture.academy.test.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.accenture.academy.exception.ModuleNotFoundException;
import com.accenture.academy.model.Student;
import com.accenture.academy.utils.AppParametersUtils;
import com.accenture.academy.utils.Module;

public class StudentTest {

	Student student;

	@Before
	public void setup() {
		student = new Student();
	}

	@Test
	public void testAssertFirstNameEquals() {
		student.setFirstName("Peter");
		assertEquals("Peter", student.getFirstName());
	}

	@Test
	public void testAssertLastNameEquals() {
		student.setLastName("Parker");
		assertEquals("Parker", student.getLastName());
	}

	@Test
	public void testAssertAgeEquals() {
		student.setDob(LocalDate.parse("1994-10-14"));
		int age = Period.between(student.getDob(), LocalDate.now()).getYears();
		student.setAge(age);
		assertEquals(age, student.getAge(), 1);
	}

	@Test
	public void testAssertDoBEquals() {
		LocalDate dob = LocalDate.parse("1994-10-14");
		student.setDob(dob);
		assertEquals(dob, student.getDob());
	}

	@Test
	public void testAssertIsStudent() {
		assertTrue(student.isAcnAcademyStudent());
	}

	@Test
	public void testAssertIsFunny() {
		assertTrue(student.isFunny());
	}

	@Test
	public void testAssertIsNotStudent() {
		student.setAcnAcademyStudent(false);
		assertFalse(student.isAcnAcademyStudent());
	}

	@Test
	public void testAssertIsNotFunny() {
		student.setFunny(false);
		assertFalse(student.isFunny());
	}

	@Test
	public void testAssertVoucherEquals() {
		assertEquals(AppParametersUtils.FREE_MODULE_VOUCHER, student.getFreeModuleVouchers());
	}

	@Test
	public void testAssertIdEquals() {
		student.setId(1);
		assertEquals(1, student.getId(), 1);
	}

	@Test
	public void testAssertEnrolledModulesEqual() {
		assertEquals(new ArrayList<>(), student.getEnrolledModules());
	}

	@Test
	public void testAssertIsNotMinor() {
		student.setDob(LocalDate.parse("1994-10-14"));
		int age = Period.between(student.getDob(), LocalDate.now()).getYears();
		student.setAge(age);
		assertFalse(student.isMinor());
	}

	@Test
	public void testAssertIsMinor() {
		student.setDob(LocalDate.parse("2005-10-14"));
		int age = Period.between(student.getDob(), LocalDate.now()).getYears();
		student.setAge(age);
		assertTrue(student.isMinor());
	}

	@Test
	public void testAssertModuleAddedToStudent() {
		Module module = Module.SPRING_FRAMEWORK;
		student.addEnrolledModule(module);
		assertTrue(student.getEnrolledModules().contains(module));
	}

	@Test
	public void testAssertModuleEquals() {
		try {
			assertEquals(Module.SPRING_FRAMEWORK, Module.getModuleByName("Spring Framework"));
		} catch (ModuleNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testAssertEnrolledModulesEquals() {
		Module module = Module.SPRING_FRAMEWORK;
		List<Module> modules = new ArrayList<>();
		modules.add(module);
		student.setEnrolledModules(modules);
		assertEquals(modules, student.getEnrolledModules());
	
	}

	@Test
	public void testAssertAttitudeEquals() {
		student.setAttitude("serious");
		assertEquals("serious", student.getAttitude());
	}

	@Test
	public void testAssertFreeVoucherEquals() {
		student.setFreeModuleVouchers(AppParametersUtils.FREE_MODULE_VOUCHER);
		assertEquals(AppParametersUtils.FREE_MODULE_VOUCHER, student.getFreeModuleVouchers());
	}

}
