package de.nordakademie.Conways_SdL;

import javax.swing.JOptionPane;

public class Benutzerdialoge {
    public static void gebeFehler(String message) {
	JOptionPane.showMessageDialog(null, message);
    }

    public static String zeigeInputDialog() {
	String eingabe = JOptionPane
		.showInputDialog("Bitte den Dateinamen (mit Endung /'.start/') eingeben. Vorkonfigurierter Pfad ist: "
			+ System.getProperty("user.home") + "/spiel_des_lebens/");
	return eingabe;
    }
}
