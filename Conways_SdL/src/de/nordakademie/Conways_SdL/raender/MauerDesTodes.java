package de.nordakademie.Conways_SdL.raender;

import java.util.ArrayList;

import de.nordakademie.Conways_SdL.Randverhalten;
import de.nordakademie.Conways_SdL.Spielfeld;

public class MauerDesTodes extends Randverhalten {

    public Spielfeld setzeRand(final Spielfeld spielfeld) {
	for (int i = 0; i < spielfeld.gibYDimension(); i++) {
	    for (int j = 0; j < spielfeld.gibXDimension(); j++) {
		if (i == 0 || i == spielfeld.gibYDimension() - 1 || j == 0 || j == spielfeld.gibXDimension() - 1) {
		    spielfeld.setzeZellzustand(false, j, i);
		}
	    }
	}
	return spielfeld;
    }

    public Spielfeld anlegenRand(final Spielfeld spielfeld) {
	ArrayList<boolean[]> zeilenSpielfeld = new ArrayList<boolean[]>();
	int zeilenLaenge = spielfeld.gibXDimension() + 2;
	boolean[] zeile = new boolean[zeilenLaenge];
	for (int i = 0; i < zeile.length; i++) {
	    zeile[i] = false;
	}
	zeilenSpielfeld.add(zeile);
	for (int i = 0; i < spielfeld.gibYDimension(); i++) {
	    zeile = new boolean[zeilenLaenge];
	    for (int j = 0; j < zeilenLaenge; j++) {
		if (j == 0 || j == zeilenLaenge - 1) {
		    zeile[j] = false;
		} else {

		    zeile[j] = spielfeld.gibZellzustand(j - 1, i);
		}
	    }
	    zeilenSpielfeld.add(zeile);
	}
	zeile = new boolean[zeilenLaenge];
	for (int i = 0; i < zeile.length; i++) {
	    zeile[i] = false;
	}
	zeilenSpielfeld.add(zeile);

	return new Spielfeld(zeilenSpielfeld);
    }

    public Spielfeld anwendenRandverhalten(final Spielfeld spielfeld) {
	ArrayList<boolean[]> zeilenSpielfeld = new ArrayList<boolean[]>();
	int laengeZeile = spielfeld.gibXDimension() - 2;

	for (int i = 1; i < spielfeld.gibYDimension() - 1; i++) {
	    boolean[] neueZeile = new boolean[laengeZeile];
	    for (int j = 0; j < neueZeile.length; j++) {
		neueZeile[j] = spielfeld.gibZellzustand(j + 1, i);
	    }
	    zeilenSpielfeld.add(neueZeile);
	}
	return new Spielfeld(zeilenSpielfeld);
    }
}
