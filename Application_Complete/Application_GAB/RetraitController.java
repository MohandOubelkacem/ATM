package application;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class RetraitController  {
	@FXML
	private BorderPane bpRetrait;
	
	 public static int getMontantRetrait() {
	        return montantRetrait;
	    }


	    public static void setMontantRetrait(int montantRetrait) {
	        RetraitController.montantRetrait = montantRetrait;
	    }
	    private static int montantRetrait;
	   
	    @FXML
	   
	    
	   
	    public void onOther(ActionEvent e) throws IOException {
			Stage primaryStage=new Stage();
	BorderPane root =FXMLLoader.load(getClass().getResource("AutreMontant.fxml"));
	Scene scene = new Scene(root,1350,700);
		
	scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	primaryStage.setScene(scene);
	//primaryStage.show();
	bpRetrait.getChildren().setAll(root);
	scene.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());

		}
	    
	    public void click20(ActionEvent e) throws IOException {
			
		
	        ConfirmationController.setModeOperation("Retrait");
	        montantRetrait=20;
	      Stage primaryStage=new Stage();	
	      BorderPane root =FXMLLoader.load(getClass().getResource("Confirmation.fxml"));
	      Scene scene = new Scene(root,1350,700);
		
	      scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	      primaryStage.setScene(scene);
	      //primaryStage.show();
	      bpRetrait.getChildren().setAll(root);
	      scene.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());

		}
	    
	    public void on50(ActionEvent e) throws IOException {
			Stage primaryStage=new Stage();
			
	        ConfirmationController.setModeOperation("Retrait");
	        montantRetrait=50;
			
	BorderPane root =FXMLLoader.load(getClass().getResource("Confirmation.fxml"));
	Scene scene = new Scene(root,1350,700);
		
	scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	primaryStage.setScene(scene);
	//primaryStage.show();
	bpRetrait.getChildren().setAll(root);
	scene.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());

		}
	    public void on70(ActionEvent e) throws IOException {
			Stage primaryStage=new Stage();
	
	        ConfirmationController.setModeOperation("Retrait");
	        montantRetrait=70;
			
	BorderPane root =FXMLLoader.load(getClass().getResource("Confirmation.fxml"));
	Scene scene = new Scene(root,1350,700);
		
	scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	primaryStage.setScene(scene);
	//primaryStage.show();
	bpRetrait.getChildren().setAll(root);
	scene.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());

		}
	    public void on90(ActionEvent e) throws IOException {
			Stage primaryStage=new Stage();
		
	        ConfirmationController.setModeOperation("Retrait");
	        montantRetrait=90;
			
	BorderPane root =FXMLLoader.load(getClass().getResource("Confirmation.fxml"));
	Scene scene = new Scene(root,1350,700);
		
	scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	primaryStage.setScene(scene);
	//primaryStage.show();
	bpRetrait.getChildren().setAll(root);
	scene.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());
		}
	    
	    public void on100(ActionEvent e) throws IOException {
			Stage primaryStage=new Stage();
			
	        ConfirmationController.setModeOperation("Retrait");
	        montantRetrait=100;
			
	BorderPane root =FXMLLoader.load(getClass().getResource("Confirmation.fxml"));
	Scene scene = new Scene(root,1350,700);
		
	scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	primaryStage.setScene(scene);
	//primaryStage.show();
	bpRetrait.getChildren().setAll(root);
	scene.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());

		}

	    public void on150(ActionEvent e) throws IOException {
			Stage primaryStage=new Stage();
	
	        ConfirmationController.setModeOperation("Retrait");
	        montantRetrait=150;
			
	BorderPane root =FXMLLoader.load(getClass().getResource("Confirmation.fxml"));
	Scene scene = new Scene(root,1350,700);
		
	scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	primaryStage.setScene(scene);
	//primaryStage.show();
	bpRetrait.getChildren().setAll(root);
	scene.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());

		}
	
	
	    public void RetourClicked(javafx.event.ActionEvent actionEvent) throws IOException {
	    	
	    	if(AccueilController.getAppartenanceClient().equals("interne")) {
				Stage primaryStage = new Stage();
				BorderPane root = FXMLLoader.load(getClass().getResource("Choix.fxml"));
				Scene scene = new Scene(root, 1350, 700);

				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				//primaryStage.show();
				bpRetrait.getChildren().setAll(root);
				scene.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());

			}else{
				Stage primaryStage = new Stage();
				BorderPane root = FXMLLoader.load(getClass().getResource("ChoixRoutage.fxml"));
				Scene scene = new Scene(root, 1350, 700);

				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				//primaryStage.show();
				bpRetrait.getChildren().setAll(root);
				scene.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());

			}
	    }

	    



}




