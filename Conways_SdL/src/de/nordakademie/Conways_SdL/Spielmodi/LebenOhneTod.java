package de.nordakademie.Conways_SdL.Spielmodi;

import de.nordakademie.Conways_SdL.Spielmodus;

public class LebenOhneTod extends Spielmodus {
    public final boolean gibLebenszustandNaechsteRunde(final int nachbaranzahl, final boolean lebenszustand) {

	if (lebenszustand || nachbaranzahl == 3) {
	    return true;
	}
	return false;
    }
}
