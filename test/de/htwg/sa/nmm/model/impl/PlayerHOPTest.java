package de.htwg.sa.nmm.model.impl;

import de.htwg.sa.nmm.controller.impl.GameController;
import de.htwg.sa.nmm.persistence.db4o.GameSessionDb4oDAO;
import junit.framework.TestCase;
import org.junit.Test;

public class PlayerHOPTest extends TestCase {

    @Test(expected = RuntimeException.class)
    public void testSetPuck() throws Exception {
        Player p = new Player("a1", Player.Man.WHITE);
        PlayerHOP ph = new PlayerHOP(p);
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
        PlayerHOP ph = new PlayerHOP(p);
        Junction j = new Junction();
        try {
            ph.pickPuck(j, p);
        } catch (Exception e) {
            assertNotNull(e);
        }
    }

    @Test(expected = RuntimeException.class)
    public void testMovePuck() throws Exception {
        GameController c = new GameController(new Board(), GameSessionDb4oDAO.getInstance());
        Player p = (Player) c.getCurrentIPlayer();
        PlayerHOP ph = new PlayerHOP(p);
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
        PlayerHOP p = new PlayerHOP(new Player("a1", Player.Man.WHITE));
        assertEquals(p.toString(), "HOP");
    }
}