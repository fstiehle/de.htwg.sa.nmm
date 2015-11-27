package de.htwg.se.nmm.entities;

import org.junit.*;
import static org.junit.Assert.*;

public class JunctionTest {
    Junction tJ;

    @Before
    public void setUp() throws Exception {
        this.tJ = new Junction();
        this.tJ.setNeighbours(tJ, tJ, tJ, tJ);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testSetNeighbours() throws Exception {
        assertNotNull(tJ.getUp());
        assertNotNull(tJ.getRight());
        assertNotNull(tJ.getDown());
        assertNotNull(tJ.getLeft());
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
    public void testEquals() throws Exception {
        assertNotEquals(tJ, new Junction());
        assertEquals(tJ,tJ);
    }

    @Test
    public void testHashCode() throws Exception {

    }
}