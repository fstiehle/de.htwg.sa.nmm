package de.htwg.se.nmm.controller;

import de.htwg.se.nmm.entities.*;
import de.htwg.se.util.observer.Observable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

public class GameController extends Observable {

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
        this.statusMessage.append("Welcome to HTWG NMM!");
    }

    private void changePlayer() {
        if (this.currentPlayer == this.white) {
            this.currentPlayer = this.black;
        } else {
            this.currentPlayer = this.white;
        }
        this.clearStatusMessage();
        this.addStatusMessage("Its " + this.currentPlayer.getName());
    }

    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }

    private void addStatusMessage(String statusMessage) {
        this.statusMessage.append("\n");
        this.statusMessage.append(statusMessage);
    }

    private void clearStatusMessage() {
        this.statusMessage = new StringBuilder();
    }

    public void setPuck(String s, Puck puck) {
        Junction j = board.get(s);

        if (!j.hasPuck() && this.currentPlayer.isStatus(Player.Status.SET) && this.currentPlayer.hasPucks()) {
            j.setPuck(puck);
            this.currentPlayer.decrementPucks();

            if (checkformill(j)) {
                this.addStatusMessage("Congratulations, Sir!");
            }

            this.changePlayer();
        } else {
            this.addStatusMessage("There already is a Puck.");
        }
    }

    public boolean checkformill(Junction j) {
        int mill = -1;
        mill += checkformillR(j, 0, "Down");
        mill += checkformillR(j, 0, "Up");
        if(mill >= 3) {
            return true;
        }

        mill = -1;
        mill += checkformillR(j, 0, "Left");
        mill += checkformillR(j, 0, "Right");
        if(mill >= 3) {
            return true;
        }

        return false;
    }

    private int checkformillR(Junction j, int sum, String direction) {
        int t = 1;
        Method method;

        String m = "get" + direction;
        try {
            method = j.getClass().getMethod(m);

            if (method.invoke(j) != null) {
                if (((Junction) method.invoke(j)).hasPuck() &&
                        ((Junction) method.invoke(j)).getPuck().getPlayer().equals(this.currentPlayer)) {
                    t += sum;
                    t += checkformillR((Junction) method.invoke(j), sum + 1, direction);
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