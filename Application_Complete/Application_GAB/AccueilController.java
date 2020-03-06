package application;


 
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.smartcardio.CardException;

import javafx.animation.PathTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
 
 
public class AccueilController extends Thread implements Initializable{
 
      private static String NumeroDeCarte = "";  
     private static String appartenanceClient = "";
        @FXML
        private BorderPane accueil;
//        @FXML
//        private Button btn;
        public static String getNumeroDeCarte () {
        	return NumeroDeCarte;
        }
        
        public static void setNumerodeCarte(String numeroDeCarte) {
        	AccueilController.NumeroDeCarte = numeroDeCarte;
        }
        
        public static String getAppartenanceClient() {
            return appartenanceClient;
        }
       
         public static void setAppartenanceClient(String appartenanceClient) {
            AccueilController.appartenanceClient = appartenanceClient;
        }
 
         @Override
         public void initialize(URL location, ResourceBundle resources) {
//                 Line line= new Line();
//                 line.setStartX(86);
//                 line.setStartY(40);
//                 line.setEndX(86);
//                 line.setEndY(60);
                 PathTransition tran = new PathTransition();
//                 tran.setDuration(Duration.seconds(1));
//                 tran.setPath(line);
//                 tran.setCycleCount(PathTransition.INDEFINITE);
//                 tran.autoReverseProperty().setValue(true);
                 tran.play();
                this.start();
// 
             }
     @FXML
        public void run() {
        	   	 
    	 	SmartCard smartCard = new SmartCard();           
//            try {
//				System.out.println(smartCard.estla());
//			} catch (CardException e1) {
//				e1.printStackTrace();
//			}
            try {
                if(smartCard.attendreCarteIN()) {
                 	
            		System.out.println("Vous devez rentrez votre carte !");
                    AccueilController.setNumerodeCarte(smartCard.getNumCarte());
                    
                    Centrale.setInfos();                   
                    if(Centrale.IsExist(AccueilController.getNumeroDeCarte())) {                    
                       if(Centrale.IsValid(AccueilController.getNumeroDeCarte())) {
                    	     
                              AccueilController.setAppartenanceClient("interne");
                         	 System.out.println("Interne "+ AccueilController.getNumeroDeCarte());  
                         	 
                        	 BorderPane pane= FXMLLoader.load(getClass().getResource("MDPasse.fxml"));                   	                       	
                        	 Platform.runLater(new Runnable() {                                
                        		 @Override
                                 public void run() { 
                                     accueil.getChildren().setAll(pane);
                                 }
                             });
                        
                        }else {
                            System.out.println("Votre carte est invalide !"+ AccueilController.getNumeroDeCarte());
                            DialogController.setMsg("Votre carte n'est plus valide !");
                            DialogController.setMode("Exit");
                            BorderPane pane = FXMLLoader.load(getClass().getResource("Dialog.fxml"));
                            
                            Platform.runLater(new Runnable() {                                
                    		 @Override
                             public void run() { 
                                 accueil.getChildren().setAll(pane);
                             }
                            });
                        }
                    }               
                    else {
                            Routage.setInfos();
                            if(Routage.IsExist(AccueilController.getNumeroDeCarte())) {                               
                                AccueilController.setAppartenanceClient("externe");                             
                                System.out.println("Externe "+AccueilController.getNumeroDeCarte());                                
                                
                                    BorderPane pane = FXMLLoader.load(getClass().getResource("MDPasse.fxml"));                                    
                                    Platform.runLater(new Runnable() {                                
                               		 @Override
                                        public void run() { 
                                            accueil.getChildren().setAll(pane);
                                        }
                                       });
                            }	else {
                            try {
                            	DialogController.setMsg("Votre carte n'existe pas !");
                            	BorderPane pane  = FXMLLoader.load(getClass().getResource("Dialog.fxml"));
                            	Platform.runLater(new Runnable() {                                
                                	@Override
                                    public void run() { 
                                		accueil.getChildren().setAll(pane);
                                    }
                                    });
                            	}
                        catch(Exception e){
                            System.err.println(e.getMessage());
                        }
                    }
                            
                }
            }          
        }
               catch (CardException e) {
                e.getStackTrace();
        // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
       
            }
    	

}