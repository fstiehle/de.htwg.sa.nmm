package entities;

import org.junit.*;
import static org.junit.Assert.*;

public class PuckTest {

    Puck puck;

    @Before
    public void setUp() throws Exception {
        this.puck = new Puck();
        this.puck.setPlayer(1);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testSetPlayer() {
        assertNotNull(puck.getPlayer());
    }

    @Test
    public void testGetPlayer() {
        assertEquals(1, puck.getPlayer());
    }



}