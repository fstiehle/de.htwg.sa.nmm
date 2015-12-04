package de.htwg.se.nmm.controller;

import de.htwg.se.nmm.entities.*;
import de.htwg.se.util.observer.Observable;

import java.util.Map;

public class GameController extends Observable {

    private String statusMessage = "Welcome to HTWG NMM!";
    private Map<String, Junction> board;
    private Player white;
    private Player black;

    public GameController(Board board) {
        this.board = board.getBoardMap();
        this.white = null;
        this.black = null;
    }

    private void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public void setPuck(String s, Puck puck) {
        Junction j = board.get(s);
        j.setPuck(puck);
    }

    public boolean checkformil(Junction j) {
        if(checkformillR(j,1) == 3) {
            return true;
        }
        return false;
    }

    public int checkformillR(Junction j, int sum) {
        int t = 0;
        if (j.getDown() != null) {
            if (j.getDown().hasPuck()) {
                t += sum;
                t += checkformillR(j.getDown(), sum + 1);
            }
        }
        return t;
    }

    public void update() {
        statusMessage = "Board was refreshed";
        notifyObservers();
    }

    public Map<String, Junction> getBoard() {
        return board;
    }

    public void createPlayer(String name1, String name2) {
        this.white = new Player(name1, Player.Man.WHITE);
        this.black = new Player(name2, Player.Man.BLACK);
    }

    public Puck createPuck(Player player) {
        return new Puck(player);
    }

    public String getBoardString() {
        return board.toString();
    }

    public String getStatus() {
        return statusMessage;
    }

}