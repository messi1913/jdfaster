package com.jdfaster.test.services.get_result;

import com.jdfaster.test.Test;
import com.jdfaster.test.TestResult;
import com.jdfaster.test.TestUtils;

public class GetTestResult {
	public GetTestResultOut getResult(GetTestResultIn input) throws Exception {
		Test test = TestUtils.getCurrentTest();

		TestResult result = new TestResult();

		TestResult data = test.getResult();

		synchronized (data) {
			result.setName(data.getName());
			result.setMinRunTime(data.getMinRunTime());
			result.setMaxRunTime(data.getMaxRunTime());
			result.setAvgRunTime(data.getAvgRunTime());
			result.setTotalRunSize(data.getTotalRunSize());
			result.setTotalRunTime(data.getTotalRunTime());
		}

		for (TestResult item : data.getList()) {
			synchronized (item) {
				TestResult svcResult = new TestResult();
				svcResult.setName(item.getName());
				svcResult.setMinRunTime(item.getMinRunTime());
				svcResult.setMaxRunTime(item.getMaxRunTime());
				svcResult.setAvgRunTime(item.getAvgRunTime());
				svcResult.setTotalRunSize(item.getTotalRunSize());
				svcResult.setTotalRunTime(item.getTotalRunTime());
				result.results.put(svcResult.getName(), svcResult);
			}
		}

		GetTestResultOut output = new GetTestResultOut();
		output.setResult(result);
		return output;
	}
}
