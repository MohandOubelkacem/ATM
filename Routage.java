package CENTRALE;

import java.awt.Desktop;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class Routage {

	private static Connection conn; 
	private static Statement stat_lecture_Rout;
	private	static Statement stat_misajour_Rout;
	private static ResultSet result_lecture_Rout;
	private static ResultSet result_misajour_Rout;

	
public static void setInfos() {
		
		Connexion.setUsername("groupeF");
		Connexion.setPassword("S1IM6OdokT7n7awj");
		Connexion.setUrl("jdbc:mysql://localhost/myDB_ext");
	}


//-------- méthode IsExist - vérifie l'existence de la carte dans la bdd----------//
public static boolean IsExist(String num_carte) throws SQLException {
	try {
	conn= Connexion.connect();
	stat_lecture_Rout = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	
	// requête pour selectionner le ref_carte de la bdd
	String requeteExist = "SELECT ref_carte FROM carte WHERE ref_carte = "+num_carte+"";
	result_lecture_Rout = stat_lecture_Rout.executeQuery(requeteExist);
	result_lecture_Rout.last();
	boolean res = (result_lecture_Rout.getRow()!=0);
	System.out.println(res);
	return (res); // retourner un booléen 
	}
	catch(SQLException e) {
		System.err.println("Error : "+e.getMessage());// afficher le message d'erreur clairement.
		System.err.println("Code : "+e.getErrorCode()); // afficher le code de l'erreur si elle se presente.  
	}
	finally {
		if (result_lecture_Rout != null){
			result_lecture_Rout.close();
		}
		if (stat_lecture_Rout != null){
			stat_lecture_Rout.close();
		}
		if(conn != null) {
			conn.close();
		}
	}
	
	return true;
}


//-------- méthode IsExiste - vérifie l'existence de la carte dans la bdd----------//
public static boolean IsValid(String num_carte) throws SQLException {
	try {
		conn = Connexion.connect();
		stat_lecture_Rout = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	
		Date dateImmediate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dateCourante = dateFormat.format(dateImmediate);
		
		String requeteDate = "SELECT date_expir FROM carte WHERE ref_carte = '"+num_carte+"';"; 
		result_lecture_Rout = stat_lecture_Rout.executeQuery(requeteDate);
		result_lecture_Rout.last();
		boolean rs = (dateCourante.compareTo(result_lecture_Rout.getString("date_expir"))<=0);
		System.out.println(rs);
		System.out.println(result_lecture_Rout.getString("date_expir"));
		
		return(rs);
	}
	catch(SQLException e){
		System.err.println("Error : "+e.getMessage());// afficher le message d'erreur clairement.
		System.err.println("Code : "+e.getErrorCode()); // afficher le code de l'erreur si elle se presente.  
	}
	finally{
		if (result_lecture_Rout != null){
			result_lecture_Rout.close();		// fermer l'execution de la requete
		}
		if (stat_lecture_Rout != null){
			stat_lecture_Rout.close();		// fermer la Statement qu'on a créée.
		}
		if(conn != null) {
			conn.close();		// fermer la connexion a la base de données.
		}
	}
	
	return true;
}

	
	public static String getCode(String num_carte) throws SQLException {
	try {	
		conn = Connexion.connect();
		stat_lecture_Rout = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE , ResultSet.CONCUR_READ_ONLY);
		
		String reqCode = "SELECT code FROM carte WHERE ref_carte = '"+num_carte+"';";
		result_lecture_Rout = stat_lecture_Rout.executeQuery(reqCode);
		result_lecture_Rout.last();
		String monCode =   result_lecture_Rout.getString("code");
		System.out.println(monCode);
		return monCode;
	}
	catch(SQLException e) {
		System.err.println("Error : "+e.getMessage());
		System.err.println("Code : "+e.getErrorCode());
	}
	finally {
		if(result_lecture_Rout != null) {
			result_lecture_Rout.close();
		}
		if(stat_lecture_Rout != null) {
			stat_lecture_Rout.close();
		}
		if(conn != null) {
			conn.close();
		}
	}
	return null;
}
	//------ la méthode getSolde ............... retourne un float (le solde du compte)-------//
	public static float getSolde(String num_carte) throws SQLException {
		try {
			conn = Connexion.connect();
			stat_lecture_Rout =(Statement) conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		
			String requetesolde = "SELECT solde FROM compte WHERE num_client IN (SELECT num_client FROM carte WHERE ref_carte = '"+num_carte+"');";
			result_lecture_Rout = stat_lecture_Rout.executeQuery(requetesolde);
			result_lecture_Rout.last();
			float sld = result_lecture_Rout.getFloat("solde");
			System.out.println(sld);
			return sld;
			}
		
		// faire un catch pour s'informer sur l'erreur si elle aura lieu;
		catch(SQLException e){
			System.err.println("Error : "+e.getMessage());
			System.err.println("Code : "+e.getErrorCode());
		}
		// fermer la connexion et la requete apres l'appel; 
		finally {
			if (result_lecture_Rout != null){
				result_lecture_Rout.close();
			}
			if (stat_lecture_Rout != null){
				stat_lecture_Rout.close();
			}
			if(conn != null) {
				conn.close();
			}
		}
		return 0;
	}
	
	
	// -------- méthode retrait - mettre a jour le solde du compte apres le retrait-------//
		public static void retrait (String num_carte, float montant) throws SQLException {
			
			try {
				conn = Connexion.connect();
				stat_misajour_Rout = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				
				float nouveausolde = getSolde(num_carte) - montant;
				String requeteretrait = "UPDATE compte SET solde='"+nouveausolde+"' WHERE num_client = (SELECT num_client FROM carte WHERE ref_carte='"+num_carte+"');";
				float solde = getSolde(num_carte);
				float squot = getSeuilQuot(num_carte);
				float shebdo = getSeuilHebdo(num_carte);
				// String getSeuil = "SELECT seuil from compte WHERE num_client = (SELECT num_client from carte WHERE ref_carte = '"+num_carte+"');";
				
				// On suppose que le client a le droit au découvert jusqu'à -100€;
				// tester si le compte a un solde >= -100€, pour donner accès au retrait; 
				if(solde <= -100) {
					System.out.println("Vous ne pouvez pas éffectuer cette opération, vous droits sont insuffisants !");
					System.exit(1);
				}
				// tester si le seuil quotidien est > 0 pour pouvoir retirer   
				if((squot - montant)<0) {
					System.out.println("Votre avez retiré plus de la somme autorisée sur ces 24h !");
					System.exit(1);
				}
				else if((shebdo - montant)<0){
					System.out.println("Votre avez retiré plus de la somme autorisée sur ces 7 jours !");
					System.exit(1);
				}
			
				stat_misajour_Rout.execute(requeteretrait);
				
				// affichage de l'opération éffectuée ;
				System.out.println("Vous venez de retirez : '"+montant+"'€");
				System.out.println("Après le retrait, votre solde est de : '"+nouveausolde+"'€");
				operation(num_carte, montant, "retrait");
				setSeuil(num_carte, montant);
			}
				
			// faire un catch pour s'informer sur l'erreur si y aura lieu;
			catch(SQLException e) {
				System.err.println("Error : "+e.getMessage());
				System.err.println("Code : "+e.getErrorCode());
			}
			finally {
				if(result_misajour_Rout != null) {
					result_misajour_Rout.close();
				}
				if(stat_misajour_Rout != null) {
					stat_misajour_Rout.close();
				}
				if(conn != null) {
					conn.close();
				}
			}
	}

		

		public static void operation(String num_carte, float montant, String action_operation) throws SQLException {
			try {
				conn = Connexion.connect();
				stat_misajour_Rout =(Statement) conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

				
				Date dateImmediate = new Date();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String dateCourante = dateFormat.format(dateImmediate);
				String requeteNumClient = "SELECT num FROM client, carte WHERE num_client = num and ref_carte = '"+num_carte+"'; ";
				result_misajour_Rout = stat_misajour_Rout.executeQuery(requeteNumClient);
				result_misajour_Rout.last();
				
				String numClient = result_misajour_Rout.getString("num");
				String requeteOperation = "INSERT INTO operation(date_oprt, somme, action_oprt, num_client) VALUES ('"+dateCourante+"','"+montant+"','"+action_operation+"','"+numClient+"');";
				
				stat_misajour_Rout.execute(requeteOperation);
				}
				catch(SQLException e){
					System.err.println("Error : "+e.getMessage());
					System.err.println("Code : "+e.getErrorCode());	
				}
				finally {
					if(result_misajour_Rout != null) {
						result_misajour_Rout.close();
					}
					if(stat_misajour_Rout != null) {
						stat_misajour_Rout.close();
					}
					if(conn != null) {
						conn.close();
					}
				}				
		}	
	
		public static final void getRib(String num_carte) throws SQLException, DocumentException, IOException {
			Document doc = new Document(PageSize.ID_3);
			StringBuffer infosRib = new StringBuffer();
			
			try {
				conn = Connexion.connect();
				stat_lecture_Rout = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				PdfWriter.getInstance(doc, new FileOutputStream("/home/bouredjioua/eclipse-workspace/BaseDeDonnee/Rib_Routage.pdf"));
				
				String requeteGetRib = "SELECT cl.num, cl.nom, cl.prenom, cl.dtNaiss, cl.adresse, cl.profession, cl.numero_tel, co.solde, co.RIB, co.IBAN\r\n"
						+ "FROM client as cl, compte as co, carte as ca WHERE\r\n"
						+ " cl.num = co.num_client AND co.num_client = ca.num_client AND ca.ref_carte = '"+num_carte+"';";
				
				result_lecture_Rout = stat_lecture_Rout.executeQuery(requeteGetRib);
				result_lecture_Rout.last();
				
				doc.open(); // ouvrir le fichier pdf;
				
				//ajouter les informations au fichier ;
				infosRib.append("Relevé bancaire du Monsieur : ");
				infosRib.append((result_lecture_Rout.getString("cl.nom")+" \t\t"));
				infosRib.append((result_lecture_Rout.getString("cl.prenom")+"\n"));
				infosRib.append(("Date de naissance : "+result_lecture_Rout.getString("cl.dtNaiss")+"\n"));
				infosRib.append(("Adresse : "+result_lecture_Rout.getString("cl.adresse")+"\n"));
				infosRib.append(("Profession : "+result_lecture_Rout.getString("cl.profession")+"\n"));
				infosRib.append(("Numéro de Tél : "+result_lecture_Rout.getString("cl.numero_tel")+"\n"));
				infosRib.append(("RIB : "+result_lecture_Rout.getString("co.RIB")+"\n"));
				infosRib.append(("IBAN : "+result_lecture_Rout.getString("co.IBAN")+"\n"));
				
				String infos = infosRib.toString();
				doc.addTitle("Relevé d'identité bancaire du M.:"+result_lecture_Rout.getString("cl.nom"));
				
				doc.add(new Paragraph(infos)); //ajouter les informations au pdf;
				doc.close();// ferler le fichier apres insertion de toutes les informations;
				operation(num_carte, 0, "Impression_Rib");// insérer une opération d'impression du rib au opérations;
				
				// ouvrir le fichier conçu;
				Desktop.getDesktop().open(new File("/home/bouredjioua/eclipse-workspace/BaseDeDonnee/Rib_Routage.pdf"));

				
			}
			catch (SQLException e) {
				// gérer les différentes exceptions qui surviennent;
				
					System.err.println("Error : "+e.getMessage());
					System.err.println("Code : "+e.getErrorCode());
				}
				catch(DocumentException r){
					System.err.println("Error : "+r.getMessage());
					System.err.println("Trace : "+r.getStackTrace());
				}
				catch(FileNotFoundException y){
					System.err.println("Error : "+y.getMessage());
					System.err.println("Trace : "+y.getStackTrace());
				}
				finally {
					if(result_lecture_Rout != null) {
					result_lecture_Rout.close();
					}
					if(stat_lecture_Rout != null) {
						stat_lecture_Rout.close();
					}
					if(conn != null) {
						conn.close();
					}
				}
			}
					
		
		
		
	
		// methode SeuilQuotidien- pour récupérer le seuil quotidien-----------------------------------
		
		public static float getSeuilQuot (String num_carte) throws SQLException {
			try {
			// ouvrir une connexion a la bdd...........
			conn = Connexion.connect();
			// créer une statement.........
			stat_lecture_Rout = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			String requeteSeuilQ = "SELECT seuilQuot FROM compte WHERE 	num_client = (SELECT num_client FROM carte WHERE ref_carte = '"+num_carte+"');";
			
			result_lecture_Rout = stat_lecture_Rout.executeQuery(requeteSeuilQ);
			result_lecture_Rout.last();
			float monseuilQ = (result_lecture_Rout.getFloat("seuilQuot"));
			System.out.println("Votre seuil quotidien est de :" +monseuilQ +"€");
			return (monseuilQ);
			
			
			}catch(SQLException e) {
				System.err.println("Error : "+e.getMessage());
				System.err.println("Code : "+e.getErrorCode());
			}
			finally {
				if(result_lecture_Rout != null) {
					result_lecture_Rout.close();
				}
				if(stat_lecture_Rout != null) {
					stat_lecture_Rout.close();
				}
				if(conn != null) {
					conn.close();
				}
			}
			return 0;
		}
			
		

		// methode seuil Hebdomadaire - renvoie le seuil hebdomadaire du compte ------------------
		public static float getSeuilHebdo(String num_carte) throws SQLException {
			try {
				// ouvrir une connexion a la bdd...........
				conn = Connexion.connect();
				// créer une statement.........
				stat_lecture_Rout = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				
				String requeteSeuilH = "SELECT seuilHebdo FROM compte WHERE num_client = (SELECT num_client FROM carte WHERE ref_carte = '"+num_carte+"');";
				
				result_lecture_Rout = stat_lecture_Rout.executeQuery(requeteSeuilH);
				result_lecture_Rout.last();
				float monseuilH = (result_lecture_Rout.getFloat("seuilHebdo"));
				System.out.println("Votre seuil hebdomadaire est de :"+monseuilH+ "€");
				return (monseuilH);
				
				
				}catch(SQLException e) {
					System.err.println("Error : "+e.getMessage());
					System.err.println("Code : "+e.getErrorCode());
				}
				finally {
					if(result_lecture_Rout != null) {
						result_lecture_Rout.close();
					}
					if(stat_lecture_Rout != null) {
						stat_lecture_Rout.close();
					}
					if(conn != null) {
						conn.close();
					}
				}
				return 0;
			}
		
		

		// ------methode setSeuil -- mettre a jour le seuil hebdomadaire et le seuil quotidien --------------
		public static void setSeuil(String num_carte, float montant) throws SQLException {
			try { 
				conn= Connexion.connect();
				stat_misajour_Rout = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			 	float nouveauSeuil = getSeuilQuot(num_carte)-montant;
			 	String requeteMAJseuil = "UPDATE compte SET seuilQuot = '"+nouveauSeuil+"' WHERE num_client = (SELECT num_client FROM carte WHERE ref_carte = '"+num_carte+"');";
			 	stat_misajour_Rout.execute(requeteMAJseuil);
			 
			 	nouveauSeuil = getSeuilHebdo(num_carte)-montant;
			 	requeteMAJseuil = "UPDATE compte SET seuilHebdo = '"+nouveauSeuil+"' WHERE num_client = (SELECT num_client FROM carte WHERE ref_carte = '"+num_carte+"');";
			 	stat_misajour_Rout.execute(requeteMAJseuil);
				}
				catch(SQLException e) {
					System.err.println("Error : "+e.getMessage());
					System.err.println("Code : "+e.getErrorCode());
				}
			finally {
				if(result_misajour_Rout != null) {
					result_misajour_Rout.close();
				}
				if(stat_misajour_Rout != null) {
					stat_misajour_Rout.close();
				}
				if(conn != null) {
					conn.close();
				}
			}
		}
		
		
		
}
