package de.nordakademie.Conways_SdL;

import java.util.ArrayList;

public class Spielfeld {

    ArrayList<boolean[]> werte;

    public Spielfeld(boolean[] zeile) {
	werte = new ArrayList<boolean[]>();
	werte.add(zeile);
    }

    public Spielfeld(ArrayList<boolean[]> holder) {
	werte = holder;
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
	int dim_x = gebeLaengeZeileZurueck(); // 1
	int dim_y = gebeZeilenAnzahl(); // 1
	int current_x;
	int current_y;
	int nachbaranzahl = 0;

	int start_x = x - 1; // 0
	int start_y = y - 1; // 0
	if (start_x < 0) {
	    start_x += dim_x;
	}

	if (start_y < 0) {
	    start_y += dim_y;
	}

	for (int i = 0; i < 3; i++) { // Zeilen
	    for (int j = 0; j < 3; j++) { // Spalten
		if (!(i == 1 && j == 1)) {
		    current_x = start_x + j;
		    current_y = start_y + i;

		    while (current_x >= dim_x) {
			current_x -= dim_x;
		    }

		    while (current_y >= dim_y) {
			current_y -= dim_y;
		    }

		    if (gebeZustandZelle(current_x, current_y)) {
			nachbaranzahl++;
		    }
		}
	    }
	}

	return nachbaranzahl;
    }

    public boolean gebeZustandZelle(int x, int y) {
	return werte.get(y)[x];
    }

    public int gebeZeilenAnzahl() {
	return werte.size();
    }

    public void setzeZustand(boolean zustand, int x, int y) {
	werte.get(y)[x] = zustand;
    }
}
