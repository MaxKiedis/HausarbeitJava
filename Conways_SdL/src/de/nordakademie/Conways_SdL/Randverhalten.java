package de.nordakademie.Conways_SdL;

public abstract class Randverhalten {
    public abstract Spielfeld anlegenRand(final Spielfeld spielfeld);

    public abstract Spielfeld setzeRand(final Spielfeld neuesFeld);

    public abstract Spielfeld anwendenRandverhalten(final Spielfeld letztesFeld);
}
