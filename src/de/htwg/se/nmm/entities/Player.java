package de.htwg.se.nmm.entities;

public class Player {

    public enum Men {
        WHITE,
        BLACK;
    }

    public enum Status {
        Set,
        Move,
        Pick,
        GameLost;
    }

    private final Men men;
    private final String name;
    private Status status;

    public Player(String name, Men men) {
        this.name = name;
        this.men = men;
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

    public Men getMen() {
        return this.men;
    }

    public String getName() {
        return this.name;
    }
}
