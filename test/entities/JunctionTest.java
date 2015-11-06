package entities;

import org.junit.*;
import static org.junit.Assert.*;

public class JunctionTest {
    Junction tJ;

    @Before
    public void setUp() throws Exception {
        this.tJ = new Junction();
        this.tJ.setNeighbours(tJ, tJ, tJ, tJ);
        this.tJ.setPosition(1,2);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testSetPosition() throws Exception {
        assertArrayEquals(new int[2], tJ.getCoordinates());
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
    public void testSetPositionX() throws Exception {
        tJ.setPositionX(2);
        int [] tmp = tJ.getCoordinates();
        assertEquals(2,tmp[0]);
    }

    @Test
    public void testSetPositionY() throws Exception {
        tJ.setPositionY(2);
        int [] tmp = tJ.getCoordinates();
        assertEquals(2,tmp[1]);
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