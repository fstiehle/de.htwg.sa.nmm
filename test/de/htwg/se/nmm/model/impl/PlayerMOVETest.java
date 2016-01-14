package de.htwg.se.nmm.model.impl;

import de.htwg.se.nmm.controller.impl.GameController;
import de.htwg.se.nmm.model.IPuck;
import junit.framework.TestCase;
import org.junit.Test;

public class PlayerMOVETest extends TestCase {

    @Test(expected = RuntimeException.class)
    public void testSetPuck() throws Exception {
        GameController c = new GameController(new Board());
        Player p = (Player) c.getCurrentIPlayer();
        PlayerSET ph = new PlayerSET(p);
        Junction j = new Junction();
        Puck puck = new Puck();
        puck.setPlayer(p);
        j.setPuck(puck);

        IPuck p2 = (IPuck) puck;
        try {
            ph.setPuck(j, p2, p);
        } catch (Exception e) {
            assertNotNull(e);
        }

        j = null;
        try {
            ph.setPuck(j, new Puck(), p);
        } catch (Exception e) {
            assertNotNull(e);
        }
    }

    @Test(expected = RuntimeException.class)
    public void testPickPuck() throws Exception {
        GameController c = new GameController(new Board());
        Player p = (Player) c.getCurrentIPlayer();
        PlayerMOVE ph = new PlayerMOVE(p);
        Junction j = new Junction();
        Puck puck = new Puck();
        puck.setPlayer(p);
        j.setPuck(puck);

        try {
            ph.pickPuck(j, p);
        } catch (Exception e) {
            assertNotNull(e);
        }
    }

    @Test(expected = RuntimeException.class)
    public void testMovePuck() throws Exception {
        GameController c = new GameController(new Board());
        Player p = (Player) c.getCurrentIPlayer();
        PlayerMOVE ph = new PlayerMOVE(p);
        Junction j = new Junction();
        Junction j2 = new Junction();
        Puck puck = new Puck();
        puck.setPlayer(p);
        j.setPuck(puck);

        try {
            ph.movePuck(j, j2, p);
        } catch (Exception e) {
            assertNotNull(e);
        }

        j.setPuck(null);
        try {
            ph.movePuck(j, j2, p);
        } catch (Exception e) {
            assertNotNull(e);
        }

        try {
            ph.movePuck(j, j2, c.getOtherPlayer());
        } catch (Exception e) {
            assertNotNull(e);
        }

        j2.setPuck(puck);
        try {
            ph.movePuck(j, j2, p);
        } catch (Exception e) {
            assertNotNull(e);
        }

        j = null;
        j2 = null;
        try {
            ph.movePuck(j, j2, p);
        } catch (Exception e) {
            assertNotNull(e);
        }
    }

    @Test
    public void testToString() throws Exception {
        PlayerMOVE p = new PlayerMOVE(new Player("a1", Player.Man.WHITE));
        assertEquals(p.toString(), "MOVE");
    }
}