package de.htwg.sa.nmm.model.impl;
import com.google.inject.Inject;
import de.htwg.sa.nmm.model.IPlayer;
import de.htwg.sa.nmm.model.IPuck;

public class Puck implements IPuck {

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