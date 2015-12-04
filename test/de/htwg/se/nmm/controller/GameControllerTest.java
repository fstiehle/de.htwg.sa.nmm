package de.htwg.se.nmm.controller;

import de.htwg.se.nmm.entities.Board;
import de.htwg.se.nmm.entities.Junction;
import de.htwg.se.nmm.entities.Player;
import de.htwg.se.nmm.entities.Puck;
import de.htwg.se.nmm.tui.TextUI;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

/**
 * Created by fabianstiehle on 27.11.15.
 */
public class GameControllerTest extends TestCase {

    Map<String, Junction> board;
    Junction j;
    GameController controller;
    Junction mill1, mill2, mill3;

    @Before
    public void setUp() {
        controller = new GameController(new Board());
        Player player = new Player("test", Player.Man.WHITE);
        controller.setPuck("a1", new Puck(player));
        controller.setPuck("a4", new Puck(player));
        controller.setPuck("a7", new Puck(player));
        controller.setPuck("b4", new Puck(player));
        controller.setPuck("c4", new Puck(player));
        controller.setPuck("b2", new Puck(player));
        controller.setPuck("d2", new Puck(player));
        controller.setPuck("f2", new Puck(player));
        this.board =  controller.getBoard();
        mill1 = this.board.get("a4");
        mill2 = this.board.get("d2");
        mill3 = this.board.get("d1");
    }

    @Test
    public void testCheckformil() throws Exception {
        assertTrue(controller.checkformill(mill1));
        assertTrue(controller.checkformill(mill2));
        assertFalse(controller.checkformill(mill3));

    }
}