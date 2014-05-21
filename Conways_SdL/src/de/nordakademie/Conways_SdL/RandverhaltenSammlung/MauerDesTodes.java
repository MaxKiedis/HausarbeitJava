package de.nordakademie.Conways_SdL.RandverhaltenSammlung;

import java.util.ArrayList;

import de.nordakademie.Conways_SdL.Randverhalten;
import de.nordakademie.Conways_SdL.Spielfeld;

public class MauerDesTodes implements Randverhalten {

    @Override
    public Spielfeld setzeRand(Spielfeld feld) {
	for (int i = 0; i < feld.gebeZeilenAnzahl(); i++) {
	    for (int j = 0; j < feld.gebeLaengeZeileZurueck(); j++) {
		if (i == 0 || i == feld.gebeZeilenAnzahl() - 1 || j == 0 || j == feld.gebeLaengeZeileZurueck() - 1) {
		    feld.setzeZustand(false, j, i);
		}
	    }
	}
	return feld;
    }

    @Override
    public Spielfeld anlegenRand(Spielfeld spielfeld) {
	ArrayList<boolean[]> werte = new ArrayList<boolean[]>();
	int zeilenLaenge = spielfeld.gebeLaengeZeileZurueck() + 2;
	boolean[] zeile = new boolean[zeilenLaenge];
	for (int i = 0; i < zeile.length; i++) {
	    zeile[i] = false;
	}
	werte.add(zeile);
	for (int i = 0; i < spielfeld.gebeZeilenAnzahl(); i++) {
	    zeile = new boolean[zeilenLaenge];
	    for (int j = 0; j < zeilenLaenge; j++) {
		if (j == 0 || j == zeilenLaenge - 1) {
		    zeile[j] = false;
		} else {

		    zeile[j] = spielfeld.gebeZustandZelle(j - 1, i);
		}
	    }
	    werte.add(zeile);
	}
	zeile = new boolean[zeilenLaenge];
	for (int i = 0; i < zeile.length; i++) {
	    zeile[i] = false;
	}
	werte.add(zeile);

	return new Spielfeld(werte);
    }

    @Override
    public Spielfeld abziehenRand(Spielfeld letztesFeld) {
	return letztesFeld;
	// TODO Auto-generated method stub
	
    }
}
