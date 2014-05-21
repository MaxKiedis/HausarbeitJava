package de.nordakademie.Conways_SdL.RandverhaltenSammlung;

import de.nordakademie.Conways_SdL.Randverhalten;
import de.nordakademie.Conways_SdL.Spielfeld;

public class PackmanUniversum implements Randverhalten {

    @Override
    public void setzeRand() {

    }

    @Override
    public Spielfeld anlegenRand(Spielfeld spielfeld) {
	return spielfeld;
    }

}
