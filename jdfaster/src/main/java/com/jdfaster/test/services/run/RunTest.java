package com.jdfaster.test.services.run;

import javax.servlet.ServletContext;

import com.jdfaster.test.TestConfigs;

public class RunTest {
	public RunTestOut run(ServletContext context, RunTestIn input) throws Exception {
		TestConfigs configs = TestConfigs.getInstance(context.getContextPath());

		configs.setTargetUrl(input.getTargetUrl());
		configs.setConnectionTimeout(input.getConnectionTimeout());
		configs.setResponseTimeout(input.getResponseTimeout());
		configs.setNumberOfThreads(input.getNumberOfThreads());

		// TODO Run

		RunTestOut output = new RunTestOut();
		return output;
	}
}
