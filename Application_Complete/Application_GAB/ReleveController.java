package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.smartcardio.CardException;

import com.itextpdf.text.DocumentException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ReleveController  {
			@FXML
			private BorderPane bpreleve;
		  	@FXML
		    private Button historique;

		    @FXML
		    private Button rib;

		    @FXML
		    private Button back;
		    
		    @FXML
		    void historique(ActionEvent event) throws IOException, SQLException, DocumentException, CardException {
		    	
		    		Centrale.getHistorique(AccueilController.getNumeroDeCarte());

		    }

		    @FXML
		    void rib(ActionEvent event) throws SQLException, DocumentException, IOException, CardException {
		    	SmartCard smartCard =new SmartCard();
		    	Centrale.getRib(smartCard.getNumCarte());
		    	
		    }
		    
		    @FXML 
		    void Back(ActionEvent event) throws IOException {
		    	
		    	if(AccueilController.getAppartenanceClient().equals("interne")) {
					Stage primaryStage = new Stage();
					BorderPane root = FXMLLoader.load(getClass().getResource("Choix.fxml"));
					Scene scene = new Scene(root, 1350, 700);

					scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					primaryStage.setScene(scene);
					//primaryStage.show();
					bpreleve.getChildren().setAll(root);
					scene.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());

				}else if(AccueilController.getAppartenanceClient().equals("externe")) {
					Stage primaryStage = new Stage();
					BorderPane root = FXMLLoader.load(getClass().getResource("ChoixRoutage.fxml"));
					Scene scene = new Scene(root, 1350, 700);

					scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					primaryStage.setScene(scene);
					//primaryStage.show();
					bpreleve.getChildren().setAll(root);
					scene.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());

				}
		    }

		
		    	
			
	
}
