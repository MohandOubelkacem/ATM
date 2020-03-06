package application;
//importer les classes nécessaires:
import java.awt.Desktop;
// classe pour ouvrir les fichiers pdf lors de la demande;
// classe pour gérer les différentes exceptions;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;// classe pour créer un ResultSet a fin de récupérer le resultat d'une requête SQL; 
import java.sql.SQLException;
import java.sql.Statement; // classe pour créer une statement a fin d'exécuter une requête SQL; 
import java.text.DateFormat;
import java.text.SimpleDateFormat;
// classe liées à la date ;
import java.util.Date;

// classes pour le traitement des fichiers pdf;
import com.itextpdf.text.Document; // crée un document pdf;
import com.itextpdf.text.DocumentException;//
import com.itextpdf.text.PageSize;// introduire la taille du pdf a faire A4/A5/lettre ...
import com.itextpdf.text.Paragraph;// pour insérer du texte au document;
import com.itextpdf.text.pdf.PdfWriter;// 
// définir la classe Centrale et ses méthodes
public class Centrale {

        // attributs de la classe Centrale; 
        private static Connection conn; // attribut de type Connexion:
        private static ResultSet result_lecture;// attribut pour récuperer un resultat d'une requete (juste lecture ); 
        private static ResultSet result_misajour; // attribut pour mettre a jour des données a l'aide d'une requete (mettre a jour des données );
        private static Statement stat_lecture; // créer une statement pour  
        private static Statement stat_misajour;
        private static String requetesolde ,requetedepot, requeteExist, requeteNumClient;
        private static String requeteOperation, requeteretrait, requetecode, requeteDate;
        private static String dateCourante, numClient, monCode; 
        private static float nouveausolde;
        private static Date dateImmediate;
        private static DateFormat dateFormat;
        
        
        // methode pour modifier les coordonnées de connexion;
        public static void setInfos() {
                
                Connexion.setUsername("root");
                Connexion.setPassword("");
                Connexion.setUrl("jdbc:mysql://localhost/bdd_interne?serverTimezone=UTC");
        }
        
        // -------------------------------------------------------------------------
        
        
        
        public static void setCodeUser(String num_carte, String codeUser) throws SQLException, UnsupportedEncodingException, NoSuchAlgorithmException {
        	try {
				
    			
            	conn = Connexion.connect();
                stat_misajour =(Statement) conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

                
                String requeteCodeUser = "UPDATE carte SET code ='"+Securite.SHA(num_carte)+Securite.MD5(codeUser)+"' WHERE ref_carte = '"+num_carte+"';";
             
                stat_misajour.execute(requeteCodeUser);
                
            	} catch (Exception e) {
    				// TODO: handle exception
    			}
            	 finally {
                     if (result_lecture != null){
                             result_lecture.close();
                     }
                     if (stat_lecture != null){
                             stat_lecture.close();
                     }
                     if(conn != null) {
                             conn.close();
                     }
            	 }
        	
        	
            
        }
        
        
        
        //------ la méthode getSolde ............... retourne un float (le solde du compte)-------//
public static float getSolde( String num_carte) throws SQLException {
                // capturer l'erreur si elle aura lieu;
                try {
                        // se connecter a la bdd;
                        conn = Connexion.connect();
                        stat_lecture =(Statement) conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                
                        // créer la requête pour récupérer le solde du compte; 
                        requetesolde = "SELECT solde FROM compte WHERE num_client IN (SELECT num_client FROM carte WHERE ref_carte = '"+num_carte+"');";
                        // exécuter la requete et mettre son resultat dans la variable result_lecture;
                        result_lecture = stat_lecture.executeQuery(requetesolde);
                        result_lecture.last();
                        float sld = result_lecture.getFloat("solde");
                       // pour afficher le 
                        return sld;// retourner le solde du compte;
                        }
                
                // faire un catch pour s'informer sur l'erreur si elle aura lieu;
                catch(SQLException e){
                        System.err.println("Error : "+e.getMessage());
                        System.err.println("Code : "+e.getErrorCode());
                }
                // fermer la connexion et la requete apres l'appel; 
                finally {
                        if (result_lecture != null){
                                result_lecture.close();
                        }
                        if (stat_lecture != null){
                                stat_lecture.close();
                        }
                        if(conn != null) {
                                conn.close();
                        }
                }
                return 0;
        }
        
