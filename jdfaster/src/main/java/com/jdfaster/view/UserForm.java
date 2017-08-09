package com.jdfaster.view;

import com.jdfaster.test.services.run.RunTestIn;
import com.jdfaster.view.data.ScTestInfo;
import com.vaadin.data.Binder;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class UserForm {
	
	TextField txtUrl, txtResTimeout, txtThrNo, txtConTimeout;
	ComboBox<String> scenarios;
	Button btnRun, btnStop, btnClear;
	Binder<ScTestInfo> binder;
	
	
	public UserForm() {
		VerticalLayout root = new VerticalLayout();
		root.addComponents(createContent(), createFooter());
		
		binder = new Binder<ScTestInfo>(ScTestInfo.class);
		
	}
	
	public void lazyInit(RunTestIn testDVO) {
		ScTestInfo testInfo = new ScTestInfo();
		binder.bindInstanceFields(this);
		
		binder.forField(txtUrl).asRequired("Required URL").bind(ScTestInfo::getUrl, ScTestInfo::setUrl);
		binder.forField(txtThrNo).asRequired("Required Thread no").bind(ScTestInfo::getThreadSize, ScTestInfo::setThreadSize);
		binder.forField(txtResTimeout).asRequired("Required Response Timeout").bind(ScTestInfo::getResTitmeOut, ScTestInfo::setResTitmeOut);
		binder.forField(txtConTimeout).asRequired("Required Connection Timeout").bind(ScTestInfo::getConTimeOut, ScTestInfo::setConTimeOut);
		
		binder.setBean(testInfo);
	}
	
	private Component createContent() {
		HorizontalLayout content = new HorizontalLayout();
		content.setSpacing(true);
		content.setMargin(new MarginInfo(true, true, false, true));
		
		FormLayout form = new FormLayout();
		form.setSizeUndefined();
		form.addComponent(txtUrl = new TextField("URL"));
		form.addComponent(txtThrNo = new TextField("Thread No"));
		form.addComponent(txtConTimeout = new TextField("Connection Timeout"));
		form.addComponent(txtResTimeout = new TextField("Response Timeout"));
		form.addComponent(scenarios = new ComboBox<String>("Scenario"));
		
		scenarios.setItems("Scenario1", "SCenario2");
		
		content.addComponent(form);
		
		return content;
	}
	
	private Component createFooter() {
		HorizontalLayout footer = new HorizontalLayout();
		footer.setSizeUndefined();
		footer.setSpacing(true);
		// 박스 형태의 배경 색상을 갖도록 지정.
		footer.setStyleName(ValoTheme.WINDOW_BOTTOM_TOOLBAR);
		footer.setWidth(100, Unit.PERCENTAGE);
		
		btnRun = new Button("Run");
		btnRun.addStyleName(ValoTheme.BUTTON_PRIMARY);
		btnRun.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub
				
			}
		});
		btnStop = new Button("Stop");
		btnStop.addStyleName(ValoTheme.BUTTON_DANGER);
		btnStop.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub
				
			}
		});
		btnClear = new Button("Run");
		btnClear.addStyleName(ValoTheme.BUTTON_PRIMARY);
		btnClear.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub
				
			}
		});
		
		footer.addComponents(btnRun, btnStop, btnClear);
//		footer.setExpandRatio(btnRun, 3);
//		footer.setComponentAlignment(btnRun, Alignment.MIDDLE_RIGHT);
//		footer.setComponentAlignment(btnStop, Alignment.MIDDLE_RIGHT);
//		footer.setComponentAlignment(btnClear, Alignment.MIDDLE_RIGHT);
		return footer;
	}
	
	
	
}
