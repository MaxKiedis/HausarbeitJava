package de.nordakademie.Conways_SdL;

/**
 * Vererbende Klasse der verschiedenen Spielmodi.
 * 
 * @author Max Anthony
 * 
 */
public abstract class Spielmodus {

    /**
     * Ermittelt abhaengig vom Spielmodus den Lebenszustand eines Feldes in der naechsten Generation.
     * 
     * @param nachbaranzahl Anzahl lebendiger Nachbarn je Feld
     * @param derzeitigenLebenstand Status des Feldes
     * @return Zustand des Feldes in naechster Generation
     */
    public abstract boolean gibLebenszustandNaechsteRunde(
	    final int nachbaranzahl, final boolean derzeitigenLebenstand);
}