        // -------- méthode IsExist - vérifie l'existence de la carte dans la bdd----------//
public static boolean IsExist(String num_carte) throws SQLException {
	
	try {
        conn= Connexion.connect();
        stat_lecture = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        
        // requête pour selectionner le ref_carte de la bdd
        requeteExist = ("SELECT ref_carte FROM carte WHERE ref_carte='"+num_carte+"';");
        result_lecture = stat_lecture.executeQuery(requeteExist);
        result_lecture.last();
        return(result_lecture.getRow()!=0);
		}
        catch(SQLException e) {
                System.err.println("Error : "+e.getMessage());// afficher le message d'erreur clairement.
                System.err.println("Code : "+e.getErrorCode()); // afficher le code de l'erreur si elle se presente.  
        }
        finally {
               
                if (stat_lecture != null){
                        stat_lecture.close();
                }
                if (result_lecture != null){
                    result_lecture.close();
                }
                if(conn != null) {
                        conn.close();
                }
        }
		return false;
        
        
}
//-----------------------------------------------------------------------------------------        
// -------- méthode IsExiste - vérifie l'existence de la carte dans la bdd----------//
public static boolean IsValid(String num_carte) throws SQLException {
        try {
                conn = Connexion.connect();
                stat_lecture = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        
                dateImmediate = new Date();// créer une nouvelle date ;
                dateFormat = new SimpleDateFormat("yyyy-MM-dd");// doner le format date souhaité;
                dateCourante = dateFormat.format(dateImmediate);// créer la nouvelle date sous le format voulu;
                
                requeteDate = "SELECT date_expir FROM carte WHERE ref_carte = '"+num_carte+"';"; // requete pour selectionner la date d'expiration de la carte ;
                result_lecture = stat_lecture.executeQuery(requeteDate);// 
                result_lecture.last();// recupérer le resultat de la requette ;
                boolean rs = (dateCourante.compareTo(result_lecture.getString("date_expir"))<=0);// retourne un boolean en comparant la date du jour avec la date d'expiration de la carte ; 
               
                return(rs);//retourner le boolean;
        }
        catch(SQLException e){
                System.err.println("Error : "+e.getMessage());// afficher le message d'erreur clairement.
                System.err.println("Code : "+e.getErrorCode()); // afficher le code de l'erreur si elle se presente.  
        }
        finally{
                if (result_lecture != null){
                        result_lecture.close();                // fermer l'execution de la requete
                }
                if (stat_lecture != null){
                        stat_lecture.close();                // fermer la Statement qu'on a créée.
                }
                if(conn != null) {
                        conn.close();                // fermer la connexion a la base de données.
                }
        }
        
        return true;
}
        
        
        // -------- méthode Opération - inserer l'operation effectuée a la bdd-----------------//
public static void operation(String num_carte, float montant, String action_operation) throws SQLException {
                
                try {
                conn = Connexion.connect();// se connecter a la base de donnée;
                stat_misajour =(Statement) conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                
                dateImmediate = new Date();
                dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// créer une nouvelle date avec se format passé en argument;
                dateCourante = dateFormat.format(dateImmediate);
                requeteNumClient = "SELECT num FROM client, carte WHERE num_client = num and ref_carte = '"+num_carte+"'; ";
                result_misajour = stat_misajour.executeQuery(requeteNumClient);// executer la requete sql;
                result_misajour.last();// recup le resultat de la requete ;
                numClient = result_misajour.getString("num");// recupérer le numero du client a partir de la colonne "num";
                requeteOperation = "INSERT INTO operation(date_oprt, somme, action_oprt, num_client) VALUES ('"+dateCourante+"','"+montant+"','"+action_operation+"','"+numClient+"');";
                stat_misajour.execute(requeteOperation);
                }
                catch(SQLException e){
                        System.err.println("Error : "+e.getMessage());
                        System.err.println("Code : "+e.getErrorCode());        
                }
                finally {
                        if(result_misajour != null) {
                                result_misajour.close();
                        }
                        if(stat_misajour != null) {
                                stat_misajour.close();
                        }
                        if(conn != null) {
                                conn.close();
                        }
                }        
        }
//-----------------------------------------------------------------------------------------        
        
