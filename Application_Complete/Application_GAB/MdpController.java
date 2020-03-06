package application;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
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
import javafx.scene.control.PasswordField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MdpController implements Initializable {

	@FXML
	private BorderPane mdpasse;
	@FXML
	private Label erreur;
	@FXML
	private Button valider;
	@FXML
	private PasswordField mdp;
	@FXML
	private Button retour;

	private int Nb_Tentative;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Nb_Tentative = 1;

	}

	private boolean testMdp = false;

	public void valider(ActionEvent actionEvent) throws IOException, InterruptedException {

		SmartCard smartCard = new SmartCard();
		try {
			if (AccueilController.getAppartenanceClient().equals("interne")) {
			//if (true) {
				 if (Securite.egal(mdp.getText(), Centrale.getCode(smartCard.getNumCarte())))
				 {
			//	if (mdp.getText().equals("1234")) {
					testMdp=true;
					BorderPane pane = FXMLLoader.load(getClass().getResource("Choix.fxml"));
					Scene scene = new Scene(pane,1350,700); // ANZER APRES AMK//
					scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					Stage stage = new Stage();
					
					stage.setScene(scene);
					
					mdpasse.getChildren().setAll(pane);
					//stage.show();
				}

				else {
					 erreur.setText("code incorrect ! "); //a regler apres !!!!!!!
					
					 mdp.clear();
					System.out.println("mdp incorrect ! ");
				}
			} else {
				if (Securite.egal(mdp.getText(), Routage.getCode(smartCard.getNumCarte()))) {
					testMdp=true;
					BorderPane pane = FXMLLoader.load(getClass().getResource("ChoixRoutage.fxml")); // il faut voir
																									// ChoixRoutage ????
					Scene scene = new Scene(pane, 1350, 700); // ANZER APRES AMK//
					scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					Stage stage = new Stage();
					stage.setScene(scene);
					mdpasse.getChildren().setAll(pane);
					//stage.show();
				} else {
					 erreur.setText("code incorrect ! ");
					mdp.clear();
					System.out.println("mdp incorrect ! ");
				}

			}

		} catch (UnsupportedEncodingException | NoSuchAlgorithmException | CardException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (Nb_Tentative >= 3 && testMdp==false) {
			Nb_Tentative = 0;
			
			BorderPane borderPane = FXMLLoader.load(getClass().getResource("IncorrectMDP.fxml"));
			Scene scene = new Scene(borderPane, 1350, 700);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Stage stage = new Stage();
			stage.setScene(scene);
			mdpasse.getChildren().setAll(borderPane);
			
			//stage.show();

		} else {
			Nb_Tentative++;
		}

	}

	public void retour(ActionEvent actionEvent) {

		try {
			BorderPane borderPane = FXMLLoader.load(getClass().getResource("Exit.fxml"));
			Scene scene = new Scene(borderPane, 1350, 700);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Stage stage = new Stage();
			stage.setScene(scene);
			mdpasse.getChildren().setAll(borderPane);
			//stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}