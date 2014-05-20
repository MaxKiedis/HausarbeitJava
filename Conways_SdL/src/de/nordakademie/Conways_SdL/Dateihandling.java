package de.nordakademie.Conways_SdL;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Dateihandling {
    public static Spielfeld oeffneDatei() {
	String eingabe = null;
	do {
	    eingabe = pruefeDateipfad(Benutzerdialoge.zeigeInputDialog());
	} while (istDateipfadLeer(eingabe));

	File datei = new File(System.getProperty("user.home") + "//spiel_des_lebens//" + eingabe);

	FileReader fileReader = null;
	BufferedReader bufferedReader = null;

	try {
	    fileReader = new FileReader(datei);
	    bufferedReader = new BufferedReader(fileReader);

	    Spielfeld spielfeld = null;

	    while (bufferedReader.ready()) {
		String zeile = bufferedReader.readLine();
		System.out.println(zeile);
		// Pruefe Inhalt
		if (!pruefeDateiinhalt(zeile)) {
		    Benutzerdialoge.gebeFehler("Es sind verbotene Zeichen in der Datei");
		    return null;
		}
		// Pruefe Format
		if (!pruefeZeilenlaenge(zeile)) {
		    Benutzerdialoge.gebeFehler("Die Daten haben nicht die richtige Laenge!");
		    return null;
		}

		// Zeile Konvertieren
		boolean[] konvertierteZeile = konvertiereZeile(zeile);

		// Spielfeld erzeugen oder Zeile anhaengen
		if (spielfeld == null) {
		    spielfeld = new Spielfeld(konvertierteZeile);
		} else if (konvertierteZeile.length == spielfeld.gebeLaengeZeileZurueck()) {
		    spielfeld.fuegeZeileHinzu(konvertierteZeile);
		} else {
		    Benutzerdialoge.gebeFehler("Die Laenge der Zeilen stimmt nicht ueberein!");
		    return null;
		}

	    }

	} catch (FileNotFoundException e) {
	    Benutzerdialoge
		    .gebeFehler("Die Datei bzw. der Pfad existieren nicht! Bitte geben sie den Dateinamen erneut ein.");
	    return null;
	} catch (IOException e) {
	    Benutzerdialoge
		    .gebeFehler("Es ist ein Fehler beim Einlesen der Datei aufgetreten. Bitte geben sie den Dateinamen erneut ein.");
	    return null;
	} finally {
	    if (fileReader != null)
		try {
		    fileReader.close();
		} catch (IOException e) {
		}

	    if (bufferedReader != null)
		try {
		    bufferedReader.close();
		} catch (IOException e) {
		}
	}

	// Pruefung Inhalt

	return null;
    }

    private static boolean[] konvertiereZeile(String zeile) {
	int length = zeile.length();
	boolean werte[] = new boolean[length];

	for (int i = 0; i < zeile.length(); i++) {
	    if (zeile.charAt(i) == 'X') {
		werte[i] = false;
	    } else {
		werte[i] = true;
	    }
	}
	return werte;
    }

    private static boolean pruefeZeilenlaenge(String zeile) {
	if (zeile.length() == 0 || zeile.length() > 100) {
	    return false;
	}
	return true;
    }

    private static boolean pruefeDateiinhalt(String zeile) {
	for (int i = 0; i < zeile.length(); i++) {
	    if (zeile.charAt(i) != 'X' && zeile.charAt(i) != 'O') {
		return false;
	    }
	}
	return true;
    }

    private static boolean istDateipfadLeer(String eingabe) {
	if (eingabe.equals("")) {
	    Benutzerdialoge.gebeFehler("Bitte geben sie einen Dateinamen ein!");
	    return true;
	}
	return false;
    }

    public static String pruefeDateipfad(String eingabe) {
	String processedInput = "";
	for (int i = 0; i < eingabe.length(); i++) {
	    // Achtung TASK
	    if (eingabe.charAt(i) == '/') {
		processedInput += "/";
	    }
	    processedInput += eingabe.charAt(i);
	}

	return processedInput;
    }
}
