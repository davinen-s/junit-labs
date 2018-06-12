package com.accenture.academy.exception;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.accenture.academy.model.Student;
import com.accenture.academy.service.UniversityService;
import com.accenture.academy.utils.Module;

public class ExceptionTest {
	
	@Test(expected = ModuleNotFoundException.class)
	public void testModuleNotFoundException() throws ModuleNotFoundException, BusinessException{
		LocalDate dob = LocalDate.of(1995, 9, 24);
		Student student = UniversityService.registerStudent("Hanna", "Dowlut", dob, false);
		
		List<String> modules = new ArrayList<>();
		modules.add(Module.AGILE_METHODOLOGY.getModuleName());
		modules.add(Module.BUILD_AND_UNIT_TEST.getModuleName());
		modules.add("MATHS");

		UniversityService.enrollModules(student.getId(), modules);
	
	}
	
	@Test(expected = BusinessException.class)
	public void testBusinessException() throws BusinessException,ModuleNotFoundException{
		LocalDate dob = LocalDate.of(1995, 9, 24);
		Student student = UniversityService.registerStudent("Hanna", "Dowlut", dob, false);
		
		List<String> modules = new ArrayList<>();
		modules.add(Module.AGILE_METHODOLOGY.getModuleName());
		modules.add(Module.BUILD_AND_UNIT_TEST.getModuleName());
		modules.add(Module.MOBILE_DEVELOPMENT.getModuleName());
		modules.add(Module.SPRING_FRAMEWORK.getModuleName());

		UniversityService.enrollModules(student.getId(), modules);
	
	}

}
