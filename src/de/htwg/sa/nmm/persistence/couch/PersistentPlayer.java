package de.htwg.sa.nmm.persistence.couch;


import de.htwg.sa.nmm.model.IPlayer;
import de.htwg.sa.nmm.persistence.IPersistentPlayer;


public class PersistentPlayer implements IPersistentPlayer {

    private Integer id;

    private State playerState;

    private IPlayer.Man man;

    private String name;

    private int numPucks;

    private int numPucksTakenAway;


    public PersistentPlayer(IPlayer player) {
        createPersistentPlayer(player);
    }

    public PersistentPlayer() {
    }

    private void createPersistentPlayer(IPlayer player) {
        setName(player.getName());
        setNumPucks(player.getNumPucks());
        setNumPucksTakenAway(player.getPucksTakenAway());
        setPlayerState(State.valueOf(player.getStatus().toString()));
        setMan(player.getMan());
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public State getPlayerState() {
        return playerState;
    }

    @Override
    public IPlayer.Man getMan() {
        return man;
    }

    @Override
    public void setMan(IPlayer.Man man) {
        this.man = man;
    }

    @Override
    public void setPlayerState(State playerState) {
        this.playerState = playerState;
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
    public int getNumPucks() {
        return numPucks;
    }

    @Override
    public void setNumPucks(int numPucks) {
        this.numPucks = numPucks;
    }

    @Override
    public int getNumPucksTakenAway() {
        return numPucksTakenAway;
    }

    @Override
    public void setNumPucksTakenAway(int numPucksTakenAway) {
        this.numPucksTakenAway = numPucksTakenAway;
    }
}
