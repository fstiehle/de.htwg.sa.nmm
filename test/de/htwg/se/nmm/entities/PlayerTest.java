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
        assertTrue(player.getMan().equals(Man.BLACK));
    }

    @Test
    public void testGetName() throws Exception {
        assertTrue(player.getName().equals("testplayer"));
    }
}