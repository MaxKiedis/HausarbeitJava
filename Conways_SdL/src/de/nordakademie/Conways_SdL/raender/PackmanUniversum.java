package de.nordakademie.Conways_SdL.raender;

import de.nordakademie.Conways_SdL.Randverhalten;
import de.nordakademie.Conways_SdL.Spielfeld;

public class PackmanUniversum extends Randverhalten {

    public final Spielfeld anlegenRand(final Spielfeld spielfeld) {
	return spielfeld;
    }

    public final Spielfeld setzeRand(final Spielfeld neuesFeld) {
	return neuesFeld;
    }

    public final Spielfeld anwendenRandverhalten(final Spielfeld letztesFeld) {
	return letztesFeld;
    }
}
