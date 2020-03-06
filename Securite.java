import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Securite {


        // Méthode pour convertir les cases d'un tableau de Byte en Hexadecimal
        public static String ByteToHex (byte [] TabByte) {
                StringBuffer sb = new StringBuffer();
        for (int i = 0; i < TabByte.length; i++) {
         sb.append(Integer.toString((TabByte[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
        }


        //Cette méthode Permet de HACHER un String passé en parametre en SHA-256
        public static String SHA (String code) throws UnsupportedEncodingException, NoSuchAlgorithmException {
                byte [] byteAux = code.getBytes("UTF-8");
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                md.update(byteAux);
                byte [] code2_0 = md.digest();


       // System.out.println( "avant =  "+code+ " voilaa "+code2_0.toString() );
       // return code2_0.toString();
        return ByteToHex(code2_0);
        }



        //Cette méthode Permet de HACHER un String passé en parametre en SHA-256
        public static String MD5 (String code) throws UnsupportedEncodingException, NoSuchAlgorithmException {
                byte [] byteAux = code.getBytes("UTF-8");

                MessageDigest md = MessageDigest.getInstance("md5");
                md.update(byteAux);
                byte [] code2_0 = md.digest();

                return ByteToHex(code2_0);
        }



        //Cette méthode permet d'avoir la date du jour
        public static String ajd () {//https://www.developpez.net/forums/d1536724/java/general-java/obtenir-date-jour-format-jour-mois-annee-heure/
                 final Date date = new Date();
               return new SimpleDateFormat("dd-MM-yyyy").format(date);

        }



        //Cette methode va comparer le code secret entré par le client avec le code Secret HACHÉ qui se trouve dans la base de donnée
        public static boolean egal (String code_utili, String code_bdd) throws UnsupportedEncodingException, NoSuchAlgorithmException {

                String date=ajd();
                //System.out.println("copier ca "+MD5(code_utili));
                String aux = date + MD5(code_utili);
                //System.out.println("aux = "+aux);
                String aux2= date + code_bdd;
                //System.out.println("aux2 = "+aux2);

                return (SHA(aux).equals( SHA(aux2)) );



        }

        }
