package de.nordakademie.Conways_SdL;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

/**
 * Stellt Funktionen fuer die Ein- und Ausgabe von Spielfeldateien bereit.
 * 
 * @author Kai Neubauer
 */
public final class Dateihandling {

    /** Maximale Spielfeldbreite. */
    private static final int MAXIMAL_X_DIMENSION = 100;
    /** Maximale Spielfeldhoehe. */
    private static final int MAXIMAL_Y_DIMENSION = 100;

    /**
     * Privater Konstruktor, da niemand Objekte erzeugen sollte.
     */
    private Dateihandling() {
    }

    /**
     * Diese Methode liest eine Datei ein und prueft, ob daraus ein Spielfeld
     * erstellt werden kann. Falls dem so ist, wird ein Spielfeld generiert und
     * zurueck gegeben.
     * 
     * @param dateiname
     *            Name/Pfad des Ausgangsspielfeldes
     * @return ausgelesenes Spielfeld
     */
    public static Spielfeld einlesenSpielfeld(final String dateiname) {
	ArrayList<boolean[]> zellwerte = new ArrayList<boolean[]>();
	File datei = new File(System.getProperty("user.home")
		+ "//spiel_des_lebens//" + dateiname + ".start");

	FileReader fileReader = null;
	BufferedReader bufferedReader = null;
	Spielfeld spielfeld = null;

	try {
	    fileReader = new FileReader(datei);
	    bufferedReader = new BufferedReader(fileReader);

	    while (bufferedReader.ready()) {
		String eingeleseneZeile = bufferedReader.readLine();

		// Nullcheck (laut Findbugs)
		if (eingeleseneZeile != null) {

		    // Pruefe Inhalt
		    if (hatVerboteneZeichen(eingeleseneZeile)) {
			Benutzerinterface
				.zeigeInfoFenster("Es sind verbotene Zeichen in der Datei!");
			return null;
		    }
		    // Pruefe Zeilenformat
		    if (eingeleseneZeile.length() > MAXIMAL_X_DIMENSION) {
			Benutzerinterface
				.zeigeInfoFenster("Eine Zeile hat zu viele Spalten.");
			return null;
		    }
		    if (eingeleseneZeile.length() == 0) {
			Benutzerinterface
				.zeigeInfoFenster("Eine eingegebene Zeile hat keine Zeichen.");
			return null;
		    }

		    // Pruefe Zeilenanzahl
		    if (spielfeld != null
			    && spielfeld.gibYDimension() >= MAXIMAL_Y_DIMENSION) {
			Benutzerinterface
				.zeigeInfoFenster("Das eingelesene Spielfeld besitzt zu "
					+ "viele Zeilen!");
			return null;
		    }

		    // Zeile Konvertieren
		    boolean[] konvertierteZeile = konvertiereZeile(eingeleseneZeile);

		    // Spielfeld erzeugen oder Zeile anhaengen
		    if (spielfeld == null) {
			spielfeld = new Spielfeld(konvertierteZeile);
		    } else if (konvertierteZeile.length == spielfeld
			    .gibXDimension()) {
			spielfeld.hinzufuegenZeile(konvertierteZeile);
		    } else {
			Benutzerinterface
				.zeigeInfoFenster("Die Laenge der Zeilen stimmt nicht "
					+ "ueberein!");
			return null;
		    }
		}
	    }

	} catch (FileNotFoundException e) {
	    Benutzerinterface
		    .zeigeInfoFenster("Die Datei bzw. der Pfad existieren nicht! Bitte "
			    + "geben sie den Dateinamen erneut ein.");
	    return null;
	} catch (IOException e) {
	    Benutzerinterface
		    .zeigeInfoFenster("Es ist ein Fehler beim Einlesen der Datei "
			    + "aufgetreten. Bitte geben sie den Dateinamen erneut ein.");
	    return null;
	} finally {
	    if (fileReader != null) {
		try {
		    fileReader.close();
		} catch (IOException e) {
		    Benutzerinterface
			    .zeigeInfoFenster("Es ist ein Fehler beim Einlesen der Datei "
				    + "aufgetreten. Bitte geben sie den Dateinamen erneut "
				    + "ein.");
		}
	    }

	    if (bufferedReader != null) {
		try {
		    bufferedReader.close();
		} catch (IOException e) {
		    Benutzerinterface
			    .zeigeInfoFenster("Es ist ein Fehler beim Einlesen der Datei "
				    + "aufgetreten. Bitte geben sie den Dateinamen erneut "
				    + "ein.");
		}
	    }
	}

	if (spielfeld == null) {
	    Benutzerinterface.zeigeInfoFenster("Die Datei hat keinen Inhalt");
	}

	return spielfeld;
    }

    /**
     * Ueberprueft ob der uebergebene String (Zeile) die korrekten Zeichen
     * enthaelt.
     * 
     * @param zeile
     *            uebergebener String
     * @return Wahrheitswert
     */
    private static boolean hatVerboteneZeichen(final String zeile) {
	for (int i = 0; i < zeile.length(); i++) {
	    if (zeile.charAt(i) != 'X' && zeile.charAt(i) != 'O') {
		return true;
	    }
	}
	return false;
    }

    /**
     * Konvertiert String in Array aus boolean-Werten.
     * 
     * @param zeile
     *            uebergebener String
     * @return boolean-Array
     */
    private static boolean[] konvertiereZeile(final String zeile) {
	boolean[] werte = new boolean[zeile.length()];

	for (int i = 0; i < zeile.length(); i++) {
	    if (zeile.charAt(i) == 'X') {
		werte[i] = false;
	    } else {
		werte[i] = true;
	    }
	}
	return werte;
    }

    /**
     * Konvertiert uebergebenes Spielfeld und uebergibt es in Datei.
     * 
     * @param spielfeld
     *            abzuspeicherndes Spielfeld
     * @param anzahlGenerationen
     *            ANzahl der durchgelaufenen Generationen bis zum Endzustand
     * @param randverhalten
     *            gewaehltes Randverhalten
     * @param dateiname
     *            Name der zu abzuspeichernden Datei
     */
    public static void speichernEndzustand(final Spielfeld spielfeld,
	    final int anzahlGenerationen, final Randverhalten randverhalten,
	    final String dateiname) {
	File datei = new File(System.getProperty("user.home")
		+ "//spiel_des_lebens//" + dateiname + ".ende");

	FileOutputStream fileOutput = null;
	PrintStream dateiAusgabe = null;

	try {
	    fileOutput = new FileOutputStream(datei);
	    dateiAusgabe = new PrintStream(fileOutput);

	    dateiAusgabe.println("Die Ausgabe ist statisch nach "
		    + anzahlGenerationen + " Generationen!");
	    dateiAusgabe.println();
	    Spielfeld feld = randverhalten.abschliessenRandverhalten(spielfeld);

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
	    Benutzerinterface
		    .zeigeInfoFenster("Die Datei wurde nicht gefunden (Fehler)! "
			    + "Sie wurde nicht gespeichert!");
	} finally {
	    if (fileOutput != null) {
		try {
		    fileOutput.close();
		} catch (IOException e) {
		    Benutzerinterface
			    .zeigeInfoFenster("Fehler beim FileOutput");
		}
	    }
	    if (dateiAusgabe != null) {
		dateiAusgabe.close();
	    }
	}
    }
}
