package de.htwg.se.nmm.model.impl;

import de.htwg.se.nmm.model.IJunction;
import de.htwg.se.nmm.model.IPlayer;
import de.htwg.se.nmm.model.IPlayerState;
import de.htwg.se.nmm.model.IPuck;

public class PlayerSET implements IPlayerState {

    Player player;

    public PlayerSET(Player player) {
        this.player = player;
    }

    @Override
    public void setPuck(IJunction j, IPuck puck, IPlayer cur) {
        if(j == null) {
            throw new RuntimeException("Illegal move, please check your coordinates.");
        }
        if (j.hasPuck()) {
            throw new RuntimeException("There already is a Puck.");
        }
    }

    @Override
    public void pickPuck(IJunction j, IPlayer p) {
        throw new RuntimeException("You don't have a valid mill, you can't pick yet.");
    }

    @Override
    public void movePuck(IJunction jFrom, IJunction jTo, IPlayer cur) {
        throw new RuntimeException("You must set all your pucks before you may move.");
    }

    @Override
    public String toString() {
        return "SET";
    }
}
