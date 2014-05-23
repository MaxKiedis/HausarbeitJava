package de.nordakademie.Conways_SdL;

import java.util.ArrayList;

public class Spielfeld {

    private ArrayList<boolean[]> zustaendeZellen;

    public Spielfeld(final boolean[] zeile) {
	zustaendeZellen = new ArrayList<boolean[]>();
	zustaendeZellen.add(zeile);
    }

    public Spielfeld(final ArrayList<boolean[]> zeilenMitZellen) {
	this.zustaendeZellen = zeilenMitZellen;
    }

    public final void hinzufuegenZeile(final boolean[] zeile) {
	zustaendeZellen.add(zeile);
    }

    public Spielfeld entwickleGeneration(Randverhalten randverhalten,
            Spielmodus modus) {
        ArrayList<boolean[]> werteFuerSpielfeld = new ArrayList<boolean[]>();
        for (int i = 0; i < this.gibYDimension(); i++) {
            boolean[] zeile = new boolean[this.gibXDimension()];
            for (int j = 0; j < this.gibXDimension(); j++) {
        	zeile[j] = modus.gibLebenszustandNaechsteRunde(
        		this.gibNachbaranzahl(j, i), this.gibZellzustand(j, i));
            }
            werteFuerSpielfeld.add(zeile);
        }
        Spielfeld neuesSpielfeld = randverhalten.bereinigenRandverhalten(new Spielfeld(
        	werteFuerSpielfeld));
        return neuesSpielfeld;
    }

    public final int gibXDimension() {
	return zustaendeZellen.get(0).length;
    }

    public final int gibYDimension() {
        return zustaendeZellen.size();
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

    public boolean istGleichesSpielfeld(final Spielfeld spielfeld) {
	for (int i = 0; i < spielfeld.gibYDimension(); i++) {
	    for (int j = 0; j < spielfeld.gibXDimension(); j++) {
		if (spielfeld.gibZellzustand(j, i) != this.gibZellzustand(j, i)) {
		    return false;
		}
	    }
	}
	return true;
    }

    public final boolean gibZellzustand(final int x, final int y) {
        return zustaendeZellen.get(y)[x];
    }

    public final void setzeZellzustand(final boolean zustand, final int x,
            final int y) {
        zustaendeZellen.get(y)[x] = zustand;
    }
}
