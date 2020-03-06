	package application;
    
    import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

    public class AutreMontantController implements Initializable {
        @FXML
        private BorderPane BpDepot;
        @FXML
        private TextField montantDeposer;


        public static int getMontantDepot() {
            return montantDepot;
        }

        public static void setMontantDepot(int montantDepot) {
        	AutreMontantController.montantDepot = montantDepot;
        }

        private static int montantDepot;
        @Override
        public void initialize(URL location, ResourceBundle resources) {
        	montantDeposer.setText("");
        }
        
    
        public void validerClicked(javafx.event.ActionEvent actionEvent) throws SQLException, IOException {

        	if(!montantDeposer.getText().equals("")){

          		
				
                int valeurDepot=Integer.parseInt(montantDeposer.getText());
               
                
                 if(valeurDepot<0){
                	DialogController.setMsg("  Le montant est négatif   ");
                    DialogController.setMode("Retrait");
                    BorderPane pane= FXMLLoader.load(getClass().getResource("Dialog.fxml"));
                    Scene scene = new Scene(pane,1350,700);
                    BpDepot.getChildren().setAll(pane);
                  	scene.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());
                }
                 else if(valeurDepot <10 && valeurDepot>0) {
                	DialogController.setMsg("  Tapez un montant supérieur à 10 € ");
                    DialogController.setMode("Retrait");
                    BorderPane pane= FXMLLoader.load(getClass().getResource("Dialog.fxml"));
                    Scene scene = new Scene(pane,1350,700);
                    BpDepot.getChildren().setAll(pane);
                  	scene.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());

                	
                }

               
                 
                else{
                RetraitController.setMontantRetrait(valeurDepot);
                ConfirmationController.setModeOperation("Retrait");
                
                Stage primaryStage=new Stage();
            	BorderPane root =FXMLLoader.load(getClass().getResource("Confirmation.fxml"));
            	Scene scene = new Scene(root,1350,700);
            		
            	scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            	primaryStage.setScene(scene);
            	//primaryStage.show();
            	BpDepot.getChildren().setAll(root);
            	scene.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());


                } 
            }
            else {
                DialogController.setMsg("la case pour saisir le montant est vide");
                DialogController.setMode("Retrait");
                Stage primaryStage=new Stage();
            	BorderPane root =FXMLLoader.load(getClass().getResource("Dialog.fxml"));
            	Scene scene = new Scene(root,1350,700);
            		
            	scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            	primaryStage.setScene(scene);
            	//primaryStage.show();
            	BpDepot.getChildren().setAll(root);
            	scene.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());

            }


        }
        public void AnnulerClicked(javafx.event.ActionEvent actionEvent) throws IOException {
          
            switch(AccueilController.getAppartenanceClient()){
            case "interne":
            	
            	Stage primaryStage=new Stage();
            	BorderPane root =FXMLLoader.load(getClass().getResource("Retrait.fxml"));
            	Scene scene = new Scene(root,1350,700);
            		
            	scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            	primaryStage.setScene(scene);
            	//primaryStage.show();
            	BpDepot.getChildren().setAll(root);
            	scene.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());

                break;
            case "externe":
            	 primaryStage=new Stage();
            	 root =FXMLLoader.load(getClass().getResource("ChoixRoutage.fxml"));
            	 scene = new Scene(root,1350,700);
            		
            	scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            	primaryStage.setScene(scene);
            	//primaryStage.show();
            	BpDepot.getChildren().setAll(root);
            	scene.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());

                break;
        }
        }
    }
    
    
