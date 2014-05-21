package de.nordakademie.Conways_SdL.RandverhaltenSammlung;

import de.nordakademie.Conways_SdL.Randverhalten;
import de.nordakademie.Conways_SdL.Spielfeld;

public class PackmanUniversum implements Randverhalten {


    @Override
    public Spielfeld anlegenRand(Spielfeld spielfeld) {
	return spielfeld;
    }

    @Override
    public Spielfeld setzeRand(Spielfeld neuesFeld) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Spielfeld abziehenRand(Spielfeld letztesFeld) {
	return letztesFeld;
	// TODO Auto-generated method stub
	
    }

}
