package de.htwg.se.nmm.controller;

import de.htwg.se.nmm.entities.*;
import de.htwg.se.util.observer.Observable;

import java.lang.reflect.InvocationTargetException;
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

    private void changePlayer() {
        if (this.getOtherPlayer().getPucksTakenAway() == LOST_THRESHOLD) {
            this.getOtherPlayer().setStatus(Player.Status.GAME_LOST);
        }

        this.currentPlayer = getOtherPlayer();
        String name = this.currentPlayer.getName();

        if(this.getCurrentPlayer().getStatus().equals(Player.Status.GAME_LOST)) {
            this.addStatusMessage(name + ": You failed.");

        } else if (!this.currentPlayer.hasPucks() && this.currentPlayer.getPucksTakenAway() < HOP_THRESHOLD) {
            this.currentPlayer.setStatus(Player.Status.MOVE);
            this.addStatusMessage(name + ": It's now time to move.");

        } else if (this.currentPlayer.getPucksTakenAway() == HOP_THRESHOLD ) {
            this.currentPlayer.setStatus(Player.Status.HOP);
            this.addStatusMessage(name + ": It's now time to hop.");

        }  else if(this.currentPlayer.hasPucks()) {
            this.currentPlayer.setStatus(Player.Status.SET);
            this.addStatusMessage(name + ": It's now time to set.");
        }
    }

    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }

    private void addStatusMessage(String statusMessage) {
        this.clearStatusMessage();
        this.statusMessage.append("\n");
        this.statusMessage.append(statusMessage);
    }

    private void clearStatusMessage() {
        this.statusMessage = new StringBuilder();
    }

    private void millAfterMove(Junction j) {
        if (checkformill(j, this.currentPlayer)) {
            this.addStatusMessage("Congratulations, Sir!\n" +
                    "You may now pick one of your opponents pucks that is not part of a mill.");
            this.currentPlayer.setStatus(Player.Status.PICK);
        } else {
            this.changePlayer();
        }
    }

    public void setPuck(String s, Puck puck) {
        Junction j = board.get(s);

        if(j == null) {
            this.addStatusMessage("Illegal move, please check your coordinates.");
            return;
        }
        if (j.hasPuck()) {
            this.addStatusMessage("There already is a Puck.");
            return;
        }
        if (!this.currentPlayer.isStatus(Player.Status.SET)) {
            this.addStatusMessage("You're not allowed to set a puck.");
            return;
        }

        j.setPuck(puck);
        if(currentPlayer.getStatus() == Player.Status.SET) {
            this.currentPlayer.decrementPucks();
            millAfterMove(j);
        }
    }

    public void pickPuck(String s) {
        Junction j = board.get(s);

        if(j == null) {
            this.addStatusMessage("Illegal move, please check your coordinates.");
            return;
        }
        if (!j.hasPuck() || !this.currentPlayer.isStatus(Player.Status.PICK) ||
                j.getPuck().getPlayer().equals(this.currentPlayer)) {
            this.addStatusMessage("Can't take away Puck.");
            return;
        }
        if(checkformill(j, getOtherPlayer())) {
            this.addStatusMessage("Can't take away a puck if it is part of a mill.");
            return;
        }

        j.setPuck(null);
        this.getOtherPlayer().incrementPucksTakenAway();
        this.changePlayer();
    }

    public void movePuck(String from, String to) {
        Junction jFrom = board.get(from);
        Junction jTo = board.get(to);

        if(jFrom == null || jTo == null) {
            this.addStatusMessage("Illegal move, please check your coordinates.");
            return;
        }
        if((!currentPlayer.isStatus(Player.Status.MOVE) && !this.currentPlayer.isStatus(Player.Status.HOP))) {
            this.addStatusMessage("Can't move.");
            return;
        }
        if(jTo.hasPuck()) {
            this.addStatusMessage("There already is a Puck.");
            return;
        }
        if(!jFrom.getPuck().getPlayer().equals(currentPlayer)) {
            this.addStatusMessage("That's not your puck unfortunately.");
            return;
        }
        if (currentPlayer.isStatus(Player.Status.MOVE)) {
            if (!checkMovement(from, to)) {
                this.addStatusMessage("Move is not allowed.");
                return;
            }
        }
        jTo.setPuck(jFrom.getPuck());
        jFrom.setPuck(null);
        millAfterMove(jTo);
    }

    private boolean checkMovement(String from, String to) {

        Junction jFrom = board.get(from);
        Junction jTo = board.get(to);

        if(jFrom.getDown() != null && jFrom.getDown().equals(jTo)) {
            return true;
        } else if(jFrom.getUp() != null && jFrom.getUp().equals(jTo)) {
            return true;
        } else if(jFrom.getRight() != null && jFrom.getRight().equals(jTo)) {
            return true;
        } else if(jFrom.getLeft() != null && jFrom.getLeft().equals(jTo)) {
            return true;
        }
        return false;
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
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return t;
    }

    public void setTurn(Player player, Player.Status status) {
        player.setStatus(status);
    }

    public void update() {
        notifyObservers();
    }

    public Map<String, Junction> getBoard() {
        return board;
    }

    public void createPlayer(String name1, String name2) {
        this.white = new Player(name1, Player.Man.WHITE);
        this.black = new Player(name2, Player.Man.BLACK);
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