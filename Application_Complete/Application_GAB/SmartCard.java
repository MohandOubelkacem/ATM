package application;
import java.util.List;

import javax.smartcardio.Card;
import javax.smartcardio.CardException;
import javax.smartcardio.CardTerminal;
import javax.smartcardio.TerminalFactory;

public class SmartCard {

	private static CardTerminal terminal;

	// Constructeur -----------------------------------------------
	public SmartCard() {
		try {

			TerminalFactory factory = TerminalFactory.getDefault();
			List<CardTerminal> terminals = factory.terminals().list();

			terminal = (CardTerminal) terminals.get(1);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		// time= A DEFINIR

	}
	
	public boolean estla() throws CardException {
		return terminal.isCardPresent();
		
	}

	// ----------------------------------------------------

	public String toString(byte[] bytes) {
		StringBuffer sbTmp = new StringBuffer();
		for (byte b : bytes) {
			sbTmp.append(String.format("%X", b));
		}
		return sbTmp.toString();
	}

	// ------------------------------------------------------

	public String getNumCarte() throws CardException {
		Card card = terminal.connect("T=0");
		System.out.println("Card: " + card);

		// Get ATR
		byte[] baATR = card.getATR().getBytes();
		card.disconnect(true);
		return this.toString(baATR);

	}
	
	public boolean attendreCarteIN () throws CardException {
		
		return terminal.waitForCardPresent(0);
			
	}
	
	
	public boolean attendreCarteOUT () throws CardException {
		return terminal.waitForCardAbsent(0);
	}

}