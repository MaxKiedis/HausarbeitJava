package de.nordakademie.Conways_SdL;

/**
 * 
 * @author Max Anthony
 * 
 */
public abstract class Randverhalten {
    /**
     * 
     * @param spielfeld
     * @return
     */
    public abstract Spielfeld initialisierenRandverhalten(
	    final Spielfeld spielfeld);

    /**
     * 
     * @param neuesFeld
     * @return
     */
    public abstract Spielfeld bereinigenRandverhalten(final 
	    Spielfeld neuesFeld);

    /**
     * 
     * @param letztesFeld
     * @return
     */
    public abstract Spielfeld abschliessenRandverhalten(
	    final Spielfeld letztesFeld);
}
