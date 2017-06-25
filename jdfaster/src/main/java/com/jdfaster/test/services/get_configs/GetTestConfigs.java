package com.jdfaster.test.services.get_configs;

import javax.servlet.ServletContext;

import com.jdfaster.test.TestConfigs;

public class GetTestConfigs {
	public GetTestConfigsOut getConfigs(ServletContext context, GetTestConfigsIn input) throws Exception {
		TestConfigs configs = TestConfigs.getInstance(context.getContextPath());

		GetTestConfigsOut output = new GetTestConfigsOut();
		output.setTargetUrl(configs.getTargetUrl());
		output.setConnectionTimeout(configs.getConnectionTimeout());
		output.setResponseTimeout(configs.getResponseTimeout());
		output.setNumberOfThreads(configs.getNumberOfThreads());
		return output;
	}
}
