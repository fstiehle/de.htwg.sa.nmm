package entities;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class JunctionTest {
    Junction tJ;

    @Before
    public void setUp() throws Exception {
        Junction tJ = new Junction();
        tJ.setNeighbours(tJ, tJ, tJ, tJ);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testSetPosition() throws Exception {
        assertEquals(tJ, tJ);
    }

    @Test
    public void testSetNeighbours() throws Exception {
        assertNotNull(tJ);
    }

    @Test
    public void testSetRight() throws Exception {

    }

    @Test
    public void testSetDown() throws Exception {

    }

    @Test
    public void testSetLeft() throws Exception {

    }

    @Test
    public void testSetPositionX() throws Exception {

    }

    @Test
    public void testSetPositionY() throws Exception {

    }

    @Test
    public void testEquals() throws Exception {

    }

    @Test
    public void testHashCode() throws Exception {

    }
}