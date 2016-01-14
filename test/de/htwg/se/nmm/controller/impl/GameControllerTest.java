package de.htwg.se.nmm.controller.impl;

import de.htwg.se.nmm.model.IPlayer;
import de.htwg.se.nmm.model.impl.Board;
import de.htwg.se.nmm.model.impl.Junction;
import de.htwg.se.nmm.model.impl.Player;
import de.htwg.se.nmm.model.impl.Puck;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;

public class GameControllerTest extends TestCase {

    Map<String, Junction> board;
    Junction j;
    GameController controller;
    Junction mill1, mill2;

    @Before
    public void setUp() {
        controller = new GameController(new Board());
        controller.createPlayer("p1", "p2");
        this.board =  controller.getBoard().getBoardMap();
        mill1 = this.board.get("a1");
        mill2 = this.board.get("a4");
    }

    @Test
    public void testGetOtherPlayer() throws Exception {
        IPlayer p = controller.getCurrentPlayer();
        IPlayer o = controller.getOtherPlayer();
        assertNotEquals(p, o);
    }
    @Test
    public void testCreatePlayer() throws Exception {
        controller.createPlayer("name1", "name2");
        assertTrue(controller.getCurrentPlayer().getName().equals("name1"));
        assertTrue(controller.getOtherPlayer().getName().equals("name2"));
    }
    @Test
    public void testGetStatus() throws Exception {
        assertNotNull(controller.getStatus());
    }

    @Test
    public void testCheckformill() throws Exception {
        Puck p = new Puck();
        p.setPlayer(controller.getCurrentIPlayer());
        controller.getCurrentPlayer().setPuck("a1", p); // p1

        p = new Puck();
        p.setPlayer(controller.getCurrentIPlayer());
        controller.getCurrentPlayer().setPuck("a4", p); // p2

        p = new Puck();
        p.setPlayer(controller.getCurrentIPlayer());
        controller.getCurrentPlayer().setPuck("a7", p); // p1

        p = new Puck();
        p.setPlayer(controller.getCurrentIPlayer());
        controller.getCurrentPlayer().setPuck("b4", p); // p2

        p = new Puck();
        p.setPlayer(controller.getCurrentIPlayer());
        controller.getCurrentPlayer().setPuck("d7", p); // p1

        mill1 = this.board.get("a1"); // p2
        assertFalse(controller.checkformill(mill1, controller.getCurrentPlayer())); // p2
        p = new Puck();
        p.setPlayer(controller.getCurrentIPlayer());
        controller.getCurrentPlayer().setPuck("c4", p); // p2

        p = new Puck();
        p.setPlayer(controller.getCurrentIPlayer());
        controller.getCurrentPlayer().setPuck("g1", p); // p1
        mill2 = this.board.get("c4"); // p2
        assertTrue(controller.checkformill(mill2, controller.getCurrentPlayer())); // p2
    }

    @Test
    public void testEmptyPucks() throws Exception {


        Puck p = new Puck();
        p.setPlayer(controller.getCurrentIPlayer());
        controller.getCurrentPlayer().setPuck("a1", p); // p1

        p = new Puck();
        p.setPlayer(controller.getCurrentIPlayer());
        controller.getCurrentPlayer().setPuck("a4", p); // p2

        p = new Puck();
        p.setPlayer(controller.getCurrentIPlayer());
        controller.getCurrentPlayer().setPuck("a7", p); // p1

        p = new Puck();
        p.setPlayer(controller.getCurrentIPlayer());
        controller.getCurrentPlayer().setPuck("b2", p); // p2

        p = new Puck();
        p.setPlayer(controller.getCurrentIPlayer());
        controller.getCurrentPlayer().setPuck("b4", p); // p1

        p = new Puck();
        p.setPlayer(controller.getCurrentIPlayer());
        controller.getCurrentPlayer().setPuck("b6", p); // p2

        p = new Puck();
        p.setPlayer(controller.getCurrentIPlayer());
        controller.getCurrentPlayer().setPuck("c3", p); // p1

        p = new Puck();
        p.setPlayer(controller.getCurrentIPlayer());
        controller.getCurrentPlayer().setPuck("c4", p); // p2

        p = new Puck();
        p.setPlayer(controller.getCurrentIPlayer());
        controller.getCurrentPlayer().setPuck("c5", p); // p1

        p = new Puck();
        p.setPlayer(controller.getCurrentIPlayer());
        controller.getCurrentPlayer().setPuck("e3", p); // p2

        p = new Puck();
        p.setPlayer(controller.getCurrentIPlayer());
        controller.getCurrentPlayer().setPuck("e4", p); // p1

        p = new Puck();
        p.setPlayer(controller.getCurrentIPlayer());
        controller.getCurrentPlayer().setPuck("e5", p); // p2

        p = new Puck();
        p.setPlayer(controller.getCurrentIPlayer());
        controller.getCurrentPlayer().setPuck("f2", p); // p1

        p = new Puck();
        p.setPlayer(controller.getCurrentIPlayer());
        controller.getCurrentPlayer().setPuck("f4", p); // p2

        p = new Puck();
        p.setPlayer(controller.getCurrentIPlayer());
        controller.getCurrentPlayer().setPuck("f6", p); // p1

        p = new Puck();
        p.setPlayer(controller.getCurrentIPlayer());
        controller.getCurrentPlayer().setPuck("g1", p); // p2

        p = new Puck();
        p.setPlayer(controller.getCurrentIPlayer());
        controller.getCurrentPlayer().setPuck("g4", p); // p2

        p = new Puck();
        p.setPlayer(controller.getCurrentIPlayer());
        controller.getCurrentPlayer().setPuck("g7", p); // p2

        p = new Puck();
        p.setPlayer(controller.getCurrentIPlayer());
        controller.getCurrentPlayer().setPuck("d1", p); // p2

        p = new Puck();
        p.setPlayer(controller.getCurrentIPlayer());
        controller.getCurrentPlayer().setPuck("d7", p); // p2

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

        Puck p = new Puck();
        p.setPlayer(controller.getCurrentPlayer());
        controller.getCurrentPlayer().setPuck("g7", p); // p1

        p.setPlayer(controller.getOtherPlayer());
        controller.getCurrentPlayer().setPuck("g4", p); // p2

        controller.getCurrentPlayer().setStatus(controller.getCurrentPlayer().getMOVE()); // p1
        controller.getCurrentPlayer().movePuck("g7", "d7");

        Junction check;
        check = this.board.get("g7");
        assertFalse(check.hasPuck());
        check = this.board.get("d7");
        assertTrue(check.hasPuck());
    }

}