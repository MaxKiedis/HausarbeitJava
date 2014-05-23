package de.nordakademie.Conways_SdL;

import java.util.ArrayList;

import de.nordakademie.Conways_SdL.RandverhaltenSammlung.MauerDesTodes;
import de.nordakademie.Conways_SdL.RandverhaltenSammlung.PackmanUniversum;
import de.nordakademie.Conways_SdL.Spielmodi.Standard;

public class Spiel {

    // Variablen
    private ArrayList<Spielfeld> spielfeldEvolutionen;
    private Spielmodus modus;
    private Randverhalten randverhalten;

    Spiel() {
	// Laden der Datei
	Spielfeld neuesFeld;
	do {
	    neuesFeld = Dateihandling.oeffneDatei();
	} while (neuesFeld == null);

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
	int auswahl = Benutzerdialoge.showRandverhalten();
	switch (auswahl) {
	case 0:
	    randverhalten = new PackmanUniversum();
	    break;
	case 1:
	    randverhalten = new MauerDesTodes();
	    break;
	default:
	    randverhalten = new PackmanUniversum();
	    break;
	}

	// Passe das Randverhalten an
	spielfeldEvolutionen = new ArrayList<Spielfeld>();
	Spielfeld feld = randverhalten.anlegenRand(neuesFeld);

	spielfeldEvolutionen.add(feld);
	spielfeldEvolutionen.get(0).printSpielfeld();
    }

    public void starten() {

	Spielfeld letztesFeld = spielfeldEvolutionen.get(0);
	int counter = 0;
	do {
	    counter++;
	    Spielfeld feld = entwickleSpielfeld(letztesFeld, randverhalten);
	    letztesFeld = feld;
	    System.out.println();
	    System.out.println("Runde " + counter);
	    letztesFeld.printSpielfeld();
	} while (!checkStatusSchoneinmalVorhanden(letztesFeld));

	// statisch oder zyklisch
	if (vergleicheFeld(letztesFeld, spielfeldEvolutionen.get(spielfeldEvolutionen.size() - 1))) {
	    Dateihandling.speichereEndzustand(letztesFeld, counter, randverhalten);
	} else {
	    Benutzerdialoge.gebeInfo("Der Zustand ist zyklisch. Erreicht nach " + counter + " Generationen");
	}

    }

    private Spielfeld entwickleSpielfeld(Spielfeld bisherigesSpielfeld, Randverhalten randverhalten) {
	ArrayList<boolean[]> holder = new ArrayList<boolean[]>();
	for (int i = 0; i < bisherigesSpielfeld.gebeZeilenAnzahl(); i++) {
	    boolean[] reihe = new boolean[bisherigesSpielfeld.gebeLaengeZeileZurueck()];
	    for (int j = 0; j < bisherigesSpielfeld.gebeLaengeZeileZurueck(); j++) {
		reihe[j] = modus.gibLebenszustandNaechsteRunde(bisherigesSpielfeld.ermittleNachbaranzahl(j, i),
			bisherigesSpielfeld.gebeZustandZelle(j, i));
	    }
	    holder.add(reihe);
	}

	Spielfeld neuesFeld = new Spielfeld(holder);
	Spielfeld ueberarbeitetesFeld = randverhalten.setzeRand(neuesFeld);

	return ueberarbeitetesFeld;
    }

    private boolean checkStatusSchoneinmalVorhanden(Spielfeld neuesFeld) {
	for (int i = 0; i < spielfeldEvolutionen.size(); i++) {
	    if (vergleicheFeld(spielfeldEvolutionen.get(i), neuesFeld)) {
		return true;
	    }
	}
	spielfeldEvolutionen.add(neuesFeld);
	return false;
    }

    private boolean vergleicheFeld(Spielfeld spielfeld, Spielfeld neuesFeld) {
	for (int i = 0; i < spielfeld.gebeZeilenAnzahl(); i++) {
	    for (int j = 0; j < spielfeld.gebeLaengeZeileZurueck(); j++) {
		if (spielfeld.gebeZustandZelle(j, i) != neuesFeld.gebeZustandZelle(j, i)) {
		    return false;
		}
	    }

	}
	return true;
    }
}
