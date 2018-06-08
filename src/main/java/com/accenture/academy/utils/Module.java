package com.accenture.academy.utils;

import java.util.EnumSet;

import com.accenture.academy.exception.ModuleNotFoundException;

/**
 * Enum representing the modules available at the university.
 * 
 * @author davinen.s.curoopen
 */
public enum Module {

	/** Spring framework. */
	SPRING_FRAMEWORK("Spring Framework"),

	/** Mobile Development. */
	MOBILE_DEVELOPMENT("Mobile Dev"),

	/** Agile Methodology. */
	AGILE_METHODOLOGY("Agile Methodology"),

	/** Web Development. */
	WEB_DEVELOPMENT("Web Development"),

	/** Build and Unit test. */
	BUILD_AND_UNIT_TEST("Build and Unit test");

	/** The module name. **/
	private String moduleName;

	/**
	 * Private constructor.
	 * 
	 * @param moduleName
	 *            the module's name.
	 */
	private Module(String moduleName) {
		this.moduleName = moduleName;
	}

	/**
	 * @return the moduleName
	 */
	public String getModuleName() {
		return moduleName;
	}

	/**
	 * find a module by name.
	 * 
	 * @param moduleName
	 *            the module's name
	 * @return
	 * @throws ModuleNotFoundException
	 *             if module is not found
	 */
	public static Module getModuleByName(String moduleName) throws ModuleNotFoundException {
		return EnumSet.allOf(Module.class).stream().filter(m -> m.getModuleName().equals(moduleName)).findAny()
				.orElseThrow(() -> new ModuleNotFoundException());
	}

}
