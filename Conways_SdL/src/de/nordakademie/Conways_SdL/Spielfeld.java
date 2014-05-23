package de.nordakademie.Conways_SdL;

import java.util.ArrayList;

/**
 * 
 * @author Moritz Vetter
 * 
 */
public class Spielfeld {

    /** Speicher fuer Zellzustaende fuer ein Spielfeld. */
    private ArrayList<boolean[]> zustaendeZellen;

    /**
     * Konstruktor fuer ein Spielfeld.
     * 
     * @param zeilenMitZellen
     *            Zellwahrheitswerte (Lebenszustaende der einzelnen Zellen)
     */
    public Spielfeld(final ArrayList<boolean[]> zeilenMitZellen) {
	zustaendeZellen = zeilenMitZellen;
    }

    /**
     * Ermittelt die naechste Gemeration anhand des gewaehlten Randverhalten und
     * Spielmodus, indem fuer jede einzelne Zelle die Nachbaranzahl ermittelt
     * wird.
     * 
     * @param randverhalten
     *            das ausgewaehlte Randverhalten
     * @param modus
     *            der ausgewaehlte Spielmodus
     * @return Spielfeld der naechsten Generation
     */
    public final Spielfeld entwickleGeneration(
	    final Randverhalten randverhalten, final Spielmodus modus) {
	ArrayList<boolean[]> werteFuerSpielfeld = new ArrayList<boolean[]>();
	for (int i = 0; i < this.gibYDimension(); i++) {
	    boolean[] zeile = new boolean[this.gibXDimension()];
	    for (int j = 0; j < this.gibXDimension(); j++) {
		zeile[j] = modus.gibLebenszustandNaechsteRunde(
			this.gibNachbaranzahl(j, i), this.gibZellzustand(j, i));
	    }
	    werteFuerSpielfeld.add(zeile);
	}
	Spielfeld neuesSpielfeld = randverhalten
		.bereinigenRandverhalten(new Spielfeld(werteFuerSpielfeld));
	return neuesSpielfeld;
    }

    /**
     * Gibt die Anzahl der Spalten zurueck.
     * 
     * @return Spaltenanzahl
     */
    public final int gibXDimension() {
	return zustaendeZellen.get(0).length;
    }

    /**
     * Gibt die Anzahl der Zeilen zurueck.
     * 
     * @return Zeilenanzahl
     */
    public final int gibYDimension() {
	return zustaendeZellen.size();
    }

    /**
     * ueberprueft den Zustand benachbarter Zellen und addiert die lebendigen
     * Zellen zusammen.
     * 
     * @param x
     *            x-koordinate der Zelle fuer die die Nachbarzahl ermittelt
     *            wird.
     * @param y
     *            y-koordinate der Zelle fuer die die Nachbarzahl ermittelt
     *            wird.
     * @return Anzahl lebendiger Nachbarzellen
     */
    public final int gibNachbaranzahl(final int x, final int y) {
	int currentX;
	int currentY;
	int nachbaranzahl = 0;

	int startX = x - 1; //
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

    /**
     * Ueberprueft ob dieses Spielfeld mit dem uebergebenen Spielfeld
     * uebereinstimmt.
     * 
     * @param spielfeld
     *            das Spielfeld zu dem dieses Spielfeld verglichen werden soll.
     * @return Wahrheitswert (wahr = die Spielfelder sind gleich; false = die
     *         Spielfelder sind nicht gleich)
     */
    public final boolean istGleichesSpielfeld(final Spielfeld spielfeld) {
	for (int i = 0; i < spielfeld.gibYDimension(); i++) {
	    for (int j = 0; j < spielfeld.gibXDimension(); j++) {
		if (spielfeld.gibZellzustand(j, i) != this.gibZellzustand(j, i)) {
		    return false;
		}
	    }
	}
	return true;
    }

    /**
     * Gibt zurueck ob die Zelle lebendig oder tot ist.
     * 
     * @param x
     *            x-koordinate der Zelle
     * @param y
     *            y-koordinate der Zelle
     * @return Wahrheitswerte (lebt = true; tot = false)
     */
    public final boolean gibZellzustand(final int x, final int y) {
	return zustaendeZellen.get(y)[x];
    }

    /**
     * Legt fuer eine Zelle fest ob sie lebt oder tot ist.
     * 
     * @param zustand
     *            Wahrheitswert (lebt = true; tot = false)
     * @param x
     *            x-koordinate der Zelle
     * @param y
     *            y-koordinate der Zelle
     */
    public final void setzeZellzustand(final boolean zustand, final int x,
	    final int y) {
	zustaendeZellen.get(y)[x] = zustand;
    }
}
