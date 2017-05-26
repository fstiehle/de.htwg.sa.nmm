package de.htwg.se.nmm.persistence.couch;

import de.htwg.se.nmm.model.IPuck;
import de.htwg.se.nmm.persistence.IPersistentPlayer;
import de.htwg.se.nmm.persistence.IPersistentPuck;

import javax.persistence.*;

public class PersistentPuck implements IPersistentPuck {

    private Integer id;

    private IPersistentPlayer player;

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
        this.player = player;
    }
}
