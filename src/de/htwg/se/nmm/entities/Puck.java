package de.htwg.se.nmm.entities;

public class Puck {

    public enum Men {
        WHITE,
        BLACK;
    }

    private int player;

    public void setPlayer(int player) {
        this.player = player;
    }

    public int getPlayer() {
        return player;
    }

}
