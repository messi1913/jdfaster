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
		TestConfigs configs = TestConfigs.getInstance(context.getContextPath());		
		TestInfo testInfo = input.getTestInfo();

		// TODO Run
		// 로드 컨트롤 호출??
		LoadController lc = new LoadController();
		List<Target> targets = new ArrayList<Target>();
		Target target = new Target();
		target.setName(testInfo.getClassName()+"."+testInfo.getMethodName());
		targets.add(target);
		lc.start(Math.toIntExact(configs.getNumberOfThreads()), targets);
		
		// 
		RunTestOut output = new RunTestOut();
		return output;
	}
}
