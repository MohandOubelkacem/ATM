package application;
import java.io.IOException;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
 
public class TicketController  {
 
   @FXML
   private BorderPane bpTicket;

    public void ouiClicked(javafx.event.ActionEvent actionEvent) throws IOException, SQLException {
        
        
       if(AccueilController.getAppartenanceClient().equals("interne")) {
       
        Centrale.getTicket();
        }
       else {Routage.getTicket();}
       depotController.setMontantDepot(0);
       
        Stage primaryStage=new Stage();
        BorderPane root =FXMLLoader.load(getClass().getResource("AutreOperation.fxml"));
        Scene scene = new Scene(root,1350,700);
           
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        primaryStage.setScene(scene);
       // primaryStage.show();
        bpTicket.getChildren().setAll(root);
        scene.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());
 
    }
    public void nonClicked(javafx.event.ActionEvent actionEvent) throws IOException, SQLException {
        Stage primaryStage=new Stage();
        BorderPane root =FXMLLoader.load(getClass().getResource("AutreOperation.fxml"));
        Scene scene = new Scene(root,1350,700);
           
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        primaryStage.setScene(scene);
        //primaryStage.show();
        bpTicket.getChildren().setAll(root);
        scene.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());
 
   
   
    }
}