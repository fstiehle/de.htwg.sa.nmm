package de.htwg.se.nmm.persistence.hibernate;

import com.sun.istack.internal.Nullable;
import de.htwg.se.nmm.model.IJunction;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "junction")
public class PersistentJunction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;

    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @Nullable
    private PersistentPuck puck;

    public PersistentJunction(String name, IJunction junction) {
        createPersistentJunction(name, junction);
    }

    public PersistentJunction() {
    }

    private void createPersistentJunction(String name, IJunction junction) {
        setName(name);
        if (junction.getPuck() != null) { // puck can be null
            setPuck(new PersistentPuck(junction.getPuck()));
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public PersistentPuck getPuck() {
        return puck;
    }

    public void setPuck(PersistentPuck puck) {
        this.puck = puck;
    }
}
