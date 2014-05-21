package de.nordakademie.Conways_SdL;

public interface Randverhalten {
    public Spielfeld anlegenRand(Spielfeld spielfeld);

    public Spielfeld setzeRand(Spielfeld neuesFeld);

    public Spielfeld abziehenRand(Spielfeld letztesFeld);
}
