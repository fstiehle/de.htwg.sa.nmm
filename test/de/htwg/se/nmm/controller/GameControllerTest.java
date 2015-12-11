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

public class GameControllerTest extends TestCase {

    Map<String, Junction> board;
    Junction j;
    GameController controller;
    Junction mill1, mill2;

    @Before
    public void setUp() {
        controller = new GameController(new Board());
        controller.createPlayer("p1", "p2");
        this.board =  controller.getBoard();
        mill1 = this.board.get("a1");
        mill2 = this.board.get("a4");
    }

    @Test
    public void testCheckformill() throws Exception {
        controller.setPuck("a1", new Puck(controller.getCurrentPlayer())); // p1
        controller.setPuck("a4", new Puck(controller.getCurrentPlayer())); // p2
        controller.setPuck("a7", new Puck(controller.getCurrentPlayer())); // p1
        controller.setPuck("b4", new Puck(controller.getCurrentPlayer())); // p2
        controller.setPuck("d7", new Puck(controller.getCurrentPlayer())); // p1
        mill1 = this.board.get("a1"); // p2
        assertFalse(controller.checkformill(mill1)); // p2
        controller.setPuck("c4", new Puck(controller.getCurrentPlayer())); // p2
        controller.setPuck("g1", new Puck(controller.getCurrentPlayer())); // p1
        mill2 = this.board.get("c4"); // p2
        assertTrue(controller.checkformill(mill2)); // p2
    }

    @Test
    public void testEmptyPucks() throws Exception {
        controller.setPuck("a1", new Puck(controller.getCurrentPlayer())); // p1
        controller.setPuck("a4", new Puck(controller.getCurrentPlayer())); // p2
        controller.setPuck("a7", new Puck(controller.getCurrentPlayer())); // p1

        controller.setPuck("b2", new Puck(controller.getCurrentPlayer())); // p2
        controller.setPuck("b4", new Puck(controller.getCurrentPlayer())); // p1
        controller.setPuck("b6", new Puck(controller.getCurrentPlayer())); // p2

        controller.setPuck("c3", new Puck(controller.getCurrentPlayer())); // p1
        controller.setPuck("c4", new Puck(controller.getCurrentPlayer())); // p2
        controller.setPuck("c5", new Puck(controller.getCurrentPlayer())); // p1

        controller.setPuck("e3", new Puck(controller.getCurrentPlayer())); // p2
        controller.setPuck("e4", new Puck(controller.getCurrentPlayer())); // p1
        controller.setPuck("e5", new Puck(controller.getCurrentPlayer())); // p2

        controller.setPuck("f2", new Puck(controller.getCurrentPlayer())); // p1
        controller.setPuck("f4", new Puck(controller.getCurrentPlayer())); // p2
        controller.setPuck("f6", new Puck(controller.getCurrentPlayer())); // p1

        controller.setPuck("g1", new Puck(controller.getCurrentPlayer())); // p2
        controller.setPuck("g4", new Puck(controller.getCurrentPlayer())); // p1
        controller.setPuck("g7", new Puck(controller.getCurrentPlayer())); // p2

        controller.setPuck("d1", new Puck(controller.getCurrentPlayer())); // p1
        controller.setPuck("d7", new Puck(controller.getCurrentPlayer())); // p2

        Junction check;

        check = this.board.get("g4");
        assertTrue(check.hasPuck());
        check = this.board.get("g7");
        assertTrue(check.hasPuck());

        check = this.board.get("d1");
        assertFalse(check.hasPuck());
        check = this.board.get("d7");
        assertFalse(check.hasPuck());
    }

    @Test
    public void testMovePucks() throws Exception {

        controller.setPuck("g7", new Puck(controller.getCurrentPlayer())); // p1
        controller.getCurrentPlayer().setStatus(Player.Status.MOVE);
        controller.movePuck("g7", "d7");

        Junction check;
        check = this.board.get("g7");
        assertFalse(check.hasPuck());
        check = this.board.get("d7");
        assertTrue(check.hasPuck());
    }
}