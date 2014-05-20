package de.nordakademie.Conways_SdL;

import java.util.ArrayList;

public class Spielfeld {

    ArrayList<boolean[]> werte;

    public Spielfeld(boolean[] zeile) {
	werte = new ArrayList<boolean[]>();
	werte.add(zeile);
    }

    public void fuegeZeileHinzu(boolean[] konvertierteZeile) {
	werte.add(konvertierteZeile);
    }

    public int gebeLaengeZeileZurueck() {
	return werte.get(0).length;
    }

}
