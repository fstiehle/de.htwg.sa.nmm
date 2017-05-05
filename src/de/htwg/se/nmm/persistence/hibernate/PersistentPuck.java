package de.htwg.se.nmm.persistence.hibernate;

import de.htwg.se.nmm.model.IPlayer;
import de.htwg.se.nmm.model.IPuck;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "puck")
public class PersistentPuck implements Serializable {

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

    public Integer getId() {
        return id;
    }

    public PersistentPlayer getPlayer() {
        return player;
    }

    public void setPlayer(PersistentPlayer player) {
        this.player = player;
    }
}
