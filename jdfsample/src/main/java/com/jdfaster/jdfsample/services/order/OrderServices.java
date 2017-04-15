package com.jdfaster.jdfsample.services.order;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jdfaster.jdfsample.services.order.create.CreateOrder;
import com.jdfaster.jdfsample.services.order.create.CreateOrderIn;
import com.jdfaster.jdfsample.services.order.create.CreateOrderOut;

@RestController
@RequestMapping("/services/order/")
public class OrderServices {

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public MesOrder get(@PathVariable("id") String id) throws Exception {
		MesOrder order = new MesOrder();
		return order;
	}

	@RequestMapping(method = RequestMethod.POST)
	public CreateOrderOut create(@RequestBody CreateOrderIn input) throws Exception {
		return new CreateOrder().create(input);
	}

	// @RequestMapping(method = RequestMethod.PUT)
	// public UpdateOrderOut update(@RequestBody UpdateOrderIn input)
	// throws Exception {
	// }

	// @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	// public void delete(@PathVariable("id") String id) throws Exception {
	// }

	// @RequestMapping(method = RequestMethod.GET)
	// public List<Order> getList() {
	// return oRep.findAll();
	// }
}
