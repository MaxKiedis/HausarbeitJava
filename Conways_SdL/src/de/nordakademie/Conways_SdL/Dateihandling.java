package de.nordakademie.Conways_SdL;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

public class Dateihandling {

    public static Spielfeld leseSpielfeldAusDatei(String dateiname) {
	File datei = new File(System.getProperty("user.home") + "//spiel_des_lebens//" + dateiname + ".start");

	FileReader fileReader = null;
	BufferedReader bufferedReader = null;
	Spielfeld spielfeld = null;

	try {
	    fileReader = new FileReader(datei);
	    bufferedReader = new BufferedReader(fileReader);

	    while (bufferedReader.ready()) {
		String eingeleseneZeile = bufferedReader.readLine();
		// Pruefe Inhalt
		if (hatVerboteneZeichen(eingeleseneZeile)) {
		    Benutzerdialoge.zeigeFehlermeldung("Es sind verbotene Zeichen in der Datei!");
		    return null;
		}
		// Pruefe Zeilenformat
		if (eingeleseneZeile.length() > 100) {
		    Benutzerdialoge.zeigeFehlermeldung("Eine Zeile hat zu viele Spalten.");
		    return null;
		}
		if (eingeleseneZeile.length() == 0) {
		    Benutzerdialoge.zeigeFehlermeldung("Eine eingegebene Zeile hat keine Zeichen.");
		    return null;
		}

		// Pruefe Zeilenanzahl
		if (spielfeld != null && spielfeld.gibYDimension() >= 100) {
		    Benutzerdialoge.zeigeFehlermeldung("Das eingelesene Spielfeld besitzt zu viele Zeilen!");
		    return null;
		}

		// Zeile Konvertieren
		boolean[] konvertierteZeile = konvertiereZeile(eingeleseneZeile);

		// Spielfeld erzeugen oder Zeile anhaengen
		if (spielfeld == null) {
		    spielfeld = new Spielfeld(konvertierteZeile);
		} else if (konvertierteZeile.length == spielfeld.gibXDimension()) {
		    spielfeld.hinzufuegenZeile(konvertierteZeile);
		} else {
		    Benutzerdialoge.zeigeFehlermeldung("Die Laenge der Zeilen stimmt nicht ueberein!");
		    return null;
		}

	    }

	} catch (FileNotFoundException e) {
	    Benutzerdialoge
		    .zeigeFehlermeldung("Die Datei bzw. der Pfad existieren nicht! Bitte geben sie den Dateinamen erneut ein.");
	    return null;
	} catch (IOException e) {
	    Benutzerdialoge
		    .zeigeFehlermeldung("Es ist ein Fehler beim Einlesen der Datei aufgetreten. Bitte geben sie den Dateinamen erneut ein.");
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
	return spielfeld;
    }

    private static boolean[] konvertiereZeile(String zeile) {
	boolean werte[] = new boolean[zeile.length()];

	for (int i = 0; i < zeile.length(); i++) {
	    if (zeile.charAt(i) == 'X') {
		werte[i] = false;
	    } else {
		werte[i] = true;
	    }
	}
	return werte;
    }

    private static boolean hatVerboteneZeichen(String zeile) {
	for (int i = 0; i < zeile.length(); i++) {
	    if (zeile.charAt(i) != 'X' && zeile.charAt(i) != 'O') {
		return true;
	    }
	}
	return false;
    }

    public static void speichereEndzustand(Spielfeld spielfeld, int anzahlGenerationen, Randverhalten randverhalten,
	    String dateiname) {
	File datei = new File(System.getProperty("user.home") + "//spiel_des_lebens//" + dateiname + ".ende");

	FileOutputStream fileOutput = null;
	PrintStream dateiAusgabe = null;

	try {
	    fileOutput = new FileOutputStream(datei);
	    dateiAusgabe = new PrintStream(fileOutput);

	    dateiAusgabe.println("Die Ausgabe ist statisch nach " + anzahlGenerationen + " Generationen!");
	    dateiAusgabe.println();
	    Spielfeld feld = randverhalten.anwendenRandverhalten(spielfeld);

	    for (int i = 0; i < feld.gibYDimension(); i++) {
		for (int j = 0; j < feld.gibXDimension(); j++) {
		    if (feld.gibZellzustand(j, i)) {
			dateiAusgabe.print("O");
		    } else {
			dateiAusgabe.print("X");
		    }
		}
		dateiAusgabe.println();
	    }
	} catch (FileNotFoundException e) {
	    Benutzerdialoge.zeigeFehlermeldung("Die Datei wurde nicht gefunden (Fehler)! Sie wurde nicht gespeichert!");
	} finally {
	    if (fileOutput != null)
		try {
		    fileOutput.close();
		} catch (IOException e) {
		}
	    if (dateiAusgabe != null)
		dateiAusgabe.close();
	}
    }
}
