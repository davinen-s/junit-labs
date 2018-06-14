package com.accenture.academy.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.accenture.academy.utils.AppParametersUtils;
import com.accenture.academy.utils.Module;

/**
 * Java Bean representing a student.
 * 
 * @author davinen.s.curoopen
 */
public class Student {

	private int id;
	private String firstName;
	private String lastName;
	private Integer age;

	/** date of birth. */
	private LocalDate dob;
	private String attitude;
	private boolean funny;
	private boolean acnAcademyStudent;
	private Integer moduleVouchers;
	private List<Module> enrolledModules;

	/**
	 * Default constructor. Initializing acnAcademyStudent, freeModuleVouchers, enrolledModules and
	 * funny field.
	 */
	public Student() {
		super();
		this.acnAcademyStudent = true;
		this.funny = true;
		this.moduleVouchers = AppParametersUtils.FREE_MODULE_VOUCHER;
		this.enrolledModules = new ArrayList<>();
	}

	/**
	 * 
	 * @return
	 */
	public int getFreeModuleVouchers() {
		return moduleVouchers;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param studentId
	 *            the id to set
	 */
	public void setId(int studentId) {
		this.id = studentId;
	}

	/**
	 * 
	 * @param freeModuleVouchers
	 */
	public void setFreeModuleVouchers(int freeModuleVouchers) {
		this.moduleVouchers = freeModuleVouchers;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the age
	 */
	public Integer getAge() {
		return age;
	}

	/**
	 * @param age
	 *            the age to set
	 */
	public void setAge(Integer age) {
		this.age = age;
	}

	/**
	 * @return the dob
	 */
	public LocalDate getDob() {
		return dob;
	}

	/**
	 * @param dob
	 *            the dob to set
	 */
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getAttitude() {
		return attitude;
	}

	public void setAttitude(String attitude) {
		this.attitude = attitude;
	}

	/**
	 * @return the funny
	 */
	public boolean isFunny() {
		return funny;
	}

	/**
	 * @param funny
	 *            the funny to set
	 */
	public void setFunny(boolean funny) {
		this.funny = funny;
	}

	/**
	 * @return the AcnAcademyStudent
	 */
	public boolean isAcnAcademyStudent() {
		return acnAcademyStudent;
	}

	/**
	 * @param acnAcademyStudent
	 *            the AcnAcademyStudent to set
	 */
	public void setAcnAcademyStudent(boolean acnAcademyStudent) {
		this.acnAcademyStudent = acnAcademyStudent;
	}

	/**
	 * @return the enrolledModules
	 */
	public List<Module> getEnrolledModules() {
		return enrolledModules;
	}

	/**
	 * @param enrolledModules
	 *            the enrolledModules to set
	 */
	public void setEnrolledModules(List<Module> enrolledModules) {
		this.enrolledModules = enrolledModules;
	}

	/**
	 * Helper method to know whether the student is under-age.
	 * 
	 * @return true if the student is a minor.
	 */
	public boolean isMinor() {
		return this.age < 18;
	}

	/**
	 * Helper method to add a module to the enrolled modules list.
	 * 
	 * @param module
	 *            the module to be added.
	 */
	public void addEnrolledModule(Module module) {
		this.enrolledModules.add(module);
	}

}
