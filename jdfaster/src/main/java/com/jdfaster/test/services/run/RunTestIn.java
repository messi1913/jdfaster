package com.jdfaster.test.services.run;

import com.jdfaster.test.services.get_list.GetTestListOut.TestInfo;
import com.jdfaster.test.services.set_configs.SetTestConfigsIn;

public class RunTestIn extends SetTestConfigsIn {
	
	public RunTestIn() {
		
	}
	
	public RunTestIn(TestInfo testInfo) {
		super();
		this.testInfo = testInfo;
	}

	private TestInfo testInfo;

	public TestInfo getTestInfo() {
		return testInfo;
	}

	public void setTestInfo(TestInfo testInfo) {
		this.testInfo = testInfo;
	}
}
