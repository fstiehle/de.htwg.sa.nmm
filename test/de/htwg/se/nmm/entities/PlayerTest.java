package de.htwg.se.nmm.entities;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import de.htwg.se.nmm.entities.Puck.Men;

public class PlayerTest {

    Player player;

    @Before
    public void setUp() throws Exception {
        player = new Player("testplayer", Men.BLACK);
    }

    @Test
    public void testPlayer() {
        Player tmpplayer = new Player("tmpplayer", Men.WHITE);
        assertTrue(tmpplayer.getName().equals("tmpplayer"));
        assertTrue(tmpplayer.getMen().equals(Men.WHITE));
        assertTrue(tmpplayer.getStatus() == Player.Status.Set);
    }

    @Test
    public void testGetStatus() throws Exception {
        assertTrue(player.isStatus(Player.Status.Set));
        player.setStatus(Player.Status.Pick);
        assertTrue(player.isStatus(Player.Status.Pick));
    }

    @Test
    public void testIsStatus() throws Exception {
        assertTrue(player.isStatus(Player.Status.Set));
        player.setStatus(Player.Status.Move);
        assertTrue(player.isStatus(Player.Status.Move));
        assertFalse(player.isStatus(Player.Status.Pick));
    }

    @Test
    public void testSetStatus() throws Exception {
        assertTrue(player.isStatus(Player.Status.Set));
        player.setStatus(Player.Status.Move);
        assertTrue(player.isStatus(Player.Status.Move));
    }

    @Test
    public void testGetMen() throws Exception {
        assertTrue(player.getMen().equals(Men.BLACK));
    }

    @Test
    public void testGetName() throws Exception {
        assertTrue(player.getName().equals("testplayer"));
    }
}