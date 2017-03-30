package com.jdfaster.jdfsample.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
//@RequestMapping("/service")
@RequestMapping("/services/user/")
public class URLRoleController {
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public void getUser(@PathVariable("id") String id){
		System.out.println("테스트 되나??? : "+id);	
	}
}
