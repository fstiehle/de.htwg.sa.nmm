package de.htwg.se.nmm.controller.impl;

import de.htwg.se.nmm.model.impl.Board;
import de.htwg.se.nmm.model.impl.Junction;
import de.htwg.se.nmm.model.impl.Player;
import de.htwg.se.nmm.model.impl.Puck;
import de.htwg.se.nmm.util.observer.Observable;

import java.lang.reflect.Method;
import java.util.Map;

public class GameController extends Observable {

    private static final int HOP_THRESHOLD = 6;
    private static final int LOST_THRESHOLD = 7;

    private StringBuilder statusMessage;
    private Map<String, Junction> board;
    private Player white;
    private Player black;
    private Player currentPlayer;

    public GameController(Board board) {
        this.board = board.getBoardMap();
        this.white = null;
        this.black = null;
        this.currentPlayer = null;
        this.statusMessage = new StringBuilder();
        this.statusMessage.append("Welcome!");
    }

    public Player getOtherPlayer() {
        if (this.currentPlayer == this.white) {
            return this.black;
        } else {
            return this.white;
        }
    }

    public void changePlayer() {
        Player otherPlayer = this.getOtherPlayer();
        if (otherPlayer.getPucksTakenAway() == LOST_THRESHOLD) {
            otherPlayer.setStatus(otherPlayer.getSET());
        }

        this.currentPlayer = otherPlayer;
        Player current = otherPlayer;
        String name = this.currentPlayer.getName();

        if(current.hasLost()) {
            this.addStatusMessage(name + ": You failed.");

        } else if (!current.hasPucks() && current.getPucksTakenAway() < HOP_THRESHOLD) {
            currentPlayer.setStatus(current.getMOVE());
            this.addStatusMessage(name + ": It's now time to move.");

        } else if (current.getPucksTakenAway() == HOP_THRESHOLD ) {
            current.setStatus(current.getHOP());
            this.addStatusMessage(name + ": It's now time to hop.");

        }  else if(this.currentPlayer.hasPucks()) {
            current.setStatus(current.getSET());
            this.addStatusMessage(name + ": It's now time to set.");
        }
    }

    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }

    public void addStatusMessage(String statusMessage) {
        this.clearStatusMessage();
        this.statusMessage.append("\n");
        this.statusMessage.append(statusMessage);
    }

    private void clearStatusMessage() {
        this.statusMessage = new StringBuilder();
    }

    public void millAfterMove(Junction j) {
        if (checkformill(j, this.currentPlayer)) {
            this.addStatusMessage("Congratulations, Sir!\n" +
                    "You may now pick one of your opponents pucks that is not part of a mill.");
            this.currentPlayer.setStatus(this.currentPlayer.getPICK());
        } else {
            this.changePlayer();
        }
    }

    public boolean checkformill(Junction j, Player p) {
        int mill = -1;
        mill += checkformillR(j, 0, "Down", p);
        mill += checkformillR(j, 0, "Up", p);
        if(mill >= 3) {
            return true;
        }

        mill = -1;
        mill += checkformillR(j, 0, "Left", p);
        mill += checkformillR(j, 0, "Right", p);
        if(mill >= 3) {
            return true;
        }

        return false;
    }

    private int checkformillR(Junction j, int sum, String direction, Player p) {
        int t = 1;
        Method method;

        String m = "get" + direction;
        try {
            method = j.getClass().getMethod(m);

            if (method.invoke(j) != null) {
                if (((Junction) method.invoke(j)).hasPuck() &&
                        ((Junction) method.invoke(j)).getPuck().getPlayer().equals(p)) {
                    t += sum;
                    t += checkformillR((Junction) method.invoke(j), sum + 1, direction, p);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    public void update() {
        notifyObservers();
    }

    public Map<String, Junction> getBoard() {
        return board;
    }

    public void createPlayer(String name1, String name2) {
        this.white = new Player(name1, Player.Man.WHITE, this);
        this.black = new Player(name2, Player.Man.BLACK, this);
        this.currentPlayer = this.white;
    }

    public Puck createPuck() {
        return new Puck(this.currentPlayer);
    }

    public String getBoardString() {
        return board.toString();
    }

    public String getStatus() {
        return this.statusMessage.toString();
    }

}