package de.htwg.se.nmm.entities;

import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class BoardTest {
    private Board testBoard;

    @Before
    public void setUp() throws Exception {
        this.testBoard = new Board();
    }

    @Test
    public void testGetBoardMap() throws Exception {
        assertNotNull(this.testBoard.getBoardMap());
    }

    @Test
    public void testCheckMovement() throws Exception {
        Map<String, Junction> boardMap = testBoard.getBoardMap();
        assertTrue(testBoard.checkMovement("a1","a4"));
        assertFalse(testBoard.checkMovement("a1","g4"));
    }
}