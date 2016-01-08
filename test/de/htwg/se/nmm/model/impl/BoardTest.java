package de.htwg.se.nmm.model.impl;

import org.junit.Before;
import org.junit.Test;

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


}