package de.htwg.se.nmm.entities;

import org.junit.Before;
import org.junit.Test;
import de.htwg.se.nmm.entities.Player.Man;

import static org.junit.Assert.*;

public class PlayerTest {

    Player player;

    @Before
    public void setUp() throws Exception {
        player = new Player("testplayer", Man.BLACK);
    }

    @Test
    public void testPlayer() {
        Player tmpplayer = new Player("tmpplayer", Man.WHITE);
        assertTrue(tmpplayer.getName().equals("tmpplayer"));
        assertTrue(tmpplayer.getMan().equals(Man.WHITE));
        assertTrue(tmpplayer.getStatus() == Player.Status.SET);
    }

    @Test
    public void testGetStatus() throws Exception {
        assertTrue(player.isStatus(Player.Status.SET));
        player.setStatus(Player.Status.PICK);
        assertTrue(player.isStatus(Player.Status.PICK));
    }

    @Test
    public void testIsStatus() throws Exception {
        assertTrue(player.isStatus(Player.Status.SET));
        player.setStatus(Player.Status.MOVE);
        assertTrue(player.isStatus(Player.Status.MOVE));
        assertFalse(player.isStatus(Player.Status.PICK));
    }

    @Test
    public void testSetStatus() throws Exception {
        assertTrue(player.isStatus(Player.Status.SET));
        player.setStatus(Player.Status.MOVE);
        assertTrue(player.isStatus(Player.Status.MOVE));
    }

    @Test
    public void testGetMen() throws Exception {
        assertTrue(player.getMan().equals(Man.BLACK));
    }

    @Test
    public void testGetName() throws Exception {
        assertTrue(player.getName().equals("testplayer"));
    }
}