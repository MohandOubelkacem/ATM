package application;
 
import java.io.IOException;
import java.sql.SQLException;

/*import BDD.Centrale;
//import BDD.Routage;*/
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
 
public class ConfirmationController {
	@FXML
	private BorderPane bp;
 
    private static String modeOperation;
 
    public static String getModeOperation() {
        return modeOperation;
    }
 
    public static void setModeOperation(String modeOperation) {
        ConfirmationController.modeOperation = modeOperation;
    }
 
    public void onOui(javafx.event.ActionEvent actionEvent) throws IOException, SQLException {
        System.out.println(modeOperation);
        if (modeOperation.equals("Depot")) {
            Centrale.setInfos();
            Centrale.depot(AccueilController.getNumeroDeCarte(), depotController.getMontantDepot());
            System.out.println(depotController.getMontantDepot());

           
            Stage primaryStage = new Stage();
            BorderPane root = FXMLLoader.load(getClass().getResource("Ticket.fxml"));
            Scene scene = new Scene(root, 1350, 700);
 
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setScene(scene);
            //primaryStage.show();
            bp.getChildren().setAll(root);
            scene.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());
        } else {
           
           
            if (modeOperation.equals("Retrait")) {             
                       
                if (AccueilController.getAppartenanceClient().equals("interne")) {           
                    Centrale.setInfos();                   
                    System.out.println(RetraitController.getMontantRetrait());
                   
                    if(Centrale.retrait(AccueilController.getNumeroDeCarte(), RetraitController.getMontantRetrait())) {
                   
                    DialogController.setMsg("IL vous reste que " + Centrale.getSeuilHebdo(AccueilController.getNumeroDeCarte())+ "  ? retirer pour cette semaine");
                    DialogController.setMode("Retrait");
               
                    Stage primaryStage = new Stage();
                    BorderPane root = FXMLLoader.load(getClass().getResource("Ticket.fxml"));
                    Scene scene = new Scene(root, 1350, 700);
                    scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
                    primaryStage.setScene(scene);
                   // primaryStage.show();
                    bp.getChildren().setAll(root);
                    scene.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());
                    
                    }
                   
                   
                    else {DialogController.setMsg("Vous avez dépassé le seuil autorisé !");
                    DialogController.setMode("AutreOperation");
                    Stage primaryStage = new Stage();
                    BorderPane root = FXMLLoader.load(getClass().getResource("Dialog.fxml"));
                    Scene scene = new Scene(root, 1350, 700);
 
                    scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
                    primaryStage.setScene(scene);
                   // primaryStage.show();
                    bp.getChildren().setAll(root);
                    scene.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());
                       
                    }
//                 
                }
            else {
                    if (RetraitController.getMontantRetrait() <= 500) {
                        Routage.setInfos();
                        System.out.println(RetraitController.getMontantRetrait());
                       if(Routage.retrait(AccueilController.getNumeroDeCarte(), RetraitController.getMontantRetrait())) {
                            
                        Stage primaryStage = new Stage();
                        BorderPane root = FXMLLoader.load(getClass().getResource("Ticket.fxml"));
                        Scene scene = new Scene(root, 1350, 700);
 
                        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
                        primaryStage.setScene(scene);
                       // primaryStage.show();
                        bp.getChildren().setAll(root);
                        scene.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());
                       }else {
                    	   
                    	   DialogController.setMsg("Vous avez dépassé le seuil autorisé !");
                       DialogController.setMode("AutreOperation");
                       Stage primaryStage = new Stage();
                       BorderPane root = FXMLLoader.load(getClass().getResource("Dialog.fxml"));
                       Scene scene = new Scene(root, 1350, 700);
    
                       scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
                       primaryStage.setScene(scene);
                      // primaryStage.show();
                       bp.getChildren().setAll(root);
                       scene.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());
                    	   
                    	   
						
					}
                       
                    } else {
                        DialogController.setMsg("vous ne pouvez pas retirer plus de 500 ? ");
                        DialogController.setMode("AutreOperation");
                       
                        
                        Stage pg = new Stage();
                        BorderPane parent = FXMLLoader.load(getClass().getResource("Dialog.fxml"));
                        Scene sc = new Scene(parent, 1350, 700);
 
                        sc.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
                        pg.setScene(sc);
                       // pg.show();
                        bp.getChildren().setAll(parent);
                        sc.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());
                    }
                }
            }
        }
 
    }
 
    public void onNon(javafx.event.ActionEvent actionEvent) throws IOException {
        if(AccueilController.getAppartenanceClient().equals("interne")) {
    	RetraitController.setMontantRetrait(0);
        depotController.setMontantDepot(0);
        Stage primaryStage = new Stage();
        BorderPane root = FXMLLoader.load(getClass().getResource("Choix.fxml"));
        Scene scene = new Scene(root, 1350, 700);
 
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        primaryStage.setScene(scene);
       // primaryStage.show();
        bp.getChildren().setAll(root);
        scene.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());
    }
        else {
        	RetraitController.setMontantRetrait(0);
            depotController.setMontantDepot(0);
            Stage primaryStage = new Stage();
            BorderPane root = FXMLLoader.load(getClass().getResource("ChoixRoutage.fxml"));
            Scene scene = new Scene(root, 1350, 700);
     
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setScene(scene);
           // primaryStage.show();
            bp.getChildren().setAll(root);
            scene.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());
        }
}
}