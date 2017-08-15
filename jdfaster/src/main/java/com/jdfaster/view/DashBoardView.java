package com.jdfaster.view;

import java.util.ArrayList;
import java.util.List;

import com.jdfaster.test.services.get_list.GetTestListOut;
import com.jdfaster.test.services.get_list.GetTestListOut.TestInfo;
import com.jdfaster.util.JsonUtils;
import com.jdfaster.util.JsonUtils.MethodType;
import com.jdfaster.view.data.ScTestInfo;
import com.vaadin.data.Binder;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings("serial")
public class DashBoardView extends VerticalLayout implements View {

	public static final String VIEW_NAME = "";
	TextField txtUrl, txtResTimeout, txtThrNo, txtConTimeout;
	ComboBox<TestInfo> scenarios;
	Button btnRun, btnStop, btnClear;
	Binder<ScTestInfo> binder;
	Label lUrl, lResTimeout, lThrNo, lConTimeout, lScenario;
	
	private List<TestInfo> testInfoList;
	public DashBoardView() throws Exception {
		lazyInit();
		super.addComponents(createTopBar(), createContents(), createFooter());
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		try {
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	public void lazyInit() throws Exception {
		lUrl = new Label("URL");
		lThrNo = new Label("Thread No");
		lConTimeout = new Label("Connection Timeout");
		lResTimeout = new Label("Response Timeout");
		lScenario = new Label("Scenario");
		txtUrl = new TextField();
		txtUrl.setSizeFull();
		txtUrl.setPlaceholder("Traget Server URL");
		txtThrNo = new TextField();
		txtConTimeout = new TextField();
		txtConTimeout.setPlaceholder("ms");
		txtResTimeout = new TextField();
		txtResTimeout.setPlaceholder("ms");
		txtConTimeout.setSizeUndefined();
		scenarios = new ComboBox<>();
		
		ScTestInfo testInfo = new ScTestInfo();
		binder = new Binder<ScTestInfo>();

		binder.forField(txtUrl).asRequired("Required URL").bind(ScTestInfo::getUrl, ScTestInfo::setUrl);
		binder.forField(txtThrNo).asRequired("Required Thread no").bind(ScTestInfo::getThreadSize,ScTestInfo::setThreadSize);
		binder.forField(txtResTimeout).asRequired("Required Response Timeout").bind(ScTestInfo::getResTitmeOut,ScTestInfo::setResTitmeOut);
		binder.forField(txtConTimeout).asRequired("Required Connection Timeout").bind(ScTestInfo::getConTimeOut,ScTestInfo::setConTimeOut);

		binder.setBean(testInfo);

		String baseUrl = "http://"+Page.getCurrent().getLocation().getHost()+":"+Page.getCurrent().getLocation().getPort();
		String uri = baseUrl+"/services/jdftest/get_list/";
		GetTestListOut dvoList = JsonUtils.request(uri, MethodType.GET, GetTestListOut.class);
		testInfoList = dvoList.getList();
		
		scenarios.setItems(testInfoList);
		// Use the name property for item captions
		scenarios.setItemCaptionGenerator(TestInfo::getName);
		scenarios.setSizeUndefined();
		// Set Value by changing combo box
		scenarios.addValueChangeListener(event -> {
			testInfo.setClassName(event.getValue().getClassName());
			testInfo.setMethodName(event.getValue().getMethodName());
			testInfo.setName(event.getValue().getName());
		});
	}

	private Component createContents() {

		GridLayout grid = new GridLayout(6, 2);
		grid.setSpacing(true);
		grid.setSizeFull();
		grid.setMargin(true);
		grid.addComponent(lUrl, 0, 0);
		grid.addComponent(txtUrl, 1, 0, 3, 0);
		grid.addComponent(lThrNo, 4, 0);
		grid.addComponent(txtThrNo, 5, 0);
		grid.addComponent(lScenario, 0, 1);
		grid.addComponent(scenarios, 1, 1);
		grid.addComponent(lConTimeout, 2, 1);
		grid.addComponent(txtConTimeout, 3, 1);
		grid.addComponent(lResTimeout, 4, 1);
		grid.addComponent(txtResTimeout, 5, 1);

		return grid;
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
