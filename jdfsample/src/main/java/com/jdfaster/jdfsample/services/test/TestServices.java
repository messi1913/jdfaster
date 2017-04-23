package com.jdfaster.jdfsample.services.test;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jdfaster.jdfsample.services.test.md.TestMd;
import com.jdfaster.jdfsample.services.test.md.TestMdIn;
import com.jdfaster.jdfsample.services.test.md.TestMdOut;
import com.jdfaster.jdfsample.services.test.scen01.TestScen01;
import com.jdfaster.jdfsample.services.test.scen01.TestScen01In;
import com.jdfaster.jdfsample.services.test.scen01.TestScen01Out;

@RestController
@RequestMapping("/services/test/")
public class TestServices {

	@RequestMapping(method = RequestMethod.POST, path = "/md/")
	public TestMdOut md(@RequestBody TestMdIn input) throws Exception {
		return new TestMd().md(input);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/scen01/")
	public TestScen01Out scen01(@RequestBody TestScen01In input) throws Exception {
		return new TestScen01().scen01(input);
	}

}
