package de.htwg.se.nmm.entities;

public class Player {

    public enum Man {
        WHITE,
        BLACK;
    }

    public enum Status {
        SET,
        MOVE,
        PICK,
        GAME_LOST;
    }

    private final Man man;
    private final String name;
    private Status status;

    public Player(String name, Man man) {
        this.name = name;
        this.man = man;
        this.status = Status.SET;
    }

    public Status getStatus() {
        return this.status;
    }

    public boolean isStatus(Status status) {
        return this.status == status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Man getMan() {
        return this.man;
    }

    public String getName() {
        return this.name;
    }
}
