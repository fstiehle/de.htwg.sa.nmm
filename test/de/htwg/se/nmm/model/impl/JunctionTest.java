package de.htwg.se.nmm.model.impl;

import de.htwg.se.nmm.aview.gui.MouseAdapterHandler;
import de.htwg.se.nmm.controller.impl.GameController;
import de.htwg.se.nmm.model.IBoard;
import de.htwg.se.nmm.model.impl.Board;
import de.htwg.se.nmm.model.impl.Junction;
import de.htwg.se.nmm.model.impl.Player;
import de.htwg.se.nmm.model.impl.Puck;
import org.junit.*;

import java.awt.*;
import java.awt.event.MouseAdapter;

import static org.junit.Assert.*;

public class JunctionTest {
    Junction tJ;
    Puck puck;
    Player player;

    @Before
    public void setUp() throws Exception {
        this.tJ = new Junction();
        this.tJ.setNeighbours(tJ, tJ, tJ, tJ);
        this.puck = new Puck();
        this.player = new Player("tmptest", Player.Man.BLACK);
        this.puck.setPlayer(this.player);
    }

    @Test
    public void testSetNeighbours() throws Exception {
        assertNotNull(tJ.getUp());
        assertNotNull(tJ.getRight());
        assertNotNull(tJ.getDown());
        assertNotNull(tJ.getLeft());
    }

    @Test
    public void testSetUp() throws Exception {
        tJ.setUp(new Junction());
        assertNotNull(tJ.getRight());
    }

    @Test
    public void testSetRight() throws Exception {
        tJ.setRight(new Junction());
        assertNotNull(tJ.getRight());
    }

    @Test
    public void testSetDown() throws Exception {
        tJ.setDown(new Junction());
        assertNotNull(tJ.getRight());
    }

    @Test
    public void testSetLeft() throws Exception {
        tJ.setLeft(new Junction());
        assertNotNull(tJ.getRight());
    }

    @Test
    public void testGetPuck() throws Exception {
        assertNull(tJ.getPuck());
        tJ.setPuck(puck);
        assertTrue(tJ.getPuck().getPlayer().getName().equals("tmptest"));
        assertTrue(tJ.getPuck().getPlayer().getMan() == Player.Man.BLACK);
    }

    @Test
    public void testHasPuck() throws Exception {
        assertFalse(tJ.hasPuck());
        tJ.setPuck(puck);
        assertTrue(tJ.hasPuck());
    }

    @Test
    public void testEquals() throws Exception {
        assertNotEquals(tJ, new Junction());
        assertEquals(tJ,tJ);
    }


    @Test
    public void testSetPuck() throws Exception {
        assertNull(tJ.getPuck());

        tJ.setPuck(puck);
        assertEquals(tJ.getPuck(), puck);
    }

    @Test
    public void testGetUp() throws Exception {
        assertEquals(tJ.getUp(), tJ);

        Junction tmpJ = new Junction();
        tJ.setUp(tmpJ);
        assertEquals(tJ.getUp(), tmpJ);
    }

    @Test
    public void testGetRight() throws Exception {
        assertEquals(tJ.getRight(), tJ);

        Junction tmpJ = new Junction();
        tJ.setRight(tmpJ);
        assertEquals(tJ.getRight(), tmpJ);
    }

    @Test
    public void testGetDown() throws Exception {
        assertEquals(tJ.getDown(), tJ);

        Junction tmpJ = new Junction();
        tJ.setDown(tmpJ);
        assertEquals(tJ.getDown(), tmpJ);
    }

    @Test
    public void testGetLeft() throws Exception {
        assertEquals(tJ.getLeft(), tJ);

        Junction tmpJ = new Junction();
        tJ.setLeft(tmpJ);
        assertEquals(tJ.getLeft(), tmpJ);
    }


    @Test
    public void testToString() throws Exception {
        String white = "\u25CB-";
        String black = "\u25CF-";
        String empty = "x-";

        assertEquals(tJ.toString(), empty);

        tJ.setPuck(puck);
        assertEquals(tJ.toString(), black);

        player = new Player("tmptest", Player.Man.WHITE);
        puck.setPlayer(this.player);
        tJ.setPuck(puck);
        assertEquals(tJ.toString(), white);
    }

    @Test
    public void testPressed() throws Exception {
        tJ.setPressed(true);
        assertTrue(tJ.isPressed());
    }

    @Test public void testPlaceOnGui() {
        tJ.setName("test");
        tJ.x = 10;
        tJ.y = 20;
        tJ.setBounds(15, 15, 50, 50);

        assertEquals(tJ.getName(), "test");
        assertEquals(tJ.x, 10);
        assertEquals(tJ.y, 20);
        assertEquals(tJ.getBounds(), new Rectangle(15,15,50,50));
    }

}