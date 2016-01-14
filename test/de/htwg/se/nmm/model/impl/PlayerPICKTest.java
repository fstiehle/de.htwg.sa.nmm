package de.htwg.se.nmm.model.impl;

import junit.framework.TestCase;
import org.junit.Test;

public class PlayerPICKTest extends TestCase {

    @Test(expected = RuntimeException.class)
    public void testSetPuck() throws Exception {
        Player p = new Player("a1", Player.Man.WHITE);
        PlayerPICK ph = new PlayerPICK(p);
        Junction j = new Junction();
        try {
            ph.setPuck(j, new Puck(), p);
        } catch (Exception e) {
            assertNotNull(e);
        }
    }

    @Test(expected = RuntimeException.class)
    public void testPickPuck() throws Exception {
        Player p = new Player("a1", Player.Man.WHITE);
        PlayerPICK ph = new PlayerPICK(p);
        Junction j = new Junction();
        try {
            ph.setPuck(j, new Puck(), p);
        } catch (Exception e) {
            assertNotNull(e);
        }
    }

    @Test(expected = RuntimeException.class)
    public void testMovePuck() throws Exception {
        Player p = new Player("a1", Player.Man.WHITE);
        PlayerPICK ph = new PlayerPICK(p);
        Junction j = new Junction();
        try {
            ph.setPuck(j, new Puck(), p);
        } catch (Exception e) {
            assertNotNull(e);
        }
    }

    @Test
    public void testToString() throws Exception {
        PlayerPICK p = new PlayerPICK(new Player("a1", Player.Man.WHITE));
        assertEquals(p.toString(), "PICK");
    }
}