package de.nordakademie.Conways_SdL;

import java.util.ArrayList;

import com.sun.media.jai.opimage.NeuQuantOpImage;

public class Spiel {

    // Variablen
    private ArrayList<Spielfeld> spielfeldEvolutionen;
    private Spielmodus modus;

    Spiel() {
	// Laden der Datei
	Spielfeld neuesFeld;
	do {
	    neuesFeld = Dateihandling.oeffneDatei();
	} while (neuesFeld == null);

	// Waehlen des Spielmodus

	// Waehlen des Randverhaltens

    }

    public void starten() {

    }
}
