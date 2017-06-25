package com.jdfaster.view.application;

import java.io.IOException;

import javafx.application.Application;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

    @FXML
    private AnchorPane main, head, body;
    @FXML
    private Button btnMain, btnResult;
    
    private AnchorPane rootLayout;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Main.fxml"));
            rootLayout = (AnchorPane) loader.load();

            // 상위 레이아웃을 포함하는 scene을 보여준다.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	private StringProperty name;

	public StringProperty nameProperty() {
		return name;
	}

	public String getName() {
		return name.get();
	}

	public void setName(String name) {
		this.name.set(name);
	}
	
	@FXML
    public void btnMainOnMouseClicked(MouseEvent event) throws IOException {
		Node node = (Node)FXMLLoader.load(getClass().getResource("../main/PerformanceMain.fxml"));
		body.getChildren().setAll(node);
    }

    @FXML
    public void btnResultOnMouseClicked(MouseEvent event) throws IOException {
    	Node node = (Node)FXMLLoader.load(getClass().getResource("../main/PerformanceResult.fxml"));
		body.getChildren().setAll(node);
    }

	
	
}
