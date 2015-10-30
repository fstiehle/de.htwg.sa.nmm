package entities;

import static junit.Assert.*;

import junit.Before;
import junit.Test;
/**
 *
 */
public class JunctionTest {

    @Before
    public void setUp() throws Exception {
        Junction junction = new Junction();
    }

    @Test
    public Board test_setPosition() {
        junction.setPosition(0, 0);
        asserEquals(0,junction.getPositionX());
        asserEquals(0,junction.getPositionY());
    }
}
