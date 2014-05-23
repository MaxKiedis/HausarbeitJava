package de.nordakademie.Conways_SdL;

import java.util.ArrayList;

public class Spielfeld {

    ArrayList<boolean[]> zeilenMitZellen;

    public Spielfeld(boolean[] zeile) {
	zeilenMitZellen = new ArrayList<boolean[]>();
	zeilenMitZellen.add(zeile);
    }

    public Spielfeld(ArrayList<boolean[]> zeilenMitZellen) {
	this.zeilenMitZellen = zeilenMitZellen;
    }

    public void hinzufuegenZeile(boolean[] zeile) {
	zeilenMitZellen.add(zeile);
    }

    public int gibXDimension() {
	return zeilenMitZellen.get(0).length;
    }

    public void schreibeSpielfeld() {
	for (int i = 0; i < zeilenMitZellen.size(); i++) {
	    for (int j = 0; j < zeilenMitZellen.get(i).length; j++) {
		if (zeilenMitZellen.get(i)[j]) {
		    System.out.print("O");
		} else {
		    System.out.print("X");
		}
	    }
	    System.out.println();
	}
    }

    public int gibNachbaranzahl(int x, int y) {
	int current_x;
	int current_y;
	int nachbaranzahl = 0;

	int start_x = x - 1;
	int start_y = y - 1;
	if (start_x < 0) {
	    start_x += gibXDimension();
	}

	if (start_y < 0) {
	    start_y += gibYDimension();
	}

	for (int i = 0; i < 3; i++) { // Zeilen
	    for (int j = 0; j < 3; j++) { // Spalten
		if (!(i == 1 && j == 1)) {
		    current_x = start_x + j;
		    current_y = start_y + i;

		    while (current_x >= gibXDimension()) {
			current_x -= gibXDimension();
		    }

		    while (current_y >= gibYDimension()) {
			current_y -= gibYDimension();
		    }

		    if (gibZellzustand(current_x, current_y)) {
			nachbaranzahl++;
		    }
		}
	    }
	}
	return nachbaranzahl;
    }

    public boolean gibZellzustand(int x, int y) {
	return zeilenMitZellen.get(y)[x];
    }

    public int gibYDimension() {
	return zeilenMitZellen.size();
    }

    public void setzeZustand(boolean zustand, int x, int y) {
	zeilenMitZellen.get(y)[x] = zustand;
    }
}
