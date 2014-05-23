package de.nordakademie.Conways_SdL;

/**
 * 
 * @author Max Anthony
 * 
 */
public abstract class Spielmodus {

    /**
     * 
     * @param nachbaranzahl
     * @param derzeitigenLebenstand
     * @return
     */
    public abstract boolean gibLebenszustandNaechsteRunde(
	    final int nachbaranzahl, final boolean derzeitigenLebenstand);
}
