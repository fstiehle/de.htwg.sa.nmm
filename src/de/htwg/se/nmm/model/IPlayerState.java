package de.htwg.se.nmm.model;

import de.htwg.se.nmm.model.impl.Puck;

public interface IPlayerState {

    void setPuck(String s, Puck puck);

    void pickPuck(String s);

    void movePuck(String from, String to);

    String toString();

}
