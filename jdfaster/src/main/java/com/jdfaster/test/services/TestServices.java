package com.jdfaster.test.services;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jdfaster.test.services.get_configs.GetTestConfigs;
import com.jdfaster.test.services.get_configs.GetTestConfigsIn;
import com.jdfaster.test.services.get_configs.GetTestConfigsOut;
import com.jdfaster.test.services.get_list.GetTestList;
import com.jdfaster.test.services.get_list.GetTestListIn;
import com.jdfaster.test.services.get_list.GetTestListOut;
import com.jdfaster.test.services.get_result.GetTestResult;
import com.jdfaster.test.services.get_result.GetTestResultIn;
import com.jdfaster.test.services.get_result.GetTestResultOut;
import com.jdfaster.test.services.get_result_list.GetTestResultList;
import com.jdfaster.test.services.get_result_list.GetTestResultListIn;
import com.jdfaster.test.services.get_result_list.GetTestResultListOut;
import com.jdfaster.test.services.run.RunTest;
import com.jdfaster.test.services.run.RunTestIn;
import com.jdfaster.test.services.run.RunTestOut;
import com.jdfaster.test.services.save_result.SaveTestResult;
import com.jdfaster.test.services.save_result.SaveTestResultIn;
import com.jdfaster.test.services.save_result.SaveTestResultOut;
import com.jdfaster.test.services.set_configs.SetTestConfigs;
import com.jdfaster.test.services.set_configs.SetTestConfigsIn;
import com.jdfaster.test.services.set_configs.SetTestConfigsOut;

@RestController
@RequestMapping("/services/jdftest/")
public class TestServices {
	@Autowired
	private ServletContext context;

	 @RequestMapping(method = RequestMethod.GET, path = "/get_configs/")
	 public GetTestConfigsOut getConfigs() throws Exception {
	 return new GetTestConfigs().getConfigs(context, new GetTestConfigsIn());
	 }

	 @RequestMapping(method = RequestMethod.POST, path = "/set_configs/")
	 public SetTestConfigsOut setConfigs(@RequestBody SetTestConfigsIn input) throws Exception {
	 return new SetTestConfigs().setConfigs(context, input);
	 }

	@RequestMapping(method = RequestMethod.GET, path = "/get_list/")
	public GetTestListOut getList() throws Exception {
		return new GetTestList().getList(context, new GetTestListIn());
	}

	@RequestMapping(method = RequestMethod.POST, path = "/run/")
	public RunTestOut run(RunTestIn input) throws Exception {
		return new RunTest().run(context, input);
	}

	// @RequestMapping(method = RequestMethod.GET, path = "/get_result/")
	// public GetTestResultOut getResult(GetTestResultIn input) throws Exception
	// {
	// return new GetTestResult().getResult(input);
	// }
	//
	// @RequestMapping(method = RequestMethod.POST, path = "/save_result/")
	// public SaveTestResultOut saveResult(SaveTestResultIn input) throws
	// Exception {
	// return new SaveTestResult().saveResult(input);
	// }
	//
	// @RequestMapping(method = RequestMethod.GET, path = "/get_result_list/")
	// public GetTestResultListOut getResultList(GetTestResultListIn input)
	// throws Exception {
	// return new GetTestResultList().getResultList(input);
	// }

}
