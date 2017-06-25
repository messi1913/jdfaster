package com.jdfaster.test.services.run;

import com.jdfaster.test.services.get_list.GetTestListOut.TestInfo;
import com.jdfaster.test.services.set_configs.SetTestConfigsIn;

public class RunTestIn extends SetTestConfigsIn {
	private TestInfo test;

	public TestInfo getTest() {
		return test;
	}

	public void setTest(TestInfo test) {
		this.test = test;
	}
}
