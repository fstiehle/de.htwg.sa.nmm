package de.htwg.se.nmm.persistence.hibernate;


import com.sun.media.jfxmedia.events.PlayerStateEvent;
import de.htwg.se.nmm.model.IPlayer;
import de.htwg.se.nmm.model.IPlayerState;

import javax.persistence.*;
import java.io.Serializable;



@Entity
@Table(name = "player")
public class PersistentPlayer implements Serializable {

    public enum State {
        SET,
        HOP,
        MOVE,
        PICK
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

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

    public PersistentPlayer() {
    }

    private void createPersistentPlayer(IPlayer player) {
        setName(player.getName());
        setNumPucks(player.getNumPucks());
        setNumPucksTakenAway(player.getPucksTakenAway());
        setPlayerState(State.valueOf(player.getStatus().toString()));
        setMan(player.getMan());
    }

    public Integer getId() {
        return id;
    }

    public State getPlayerState() {
        return playerState;
    }

    public IPlayer.Man getMan() {
        return man;
    }

    public void setMan(IPlayer.Man man) {
        this.man = man;
    }

    public void setPlayerState(State playerState) {
        this.playerState = playerState;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumPucks() {
        return numPucks;
    }

    public void setNumPucks(int numPucks) {
        this.numPucks = numPucks;
    }

    public int getNumPucksTakenAway() {
        return numPucksTakenAway;
    }

    public void setNumPucksTakenAway(int numPucksTakenAway) {
        this.numPucksTakenAway = numPucksTakenAway;
    }
}
