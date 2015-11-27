package de.htwg.se.nmm.controller;

import de.htwg.se.nmm.entities.*;
import de.htwg.se.util.observer.Observable;

import java.util.HashMap;
import java.util.Map;

public class GameController extends Observable {

    private String statusMessage = "Welcome to HTWG NMM!";
    private Map<String, Junction> board;
    private Player WHITE;
    private Player BLACK;

    public GameController(Map<String, Junction> board) {
        this.board = board;
    }

    private void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public void setPuck(String s, Puck puck) {
        Junction j = board.get(s);
        j.setPuck(puck);
    }

    public void update() {
        statusMessage = "Board was refreshed";
        notifyObservers();
    }

    public Map<String, Junction> getBoard() {
        return board;
    }

    public void createPlayer(String name1, String name2) {
        this.WHITE = new Player(name1, Player.Man.WHITE);
        this.BLACK = new Player(name2, Player.Man.BLACK);
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