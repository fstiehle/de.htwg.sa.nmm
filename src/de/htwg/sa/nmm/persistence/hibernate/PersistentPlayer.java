package de.htwg.sa.nmm.persistence.hibernate;

import de.htwg.sa.nmm.model.IPlayer;
import de.htwg.sa.nmm.persistence.IPersistentPlayer;

import javax.persistence.*;
import java.util.UUID;


@Entity
@Table(name = "player")
public class PersistentPlayer implements IPersistentPlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Enumerated(EnumType.ORDINAL)
    private State playerState;

    @Enumerated(EnumType.ORDINAL)
    private IPlayer.Man man;

    private String name;

    @Column(name = "num_pucks")
    private int numPucks;

    @Column(name = "num_pucks_taken_away")
    private int numPucksTakenAway;


    public PersistentPlayer(IPlayer player) {
        createPersistentPlayer(player);
    }

    public PersistentPlayer() {}

    private void createPersistentPlayer(IPlayer player) {
        setID(player.getUserID());
        setName(player.getName());
        setNumPucks(player.getNumPucks());
        setNumPucksTakenAway(player.getPucksTakenAway());
        setPlayerState(State.valueOf(player.getStatus().toString()));
        setMan(player.getMan());
    }

    @Override
    public State getPlayerState() {
        return playerState;
    }

    @Override
    public UUID getID() {
        return id;
    }

    @Override
    public void setID(UUID id) {
        this.id = id;
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
