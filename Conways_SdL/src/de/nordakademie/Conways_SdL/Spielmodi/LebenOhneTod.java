package de.nordakademie.Conways_SdL.Spielmodi;

import de.nordakademie.Conways_SdL.Spielmodus;

public class LebenOhneTod extends Spielmodus {
    public boolean gibLebenszustandNaechsteRunde(int nachbaranzahl, boolean lebenszustand) {

	if (lebenszustand || nachbaranzahl == 3) {
	    return true;
	}

	return false;

    }
}
