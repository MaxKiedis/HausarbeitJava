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
	    randverhalten = new MauerDesTodes();
	    break;
	case 1:
	    randverhalten = new PackmanUniversum();
	    break;
	default:
	    randverhalten = new PackmanUniversum();
	    break;
	}

	// Passe das Randverhalten an
	spielfeldEvolutionen = new ArrayList<Spielfeld>();
	neuesFeld = randverhalten.anlegenRand(neuesFeld);

	spielfeldEvolutionen.add(neuesFeld);
    }

    public void starten() {
	spielfeldEvolutionen.get(0).printSpielfeld();

	System.out.println();
	for (int i = 0; i < spielfeldEvolutionen.get(0).gebeZeilenAnzahl(); i++) {
	    for (int j = 0; j < spielfeldEvolutionen.get(0).gebeLaengeZeileZurueck(); j++) {
		System.out.print(spielfeldEvolutionen.get(0).ermittleNachbaranzahl(j, i));
	    }
	    System.out.println();
	}

	ArrayList<boolean[]> holder = new ArrayList<boolean[]>();
	for (int i = 0; i < spielfeldEvolutionen.get(0).gebeZeilenAnzahl(); i++) {
	    boolean[] reihe = new boolean[spielfeldEvolutionen.get(0).gebeLaengeZeileZurueck()];
	    for (int j = 0; j < spielfeldEvolutionen.get(0).gebeLaengeZeileZurueck(); j++) {
		reihe[j] = modus.gibLebenszustandNaechsteRunde(spielfeldEvolutionen.get(0).ermittleNachbaranzahl(j, i),
			spielfeldEvolutionen.get(0).gebeZustandZelle(j, i));
	    }
	    holder.add(reihe);
	}
	Spielfeld entwickeltesFeld = new Spielfeld(holder);
	System.out.println();
	entwickeltesFeld.printSpielfeld();

    }
}
