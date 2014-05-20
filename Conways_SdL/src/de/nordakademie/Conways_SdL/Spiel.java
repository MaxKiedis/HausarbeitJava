package de.nordakademie.Conways_SdL;

import java.util.ArrayList;

import de.nordakademie.Conways_SdL.Spielmodi.Standard;

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

	spielfeldEvolutionen = new ArrayList<Spielfeld>();
	spielfeldEvolutionen.add(neuesFeld);

	// Waehlen des Spielmodus

	// 0 = Leben ohne Tod
	// 1 = 34 Leben
	// 2 = Standard
	int spielmodus = Benutzerdialoge.showSpielmodus();
	switch (spielmodus) {
	case 0:
	    modus = new Standard();
	    break;
	case 1:
	    modus = new Standard();
	    break;
	case 2:
	    modus = new Standard();
	    break;
	default:
	    modus = new Standard();
	    break;
	}

	// Waehlen des Randverhaltens
	// 0 = Wand des Todes
	// 1 = Pacman Universum
	int randverhalten = Benutzerdialoge.showRandverhalten();

    }

    public void starten() {
	spielfeldEvolutionen.get(0).printSpielfeld();
    }
}
