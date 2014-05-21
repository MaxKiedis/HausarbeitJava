package de.nordakademie.Conways_SdL.RandverhaltenSammlung;

import java.util.ArrayList;

import de.nordakademie.Conways_SdL.Randverhalten;
import de.nordakademie.Conways_SdL.Spielfeld;

public class MauerDesTodes implements Randverhalten {

    @Override
    public void setzeRand() {
	// TODO Auto-generated method stub

    }

    @Override
    public Spielfeld anlegenRand(Spielfeld spielfeld) {
	ArrayList<boolean[]> werte = null;
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
		    zeile[j] = spielfeld.gebeZustandZelle(j - 1, i - 1);
		}
	    }
	    werte.add(zeile);
	}
	
	return new Spielfeld(werte);
    }
}
