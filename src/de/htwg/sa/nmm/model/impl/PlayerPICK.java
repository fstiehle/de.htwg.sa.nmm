package de.htwg.sa.nmm.model.impl;

import de.htwg.sa.nmm.model.IPuck;
import de.htwg.sa.nmm.util.NmmRuntimeException;
import de.htwg.sa.nmm.model.IJunction;
import de.htwg.sa.nmm.model.IPlayer;
import de.htwg.sa.nmm.model.IPlayerState;

public class PlayerPICK implements IPlayerState {

    Player player;

    public PlayerPICK(Player player) {
        this.player = player;
    }

    @Override
    public void setPuck(IJunction j, IPuck puck, IPlayer cur) {
        throw new NmmRuntimeException("No more Pucks to set.");
    }

    @Override
    public void pickPuck(IJunction j, IPlayer cur) {

        if(j == null) {
            throw new NmmRuntimeException("Illegal move, please check your coordinates.");
        }
        if (!j.hasPuck()) {
            throw new NmmRuntimeException("There is no puck to take away.");
        }
        if (j.getPuck().getPlayer().equals(cur)) {
            throw new NmmRuntimeException("This is your own puck!");
        }
    }

    @Override
    public void movePuck(IJunction from, IJunction to, IPlayer cur) {
        throw new NmmRuntimeException("Don't you want to pick an enemy puck? (They won't enjoy that.)");
    }

    @Override
    public String toString() {
        return "PICK";
    }
}
