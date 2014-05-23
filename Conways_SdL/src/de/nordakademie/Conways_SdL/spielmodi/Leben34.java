package de.nordakademie.Conways_SdL.spielmodi;

import de.nordakademie.Conways_SdL.Spielmodus;

public class Leben34 extends Spielmodus {
    public boolean gibLebenszustandNaechsteRunde(final int nachbaranzahl, final boolean lebenszustand) {
	switch (nachbaranzahl) {
	case 0:
	case 1:
	case 2:
	    return false;
	case 3:
	case 4:
	    return true;
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
