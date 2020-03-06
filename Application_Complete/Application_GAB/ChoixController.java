package application;

import java.io.IOException;
import java.sql.SQLException;

import javax.smartcardio.CardException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ChoixController {
@FXML
private BorderPane choixPage;
	public void onRetrait(ActionEvent e) throws IOException {
		Stage primaryStage = new Stage();
		BorderPane root = FXMLLoader.load(getClass().getResource("Retrait.fxml"));
		Scene scene = new Scene(root, 1350, 700);

		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		//primaryStage.show();
		choixPage.getChildren().setAll(root);
		scene.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());

	}

	public void onDepot(ActionEvent e) throws IOException {
		Stage primaryStage = new Stage();
		BorderPane root = FXMLLoader.load(getClass().getResource("depot.fxml"));
		Scene scene = new Scene(root, 1350, 700);

		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		//primaryStage.show();
		choixPage.getChildren().setAll(root);
		scene.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());
		
	}

	public void onReleve(ActionEvent e) throws IOException {
		Stage primaryStage = new Stage();
		BorderPane root = FXMLLoader.load(getClass().getResource("Releve.fxml"));
		Scene scene = new Scene(root, 1350, 700);

		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		//primaryStage.show();
		choixPage.getChildren().setAll(root);
		scene.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());

	}

	public void onSolde(ActionEvent e) throws IOException, SQLException, CardException {
//		SmartCard smartCard = new SmartCard();
//		SoldeController soldeController = new SoldeController();
//	SoldeController.setMessage();

		Stage primaryStage = new Stage();
		BorderPane root = FXMLLoader.load(getClass().getResource("ConsulterSolde.fxml"));
		Scene scene = new Scene(root, 1350, 700);

		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		//primaryStage.show();
		choixPage.getChildren().setAll(root);
		scene.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());

	}

	public void onRetour(ActionEvent e) throws IOException {
		Stage primaryStage = new Stage();
		BorderPane root = FXMLLoader.load(getClass().getResource("Exit.fxml"));
		Scene scene = new Scene(root, 1350, 700);

		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		//primaryStage.show();
		choixPage.getChildren().setAll(root);
		scene.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());

	}

}