package de.nordakademie.Conways_SdL;

import javax.swing.JOptionPane;

/**
 * Stellt Funktionen zur grafischen Benutzerinteraktion bereit.
 * 
 * @author kaineubauer
 */
public class Benutzerinterface {

    /**
     * Privater Konstruktor, da niemand Objekte erzeugen sollte
     */
    private Benutzerinterface() {
    }

    /**
     * Zeigt einen Inputdialog und prueft den eingegebenen Wert. Bereitet diesen
     * ggf. auf.
     * 
     * @return aufbereiteter Dateiname/pfad
     */
    public static String ermittlePfad() {
	String input;
	do {
	    input = Benutzerinterface.zeigeInputDialog();
	    if (input == null) {
		System.exit(0);
	    }
	} while (istDateipfadLeer(bereiteDateipfadAuf(input)));
	return input;
    }

    /**
     * Zeigt eine MessageDialogBox an
     * 
     * @param message
     *            anzuzeigender String
     */
    public static void zeigeInfoFenster(final String message) {
	JOptionPane.showMessageDialog(null, message);
    }

    /**
     * Zeigt eine InputDialogBox an
     * 
     * @return Eingabewert
     */
    public static String zeigeInputDialog() {
	String eingabe = JOptionPane
		.showInputDialog("Bitte den Dateinamen (OHNE Endung /'.start/') eingeben. Vorkonfigurierter Pfad ist: "
			+ System.getProperty("user.home")
			+ "/spiel_des_lebens/");
	return eingabe;
    }

    /**
     * Zeigt eine OptionDialogBox an mit waehlbaren Spielmodi
     * 
     * @return gewaehlter Spielmodus
     */
    public static int zeigeSpielmoduseingabe() {
	Object[] options = { "Leben ohne Tod", "34 Leben", "Standard" };
	int n = JOptionPane.showOptionDialog(null,
		"Waehlen Sie den Spielmodus", "Spielmodus",
		JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
		options, options[0]);
	return n;
    }

    /**
     * Zeigt eine OptionDialogBox an mit waehlbarem Randverhalten
     * 
     * @return gewaehltes Randverhalten
     */
    public static int zeigeRandverhalteneingabe() {
	Object[] options = { "Pacman Universum", "Mauer des Todes" };
	int n = JOptionPane.showOptionDialog(null,
		"Waehlen Sie das Randverhalten", "Randverhalten",
		JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
		options, options[0]);
	return n;
    }

    /**
     * ueberprueft ob der uebergebene String leer ist
     * 
     * @param dateipfad
     *            zu ueberpruefender String
     * @return Wahrheitswert bzgl. StringLaenge
     */
    private static boolean istDateipfadLeer(final String dateipfad) {
	if (dateipfad.equals("")) {
	    Benutzerinterface
		    .zeigeInfoFenster("Bitte geben sie einen Dateinamen ein!");
	    return true;
	}
	return false;
    }

    /**
     * Liest String ein und ergaenzt ggf. die Sonderzeichen "/" bzw. "\"
     * 
     * @param unverarbeiteterDateipfad
     *            Dateipfad
     * @return aufbereiteter Dateipfad
     */
    private static String bereiteDateipfadAuf(
	    final String unverarbeiteterDateipfad) {
	String verarbeiteterDateipfad = "";
	for (int i = 0; i < unverarbeiteterDateipfad.length(); i++) {
	    if (unverarbeiteterDateipfad.charAt(i) == '/') { // Abfrage fuer
							     // UNIX
		verarbeiteterDateipfad += "/";
	    }
	    // }else if(unverarbeiteterDateipfad.charAt(i) == '\'){ // Abfrage
	    // fuer Windows
	    // verarbeiteterDateipfad += "\";
	    // }
	    verarbeiteterDateipfad += unverarbeiteterDateipfad.charAt(i);
	}
	return verarbeiteterDateipfad;
    }
}
