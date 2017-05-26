package de.htwg.se.nmm.persistence;

import de.htwg.se.nmm.persistence.hibernate.PersistentPuck;

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
