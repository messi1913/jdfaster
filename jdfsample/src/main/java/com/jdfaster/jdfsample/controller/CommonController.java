package com.jdfaster.jdfsample.controller;

import javax.persistence.EntityManager;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;

@Controller
public class CommonController implements ApplicationContextAware {
	@Autowired
	private EntityManager em;

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	private static ApplicationContext ac;

	public static ApplicationContext getApplicationContext() {
		return ac;
	}

	@Override
	public void setApplicationContext(ApplicationContext ac) throws BeansException {
		CommonController.ac = ac;
	}

}
