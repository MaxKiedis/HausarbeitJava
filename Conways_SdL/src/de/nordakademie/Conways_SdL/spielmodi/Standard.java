package de.nordakademie.Conways_SdL.spielmodi;

import de.nordakademie.Conways_SdL.Spielmodus;

/**
 * 
 * @author Max Anthony
 * 
 */
public class Standard extends Spielmodus {

    /**
     * 
     */
    public final boolean gibLebenszustandNaechsteRunde(final int nachbaranzahl,
	    boolean lebenszustand) {
	switch (nachbaranzahl) {
	case 0:
	case 1:
	    return false;
	case 2:
	    if (lebenszustand) {
		return true;
	    } else {
		return false;
	    }
	case 3:
	    return true;
	case 4:
	case 5:
	case 6:
	case 7:
	case 8:
	    return false;
	default:
	    return false;
	}

    }
}
