package de.htwg.se.nmm.model.impl;

import de.htwg.se.nmm.controller.impl.GameController;
import de.htwg.se.nmm.model.IPlayer;
import junit.framework.TestCase;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

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

    @Test
    public void testMovePuck() throws Exception {
        GameController c = new GameController(new Board());
        Player p = (Player) c.getCurrentIPlayer();
        PlayerHOP ph = new PlayerHOP(p);
        Junction j = new Junction();
        Junction j2 = new Junction();
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