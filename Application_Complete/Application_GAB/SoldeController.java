package application;

import java.io.IOException;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.smartcardio.CardException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SoldeController implements Initializable {
	@FXML
	private BorderPane bpSolde;
	@FXML
	private Label message;

	@FXML
	private Button Back;

	@FXML
	private Button imprimer;

	public void setMessage() throws SQLException, CardException {
		SmartCard smartCard = new SmartCard();
		message.setText(Float.toString(Centrale.getSolde(smartCard.getNumCarte())));
		message.setText("€");
	}

	@FXML
	public void Back(ActionEvent event) throws IOException {
		
		if(AccueilController.getAppartenanceClient().equals("interne")) {
			Stage primaryStage = new Stage();
			BorderPane root = FXMLLoader.load(getClass().getResource("Choix.fxml"));
			Scene scene = new Scene(root, 1350, 700);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			//primaryStage.show();
			bpSolde.getChildren().setAll(root);
			scene.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());

		}else if(AccueilController.getAppartenanceClient().equals("externe")) {
			Stage primaryStage = new Stage();
			BorderPane root = FXMLLoader.load(getClass().getResource("ChoixRoutage.fxml"));
			Scene scene = new Scene(root, 1350, 700);

			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			//primaryStage.show();
			bpSolde.getChildren().setAll(root);
			scene.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());

		}
	}

	@FXML
	public void imprimer(ActionEvent event) throws SQLException, IOException {
		Centrale.setInfos();
		Centrale.getSoldeTicket();
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		SmartCard smartCard = new SmartCard();
		try {
			message.setText(Float.toString(Centrale.getSolde(smartCard.getNumCarte()))+"€");
		} catch (SQLException | CardException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
