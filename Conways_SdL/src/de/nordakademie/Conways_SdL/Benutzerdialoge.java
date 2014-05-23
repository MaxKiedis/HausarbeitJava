package de.nordakademie.Conways_SdL;

import javax.swing.JOptionPane;

public class Benutzerdialoge {

    public static String zeigePfadeingabe() {
	String input;
	do {
	    input = Benutzerdialoge.zeigeInputDialog();
	    if (input == null) {
		System.exit(0);
	    }
	} while (istDateipfadLeer(bereiteDateipfadAuf(input)));
	System.out.println(input);
	return input;
    }

    public static void zeigeFehlermeldung(final String message) {
	JOptionPane.showMessageDialog(null, message);
    }

    public static void zeigeInfoFenster(final String message) {
	JOptionPane.showMessageDialog(null, message);
    }

    public static String zeigeInputDialog() {
	String eingabe = JOptionPane
		.showInputDialog("Bitte den Dateinamen (OHNE Endung /'.start/') eingeben. Vorkonfigurierter Pfad ist: "
			+ System.getProperty("user.home")
			+ "/spiel_des_lebens/");
	return eingabe;
    }

    public static int zeigeSpielmoduseingabe() {
	Object[] options = { "Leben ohne Tod", "34 Leben", "Standard" };
	int n = JOptionPane.showOptionDialog(null,
		"Waehlen Sie den Spielmodus", "Spielmodus",
		JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
		options, options[0]);
	return n;
    }

    public static int zeigeRandverhalteneingabe() {
	Object[] options = { "Pacman Universum", "Mauer des Todes" };
	int n = JOptionPane.showOptionDialog(null,
		"Waehlen Sie das Randverhalten", "Randverhalten",
		JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
		options, options[0]);
	return n;
    }

    private static boolean istDateipfadLeer(final String dateipfad) {
	if (dateipfad.equals("")) {
	    Benutzerdialoge
		    .zeigeFehlermeldung("Bitte geben sie einen Dateinamen ein!");
	    return true;
	}
	return false;
    }

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
