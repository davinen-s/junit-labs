package com.accenture.academy.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.accenture.academy.exception.BusinessException;
import com.accenture.academy.exception.ModuleNotFoundException;
import com.accenture.academy.model.Student;
import com.accenture.academy.model.University;

public class UniversityServiceTest {
	
	private static Student s, s1;
	
	@BeforeClass
	public static void init() {
		UniversityService.university = new University();
		s=UniversityService.registerStudent("Ammaarah", "Joosub", LocalDate.of(1994, 04, 25), false);
		s1=UniversityService.registerStudent("Ammaarah", "Joosub", LocalDate.of(1994, 04, 25), true);
		
	}
	
	@Test
	public void testRegStudent() {
		
		assertNotNull(UniversityService.registerStudent("Ammaarah", "Joosub", LocalDate.of(1994, 04, 25), true));
		
	}
	
	@Test
	public void testEnrollModules() throws ModuleNotFoundException, BusinessException {
		List <String> list = new ArrayList<>();
		list.add("Spring Framework");
		UniversityService.enrollModules(s.getId(), list);
		assertEquals(2, s.getFreeModuleVouchers());
		UniversityService.enrollModules(s1.getId(), list);
	}
	
	@Test(expected=ModuleNotFoundException.class)
	public void testModuleNotFoundException() throws ModuleNotFoundException, BusinessException {
		List <String> list = new ArrayList<>();
		list.add("Node JS");
		UniversityService.enrollModules(s.getId(), list);
		
	}
	
	@Test(expected=BusinessException.class)
	public void testBusinessException() throws ModuleNotFoundException, BusinessException {
		List <String> list = new ArrayList<>();
		list.add("Spring Framework");
		list.add("Mobile Dev");
		list.add("Agile Methodology");
		list.add("Web Development");
		UniversityService.enrollModules(s.getId(), list);
		
	}

}
