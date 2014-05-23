package de.nordakademie.Conways_SdL;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

/**
 * 
 * 
 * @author kaineubauer
 */
public class Dateihandling {

    /**
     * 
     */
    private static final int MAXIMAL_X_DIMENSION = 100;

    private static final int MAXIMAL_Y_DIMENSION = 100;

    /**
     * Privater Konstruktor, da niemand Objekte erzeugen sollte
     */
    private Dateihandling() {
    }

    public static Spielfeld einlesenSpielfeld(String dateiname) {
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
				.zeigeInfoFenster("Das eingelesene Spielfeld besitzt zu viele Zeilen!");
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
				.zeigeInfoFenster("Die Laenge der Zeilen stimmt nicht ueberein!");
			return null;
		    }
		}
	    }

	} catch (FileNotFoundException e) {
	    Benutzerinterface
		    .zeigeInfoFenster("Die Datei bzw. der Pfad existieren nicht! Bitte geben sie den Dateinamen erneut ein.");
	    return null;
	} catch (IOException e) {
	    Benutzerinterface
		    .zeigeInfoFenster("Es ist ein Fehler beim Einlesen der Datei aufgetreten. Bitte geben sie den Dateinamen erneut ein.");
	    return null;
	} finally {
	    if (fileReader != null) {
		try {
		    fileReader.close();
		} catch (IOException e) {
		    Benutzerinterface
			    .zeigeInfoFenster("Es ist ein Fehler beim Einlesen der Datei aufgetreten. Bitte geben sie den Dateinamen erneut ein.");
		}
	    }

	    if (bufferedReader != null) {
		try {
		    bufferedReader.close();
		} catch (IOException e) {
		    Benutzerinterface
			    .zeigeInfoFenster("Es ist ein Fehler beim Einlesen der Datei aufgetreten. Bitte geben sie den Dateinamen erneut ein.");
		}
	    }
	}

	if (spielfeld == null) {
	    Benutzerinterface.zeigeInfoFenster("Die Datei hat keinen Inhalt");
	}

	return spielfeld;
    }

    private static boolean hatVerboteneZeichen(final String zeile) {
        for (int i = 0; i < zeile.length(); i++) {
            if (zeile.charAt(i) != 'X' && zeile.charAt(i) != 'O') {
        	return true;
            }
        }
        return false;
    }

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
        	    .zeigeInfoFenster("Die Datei wurde nicht gefunden (Fehler)! Sie wurde nicht gespeichert!");
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
}
