package CENTRALE;

import java.io.IOException;
import java.sql.SQLException;

import com.itextpdf.text.DocumentException;


public class TestCentrale {

	public static void main(String[] args) throws SQLException, IOException, DocumentException {
	 
		//Routage.setInfos();
		Centrale.setInfos();   
		
		
		// Routage.getRib("123456782");
		 
		 // Routage.getSolde("123456782");
		 // Routage.retrait("123456782", 10);
		//Routage.getRib("123456782");
		 // Centrale.getHistorique("123456789");
		Centrale.getRib("123456789");
		 
		//Centrale.IsExist("123456789");
		 
		//Routage.IsExist("123456789");
		
		// Centrale.getSolde("123456782"); // ***************
		// Centrale.getCode("123456789"); // ***************  // il reste a rentrer le code haché a la bdd;
		// Centrale.depot("123456789", 200); // *************
		// Centrale.operation("123456789", 150, "depot"); // **************
		 // Centrale.retrait("123456789", 100); // **************
		//Centrale.IsValid("123456782"); // ****************
		// Centrale.IsExist("123456789"); // ***************
		// Centrale.getSeuilQuot("123456789"); // ****************
		// Centrale.getSeuilHebdo("123456789");	// ***************
		// Centrale.setSeuil("123456789", 10); // ****************
		//Centrale.getRib("123456789"); // *************
		// Centrale.getHistorique("123456789");
		// Centrale.getHist("123456789");
	//	}
//		else {Routage.setInfos(); 
//			if(Routage.IsExist("123456789")== true) {		
//		
//		// Routage.getCode("123456789");
//		// Routage.getSolde("123456782");
//		Routage.retrait("123456789", 10);
//		// Routage.IsValid("123456789");
//		// Routage.IsExist("123456789");
//		// Routage.operation("123456789", 40, "depot");
//		// Routage.getRib("123456789");
//		// Routage.getSeuilHebdo("123456789");
//		// Routage.getSeuilQuot("123456789");	
//		}
//		}


	 }


}
