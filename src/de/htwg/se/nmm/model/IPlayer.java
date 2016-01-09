package de.htwg.se.nmm.model;

import de.htwg.se.nmm.model.impl.Puck;

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
    void setPuck(String s, IPuck puck);

    void pickPuck(String s);

    void movePuck(String from, String to);

    IPlayerState getHOP();

    IPlayerState getSET();

    IPlayerState getMOVE();

    IPlayerState getPICK();

    enum Man {
        WHITE,
        BLACK,
    }
}
