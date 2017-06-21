package de.htwg.sa.nmm.model.impl;

import de.htwg.sa.nmm.controller.impl.GameController;
import de.htwg.sa.nmm.model.IPlayer;
import de.htwg.sa.nmm.persistence.db4o.GameSessionDb4oDAO;
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
        GameController c = new GameController(new Board(), GameSessionDb4oDAO.getInstance());
        Player p = (Player) c.getCurrentIPlayer();
        PlayerPICK ph = new PlayerPICK(p);
        Puck puck = new Puck();
        puck.setPlayer(p);
        Junction j;
        j = null;
        try {
            ph.pickPuck(j, p);
        } catch (Exception e) {
            assertNotNull(e);
        }
        j = new Junction();
        try {
            ph.pickPuck(j, p);
        } catch (Exception e) {
            assertNotNull(e);
        }
        j = new Junction();
        j.setPuck(puck);
        IPlayer op = c.getOtherPlayer();
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
        PlayerPICK ph = new PlayerPICK(p);
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
    }

    @Test
    public void testToString() throws Exception {
        PlayerPICK p = new PlayerPICK(new Player("a1", Player.Man.WHITE));
        assertEquals(p.toString(), "PICK");
    }
}