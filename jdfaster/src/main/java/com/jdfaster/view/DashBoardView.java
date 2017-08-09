package com.jdfaster.view;


import com.jdfaster.test.services.run.RunTestIn;
import com.jdfaster.view.data.ScTestInfo;
import com.vaadin.data.Binder;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings("serial")
public class DashBoardView extends VerticalLayout implements View {

	public static final String VIEW_NAME = "";
	TextField txtUrl, txtResTimeout, txtThrNo, txtConTimeout;
	ComboBox<String> scenarios;
	Button btnRun, btnStop, btnClear;
	Binder<ScTestInfo> binder;
	Label lUrl, lResTimeout, lThrNo, lConTimeout, lScenario;
	
	public DashBoardView() {
		super.addComponents(createTopBar(), createContents(), createFooter());
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}
	
	public HorizontalLayout createTopBar() {
		Label title = new Label("Test Info");
		title.setSizeUndefined();
		title.addStyleName(ValoTheme.LABEL_H1);
		title.addStyleName(ValoTheme.LABEL_NO_MARGIN);
		
		HorizontalLayout layout = new HorizontalLayout();
		layout.setSpacing(true);
		layout.setWidth(100, Unit.PERCENTAGE);
		layout.addComponent(title);
		layout.setComponentAlignment(title, Alignment.MIDDLE_LEFT);
		return layout;
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
	
	private Component createContents() {
		VerticalLayout verticalLayout = new VerticalLayout();
		verticalLayout.setSpacing(true);
		verticalLayout.setSizeUndefined();
		HorizontalLayout content = new HorizontalLayout();
		HorizontalLayout content2 = new HorizontalLayout();
		content.setSpacing(true);
		content.setMargin(new MarginInfo(true, true, false, true));
		content2.setSpacing(true);
		content2.setMargin(new MarginInfo(true, true, false, true));
		
		FormLayout form = new FormLayout();
		form.setSizeUndefined();
		form.addComponent(txtUrl = new TextField());
		form.addComponent(txtThrNo = new TextField());
		form.addComponent(txtConTimeout = new TextField());
		form.addComponent(txtResTimeout = new TextField());
		form.addComponent(scenarios = new ComboBox<String>());
		
		lUrl = new Label("URL");
		lThrNo = new Label("Thread No");
		lConTimeout = new Label("Connection Timeout");
		lResTimeout = new Label("Response Timeout");
		lScenario = new Label("Scenario");
		
		scenarios.setItems("Scenario1", "SCenario2");
		
		content.addComponents(lUrl, txtUrl, lThrNo, txtThrNo);
		content2.addComponents(lScenario, scenarios, lConTimeout,txtConTimeout, lResTimeout,txtResTimeout);
		
		verticalLayout.addComponents(content, content2);
		
		return verticalLayout;
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
		btnClear = new Button("Clear");
		btnClear.addStyleName(ValoTheme.BUTTON_PRIMARY);
		btnClear.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub
				
			}
		});
		
		footer.addComponents(btnRun, btnStop, btnClear);
		footer.setExpandRatio(btnRun, 1);
		footer.setComponentAlignment(btnRun, Alignment.MIDDLE_RIGHT);
		footer.setComponentAlignment(btnStop, Alignment.MIDDLE_RIGHT);
		footer.setComponentAlignment(btnClear, Alignment.MIDDLE_RIGHT);
		return footer;
	}

}
