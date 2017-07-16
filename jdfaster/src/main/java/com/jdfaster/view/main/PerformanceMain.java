package com.jdfaster.view.main;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;

import com.jdfaster.test.services.get_configs.GetTestConfigsOut;
import com.jdfaster.test.services.get_list.GetTestListOut;
import com.jdfaster.test.services.get_list.GetTestListOut.TestInfo;
import com.jdfaster.test.services.run.RunTestIn;
import com.jdfaster.test.services.run.RunTestOut;
import com.jdfaster.test.services.run.TestResult;
import com.jdfaster.test.services.set_configs.SetTestConfigsIn;
import com.jdfaster.test.services.set_configs.SetTestConfigsOut;
import com.jdfaster.util.JsonUtils;
import com.jdfaster.util.JsonUtils.MethodType;
import com.jdfaster.util.SvcUtils;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class PerformanceMain {

	@FXML
	private TextField txtUrl, txtResTimeout, txtThrNo, txtConTimeout;

	@FXML
	private Button btnRun, btnStop, btnClear, btnRetrieve;

	@FXML
	private ComboBox<String> cmbScenario;

	@FXML
	private TableView<TestResult> tvResult;

	@FXML
	private TableColumn<TestResult, String> colNo, colService, colthreadName, colStartTime, colRunTime, colStatus,
			colConTime;
	
	private RunTestIn testDVO;
	private List<TestInfo> testInfoList;
	private String baseUrl;

	private static final ObjectMapper mapper;
	static {
		mapper = new ObjectMapper();
	}

	@FXML
	void cmbScenarioOnMouseClicked(MouseEvent event) {

	}

	public PerformanceMain() {

	}

	@FXML
	private void initialize() throws Exception {
		
		
		baseUrl = SvcUtils.getProperties("system.remote.server.url");
		// 서비스 목록 갖고 옴.
		List<String> serviceList = new ArrayList<String>();
		String uri = baseUrl+"/services/jdftest/get_list/";
		GetTestListOut dvoList = JsonUtils.request(uri, MethodType.GET, GetTestListOut.class);
		testInfoList = dvoList.getList();
		for (TestInfo dvo : testInfoList) {
			serviceList.add(dvo.getName());
			System.out.println("###### URL : "+dvo.getUrl());
			System.out.println("###### Class : "+dvo.getClassName());
			System.out.println("###### Method : "+dvo.getMethodName());
		}

		cmbScenario.getItems().setAll(serviceList);
		cmbScenario.getSelectionModel().select(0);

		// 그리드 설정
		colService.setCellValueFactory(param -> param.getValue().methodNameProperty());
		colConTime.setCellValueFactory(param -> param.getValue().connectionTimeProperty());
		colRunTime.setCellValueFactory(param -> param.getValue().runTimeProperty());
		colStartTime.setCellValueFactory(param -> param.getValue().startTimeProperty());
		colStatus.setCellValueFactory(param -> param.getValue().statusProperty());
		colthreadName.setCellValueFactory(param -> param.getValue().threadNameProperty());

	}

	@FXML
	public void btnRunOnMouseClicked(MouseEvent event) throws Exception {
		testDVO = new RunTestIn();
		String scenarioName = cmbScenario.getSelectionModel().getSelectedItem();
		for(TestInfo info : testInfoList) {
			if(scenarioName.equals(info.getName())) {
				testDVO.setTestInfo(info);
				testDVO.setTargetUrl(txtUrl.getText()+info.getUrl());
				break;
			}
		}
		
		testDVO.setConnectionTimeout(Long.parseLong(txtConTimeout.getText()));
		testDVO.setResponseTimeout(Long.parseLong(txtResTimeout.getText()));
		testDVO.setNumberOfThreads(Integer.parseInt(txtThrNo.getText()));
		
		
		String uri = baseUrl+"/services/jdftest/run/";
		// TODO : Thead 수 만큼 호출 
		RunTestOut testResult = JsonUtils.request(uri, MethodType.POST, mapper.writeValueAsString(testDVO), RunTestOut.class);
//		if(testResult == null) return;
//		List<TestResult> testList = testResult.getResultDVOList();
//		tvResult.getItems().clear();
//		tvResult.setItems(FXCollections.observableArrayList(testList));
//		
//		// 결과값 저장 1. JSON , 2. XML
//		SvcUtils.makeJsonFile(new ObjectMapper().writeValueAsString(testResult));
//		SvcUtil.makeResultXMLFile(testResult);
		
	}

	@FXML
	public void btnClearOnMouseClicked(MouseEvent event) {
		txtConTimeout.setText("");
		txtResTimeout.setText("");
		txtThrNo.setText("");
		txtUrl.setText("");

	}

	@FXML
	public void btnRetrieveOnMouseClicked(MouseEvent event) throws Exception {
		getConfigs();
	}

	private SetTestConfigsOut setConfigs() throws Exception {
		SetTestConfigsIn configsDVO = new SetTestConfigsIn();
		configsDVO.setConnectionTimeout(Long.parseLong(txtConTimeout.getText()));
		configsDVO.setNumberOfThreads(Integer.parseInt(txtThrNo.getText()));
		configsDVO.setResponseTimeout(Long.parseLong(txtResTimeout.getText()));
		configsDVO.setTargetUrl(txtUrl.getText());
		String uri = baseUrl+"/services/jdftest/set_configs/";
		return JsonUtils.request(uri, MethodType.POST, mapper.writeValueAsString(configsDVO), SetTestConfigsOut.class);
	}

	private void getConfigs() throws Exception {
		String uri = baseUrl+"/services/jdftest/get_configs/";
		GetTestConfigsOut outDVO = JsonUtils.request(uri, MethodType.GET, GetTestConfigsOut.class);
		txtConTimeout.setText(Long.toString(outDVO.getConnectionTimeout()));
		txtResTimeout.setText(Long.toString(outDVO.getResponseTimeout()));
		txtThrNo.setText(Integer.toString(outDVO.getNumberOfThreads()));
		txtUrl.setText(outDVO.getTargetUrl());
	}

}
