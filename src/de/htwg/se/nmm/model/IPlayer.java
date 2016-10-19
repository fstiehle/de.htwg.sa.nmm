package de.htwg.se.nmm.model;

public interface IPlayer {

    int getNumPucks();

    int getPucksTakenAway();

    void decrementPucks();

    void incrementPucksTakenAway();

    boolean hasPucks();

    IPlayerState getStatus();

    boolean isStatus(IPlayerState status);

    void setStatus(IPlayerState status);

    boolean hasLost();

    Man getMan();

    String getName();

    /* State behaviour */
    void setPuck(IJunction j, IPuck puck, IPlayer currentPlayer);

    void pickPuck(IJunction j, IPlayer currentPlayer);

    void movePuck(IJunction jFrom, IJunction jTo, IPlayer currentPlayer);

    void setName(String name);

    IPlayerState getHOP();

    IPlayerState getSET();

    IPlayerState getMOVE();

    IPlayerState getPICK();

    enum Man {
        WHITE,
        BLACK,
    }
}
