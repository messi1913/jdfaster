package com.jdfaster.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

import com.jdfaster.test.TestResult;
import com.jdfaster.test.TestUtils;
import com.jdfaster.test.services.get_list.GetTestListOut;
import com.jdfaster.test.services.get_list.GetTestListOut.TestInfo;
import com.jdfaster.test.services.run.RunTestIn;
import com.jdfaster.test.services.run.RunTestOut;
import com.jdfaster.util.JsonUtils;
import com.jdfaster.util.JsonUtils.MethodType;
import com.jdfaster.view.data.ScTestInfo;
import com.jdfaster.view.data.ScTestResult;
import com.vaadin.data.Binder;
import com.vaadin.data.converter.StringToLongConverter;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.ProgressBar;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings("serial")
public class DashBoardView extends VerticalLayout implements View {
	
	private UI ui;
	public static final String VIEW_NAME = "";
	TextField txtUrl, txtResTimeout, txtThrNo, txtConTimeout, txtAvgRunTime, txtMinRunTime, txtMaxRunTime, txtTotalRunSize, txtTotalRunTime;
	ComboBox<TestInfo> scenarios;
	Button btnRun, btnStop, btnSave;
	Binder<ScTestInfo> binder;
	Binder<TestResult> resultBinder;
	Label lUrl, lResTimeout, lThrNo, lConTimeout, lScenario;
	final ProgressBar bar = new ProgressBar(new Float(0.0));
	boolean isOnGoing = true;
	ScTestInfo scTestInfo;
	RunTestIn testDVO;
	String baseURL;
	TestResult testResult;
	Grid<ScTestResult> grdResult; 
	List<ScTestResult> testResultList;

	private List<TestInfo> testInfoList;

	private static final ObjectMapper mapper;
	static {
		mapper = new ObjectMapper();
	}

	public DashBoardView() throws Exception {
		this.ui = UI.getCurrent();
		baseURL = "http://" + Page.getCurrent().getLocation().getHost() + ":"
				+ Page.getCurrent().getLocation().getPort();
		lazyInit();
		super.addComponents(createTopBar(), createContents(), createFooter(), creatResultTop(), createMainResult() );
		this.addComponent(bar);
		this.setComponentAlignment(bar, Alignment.MIDDLE_CENTER);
		bar.setEnabled(false);
		bar.setVisible(false);
		
		new TestResultThread().start();
	}

