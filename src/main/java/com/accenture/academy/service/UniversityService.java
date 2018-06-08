package com.accenture.academy.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.accenture.academy.exception.BusinessException;
import com.accenture.academy.exception.ModuleNotFoundException;
import com.accenture.academy.model.Student;
import com.accenture.academy.model.University;
import com.accenture.academy.utils.AppParametersUtils;
import com.accenture.academy.utils.Module;

/**
 * Service providing university facilities and administrative services.
 * 
 * @author davinen.s.curoopen
 */
public class UniversityService {

	/**
	 * creating a static variable for an object is a VERY bad practice. This a an
	 * exception to avoid increasing the complexity of the lab by add a Repository
	 * layer.
	 */

	public static University university;

	/**
	 * Register a student in the university database.
	 * 
	 * @param firstName
	 *            student's first name
	 * @param lastName
	 *            student's last name
	 * @param dob
	 *            student date of birth
	 * @param acnAcademyStudent
	 *            flag indicating whether student is an acn academy student.
	 * @return a registered student
	 */
	public static Student registerStudent(String firstName, String lastName, LocalDate dob, Boolean acnAcademyStudent) {

		Student student = new Student();
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setDob(dob);
		student.setAcnAcademyStudent(acnAcademyStudent);

		// calculating age of the student.
		LocalDate now = LocalDate.of(2018, 5, 8);
		Period period = Period.between(dob, now);

		student.setAge(period.getYears());

		int studentId;
		// generating a student id.
		do {
			studentId = (int) ThreadLocalRandom.current().nextLong(AppParametersUtils.STUDENT_ID_FLOOR,
					AppParametersUtils.STUDENT_ID_CEILING + 1);
		} while (university.getCurrentStudent().containsKey(studentId));

		student.setId(studentId);
		return student;

	}

	/**
	 * Enroll modules for the specified student.
	 * 
	 * @param studentId
	 *            the unique identifier of the student
	 * @param moduleNames
	 *            modules to be registered for the student
	 * @throws ModuleNotFoundException
	 * @throws BusinessException 
	 */
	public static void enrollModules(Integer studentId, List<String> moduleNames) throws ModuleNotFoundException, BusinessException {
		
		Student student = university.getCurrentStudent().get(studentId);
		
		for (String moduleName : moduleNames) {
			Module module = Module.getModuleByName(moduleName);
			
			if(student.isAcnAcademyStudent()) {
				//module is free
				student.addEnrolledModule(module);
			} else {
				int freeVouchers = student.getFreeModuleVouchers();
				
				//we use the free vouchers.
				if(freeVouchers > 0) {
					//decrementing voucher
					student.setFreeModuleVouchers(freeVouchers -1);
				} else {
					throw new BusinessException("No module vouchers found. Please purchase more vouchers to enroll additional modules.");
				}
			}
		}
	}

}
