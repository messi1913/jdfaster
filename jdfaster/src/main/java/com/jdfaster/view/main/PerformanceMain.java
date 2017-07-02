package com.jdfaster.view.main;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;

import com.jdfaster.test.services.get_configs.GetTestConfigsOut;
import com.jdfaster.test.services.get_list.GetTestListOut;
import com.jdfaster.test.services.get_list.GetTestListOut.TestInfo;
import com.jdfaster.test.services.run.RunTestOut.TestResultDVO;
import com.jdfaster.test.services.set_configs.SetTestConfigsIn;
import com.jdfaster.test.services.set_configs.SetTestConfigsOut;
import com.jdfaster.util.JsonUtil;
import com.jdfaster.util.JsonUtil.MethodType;
import com.jdfaster.view.model.ScenarioDVO;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

public class PerformanceMain {

	@FXML
	private TextField txtUrl, txtResTimeout, txtThrNo, txtConTimeout;

	@FXML
	private Button btnRun, btnStop, btnClear, btnRetrieve;

	@FXML
	private ComboBox<String> cmbScenario, cmbMethod;
	
	@FXML
    private TableView<TestResultDVO> tvResult;

    @FXML
    private TableColumn<TestResultDVO, String> colNo, colService, colthreadName, colStartTime, colRunTime, colStatus, colConTime;
	
	
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
		// 콤보박스 값 채움. 일단은 테스트 용. 
		List<String> methodList = new ArrayList<String>();
		methodList.add("POST");
		methodList.add("GET");
		cmbMethod.getItems().setAll(methodList);
		cmbMethod.getSelectionModel().select(0);
		
		// 서비스 목록 갖고 옴. 
		List<String> serviceList = new ArrayList<String>();
		String uri = "http://localhost:8080/services/jdftest/get_list/";
		GetTestListOut dvoList = JsonUtil.request(uri, MethodType.GET, GetTestListOut.class);
		List<TestInfo> list = dvoList.getList();
		for(TestInfo dvo : list){
			serviceList.add(dvo.getName());
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
		SetTestConfigsOut setConfigs = setConfigs();
		System.out.println(setConfigs.getResult());
		
		
		ScenarioDVO dvo = new ScenarioDVO();
		dvo.setConnectTimeout(Integer.parseInt(txtConTimeout.getText()));
		dvo.setMethodType(cmbMethod.getSelectionModel().getSelectedItem());
		dvo.setResponseTimeout(Integer.parseInt(txtResTimeout.getText()));
		dvo.setScenario(cmbScenario.getSelectionModel().getSelectedItem());
		dvo.setServerUrl(txtUrl.getText());
		dvo.setSizeOfThread(Integer.parseInt(txtThrNo.getText()));
		// 실제 RUN 수행 
		
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
	
	private SetTestConfigsOut setConfigs() throws Exception{
		SetTestConfigsIn configsDVO = new SetTestConfigsIn();
		configsDVO.setConnectionTimeout(Long.parseLong(txtConTimeout.getText()));
		configsDVO.setNumberOfThreads(Integer.parseInt(txtThrNo.getText()));
		configsDVO.setResponseTimeout(Long.parseLong(txtResTimeout.getText()));
		configsDVO.setTargetUrl(txtUrl.getText());
		String uri = "http://localhost:8080/services/jdftest/set_configs/";
		return JsonUtil.request(uri, MethodType.POST, mapper.writeValueAsString(configsDVO), SetTestConfigsOut.class);
	}
	
	private void getConfigs() throws Exception {
		String uri = "http://localhost:8080/services/jdftest/get_configs/";
		GetTestConfigsOut outDVO = JsonUtil.request(uri, MethodType.GET, GetTestConfigsOut.class);
		txtConTimeout.setText(Long.toString(outDVO.getConnectionTimeout()));
		txtResTimeout.setText(Long.toString(outDVO.getResponseTimeout()));
		txtThrNo.setText(Integer.toString(outDVO.getNumberOfThreads()));
		txtUrl.setText(outDVO.getTargetUrl());
	}

}