        // -------- méthode dépot - mettre a jour le solde du compte apres un depot-----------------//
        public final static void depot (String num_carte, float montant) throws SQLException {
                
                try {
                        //etalir une connexion a la bdd; 
                        conn = Connexion.connect();
                
                        // créer une Statement;
                        stat_misajour = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE , ResultSet.CONCUR_READ_ONLY);
                        nouveausolde = getSolde(num_carte)+montant;
                        
                        // créer la requette en sql;
                        requetedepot = "UPDATE compte SET solde = '"+nouveausolde+"' WHERE num_client=(SELECT num_client FROM carte WHERE ref_carte = '"+num_carte+"'); ";
                        stat_misajour.executeUpdate(requetedepot);
                        // affichage de l'opération éffectuée ;
                      
                        operation(num_carte, montant, "depot");
                }
                
                // faire un catch pour s'informer sur l'erreur si elle aura lieu;
                catch(SQLException e) {
                        System.err.println("Error : "+e.getMessage());
                        System.err.println("Code : "+e.getErrorCode());
                }
                finally {
                        if(result_misajour != null) {
                                result_misajour.close();
                        }
                        if(stat_misajour != null) {
                                stat_misajour.close();
                        }
                        if(conn != null) {
                                conn.close();
                        }
                }
        }
        
        //-----------------------------------------------------------------------------------------        
        
        // -------- méthode retrait - mettre a jour le solde du compte apres le retrait-------//
        public static boolean retrait (String num_carte, float montant) throws SQLException {
                boolean res=true;
                try {
                        conn = Connexion.connect();
                        stat_misajour = (Statement) conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                        
                        nouveausolde = getSolde(num_carte) - montant;
                        requeteretrait = "UPDATE compte SET solde='"+nouveausolde+"' WHERE num_client=(SELECT num_client FROM carte WHERE ref_carte='"+num_carte+"');";
                        float solde = getSolde(num_carte);
                        float squot = getSeuilQuot(num_carte);
                        float shebdo = getSeuilHebdo(num_carte);
                        // String getSeuil = "SELECT seuil from compte WHERE num_client = (SELECT num_client from carte WHERE ref_carte = '"+num_carte+"');";
                        
                        // On suppose que le client a le droit au découvert jusqu'à -100€;
                        // tester si le compte a un solde >= -100€, pour donner accès au retrait; 
                        if(solde <= 0) {                           
                                return !res;
                        }
                        // tester si le seuil quotidien est > 0 pour pouvoir retirer   
                        if((squot - montant)<0) {                             
                        	return !res;                        }
                        else if((shebdo - montant)<0){                             
                        	return !res;
                        }
                        
                        stat_misajour.execute(requeteretrait);
                        
                        // affichage de l'opération éffectuée ;
                      
                        operation(num_carte, montant, "retrait");
                        setSeuil(num_carte, montant);
                        
                        
                }
                        
                // faire un catch pour s'informer sur l'erreur si y aura lieu;
                catch(SQLException e) {
                        System.err.println("Error : "+e.getMessage());
                        System.err.println("Code : "+e.getErrorCode());
                }
                finally {
                        if(result_misajour != null) {
                                result_misajour.close();
                        }
                        if(stat_misajour != null) {
                                stat_misajour.close();
                        }
                        if(conn != null) {
                                conn.close();
                        }
                }
				return res;
}
        
//-----------------------------------------------------------------------------------------                
        
        // -------- méthode CODE - mettre a jour le solde du compte apres le retrait-------//
