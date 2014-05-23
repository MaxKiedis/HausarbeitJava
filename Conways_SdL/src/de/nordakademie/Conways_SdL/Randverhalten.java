package de.nordakademie.Conways_SdL;

public abstract class Randverhalten {
    public abstract Spielfeld initialisierenRandverhalten(final 
	    Spielfeld spielfeld);

    public abstract Spielfeld bereinigenRandverhalten(final 
	    Spielfeld neuesFeld);

    public abstract Spielfeld abschliessenRandverhalten(final 
	    Spielfeld letztesFeld);
}
