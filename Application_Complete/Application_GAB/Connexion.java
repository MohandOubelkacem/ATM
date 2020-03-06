package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {

	private static String username ;
	private static String password;
	private static String url;

	
	// methode de modification du nom d'utilisateur
	protected static void setUsername(String username) {
		Connexion.username = username;
	}
	
	// methode de modification du mot de passe de la base de données
	protected static void setPassword(String password) {
		Connexion.password = password;
	}
	
	// methode de modification de l'url la base de données
	protected static void setUrl(String url) {
		Connexion.url = url;
	}
	
		public static Connection connect() throws SQLException {
				return DriverManager.getConnection(url, username, password);
	}
	
}
