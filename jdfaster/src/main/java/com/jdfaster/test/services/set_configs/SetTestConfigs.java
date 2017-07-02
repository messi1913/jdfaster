package com.jdfaster.test.services.set_configs;

import javax.servlet.ServletContext;

import com.jdfaster.test.TestConfigs;

public class SetTestConfigs {
	public SetTestConfigsOut setConfigs(ServletContext context, SetTestConfigsIn input) throws Exception {
		TestConfigs configs = TestConfigs.getInstance(context.getContextPath());

		configs.setTargetUrl(input.getTargetUrl());
		configs.setConnectionTimeout(input.getConnectionTimeout());
		configs.setResponseTimeout(input.getResponseTimeout());
		configs.setNumberOfThreads(input.getNumberOfThreads());

		SetTestConfigsOut output = new SetTestConfigsOut();
		output.setResult("Completed");
		return output;
	}
}
