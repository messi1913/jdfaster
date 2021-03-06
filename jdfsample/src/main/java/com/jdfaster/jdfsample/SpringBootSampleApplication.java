package com.jdfaster.jdfsample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.jdfaster.jdfsample.config.BeanNameGenerator;

@ComponentScan(value = { "com.jdfaster.service", "com.jdfaster.test.services", "com.jdfaster.jdfsample.services", "com.jdfaster.view.vaadin", "com.vaadin",
		"com.jdfaster.jdfsample.aop", "com.jdfaster.jdfsample.config","com.jdfaster.jdfsample.controller", "com.jdfaster.jdfsample.utils" }, nameGenerator = BeanNameGenerator.class)
@SpringBootApplication
public class SpringBootSampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSampleApplication.class, args);
	}
}
