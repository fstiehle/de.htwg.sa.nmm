package de.htwg.se.nmm.model.impl;
import com.google.inject.Inject;
import de.htwg.se.nmm.model.IPlayer;
import de.htwg.se.nmm.persistence.hibernate.PersistentPuck;

public class Puck implements de.htwg.se.nmm.model.IPuck {

    private IPlayer player;

    @Inject
    public Puck() {
        this.player = null;
    }

    @Override
    public void setPlayer(IPlayer player) {
        this.player = player;
    }

    @Override
    public Player getPlayer() {
        return (Player) this.player;
    }

}