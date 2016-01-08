package de.htwg.se.nmm.model;

import de.htwg.se.nmm.controller.impl.GameController;
import de.htwg.se.nmm.model.impl.Board;
import de.htwg.se.nmm.model.impl.Player;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {

    @Test
    public void testPlayer() {
        Player player = new Player("testplayer", Player.Man.BLACK, new GameController(new Board()));

        assertTrue(player.getName().equals("testplayer"));
        assertTrue(player.getMan().equals(Player.Man.BLACK));
        assertTrue(player.getStatus() == player.getSET());
    }

    @Test
    public void testGetStatus() throws Exception {
        Player player = new Player("testplayer", Player.Man.BLACK, new GameController(new Board()));

        assertTrue(player.getStatus().equals(player.getSET()));
        player.setStatus(player.getPICK());
        assertTrue(player.getStatus().equals(player.getPICK()));
    }

    @Test
    public void testIsStatus() throws Exception {
        Player player = new Player("testplayer", Player.Man.BLACK, new GameController(new Board()));

        assertTrue(player.getStatus().equals(player.getSET()));
        player.setStatus(player.getMOVE());
        assertTrue(player.getStatus().equals(player.getMOVE()));
        assertFalse(player.getStatus().equals(player.getPICK()));
    }

    @Test
    public void testSetStatus() throws Exception {
        Player player = new Player("testplayer", Player.Man.BLACK, new GameController(new Board()));

        assertTrue(player.isStatus(player.getSET()));
        player.setStatus(player.getMOVE());
        assertTrue(player.isStatus(player.getMOVE()));
    }

    @Test
    public void testGetMen() throws Exception {
        Player player = new Player("testplayer", Player.Man.BLACK, new GameController(new Board()));

        assertTrue(player.getMan().equals(Player.Man.BLACK));
    }

    @Test
    public void testGetName() throws Exception {
        Player player = new Player("testplayer", Player.Man.BLACK, new GameController(new Board()));

        assertTrue(player.getName().equals("testplayer"));
    }

    @Test
    public void testEquals() throws Exception {
        GameController controller = new GameController(new Board());

        Player p1 = new Player("p1", Player.Man.WHITE, controller);
        Player p2 = new Player("p2", Player.Man.WHITE, controller);
        assertTrue(p1.equals(p2));
    }
}