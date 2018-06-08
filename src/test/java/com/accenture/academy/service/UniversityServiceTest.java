package com.accenture.academy.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import com.accenture.academy.exception.BusinessException;
import com.accenture.academy.exception.ModuleNotFoundException;
import com.accenture.academy.model.Student;
import com.accenture.academy.model.University;
import com.accenture.academy.utils.AppParametersUtils;
import com.accenture.academy.utils.Module;

public class UniversityServiceTest {
	//need to create a university to avoid a null pointer exception
	@BeforeClass
	public static void setters() {
		UniversityService.university=new University();
	}
	@Test
	public void creatingUniversityServiceJustForCoverage() {
		UniversityService universityService= new UniversityService();
		universityService.getClass();
	}
	@Test
	public void creatingStudentAndCheckingValuesSet() {
		//creating variables to be used
		LocalDate dob = LocalDate.of(1994, 9, 26);
		String firstName=new String("firstName");
		String lastName =new String("lastName");
		LocalDate now = LocalDate.now();
		Period period = Period.between(dob, now);
		int age=period.getYears();
		
		List<String> moduleNames = new ArrayList<>();
		moduleNames.add(new String("Spring Framework"));
		moduleNames.add(new String("Mobile Dev"));
		moduleNames.add(new String("Agile Methodology"));
		
		List<Module> modules = new ArrayList<>();
		for (String moduleName : moduleNames) {
			try {
				modules.add(Module.getModuleByName(moduleName));
			} catch (ModuleNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//instantiate student
		Student student = UniversityService.registerStudent(firstName, lastName, dob, true);
		
		//check if student is null
		assertNotNull(student);
		
		//checking values if student is not null
		if(student!=null) {
				
			//check if set values are as set
			assertTrue("student id is higher than approved ceiling",student.getId()<=AppParametersUtils.STUDENT_ID_CEILING);
			assertTrue("student id is lower than approved floor",student.getId()>=AppParametersUtils.STUDENT_ID_FLOOR);
			assertEquals("student's first name didn't set properly",firstName, student.getFirstName());
			assertEquals("student's last name didn't set properly",lastName, student.getLastName());		
			assertTrue("student's age isn't as expected",age==student.getAge());
			
			//the check below is not required as age caters for it?
			assertEquals("student's age didn't set properly",dob, student.getDob());
			
			//check java default values
			assertNull("student's default attitude is as java default(null)",student.getAttitude());
			
			//check values set in default constructor
			assertTrue("student's funny property is not as set",student.isFunny());
			assertTrue("student has not been property set as an accenture academy student",student.isAcnAcademyStudent());
			assertTrue("student was not given the expected amount of gift vouchers",student.getFreeModuleVouchers()==AppParametersUtils.FREE_MODULE_VOUCHER);
			
			//check attribute instantiated by default
			assertEquals("the minor status was not set properly",age<18, student.isMinor());
			
			//set additional attributes
			student.setAttitude("cheeky");
			student.setFunny(false);
			student.setEnrolledModules(modules);
			
			//check these attributes
			assertTrue("the attitude was not set properly",student.getAttitude().equals("cheeky"));
			assertFalse("the funny status was not set properly",student.isFunny());
			assertTrue("student's modules were not registered successfully",student.getEnrolledModules().containsAll(modules));
			
		}
		
	}
	
	@Test
	public void enrollingStudentInUniversity() {
		//creating variables to be used
				LocalDate dob = LocalDate.of(1994, 9, 26);
				String firstName=new String("firstName");
				String lastName =new String("lastName");
				
		//instantiate student
		Student student = UniversityService.registerStudent(firstName, lastName, dob, true);
		
		//check if student is null
		assertNotNull("student was not instantiated successfully",student);
		if(student!=null) {
			//get current student list
			Map<Integer, Student> currentStudent = UniversityService.university.getCurrentStudent();
			//add to list
			currentStudent.put(student.getId(), student);
			//replace list
			UniversityService.university.addStudent(currentStudent);
			assertNotNull("student was not enrolled in the university successfully",UniversityService.university.getCurrentStudent().get(student.getId()));
		}
	}
	
	@Test
	public void enrollingStudentModulesWithSuccess() {
		//creating variables to be used
		
				LocalDate dob = LocalDate.of(LocalDate.now().getYear(), 9, 26);
				String firstName=new String("firstName");
				String lastName =new String("lastName");
				
				List<String> moduleNames = new ArrayList<>();
				moduleNames.add(new String("Spring Framework"));
				moduleNames.add(new String("Mobile Dev"));
				moduleNames.add(new String("Agile Methodology"));
				
				List<Module> modules = new ArrayList<>();
				for (String moduleName : moduleNames) {
					try {
						modules.add(Module.getModuleByName(moduleName));
					} catch (ModuleNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
		
		//instantiate student
		Student student = UniversityService.registerStudent(firstName, lastName, dob, true);
		
		//check if student is null
		assertNotNull("student was not instantiated successfully",student);
		if(student!=null) {
			assertTrue(student.isMinor());
			//get current student list
			Map<Integer, Student> currentStudent = UniversityService.university.getCurrentStudent();
			//add to list
			currentStudent.put(student.getId(), student);
			//replace list
			UniversityService.university.addStudent(currentStudent);
			assertNotNull("student was not enrolled in the university successfully",UniversityService.university.getCurrentStudent().get(student.getId()));
			//check for found
			if(UniversityService.university.getCurrentStudent().get(student.getId())!=null) {
				//add modules
				try {
					UniversityService.enrollModules(student.getId(), moduleNames);
				} catch (ModuleNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (BusinessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//check modules
				assertTrue("student's modules were not registered successfully",student.getEnrolledModules().containsAll(modules));
			}
		}
	}
	
	@Test(expected=ModuleNotFoundException.class)
	public void throwsModuleNotFoundException() throws ModuleNotFoundException, BusinessException {
		//creating variables to be used
				LocalDate dob = LocalDate.of(1994, 9, 26);
				String firstName=new String("firstName");
				String lastName =new String("lastName");
				
				List<String> moduleNames = new ArrayList<>();
				moduleNames.add(new String("Spring Framework"));
				moduleNames.add(new String("Mobile Dev"));
				
				//irrelevant module below
				moduleNames.add(new String("tadadadaaaaaa"));
				
				List<Module> modules = new ArrayList<>();
				for (String moduleName : moduleNames) {
					try {
						modules.add(Module.getModuleByName(moduleName));
					} catch (ModuleNotFoundException e) {
						//do nothing
					}
				}
		
		//instantiate student
		Student student = UniversityService.registerStudent(firstName, lastName, dob, true);
		
		//check if student is null
		assertNotNull("student was not instantiated successfully",student);
		if(student!=null) {
			//get current student list
			Map<Integer, Student> currentStudent = UniversityService.university.getCurrentStudent();
			//add to list
			currentStudent.put(student.getId(), student);
			//replace list
			UniversityService.university.addStudent(currentStudent);
			assertNotNull("student was not enrolled in the university successfully",UniversityService.university.getCurrentStudent().get(student.getId()));
			//check for found
			if(UniversityService.university.getCurrentStudent().get(student.getId())!=null) {
				//add modules
				UniversityService.enrollModules(student.getId(), moduleNames);
				//check modules
				assertTrue("student's modules were not registered successfully",student.getEnrolledModules().containsAll(modules));
			}
		}
	}
	
	@Test(expected=BusinessException.class)
	public void throwsBusinessException() throws ModuleNotFoundException, BusinessException {
		//creating variables to be used
				LocalDate dob = LocalDate.of(1994, 9, 26);
				String firstName=new String("firstName");
				String lastName =new String("lastName");
				
				List<String> moduleNames = new ArrayList<>();
				moduleNames.add(new String("Spring Framework"));
				moduleNames.add(new String("Mobile Dev"));
				moduleNames.add(new String("Agile Methodology"));
				moduleNames.add(new String("Web Development"));
				
				List<Module> modules = new ArrayList<>();
				for (String moduleName : moduleNames) {
					modules.add(Module.getModuleByName(moduleName));
				}
		
		//instantiate student
		Student student = UniversityService.registerStudent(firstName, lastName, dob, true);
		student.setAcnAcademyStudent(false);
		assertFalse(student.isAcnAcademyStudent());
		//check if student is null
		assertNotNull("student was not instantiated successfully",student);
		if(student!=null) {
			//get current student list
			Map<Integer, Student> currentStudent = UniversityService.university.getCurrentStudent();
			//add to list
			currentStudent.put(student.getId(), student);
			//replace list
			UniversityService.university.addStudent(currentStudent);
			assertNotNull("student was not enrolled in the university successfully",UniversityService.university.getCurrentStudent().get(student.getId()));
			//check for found
			if(UniversityService.university.getCurrentStudent().get(student.getId())!=null) {
				//add modules
				UniversityService.enrollModules(student.getId(), moduleNames);
				//check modules
				assertTrue("student's modules were not registered successfully",student.getEnrolledModules().containsAll(modules));
			}
		}
	}
	
}
