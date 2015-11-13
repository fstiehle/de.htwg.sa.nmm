package controller;

import entities.*;
import util.observer.Observable;

public class GameController {

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