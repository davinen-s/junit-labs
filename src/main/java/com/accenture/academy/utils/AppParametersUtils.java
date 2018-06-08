package com.accenture.academy.utils;

/**
 * Utility class holding all the configuration variable used in the applications.
 * 
 * @author davinen.s.curoopen
 */
public class AppParametersUtils {
	
	/** The number of free module voucher allocated to a student upon registering. */
	public static final int FREE_MODULE_VOUCHER = 3;
	
	/** The last possible student id. */
	public static final long STUDENT_ID_CEILING = 999_999_999;

	/** The starting number for student id. AKA the minimum id possible. */
	public static final long STUDENT_ID_FLOOR = 100_000_000;
	
	/**
	 * Private constructor to prevent instantiation of this utility class.
	 */
	private AppParametersUtils( ) {
		super();
	}

}
