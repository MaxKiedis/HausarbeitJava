package de.nordakademie.Conways_SdL;

import java.util.ArrayList;

public class Spielfeld {

    private ArrayList<boolean[]> zeilenMitZellen;

    public Spielfeld(final boolean[] zeile) {
	zeilenMitZellen = new ArrayList<boolean[]>();
	zeilenMitZellen.add(zeile);
    }

    public Spielfeld(final ArrayList<boolean[]> zeilenMitZellen) {
	this.zeilenMitZellen = zeilenMitZellen;
    }

    public final void hinzufuegenZeile(final boolean[] zeile) {
	zeilenMitZellen.add(zeile);
    }

    public final int gibXDimension() {
	return zeilenMitZellen.get(0).length;
    }

    public final void schreibeSpielfeld() {
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

    public final int gibNachbaranzahl(final int x, final int y) {
	int currentX;
	int currentY;
	int nachbaranzahl = 0;

	int startX = x - 1;
	int startY = y - 1;
	if (startX < 0) {
	    startX += gibXDimension();
	}

	if (startY < 0) {
	    startY += gibYDimension();
	}

	for (int i = 0; i < 3; i++) { // Zeilen
	    for (int j = 0; j < 3; j++) { // Spalten
		if (!(i == 1 && j == 1)) {
		    currentX = startX + j;
		    currentY = startY + i;

		    while (currentX >= gibXDimension()) {
			currentX -= gibXDimension();
		    }

		    while (currentY >= gibYDimension()) {
			currentY -= gibYDimension();
		    }

		    if (gibZellzustand(currentX, currentY)) {
			nachbaranzahl++;
		    }
		}
	    }
	}
	return nachbaranzahl;
    }

    public final boolean gibZellzustand(final int x, final int y) {
	return zeilenMitZellen.get(y)[x];
    }

    public final int gibYDimension() {
	return zeilenMitZellen.size();
    }

    public final void setzeZustand(final boolean zustand, final int x,
	    final int y) {
	zeilenMitZellen.get(y)[x] = zustand;
    }
}
