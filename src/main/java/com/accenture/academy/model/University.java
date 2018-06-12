package com.accenture.academy.model;

import java.util.Map;
import java.util.TreeMap;

/**
 * Java Bean representing a University.
 * 
 * @author davinen.s.curoopen
 */
public class University {

	/** a map of current student. Key is the student id. */
	private Map<Integer, Student> currentStudent;
	
	/**
	 * Default constructor. Initialize a map of current student.
	 */
	public University( ) {
		currentStudent = new TreeMap<Integer, Student>();
	}

	/**
	 * @return the currentStudent
	 */
	public Map<Integer, Student> getCurrentStudent() {
		return currentStudent;
	}

	/**
	 * Add a student to the map of current students.
	 * 
	 * @param student
	 *            the currentStudent to set
	 */
	public void addStudent(Student student) {
		//Student student = new Student();
		this.currentStudent.put(student.getId(), student);
	}

}
