package de.htwg.sa.nmm.persistence;

import de.htwg.sa.nmm.model.IPlayer;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by fabianstiehle on 26.05.17.
 */
public interface IPersistentPlayer extends Serializable {

    enum State {
        SET,
        HOP,
        MOVE,
        PICK
    }

    UUID getID();

    void setID(UUID id);

    IPlayer.Man getMan();

    void setMan(IPlayer.Man man);

    void setPlayerState(State playerState);

    State getPlayerState();

    String getName();

    void setName(String name);

    int getNumPucks();

    void setNumPucks(int numPucks);

    int getNumPucksTakenAway();

    void setNumPucksTakenAway(int numPucksTakenAway);
}
