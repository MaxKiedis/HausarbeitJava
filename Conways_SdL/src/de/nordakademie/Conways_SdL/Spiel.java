package de.nordakademie.Conways_SdL;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import de.nordakademie.Conways_SdL.raender.MauerDesTodes;
import de.nordakademie.Conways_SdL.raender.PackmanUniversum;
import de.nordakademie.Conways_SdL.spielmodi.Leben34;
import de.nordakademie.Conways_SdL.spielmodi.LebenOhneTod;
import de.nordakademie.Conways_SdL.spielmodi.Standard;

public class Spiel {

    // Variablen
    private ArrayList<Spielfeld> vergangeneSpielfelder;
    private Spielmodus modus;
    private Randverhalten randverhalten;
    private String dateiname;
    private JFrame ladebalkenFenster;
    private JLabel labelAnzahlGenerationen;

    Spiel() {
	Spielfeld importiertesSpielfeld;
	do {
	    // Abfrage Dateipfad
	    dateiname = Benutzerinterface.ermittlePfad();
	    // Laden der Datei
	    importiertesSpielfeld = Dateihandling.einlesenSpielfeld(dateiname);
	} while (importiertesSpielfeld == null);

	// Waehlen des Spielmodus
	// 0 = Leben ohne Tod
	// 1 = 34 Leben
	// 2 = Standard
	switch (Benutzerinterface.zeigeSpielmoduseingabe()) {
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
	switch (Benutzerinterface.zeigeRandverhalteneingabe()) {
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

	zeigeLadebalken();

	// Eingelesenes Spielfeld als Startzustand definieren
	Spielfeld spielfeldAlteGeneration = vergangeneSpielfelder.get(0);
	int anzahlGenerationen = 0;

	// Dies ist der Algorithmus zum entwickeln der Spielfelder
	do {
	    anzahlGenerationen++;
	    Spielfeld spielfeldNeueGeneration = spielfeldAlteGeneration
		    .entwickleGeneration(randverhalten, modus);
	    spielfeldAlteGeneration = spielfeldNeueGeneration;
	    labelAnzahlGenerationen
		    .setText("Generation: " + anzahlGenerationen);
	} while (!istInVergangenenSpielfeldernVorhanden(spielfeldAlteGeneration));

	versteckeLadebalken();

	// statisch oder zyklisch
	if (spielfeldAlteGeneration.istGleichesSpielfeld(vergangeneSpielfelder
		.get(vergangeneSpielfelder.size() - 1))) {
	    Dateihandling.speichernSpielfeld(spielfeldAlteGeneration,
		    anzahlGenerationen, randverhalten, dateiname);
	} else {
	    Benutzerinterface
		    .zeigeInfoFenster("Der Zustand ist zyklisch. Erreicht nach "
			    + anzahlGenerationen + " Generationen");
	}
    }

    private boolean istInVergangenenSpielfeldernVorhanden(
	    final Spielfeld spielfeld) {
	for (int i = 0; i < vergangeneSpielfelder.size(); i++) {
	    if (spielfeld.istGleichesSpielfeld(vergangeneSpielfelder.get(i))) {
		return true;
	    }
	}
	vergangeneSpielfelder.add(spielfeld);
	return false;
    }

    private void zeigeLadebalken() {
	ladebalkenFenster = new JFrame("Working...");
	ImageIcon loading = new ImageIcon("img/spinner.gif");
	labelAnzahlGenerationen = new JLabel("Generation: 1", loading,
		JLabel.CENTER);
	ladebalkenFenster.add(labelAnzahlGenerationen);
	ladebalkenFenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	ladebalkenFenster.setSize(300, 60);
	ladebalkenFenster.setVisible(true);
    }

    private void versteckeLadebalken() {
	ladebalkenFenster.setVisible(false);
	ladebalkenFenster.dispose();
    }
}