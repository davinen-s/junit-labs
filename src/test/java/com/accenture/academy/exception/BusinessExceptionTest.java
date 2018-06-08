package com.accenture.academy.exception;

import org.junit.Test;

public class BusinessExceptionTest {

	@Test(expected=BusinessException.class)
	public void justToGetCoverage() throws BusinessException {
		throw new BusinessException();
	}

}
