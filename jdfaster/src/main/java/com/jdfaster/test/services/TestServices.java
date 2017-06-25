package com.jdfaster.test.services;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

@RestController
@RequestMapping("/services/jdftest/")
public class TestServices {

	@RequestMapping(method = RequestMethod.GET, path = "/get_list/")
	public GetTestListOut getList() throws Exception {
		return new GetTestList().getList(new GetTestListIn());
	}

	@RequestMapping(method = RequestMethod.GET, path = "/get_result/")
	public GetTestResultOut getResult(GetTestResultIn input) throws Exception {
		return new GetTestResult().getResult(input);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/get_result_list/")
	public GetTestResultListOut getResultList(GetTestResultListIn input) throws Exception {
		return new GetTestResultList().getResultList(input);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/run/")
	public RunTestOut run(RunTestIn input) throws Exception {
		return new RunTest().run(input);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/save_result/")
	public SaveTestResultOut saveResult(SaveTestResultIn input) throws Exception {
		return new SaveTestResult().saveResult(input);
	}

}
