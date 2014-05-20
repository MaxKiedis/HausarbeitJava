package de.nordakademie.Conways_SdL.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import de.nordakademie.Conways_SdL.Dateihandling;

public class DateihandlingTest {

    @Test
    public void testPruefeDateipfad() {
	assertEquals("//kai//workspace//datei.start", Dateihandling.pruefeDateipfad("/kai/workspace/datei.start"));
	assertEquals("", Dateihandling.pruefeDateipfad(""));
    }

}
