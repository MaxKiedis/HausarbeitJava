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

    public static int showSpielmodus() {
	Object[] options = { "Leben ohne Tod", "34 Leben", "Standard" };
	int n = JOptionPane.showOptionDialog(null, "Waehlen Sie den Spielmodus", "Spielmodus",
		JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
	return n;
    }
    
    public static int showRandverhalten() {
	Object[] options = { "Pacman Universum", "Mauer des Todes" };
	int n = JOptionPane.showOptionDialog(null, "Waehlen Sie das Randverhalten", "Randverhalten",
		JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
	return n;
    }
}
