package application;

import java.net.URL;
import java.util.ResourceBundle;

import javax.smartcardio.CardException;

import javafx.animation.PathTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

public class ExitController extends Thread implements Initializable {
	
	@FXML
	private BorderPane exit;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//            Line line= new Line();
//            line.setStartX(86);
//            line.setStartY(40);
//            line.setEndX(86);
//            line.setEndY(60);
            PathTransition tran = new PathTransition();
//            tran.setDuration(Duration.seconds(1));
//            tran.setPath(line);
//            tran.setCycleCount(PathTransition.INDEFINITE);
//            tran.autoReverseProperty().setValue(true);
            tran.play();
           this.start();
//
        }
@FXML
   public void run() {
   	   	 
	 SmartCard smartCard = new SmartCard();           
//       try {
//			System.out.println(smartCard.estla());
//		} catch (CardException e1) {
//			e1.printStackTrace();
//		}
       
          try {
			if(smartCard.attendreCarteOUT()) {
			  // if(true) { 	
				                  	      System.exit(0);                 	
			           	 Platform.runLater(new Runnable() {                                
			           		 @Override
			                    public void run() { 
			                        exit.getChildren().setAll();
			                    }
			                });
			           
			           }
		} catch (CardException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
             
             
                       	}
                  
               
                       

                 
   
  
       
	


	
	
	
	
}
