package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class DialogController implements Initializable {

	@FXML
	BorderPane BpDialog;
	@FXML
	private Label errorMessage;
	private static String Msg;

	private static String mode;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		errorMessage.setText(Msg);

	}

	public static String getMsg() {
		return Msg;
	}

	public static void setMsg(String msg) {
		Msg = msg;
	}

	public static String getMode() {
		return mode;
	}

	public static void setMode(String mode) {
		DialogController.mode = mode;
	}

	public void onOK(javafx.event.ActionEvent actionEvent) throws IOException {
		

		Stage primaryStage = new Stage();
		BorderPane root = FXMLLoader.load(getClass().getResource(mode+".fxml"));
		Scene scene = new Scene(root, 1350, 700);

		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		
		//primaryStage.show();
		BpDialog.getChildren().setAll(root);
		scene.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());
		


	}

}
