package de.htwg.sa.nmm.persistence.hibernate;

import de.htwg.sa.nmm.model.IPuck;
import de.htwg.sa.nmm.persistence.IPersistentPuck;
import de.htwg.sa.nmm.persistence.IPersistentPlayer;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "puck")
public class PersistentPuck implements IPersistentPuck {

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
