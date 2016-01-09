package de.htwg.se.nmm.model;

import de.htwg.se.nmm.model.impl.Board;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BoardImageTest {
    private IBoard testIBoard;

    @Before
    public void setUp() throws Exception {
        this.testIBoard = new Board();
    }

    @Test
    public void testGetBoardMap() throws Exception {
        assertNotNull(this.testIBoard.getBoardMap());
    }


}