package de.nordakademie.Conways_SdL;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import de.nordakademie.Conways_SdL.RandverhaltenSammlung.MauerDesTodes;
import de.nordakademie.Conways_SdL.RandverhaltenSammlung.PackmanUniversum;
import de.nordakademie.Conways_SdL.Spielmodi.Leben34;
import de.nordakademie.Conways_SdL.Spielmodi.LebenOhneTod;
import de.nordakademie.Conways_SdL.Spielmodi.Standard;

public class Spiel {

    // Variablen
    private ArrayList<Spielfeld> vergangeneSpielfelder;
    private Spielmodus modus;
    private Randverhalten randverhalten;
    private String dateiname;
    private JFrame loaderWindow;
    private JLabel gen_counter;

    Spiel() {
	Spielfeld importiertesSpielfeld;
	do {
	    // Abfrage Dateipfad
	    dateiname = Benutzerdialoge.zeigePfadeingabe();
	    // Laden der Datei
	    importiertesSpielfeld = Dateihandling
		    .leseSpielfeldAusDatei(dateiname);
	} while (importiertesSpielfeld == null);

	// Waehlen des Spielmodus
	// 0 = Leben ohne Tod
	// 1 = 34 Leben
	// 2 = Standard
	switch (Benutzerdialoge.zeigeSpielmoduseingabe()) {
	case 0:
	    modus = new LebenOhneTod();
	    break;
	case 1:
	    modus = new Leben34();
	    break;
	case 2:
	    modus = new Standard();
	    break;
	default:
	    // Falls der Exit-Button gedrueckt werden sollte, bricht das
	    // Programm ab
	    System.exit(0);
	    break;
	}

	// Waehlen des Randverhaltens
	// 0 = Wand des Todes
	// 1 = Pacman Universum
	switch (Benutzerdialoge.zeigeRandverhalteneingabe()) {
	case 0:
	    randverhalten = new PackmanUniversum();
	    break;
	case 1:
	    randverhalten = new MauerDesTodes();
	    break;
	default:
	    // Falls der Exit-Button gedrueckt werden sollte, bricht das
	    // Programm ab
	    System.exit(0);
	    break;
	}

	// Passe das Randverhalten fuer das eingelesene Spielfeld an
	vergangeneSpielfelder = new ArrayList<Spielfeld>();
	Spielfeld angepasstesSpielfeld = randverhalten
		.anlegenRand(importiertesSpielfeld);

	// Das angepasste Feld zur ArrayList hinzufuegen
	vergangeneSpielfelder.add(angepasstesSpielfeld);
    }

    public final void starten() {

	showLoaderWindow();

	// Eingelesenes Spielfeld als Startzustand definieren
	Spielfeld spielfeldAlteGeneration = vergangeneSpielfelder.get(0);
	int anzahlGenerationen = 0;

	// Dies ist der Algorithmus zum entwickeln der Spielfelder
	do {
	    anzahlGenerationen++;
	    Spielfeld spielfeldNeueGeneration = entwickleGeneration(
		    spielfeldAlteGeneration, randverhalten);
	    spielfeldAlteGeneration = spielfeldNeueGeneration;
	    gen_counter.setText("Generation: " + anzahlGenerationen);
	} while (!istDublette(spielfeldAlteGeneration));

	hideLoaderWindow();

	// statisch oder zyklisch
	if (istGleichesSpielfeld(spielfeldAlteGeneration,
		vergangeneSpielfelder.get(vergangeneSpielfelder.size() - 1))) {
	    Dateihandling.speichereEndzustand(spielfeldAlteGeneration,
		    anzahlGenerationen, randverhalten, dateiname);
	} else {
	    Benutzerdialoge
		    .zeigeInfoFenster("Der Zustand ist zyklisch. Erreicht nach "
			    + anzahlGenerationen + " Generationen");
	}
	System.exit(0);
    }

    private Spielfeld entwickleGeneration(final Spielfeld altesSpielfeld,
	    final Randverhalten randverhalten) {
	ArrayList<boolean[]> werteFuerSpielfeld = new ArrayList<boolean[]>();
	for (int i = 0; i < altesSpielfeld.gibYDimension(); i++) {
	    boolean[] zeile = new boolean[altesSpielfeld.gibXDimension()];
	    for (int j = 0; j < altesSpielfeld.gibXDimension(); j++) {
		zeile[j] = modus.gibLebenszustandNaechsteRunde(
			altesSpielfeld.gibNachbaranzahl(j, i),
			altesSpielfeld.gibZellzustand(j, i));
	    }
	    werteFuerSpielfeld.add(zeile);
	}
	Spielfeld neuesSpielfeld = randverhalten.setzeRand(new Spielfeld(
		werteFuerSpielfeld));
	return neuesSpielfeld;
    }

    private boolean istDublette(final Spielfeld spielfeld) {
	for (int i = 0; i < vergangeneSpielfelder.size(); i++) {
	    if (istGleichesSpielfeld(vergangeneSpielfelder.get(i), spielfeld)) {
		return true;
	    }
	}
	vergangeneSpielfelder.add(spielfeld);
	return false;
    }

    private boolean istGleichesSpielfeld(final Spielfeld spielfeld,
	    final Spielfeld neuesFeld) {
	for (int i = 0; i < spielfeld.gibYDimension(); i++) {
	    for (int j = 0; j < spielfeld.gibXDimension(); j++) {
		if (spielfeld.gibZellzustand(j, i) != neuesFeld.gibZellzustand(
			j, i)) {
		    return false;
		}
	    }
	}
	return true;
    }

    private void showLoaderWindow() {
	loaderWindow = new JFrame("Working...");

	ImageIcon loading = new ImageIcon("img/spinner.gif");
	gen_counter = new JLabel("Generation: 1", loading, JLabel.CENTER);
	loaderWindow.add(gen_counter);

	loaderWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	loaderWindow.setSize(300, 60);
	loaderWindow.setVisible(true);
    }

    private void hideLoaderWindow() {
	loaderWindow.setVisible(false);
    }
}