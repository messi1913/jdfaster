package com.jdfaster.jdfsample.services.test;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
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
import com.jdfaster.test.TestScenario;

@RequestMapping("/services/test/")
@RestController
@Transactional
//@Transactional(propagation = Propagation.SUPPORTS)
/**
We are using JPA (Hibernate 4) with Spring 4 managing the JTA transactions. To allow lazy initialization even when simply reading from the database without any transaction we added the "OpenEntityManager" pattern.
We know that there is a difference between having no transaction synchronization at all and SUPPORTS synchronization. But the JPA behaviour seems to be inconsistent somehow:
If there is no transaction synchronization active, we get a TransactionRequiredException from Springs SharedEntityManagerCreator when trying to persist something using JPA.
If there is only a SUPPORTS transaction synchronization active, we don't get this exception and the persist request is silently ignored. This however is exactly the situation to avoid in the SharedEntityManagerCreator when creating the above exception.
If there is an "OpenEntityManager" pattern active, there is no exception also and the persist request is silently ignored as well.
In my opinion there is no real difference between these situations and I would expect to see this exception in all cases or none?
 * @author ksm
 *
 */
public class TestServices {

	@RequestMapping(method = RequestMethod.POST, path = "/md/")
	public TestMdOut md() throws Exception {
		return new TestMd().md(new TestMdIn());
	}

	@RequestMapping(method = RequestMethod.POST, path = "/scen01/")
	@TestScenario
	public TestScen01Out scen01(@RequestBody TestScen01In input) throws Exception {
		return new TestScen01().scen01(input);
	}

}
