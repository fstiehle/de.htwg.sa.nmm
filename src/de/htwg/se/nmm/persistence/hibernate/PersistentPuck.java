package de.htwg.se.nmm.persistence.hibernate;

import de.htwg.se.nmm.model.IPuck;
import de.htwg.se.nmm.persistence.IPersistentPlayer;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "puck")
public class PersistentPuck implements de.htwg.se.nmm.persistence.IPersistentPuck {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    private PersistentPlayer player;

    public PersistentPuck(IPuck puck) {
        createPersistentPuck(puck);
    }

    public PersistentPuck() {
    }

    private void createPersistentPuck(IPuck puck) {
        setPlayer(new PersistentPlayer(puck.getPlayer()));
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public IPersistentPlayer getPlayer() {
        return player;
    }

    @Override
    public void setPlayer(IPersistentPlayer player) {
        this.player = (PersistentPlayer) player;
    }
}
