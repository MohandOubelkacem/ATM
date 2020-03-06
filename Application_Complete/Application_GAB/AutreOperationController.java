package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class AutreOperationController {

	@FXML
	private BorderPane autreOperation;
    public void onOui(javafx.event.ActionEvent actionEvent) throws IOException {
        System.out.println(AccueilController.getAppartenanceClient());
        if(AccueilController.getAppartenanceClient().equals("interne")) {
            	 Stage primaryStage=new Stage();
             	BorderPane root =FXMLLoader.load(getClass().getResource("Choix.fxml"));
             	Scene scene = new Scene(root,1350,700);
             		
             	scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
             	primaryStage.setScene(scene);
             	//primaryStage.show();
             	autreOperation.getChildren().setAll(root);
             	scene.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());            
        	}else {					
            	Stage ps=new Stage();
             	BorderPane parent =FXMLLoader.load(getClass().getResource("ChoixRoutage.fxml"));
             	Scene sc = new Scene(parent,1350,700);
             		
             	sc.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
             	ps.setScene(sc);
             	//ps.show();
            	autreOperation.getChildren().setAll(parent);
             	sc.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());
        }
    }
    public void onNon(javafx.event.ActionEvent actionEvent) throws IOException{
    	AccueilController.setAppartenanceClient(null);
    	 Stage primaryStage=new Stage();
     	BorderPane root =FXMLLoader.load(getClass().getResource("Exit.fxml"));
     	Scene scene = new Scene(root,1350,700);
     		
     	scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
     	primaryStage.setScene(scene);
     	//primaryStage.show();
    	autreOperation.getChildren().setAll(root);
     	scene.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());

    }
}



