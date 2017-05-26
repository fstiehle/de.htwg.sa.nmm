package de.htwg.se.nmm.persistence.couch;

import com.sun.istack.internal.Nullable;
import de.htwg.se.nmm.model.IJunction;
import de.htwg.se.nmm.persistence.IPersistentJunction;
import de.htwg.se.nmm.persistence.IPersistentPuck;

import javax.persistence.*;

public class PersistentJunction implements IPersistentJunction {

    public Integer id;

    private String name;

    private PersistentPuck puck;

    public PersistentJunction(String name, IJunction junction) {
        createPersistentJunction(name, junction);
    }

    public PersistentJunction() {}

    private void createPersistentJunction(String name, IJunction junction) {
        setName(name);
        if (junction.getPuck() != null) { // puck can be null
            setPuck(new PersistentPuck(junction.getPuck()));
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public IPersistentPuck getPuck() {
        return puck;
    }

    @Override
    public void setPuck(IPersistentPuck puck) {
        this.puck = (PersistentPuck) puck;
    }
}
