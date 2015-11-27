package de.htwg.se.nmm.entities;

public class Player {

    public enum Man {
        WHITE,
        BLACK;
    }

    public enum Status {
        Set,
        Move,
        Pick,
        GameLost;
    }

    private final Man man;
    private final String name;
    private Status status;

    public Player(String name, Man man) {
        this.name = name;
        this.man = man;
        this.status = Status.Set;
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
