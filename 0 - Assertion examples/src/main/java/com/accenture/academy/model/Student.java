package com.accenture.academy.model;

import java.time.LocalDate;

/**
 * Java Bean representing a student.
 * 
 * @author davinen.s.curoopen
 */
public class Student {
	
	private String firstName;
	private String lastName;
	private Integer age;
	private LocalDate dob;
	private String attitude;
	private boolean funny;
	private boolean acnAcademyStudent = true;
	/**
	 * Default constructor.
	 */
	public Student() {
		super();
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
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
	 * @param lastName the lastName to set
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
	 * @param age the age to set
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
	 * @param dob the dob to set
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
	 * @param funny the funny to set
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
	 * @param AcnAcademyStudent the AcnAcademyStudent to set
	 */
	public void setAcnAcademyStudent(boolean AcnAcademyStudent) {
		this.acnAcademyStudent = AcnAcademyStudent;
	}

	/**
	 * Helper method to know whether the student is under-age.
	 * 
	 * @return true if the student is a minor.
	 */
	public boolean isMinor() {
		return this.age < 18;
	}
	
}
