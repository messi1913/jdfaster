package com.jdfaster.test.services.run;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import com.jdfaster.service.LoadController;
import com.jdfaster.service.Target;
import com.jdfaster.test.TestConfigs;
import com.jdfaster.test.services.get_list.GetTestListOut.TestInfo;

public class RunTest {
	public RunTestOut run(ServletContext context, RunTestIn input) throws Exception {
		// 시나리오 정
		TestInfo testInfo = input.getTestInfo();
		
		TestConfigs configs = TestConfigs.getInstance(context.getContextPath());
		configs.setTargetUrl(input.getTargetUrl());
		configs.setConnectionTimeout(input.getConnectionTimeout());
		configs.setResponseTimeout(input.getResponseTimeout());
		configs.setNumberOfThreads(input.getNumberOfThreads());


		// TODO Run
		// 로드 컨트롤 호출??
		LoadController lc = new LoadController();
		List<Target> targets = new ArrayList<Target>();
		Target target = new Target();
		target.setName(testInfo.getClassName() + "." + testInfo.getMethodName());
		target.setArgs(new ArrayList<Object>());
		target.setLoadFactor(1);
		targets.add(target);
		lc.start(Math.toIntExact(configs.getNumberOfThreads()), targets);

		//
		RunTestOut output = new RunTestOut();
		return output;
	}
}
