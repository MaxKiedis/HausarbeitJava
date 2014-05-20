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

    public void printSpielfeld() {
	for (int i = 0; i < werte.size(); i++) {
	    for (int j = 0; j < werte.get(i).length; j++) {
		if (werte.get(i)[j]) {
		    System.out.print("O");
		} else {
		    System.out.print("X");
		}
	    }
	    System.out.println();
	}
    }

    public int ermittleNachbaranzahl(int x, int y) {

	
	return 0;
    }

}
