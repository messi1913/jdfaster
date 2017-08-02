package com.jdfaster.view.menu;

import com.vaadin.navigator.View;
import com.vaadin.server.Resource;

public class Navi {
	private String fragement;
	private String viewName;
	private Class<? extends View> viewClass;
	private Resource icon;
	
	
	
	public Navi(String fragement, String viewName, Class<? extends View> viewClass, Resource icon) {
		super();
		this.fragement = fragement;
		this.viewName = viewName;
		this.viewClass = viewClass;
		this.icon = icon;
	}
	public String getFragement() {
		return fragement;
	}
	public void setFragement(String fragement) {
		this.fragement = fragement;
	}
	public String getViewName() {
		return viewName;
	}
	public void setViewName(String viewName) {
		this.viewName = viewName;
	}
	public Class<? extends View> getViewClass() {
		return viewClass;
	}
	public void setViewClass(Class<? extends View> viewClass) {
		this.viewClass = viewClass;
	}
	public Resource getIcon() {
		return icon;
	}
	public void setIcon(Resource icon) {
		this.icon = icon;
	}
	
	
	
}
