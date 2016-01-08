package de.htwg.se.nmm.model.impl;

import de.htwg.se.nmm.controller.impl.GameController;
import de.htwg.se.nmm.model.IPlayerState;

public class Player {

    private static final int NUM_PUCKS = 9;

    public enum Man {
        WHITE,
        BLACK,
    }

    private IPlayerState SET;
    private IPlayerState MOVE;
    private IPlayerState PICK;
    private IPlayerState HOP;

    private IPlayerState currentState;
    boolean gameLost;

    private final Man man;
    private final String name;
    private int numPucks;
    private int numPucksTakenAway;

    public Player(String name, Man man, GameController controller) {
        this.name = name;
        this.man = man;
        this.numPucks = NUM_PUCKS;
        this.numPucksTakenAway = 0;

        this.SET = new PlayerSET(this, controller);
        this.MOVE = new PlayerMOVE(this, controller);
        this.PICK = new PlayerPICK(this, controller);
        this.HOP = new PlayerHOP(this, controller);

        this.currentState = this.SET;
        this.gameLost = false;
    }

    public int getNumPucks() {
        return this.numPucks;
    }

    public int getPucksTakenAway() {
        return numPucksTakenAway;
    }

    public void decrementPucks() {
        if (this.numPucks == 0) {
            throw new RuntimeException("No Pucks left");
        }
        this.numPucks--;
    }

    public void incrementPucksTakenAway() {
        this.numPucksTakenAway++;
    }

    public boolean hasPucks() {
        return this.numPucks > 0;
    }

    public IPlayerState getStatus() {
        return this.currentState;
    }

    public boolean isStatus(IPlayerState status) {
        return this.currentState.equals(status);
    }

    public void setStatus(IPlayerState status) {
        this.currentState = status;
    }

    public boolean hasLost() {
        return gameLost;
    }

    public Man getMan() {
        return this.man;
    }

    public String getName() {
        return this.name;
    }

    /* State behaviour */
    public void setPuck(String s, Puck puck) {
        currentState.setPuck(s, puck);
    }

    public void pickPuck(String s) {
        currentState.pickPuck(s);
    }

    public void movePuck(String from, String to) {
        currentState.movePuck(from, to);
    }

    public IPlayerState getHOP() {
        return HOP;
    }

    public IPlayerState getSET() {
        return SET;
    }

    public IPlayerState getMOVE() {
        return MOVE;
    }

    public IPlayerState getPICK() {
        return PICK;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        return getMan() == player.getMan();

    }

    @Override
    public int hashCode() {
        return getMan().hashCode();
    }
}