public static String getCode(String num_carte) throws SQLException {
        
        try {
        conn = Connexion.connect();
        stat_lecture = (Statement) conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        
        requetecode = "SELECT code FROM carte WHERE ref_carte = '"+num_carte+"';";
        result_lecture = stat_lecture.executeQuery(requetecode);
        result_lecture.last();
        monCode = result_lecture.getString("code");
       
        return monCode;
        }
        catch(SQLException e) {
                System.err.println("Error : "+e.getMessage());
                System.err.println("Code : "+e.getErrorCode());
        }
        finally {
                if(result_lecture != null) {
                        result_lecture.close();
                }
                if(stat_lecture != null) {
                        stat_lecture.close();
                }
                if(conn != null) {
                        conn.close();
                }
        }
        return null;
}
//-----------------------------------------------------------------------------------------        
// methode SeuilQuotidien- pour récupérer le seuil quotidien-----------------------------------
        
public static float getSeuilQuot (String num_carte) throws SQLException {
        try {
        // ouvrir une connexion a la bdd...........
        conn = Connexion.connect();
        // créer une statement.........
        stat_lecture = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        
        String requeteSeuilQ = "SELECT seuilQuot FROM compte WHERE         num_client = (SELECT num_client FROM carte WHERE ref_carte = '"+num_carte+"');";
        
        result_lecture = stat_lecture.executeQuery(requeteSeuilQ);
        result_lecture.last();
        float monseuilQ = (result_lecture.getFloat("seuilQuot"));
      
        return (monseuilQ);
        
        
        }catch(SQLException e) {
                System.err.println("Error : "+e.getMessage());
                System.err.println("Code : "+e.getErrorCode());
        }
        finally {
                if(result_lecture != null) {
                        result_lecture.close();
                }
                if(stat_lecture != null) {
                        stat_lecture.close();
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
                stat_lecture = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                
                String requeteSeuilH = "SELECT seuilHebdo FROM compte WHERE num_client = (SELECT num_client FROM carte WHERE ref_carte = '"+num_carte+"');";
                
                result_lecture = stat_lecture.executeQuery(requeteSeuilH);
                result_lecture.last();
                float monseuilH = (result_lecture.getFloat("seuilHebdo"));
            
                return (monseuilH);
                
                
                }catch(SQLException e) {
                        System.err.println("Error : "+e.getMessage());
                        System.err.println("Code : "+e.getErrorCode());
                }
                finally {
                        if(result_lecture != null) {
                                result_lecture.close();
                        }
                        if(stat_lecture != null) {
                                stat_lecture.close();
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
                stat_misajour = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        
                 float nouveauSeuil = getSeuilQuot(num_carte)-montant;
                 String requeteMAJseuil = "UPDATE compte SET seuilQuot = '"+nouveauSeuil+"' WHERE num_client = (SELECT num_client FROM carte WHERE ref_carte = '"+num_carte+"');";
                 stat_misajour.execute(requeteMAJseuil);
         
                 nouveauSeuil = getSeuilHebdo(num_carte)-montant;
                 requeteMAJseuil = "UPDATE compte SET seuilHebdo = '"+nouveauSeuil+"' WHERE num_client = (SELECT num_client FROM carte WHERE ref_carte = '"+num_carte+"');";
                 stat_misajour.execute(requeteMAJseuil);
                }
                catch(SQLException e) {
                        System.err.println("Error : "+e.getMessage());
                        System.err.println("Code : "+e.getErrorCode());
                }
        finally {
                if(result_misajour != null) {
                        result_misajour.close();
                }
                if(stat_misajour != null) {
                        stat_misajour.close();
                }
                if(conn != null) {
                        conn.close();
                }
        }
}
// -----------------------methode getRib en PDF--------------------//
        public static final void getRib(String num_carte) throws SQLException, DocumentException, IOException {
                
                Document monrib = new Document(PageSize.ID_3);
                StringBuilder st = new StringBuilder();
                try {
                        conn = Connexion.connect();
                        stat_lecture = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                        
                        PdfWriter.getInstance(monrib, new FileOutputStream("C:/Users/portable/Documents/ProgrammationJava/MonProjetF/MonRib_Historique/Rib.pdf"));
                        
                        String requeteGetRib = "SELECT cl.num, cl.nom, cl.prenom, cl.dtNaiss, cl.adresse, cl.profession, cl.numero_tel, co.solde, co.RIB, co.IBAN\r\n"
                                        + "FROM client as cl, compte as co, carte as ca WHERE\r\n"
                                        + " cl.num = co.num_client AND co.num_client = ca.num_client AND ca.ref_carte = '"+num_carte+"';";
                        result_lecture = stat_lecture.executeQuery(requeteGetRib);
                        result_lecture.last();
                        monrib.open();// ouvrir le fichier pdf;
                        
                        //ajouter les informations au fichier ;
                        st.append("Relevé bancaire du Monsieur : "); 
                        st.append((result_lecture.getString("cl.nom")+" \t\t"));
                        st.append((result_lecture.getString("cl.prenom")+"\n"));
                        st.append(("Date de naissance : "+result_lecture.getString("cl.dtNaiss")+"\n"));
                        st.append(("Adresse : "+result_lecture.getString("cl.adresse")+"\n"));
                        st.append(("Profession : "+result_lecture.getString("cl.profession")+"\n"));
                        st.append(("Numéro de Tél : "+result_lecture.getString("cl.numero_tel")+"\n"));
                        st.append(("RIB : "+result_lecture.getString("co.RIB")+"\n"));
                        st.append(("IBAN : "+result_lecture.getString("co.IBAN")+"\n"));
                        
                        String mesInfos = st.toString(); 
                        monrib.addTitle("Relevé d'identité bancaire du M.:"+result_lecture.getString("cl.nom"));
                        
                        monrib.add(new Paragraph(mesInfos));
                        
                        monrib.close();// ferler le fichier apres insertion de toutes les informations;
                        operation(num_carte, 0, "Impression_Rib");// insérer une opération d'impression du rib au opérations;
                        
                        // ouvrir le fichier conçu;
                        Desktop.getDesktop().open(new File("C:/Users/portable/Documents/ProgrammationJava/MonProjetF/MonRib_Historique/Rib.pdf"));
                                        
                        
                } // gérer les différentes exceptions qui surviennent;
                catch(SQLException e) {
                        System.err.println("Error : "+e.getMessage());
                        System.err.println("Code : "+e.getErrorCode());
                }
                catch(DocumentException s){
                        System.err.println("Error : "+s.getMessage());
                        System.err.println("Trace : "+s.getStackTrace());
                }
                catch(FileNotFoundException y){
                        System.err.println("Error : "+y.getMessage());
                        System.err.println("Trace : "+y.getStackTrace());
                }
                finally {
                        if(result_lecture != null) {
                                result_lecture.close();
                        }
                        if(stat_lecture != null) {
                                stat_lecture.close();
                        }
                        if(conn != null) {
                                conn.close();
                        }
                }
                
                
                
        }
        
        // ---------------------GetHistorique --------------------------// 
        public static final void getHistorique(String num_carte) throws IOException, SQLException, DocumentException {
                
                Document monHistorique = new Document(PageSize.A4); // créer un neauveau document avec un format A4;
                StringBuilder st = new StringBuilder();  
                
                try {
                        // connecter a la bdd;
                        conn = Connexion.connect();
                        stat_lecture = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                        
                        // créer un nouveau fichier pdf ;
                        PdfWriter.getInstance(monHistorique, new FileOutputStream("C:/Users/portable/Documents/ProgrammationJava/MonProjetF/MonRib_Historique/Historique.pdf"));
                        
                        // requete sql pour recuperer les données depuis la bdd;
                        String requeteHistor = "SELECT op.num_client, op.date_oprt, op.action_oprt, op.somme, cl.num, cl.nom, cl.prenom "
                                        + "FROM operation AS op, client AS cl, carte AS ca\r\n"
                                        + "WHERE op.num_client = cl.num AND op.num_client = ca.num_client AND ref_carte = '"+num_carte+"';"; 
                        
                        result_lecture = stat_lecture.executeQuery(requeteHistor);
                      
                        
                        monHistorique.open();// ouvrir le fichier pdf;
                        
                        result_lecture.first();
                       		st.append("Relevé bancaire du M."+result_lecture.getString("cl.nom"));
                            st.append(" "+result_lecture.getString("cl.prenom")+"\n\n");
                        
                        while(result_lecture.next()) {
                                st.append("Numero de client : "+ result_lecture.getString("num_client")+" \t");
                                st.append("\t Date : "+ result_lecture.getString("date_oprt")+" \t");
                                st.append("Opération : "+ result_lecture.getString("action_oprt")+" \t");
                                st.append("Montant : "+ result_lecture.getString("somme")+"€ \n\n");
                        }
                        
                        String mouvements = st.toString();
                        monHistorique.addTitle("Relevé bancaire du client");// rajouter un titre au fichier;        
                        monHistorique.add(new Paragraph(mouvements));// insérer les informations au fichier pdf;
                        monHistorique.close();// fermer le fichier;
                        
                        // ouvrir le document générer pour le client; 
                        Desktop.getDesktop().open(new File("C:/Users/portable/Documents/ProgrammationJava/MonProjetF/MonRib_Historique/Historique.pdf"));
                        operation(num_carte, 0, "Impr_Historique");// insérer une opération d'impression du rib au opérations;
                    
                       
                } 
                catch(SQLException e) // gérer les exceptions du type SQLException;
                {  
                        System.err.println("Error : "+e.getMessage());
                        System.err.println("Code : "+e.getErrorCode());
                }
                catch(DocumentException s)  // gérer les exceptions du type DocumentException;
                {
                        System.err.println("Error : "+s.getMessage());
                        System.err.println("Trace : "+s.getStackTrace());
                }
                catch(FileNotFoundException y)  // gérer les exceptions du type FileNotFoundException;
                {
                        System.err.println("Error : "+y.getMessage());
                        System.err.println("Trace : "+y.getStackTrace());
                }
                finally // ce bloque s'exécutera dans l cas ou y'aura une exception ou non;
                {
                        if(result_lecture != null) {
                                result_lecture.close();                // fermer le ResultSet;
                        }
                        if(stat_lecture != null) {  
                                stat_lecture.close();// fermer la statement;
                        }
                        if(conn != null) {
                                conn.close();   // fermer la connexion a la bdd;
                        }
                }
			
			
        }
        
        
 //-------------------------------------Methode getTicket-----------------------------------------------------
        

public static void getTicket() throws SQLException, IOException {
    Document monSoldeTicket = new Document(PageSize.A7);
     StringBuilder st = new StringBuilder();
     Date date = new Date();
     SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     String dateCourante = dateFormat.format(date);
     try {
             conn = Connexion.connect();
             stat_lecture = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            
             PdfWriter.getInstance(monSoldeTicket, new FileOutputStream("C:/Users/portable/Documents/ProgrammationJava/MonProjetF/MonRib_Historique/Ticket.pdf"));
            
             String requeteGetTicket = "SELECT num_compte FROM compte WHERE num_client =(SELECT num_client FROM carte WHERE ref_carte ='"+AccueilController.getNumeroDeCarte()+"');";
                            
             result_lecture = stat_lecture.executeQuery(requeteGetTicket);
             result_lecture.last();
             monSoldeTicket.open();// ouvrir le fichier pdf;
            
             //ajouter les informations au fichier ;
             st.append("Banque Pro \n");
             st.append(dateCourante+"\n");
             st.append(ConfirmationController.getModeOperation()+" D'ESPECES \n");
             st.append("Compte : "+(result_lecture.getString("num_compte"))+"\n");
            
             if (ConfirmationController.getModeOperation().equals("Depot")) {
                 st.append("Montant : "+depotController.getMontantDepot()+"€ \n");}
             else {st.append("Montant : "+RetraitController.getMontantRetrait()+"€ \n");}
            st.append("(TICKET A CONSERVER) \n");
            st.append("Ticket de "+ConfirmationController.getModeOperation()+"\n");
            
             String mesInfos = st.toString();
             monSoldeTicket.addTitle("Ticket de "+ConfirmationController.getModeOperation());
            
             monSoldeTicket.add(new Paragraph(mesInfos));
             
             monSoldeTicket.close();// ferler le fichier apres insertion de toutes les informations;
             operation(AccueilController.getNumeroDeCarte(), 0, "Impression_Ticket");// insérer une opération d'impression du rib au opérations;
            
             // ouvrir le fichier conçu;
             Desktop.getDesktop().open(new File("C:/Users/portable/Documents/ProgrammationJava/MonProjetF/MonRib_Historique/Ticket.pdf"));
                            
            
     } // gérer les différentes exceptions qui surviennent;
     catch(SQLException e) {
             System.err.println("Error : "+e.getMessage());
             System.err.println("Code : "+e.getErrorCode());
     }
     catch(DocumentException s){
             System.err.println("Error : "+s.getMessage());
             System.err.println("Trace : "+s.getStackTrace());
     }
     catch(FileNotFoundException y){
             System.err.println("Error : "+y.getMessage());
             System.err.println("Trace : "+y.getStackTrace());
     }
     finally {
             if(result_lecture != null) {
                     result_lecture.close();
             }
             if(stat_lecture != null) {
                     stat_lecture.close();
             }
             if(conn != null) {
                     conn.close();
             }
     }
    
    
    
	}


// ------------------------methode getsoldeTicket -----------------------------------------------


public static void getSoldeTicket () throws SQLException, IOException {
    Document monSoldeTicket = new Document(PageSize.A7);
     StringBuilder st = new StringBuilder();
     Date date = new Date();
     SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     String dateCourante = dateFormat.format(date);
     try {
             conn = Connexion.connect();
             stat_lecture = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            
             PdfWriter.getInstance(monSoldeTicket, new FileOutputStream("C:/Users/portable/Documents/ProgrammationJava/MonProjetF/MonRib_Historique/SoldeTicket.pdf"));
            
             String requeteGetTicket = "SELECT * FROM compte WHERE num_client =(SELECT num_client FROM carte WHERE ref_carte ='"+AccueilController.getNumeroDeCarte()+"');";
                            
             result_lecture = stat_lecture.executeQuery(requeteGetTicket);
             result_lecture.last();
             monSoldeTicket.open();// ouvrir le fichier pdf;
            
             //ajouter les informations au fichier ;
           
             st.append("Banque Pro \n");
             st.append("Ticket de consultation de solde\n");
             st.append(dateCourante+"\n");
             st.append("Compte : "+(result_lecture.getString("num_compte"))+"\n");                        
             st.append("Votre solde est de : "+result_lecture.getString("solde")+"€ \n");
             st.append("(TICKET A CONSERVER) \n");
             
            
             String mesInfos = st.toString();
             monSoldeTicket.addTitle("Ticket de consultation de solde");
            
             monSoldeTicket.add(new Paragraph(mesInfos));
             
             monSoldeTicket.close();// ferler le fichier apres insertion de toutes les informations;
             operation(AccueilController.getNumeroDeCarte(), 0, "Impression_SoldeTicket");// insérer une opération d'impression du rib au opérations;
            
             // ouvrir le fichier conçu;
             Desktop.getDesktop().open(new File("C:/Users/portable/Documents/ProgrammationJava/MonProjetF/MonRib_Historique/SoldeTicket.pdf"));
                            
            
     } // gérer les différentes exceptions qui surviennent;
     catch(SQLException e) {
             System.err.println("Error : "+e.getMessage());
             System.err.println("Code : "+e.getErrorCode());
     }
     catch(DocumentException s){
             System.err.println("Error : "+s.getMessage());
             System.err.println("Trace : "+s.getStackTrace());
     }
     catch(FileNotFoundException y){
             System.err.println("Error : "+y.getMessage());
             System.err.println("Trace : "+y.getStackTrace());
     }
     finally {
             if(result_lecture != null) {
                     result_lecture.close();
             }
             if(stat_lecture != null) {
                     stat_lecture.close();
             }
             if(conn != null) {
                     conn.close();
             }
     }
    
    
    
	}


}

