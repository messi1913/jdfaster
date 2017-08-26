package com.jdfaster.test.services.get_result;

import com.jdfaster.test.TestResult;
import com.jdfaster.test.TestUtils;

public class GetTestResult {
	public GetTestResultOut getResult(GetTestResultIn input) throws Exception {
		TestResult result = TestUtils.getClonedResult();

		GetTestResultOut output = new GetTestResultOut();
		output.setResult(result);
		return output;
	}
}
