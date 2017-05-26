package de.htwg.se.nmm.persistence;

import java.io.Serializable;

/**
 * Created by fabianstiehle on 26.05.17.
 */
public interface IPersistentPuck extends Serializable {
    Integer getId();

    IPersistentPlayer getPlayer();

    void setPlayer(IPersistentPlayer player);
}
