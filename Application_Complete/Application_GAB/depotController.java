	package application;
    
    import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

    public class depotController implements Initializable {
    	@FXML
        private Button valider;

        @FXML
        private Button retour;
        
      @FXML
        private BorderPane BpDepot;
        @FXML
        private TextField montantDeposer;


        public static int getMontantDepot() {
            return montantDepot;
        }

        public static void setMontantDepot(int montantDepot) {
            depotController.montantDepot = montantDepot;
        }

        private static int montantDepot;
        @Override
        public void initialize(URL location, ResourceBundle resources) {
        	montantDeposer.setText("");
        }
    
        public void onValide(javafx.event.ActionEvent actionEvent) throws SQLException, IOException {

            if(!montantDeposer.getText().equals("")){
                int montantAux=Integer.parseInt(montantDeposer.getText());
                if(montantAux<0){
                	DialogController.setMsg("  Le montant est négatif ");
                    DialogController.setMode("depot");
                    BorderPane pane= FXMLLoader.load(getClass().getResource("Dialog.fxml"));
                	Scene scene = new Scene(pane,1350,700);
                    BpDepot.getChildren().setAll(pane);
                	scene.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());
                }
                else if(montantAux>1000) {
                  	DialogController.setMsg(" Vous avez dépassé le montant autorisé ");
                    DialogController.setMode("depot");
                    BorderPane pane= FXMLLoader.load(getClass().getResource("Dialog.fxml"));
                	Scene scene = new Scene(pane,1350,700);
                    BpDepot.getChildren().setAll(pane);
                	scene.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());	
                }
                else{
                depotController.setMontantDepot(montantAux);
                ConfirmationController.setModeOperation("Depot");
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
                DialogController.setMode("depot");
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
        public void onRetour(javafx.event.ActionEvent actionEvent) throws IOException {
            if(AccueilController.getAppartenanceClient().equals("interne")){
                	Stage primaryStage=new Stage();
                	BorderPane root =FXMLLoader.load(getClass().getResource("Choix.fxml"));
                	Scene scene = new Scene(root,1350,700);
                		
                	scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
                	primaryStage.setScene(scene);
                	//primaryStage.show();
                	BpDepot.getChildren().setAll(root);
                	scene.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());

            }
            else {
            	
            	Stage primaryStage=new Stage();
            	BorderPane root =FXMLLoader.load(getClass().getResource("ChoixRoutage.fxml"));
            	Scene scene = new Scene(root,1350,700);
            		
            	scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            	primaryStage.setScene(scene);
            	//primaryStage.show();
            	BpDepot.getChildren().setAll(root);
            	scene.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());

                  
            }
        }



    
    
    }
    
    
