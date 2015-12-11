package de.htwg.se.nmm.entities;

public class Player {

    private static final int NUM_PUCKS = 3;

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
    private int numPucks;

    public Player(String name, Man man) {
        this.name = name;
        this.man = man;
        this.status = Status.SET;
        this.numPucks = NUM_PUCKS;
    }

    public int getNumPucks() {
        return this.numPucks;
    }

    public void decrementPucks() {
        if (this.numPucks == 0) {
            throw new RuntimeException("No Pucks left");
        }
        this.numPucks--;
    }

    public boolean hasPucks() {
        return this.numPucks > 0;
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
