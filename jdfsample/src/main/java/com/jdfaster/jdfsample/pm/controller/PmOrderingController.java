package com.jdfaster.jdfsample.pm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jdfaster.jdfsample.pm.model.entity.ProductOrder;
import com.jdfaster.jdfsample.pm.model.repository.ProductOrderRepository;

@Transactional
@RestController
@RequestMapping("/pm/ordering/")
public class PmOrderingController {
	@Autowired
	private ProductOrderRepository oRep;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ProductOrder getOrder(@PathVariable("id") String id){
		System.out.println("getMethod : "+id);
		return oRep.readProductOrderById(id);
	}
	@RequestMapping(method = RequestMethod.POST)
	public ProductOrder create(@RequestBody ProductOrder productOrder) {
		System.out.println("createMethod : "+productOrder.toString());
		return oRep.save(productOrder);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public ProductOrder update(@RequestBody ProductOrder productOrder) {
		System.out.println("updateMethod : "+productOrder.toString());
		return oRep.save(productOrder);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") String id) {
		System.out.println("deleteMethod : "+id);
		oRep.delete(id);
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<ProductOrder> findAll() {
		return oRep.findAll();
	}

	@RequestMapping(value = "/findByName", method = RequestMethod.GET)
	public List<ProductOrder> findByName(@RequestParam("name") String name) {
		return oRep.findByNameLike("%" + name + "%");
	}
	
}