	@Override
	public void enter(ViewChangeEvent event) {

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

		scTestInfo = new ScTestInfo();
		binder = new Binder<ScTestInfo>();

		binder.forField(txtUrl).bind(ScTestInfo::getUrl, ScTestInfo::setUrl);
		binder.forField(txtThrNo).bind(ScTestInfo::getThreadSize, ScTestInfo::setThreadSize);
		binder.forField(txtResTimeout).bind(ScTestInfo::getResTitmeOut, ScTestInfo::setResTitmeOut);
		binder.forField(txtConTimeout).bind(ScTestInfo::getConTimeOut, ScTestInfo::setConTimeOut);

		binder.setBean(scTestInfo);
		
		// Main 결과값
		txtAvgRunTime = new TextField("Average Run Time");
		txtMinRunTime = new TextField("Min Run Time");
		txtMaxRunTime = new TextField("Max Run Time");
		txtTotalRunTime = new TextField("Total Run Time");
		txtTotalRunSize = new TextField("Total Run Size");	
		resultBinder = new Binder<>();
		testResult = new TestResult();
		resultBinder.forField(txtAvgRunTime).withConverter(new StringToLongConverter("")).bind(TestResult::getAvgRunTime, TestResult::setAvgRunTime);
		resultBinder.forField(txtMinRunTime).withConverter(new StringToLongConverter("")).bind(TestResult::getMinRunTime, TestResult::setMinRunTime);
		resultBinder.forField(txtMaxRunTime).withConverter(new StringToLongConverter("")).bind(TestResult::getMaxRunTime, TestResult::setMaxRunTime);
		resultBinder.forField(txtTotalRunTime).withConverter(new StringToLongConverter("")).bind(TestResult::getTotalRunTime, TestResult::setTotalRunTime);
		resultBinder.forField(txtTotalRunSize).withConverter(new StringToLongConverter("")).bind(TestResult::getTotalRunSize, TestResult::setTotalRunSize);
		resultBinder.setBean(testResult);
		resultBinder.setReadOnly(true);

		String uri = baseURL + "/services/jdftest/get_list/";
		GetTestListOut dvoList = JsonUtils.request(uri, MethodType.GET, GetTestListOut.class);
		testInfoList = dvoList.getList();

		testDVO = new RunTestIn();

		scenarios.setItems(testInfoList);
		// Use the name property for item captions
		scenarios.setItemCaptionGenerator(TestInfo::getName);
		scenarios.setSizeUndefined();
		// Set Value by changing combo box
		scenarios.addValueChangeListener(event -> {
			testDVO.setTestInfo(event.getValue());
			testDVO.setTargetUrl(txtUrl.getValue());
			scTestInfo.setClassName(event.getValue().getClassName());
			scTestInfo.setMethodName(event.getValue().getMethodName());
			scTestInfo.setName(event.getValue().getName());
		});
		// 기본 설정 테스트 빨리 하기 위해서 
//		scenarios.setValue(testInfoList.get(0));
		txtUrl.setValue("http://localhost:8080");
		txtThrNo.setValue("1");
		txtConTimeout.setValue("1000");
		txtResTimeout.setValue("1000");
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
				try {
					testDVO.setConnectionTimeout(Long.parseLong(txtConTimeout.getValue()));
					testDVO.setNumberOfThreads(Integer.parseInt(txtThrNo.getValue()));
					testDVO.setResponseTimeout(Long.parseLong(txtResTimeout.getValue()));
					String uri = baseURL + "/services/jdftest/run/";
					bar.setEnabled(true);
					bar.setVisible(true);
					bar.setIndeterminate(true);
					isOnGoing = true;
					if(!isOnGoing) return;
					RunTestOut outDVO = JsonUtils.request(uri, MethodType.POST, mapper.writeValueAsString(testDVO),RunTestOut.class);
					bar.setVisible(false);
					System.err.println("End");

				} catch (Exception e) {
					bar.setVisible(false);
					e.printStackTrace();
				}
			}
		});
		btnStop = new Button("Stop");
		btnStop.addStyleName(ValoTheme.BUTTON_DANGER);
		btnStop.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				isOnGoing = false;
				String uri = baseURL+"/services/jdftest/stop/";
				try {
					JsonUtils.request(uri, MethodType.POST, mapper.writeValueAsString(testDVO), RunTestOut.class);
				} catch (Exception e) {
					e.printStackTrace();
				}
				bar.setVisible(false);
				Notification.show("STOP!!");
			}
		});
		btnSave = new Button("Save");
		btnSave.addStyleName(ValoTheme.BUTTON_PRIMARY);
		btnSave.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				//TO DO : 파일 남기
				
			}
		});

		footer.addComponents(btnRun, btnStop, btnSave);
		footer.setExpandRatio(btnRun, 1);
		footer.setComponentAlignment(btnRun, Alignment.MIDDLE_RIGHT);
		footer.setComponentAlignment(btnStop, Alignment.MIDDLE_RIGHT);
		footer.setComponentAlignment(btnSave, Alignment.MIDDLE_RIGHT);

		return footer;
	}
	
	public HorizontalLayout creatResultTop() {
		Label title = new Label("Test Result");
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

	private Component createMainResult() {
		
		VerticalLayout layout = new VerticalLayout();
		HorizontalLayout result = new HorizontalLayout();
//		HorizontalLayout result = new HorizontalLayout();
		result.setSizeUndefined();
		result.setSpacing(true);
		result.setStyleName(ValoTheme.WINDOW_BOTTOM_TOOLBAR);
		result.setWidth(100, Unit.PERCENTAGE);
		GridLayout gridLayout = new GridLayout(5, 1);
		gridLayout.setSpacing(true);
		gridLayout.setSizeFull();
		gridLayout.setMargin(true);
		gridLayout.addComponent(txtAvgRunTime, 0, 0);
		gridLayout.addComponent(txtMinRunTime, 1, 0);
		gridLayout.addComponent(txtMaxRunTime, 2, 0);
		gridLayout.addComponent(txtTotalRunTime, 3, 0);
		gridLayout.addComponent(txtTotalRunSize, 4, 0);
		
		result.addComponent(gridLayout);
		
		testResultList = new ArrayList<>();
		
		grdResult = new Grid<>();
		grdResult.setSizeFull();
		
		grdResult.setItems(testResultList);
		grdResult.addColumn(ScTestResult::getServiceName).setCaption("Service Name").setWidthUndefined().setResizable(true);
		grdResult.addColumn(ScTestResult::getAvgTime).setCaption("Average Run Time").setWidthUndefined();
		grdResult.addColumn(ScTestResult::getMaxTime).setCaption("Max Run Time").setWidthUndefined().setResizable(true);
		grdResult.addColumn(ScTestResult::getMinTime).setCaption("Min Run Time");
		grdResult.addColumn(ScTestResult::getTotalRunTime).setCaption("Total Run Time");
		grdResult.addColumn(ScTestResult::getTotalRunSize).setCaption("Total Run Size");
		grdResult.addColumn(ScTestResult::getStatus).setCaption("Status").setWidthUndefined().setResizable(true);

		layout.addComponents(result, grdResult);
		
		return layout;
	}
	
	class TestResultThread extends Thread {
		@Override
		public void run() {
			try {
				while(true) {
					Thread.sleep(1000);
					if(!isOnGoing) continue;
					
					TestResult clonedResult = TestUtils.getClonedResult();
					if(clonedResult == null) continue;
					
					ui.access(() -> {
						txtAvgRunTime.setValue(Long.toString(clonedResult.getAvgRunTime()));
						txtMaxRunTime.setValue(Long.toString(clonedResult.getMaxRunTime()));
						txtMinRunTime.setValue(Long.toString(clonedResult.getMinRunTime()));
						txtTotalRunSize.setValue(Long.toString(clonedResult.getTotalRunSize()));
						txtTotalRunTime.setValue(Long.toString(clonedResult.getTotalRunTime()));

						testResultList.clear();
						Map<String, TestResult> results = clonedResult.results;
						for (String service : results.keySet()) {
							TestResult tr = results.get(service);
							ScTestResult result = new ScTestResult();
							result.setServiceName(tr.getName());
							result.setAvgTime(Long.toString(tr.getAvgRunTime()));
							result.setMaxTime(Long.toString(tr.getMaxRunTime()));
							result.setMinTime(Long.toString(tr.getMinRunTime()));
							result.setTotalRunTime(Long.toString(tr.getTotalRunTime()));
							result.setTotalRunSize(Long.toString(tr.getTotalRunSize()));
							result.setStatus("200 OK");
							testResultList.add(result);
						}

						grdResult.setItems(testResultList);
					});
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

}
