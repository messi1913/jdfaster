package com.jdfaster.view.main;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.entity.ContentType;
import org.codehaus.jackson.map.ObjectMapper;

import com.jdfaster.test.services.get_list.GetTestListOut;
import com.jdfaster.test.services.get_list.GetTestListOut.TestInfo;
import com.jdfaster.util.JsonUtil;
import com.jdfaster.util.JsonUtil.MethodType;
import com.jdfaster.view.model.ScenarioDVO;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;import javafx.util.Callback;

public class PerformanceMain {

	@FXML
	private TextField txtUrl;

	@FXML
	private PasswordField txtResTimeout;

	@FXML
	private TextField txtThrNo;

	@FXML
	private PasswordField txtConType;

	@FXML
	private TextField txtConTimeout;

	@FXML
	private Button btnRun;

	@FXML
	private ComboBox<String> cmbScenario, cmbMethod;
	
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
		
	}

	@FXML
	public void btnRunOnMouseClicked(MouseEvent event) {
		ScenarioDVO dvo = new ScenarioDVO();
		dvo.setConnectTimeout(Integer.parseInt(txtConTimeout.getText()));
		dvo.setContentType(txtConType.getText());
		dvo.setMethodType(cmbMethod.getSelectionModel().getSelectedItem());
		dvo.setResponseTimeout(Integer.parseInt(txtResTimeout.getText()));
		dvo.setScenario(cmbScenario.getSelectionModel().getSelectedItem());
		dvo.setServerUrl(txtUrl.getText());
		dvo.setSizeOfThread(Integer.parseInt(txtThrNo.getText()));
	}

}
