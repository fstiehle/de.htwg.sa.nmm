package de.htwg.se.nmm.controller;

import de.htwg.se.nmm.entities.*;
import de.htwg.se.util.observer.Observable;

public class GameController extends Observable {

    private String statusMessage = "Welcome to HTWG NMM!";
    private Board board;


    public GameController(Board board) {
        this.board = board;
    }

    private void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public void setPuck(int a, int x, Puck puck) {

    }

    public String getStatus() {
        return statusMessage;
    }

}