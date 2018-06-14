package com.accenture.academy.test.model;

import static org.junit.Assert.assertEquals;

import java.util.Map;
import java.util.TreeMap;

import org.junit.Before;
import org.junit.Test;

import com.accenture.academy.model.Student;
import com.accenture.academy.model.University;

public class UniversityTest {

	University university;
	Student student;

	@Before
	public void setup() {
		university = new University();
		student = new Student();
	}

	@Test
	public void testAssertCurrentStudentsEquals() {
		assertEquals(new TreeMap<Integer, Student>(), university.getCurrentStudent());
	}

	@Test
	public void testAssertaddedStudentsEquals() {
		Map<Integer, Student> map = new TreeMap<Integer, Student>();
		map.put(1, student);
		university.addStudent(map);
		assertEquals(map, university.getCurrentStudent());
	}

}
