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

    @Before
    public void setUp() {
        controller = new GameController(new Board());
        Player player = new Player("test", Player.Man.WHITE);
        controller.setPuck("a1", new Puck(player));
        controller.setPuck("a4", new Puck(player));
        controller.setPuck("a7", new Puck(player));
        this.board =  controller.getBoard();
        j = this.board.get("a1");
    }

    @Test
    public void testCheckformil() throws Exception {
        assertEquals(controller.checkformil(j), true);

    }
}