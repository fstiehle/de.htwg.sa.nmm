package de.htwg.sa.nmm.persistence.couch;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.htwg.sa.nmm.model.IPuck;
import de.htwg.sa.nmm.persistence.IPersistentPlayer;
import de.htwg.sa.nmm.persistence.IPersistentPuck;

public class PersistentPuck implements IPersistentPuck {

    private Integer id;

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
    @JsonIgnore
    public void setPlayer(IPersistentPlayer player) {
        this.player = (PersistentPlayer) player;
    }

    @JsonProperty("player")
    public void setPlayer(PersistentPlayer player) {
        this.player = player;
    }
}

