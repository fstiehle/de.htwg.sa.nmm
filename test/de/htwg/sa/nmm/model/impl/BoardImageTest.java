package de.htwg.sa.nmm.model.impl;

import de.htwg.sa.nmm.model.IBoard;
import de.htwg.sa.nmm.model.IJunction;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

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
        Map<String, IJunction> tmp = testIBoard.getBoardMap();
        assertEquals(testIBoard.getBoardMap(), tmp);
    }


}