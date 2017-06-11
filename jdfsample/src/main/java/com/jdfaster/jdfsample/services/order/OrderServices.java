package com.jdfaster.jdfsample.services.order;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jdfaster.jdfsample.services.order.create.CreateOrder;
import com.jdfaster.jdfsample.services.order.create.CreateOrderIn;
import com.jdfaster.jdfsample.services.order.create.CreateOrderOut;
import com.jdfaster.jdfsample.services.order.create_test.CreateOrderTest;
import com.jdfaster.jdfsample.services.order.create_test.CreateOrderTestIn;
import com.jdfaster.jdfsample.services.order.create_test.CreateOrderTestOut;
import com.jdfaster.jdfsample.services.order.pull_test.PullOrderTest;
import com.jdfaster.jdfsample.services.order.pull_test.PullOrderTestIn;
import com.jdfaster.jdfsample.services.order.pull_test.PullOrderTestOut;

@RestController
@RequestMapping("/services/order/")
@Transactional
public class OrderServices {

	// @RequestMapping(value = "/{id}", method = RequestMethod.GET)
	// public MesOrder get(@PathVariable("id") String id) throws Exception {
	// MesOrder order = new MesOrder();
	// return order;
	// }

	@RequestMapping(method = RequestMethod.POST, path = "/pull_test/")
	public PullOrderTestOut pullTest(@RequestBody PullOrderTestIn input) throws Exception {
		return new PullOrderTest().pullTest(input);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/create_test/")
	public CreateOrderTestOut createTest(@RequestBody CreateOrderTestIn input) throws Exception {
		return new CreateOrderTest().createTest(input);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/create/")
	public CreateOrderOut create(@RequestBody CreateOrderIn input) throws Exception {
		return new CreateOrder().create(input);
	}

}
