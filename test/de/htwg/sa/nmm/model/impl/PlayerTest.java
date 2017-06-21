package de.htwg.sa.nmm.model.impl;

import de.htwg.sa.nmm.controller.impl.GameController;
import de.htwg.sa.nmm.controller.impl.MillController;
import de.htwg.sa.nmm.model.IJunction;
import de.htwg.sa.nmm.persistence.db4o.GameSessionDb4oDAO;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {

    @Test
    public void testPlayer() {
        Player player = new Player("testplayer", Player.Man.BLACK);

        assertTrue(player.getName().equals("testplayer"));
        assertTrue(player.getMan().equals(Player.Man.BLACK));
        assertTrue(player.getStatus() == player.getSET());
    }

    @Test
    public void testGetStatus() throws Exception {
        Player player = new Player("testplayer", Player.Man.BLACK);

        assertTrue(player.getStatus().equals(player.getSET()));
        player.setStatus(player.getPICK());
        assertTrue(player.getStatus().equals(player.getPICK()));
    }

    @Test
    public void testIsStatus() throws Exception {
        Player player = new Player("testplayer", Player.Man.BLACK);

        assertTrue(player.getStatus().equals(player.getSET()));
        player.setStatus(player.getMOVE());
        assertTrue(player.getStatus().equals(player.getMOVE()));
        assertFalse(player.getStatus().equals(player.getPICK()));
    }

    @Test
    public void testSetStatus() throws Exception {
        Player player = new Player("testplayer", Player.Man.BLACK);

        assertTrue(player.isStatus(player.getSET()));
        player.setStatus(player.getMOVE());
        assertTrue(player.isStatus(player.getMOVE()));
    }

    @Test
    public void testGetMen() throws Exception {
        Player player = new Player("testplayer", Player.Man.BLACK);

        assertTrue(player.getMan().equals(Player.Man.BLACK));
    }

    @Test
    public void testGetName() throws Exception {
        Player player = new Player("testplayer", Player.Man.BLACK);

        assertTrue(player.getName().equals("testplayer"));
    }

    @Test
    public void testEquals() throws Exception {
        Player p1 = new Player("p1", Player.Man.WHITE);
        Player p2 = new Player("p2", Player.Man.WHITE);
        assertTrue(p1.equals(p2));
    }

    @Test
    public void testGetNumPucks() throws Exception {
        Player p1 = new Player("p1", Player.Man.WHITE);
        assertEquals(p1.getNumPucks(), 9);
    }

    @Test(expected = RuntimeException.class)
    public void testDecrementPucks() throws Exception {
        Player p1 = new Player("p1", Player.Man.WHITE);
        p1.decrementPucks();
        assertEquals(p1.getNumPucks(), 8);
        for (int i = 0; i < 9; i++) {
            p1.decrementPucks();
        }
    }

    @Test
    public void testIncrementPucksTakenAway() throws Exception {
        Player p1 = new Player("p1", Player.Man.WHITE);
        p1.incrementPucksTakenAway();
        assertEquals(p1.getPucksTakenAway(), 1);
    }

    @Test
    public void testSetPuck() {
        GameController c = new GameController(new Board(), GameSessionDb4oDAO.getInstance(), new MillController());
        c.createPlayer("a", "b");
        Puck p = new Puck();
        p.setPlayer(c.getCurrentIPlayer());
        c.getCurrentIPlayer().setStatus(c.getCurrentIPlayer().getSET());
        c.setPuck("a1", p);
        IJunction j = c.getBoard().getBoardMap().get("a1");
        assertEquals(j.getPuck(), p);
    }

    @Test
    public void testGetHOP() {
        Player p = new Player("p1", Player.Man.WHITE);
        PlayerHOP ph = new PlayerHOP(p);
        assertNotEquals(p.getHOP(), ph);
    }

    @Test
    public void testGetHashcode() {
        Player p1 = new Player("a", Player.Man.WHITE);
        Player p2 = new Player("b", Player.Man.BLACK);
        assertNotEquals(p1.hashCode(), p2.hashCode());
    }
}