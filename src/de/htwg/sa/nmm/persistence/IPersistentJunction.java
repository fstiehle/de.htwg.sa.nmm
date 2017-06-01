package de.htwg.sa.nmm.persistence;

import java.io.Serializable;

/**
 * Created by fabianstiehle on 26.05.17.
 */
public interface IPersistentJunction extends Serializable {
    String getName();

    void setName(String name);

    Integer getId();

    IPersistentPuck getPuck();

    void setPuck(IPersistentPuck puck);
}
