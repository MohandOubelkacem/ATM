package application;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Securite {

	// Méthode pour convertir les cases d'un tableau de Byte en Hexadecimal
	public static String ByteToHex(byte[] TabByte) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < TabByte.length; i++) {
			sb.append(Integer.toString((TabByte[i] & 0xff) + 0x100, 16).substring(1));
		}
		return sb.toString();
	}

	// Cette méthode Permet de HACHER un String passé en parametre en SHA-256
	public static String SHA(String code) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		byte[] byteAux = code.getBytes("UTF-8");
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(byteAux);
		byte[] code2_0 = md.digest();		
		return ByteToHex(code2_0);
	}

	// Cette méthode Permet de HACHER un String passé en parametre en SHA-256
	public static String MD5(String code) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		byte[] byteAux = code.getBytes("UTF-8");
		MessageDigest md = MessageDigest.getInstance("md5");
		md.update(byteAux);
		byte[] code2_0 = md.digest();
		return ByteToHex(code2_0);
	}


	// Cette methode va comparer le code secret entré par le client avec le code
	// Secret HACHÉ qui se trouve dans la base de donnée
	public static boolean egal(String code_utili, String code_bdd) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		String aux = SHA(AccueilController.getNumeroDeCarte()) + MD5(code_utili);
		return aux.equals(code_bdd);

	}

}
