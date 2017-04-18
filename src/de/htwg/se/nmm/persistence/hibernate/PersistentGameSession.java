package de.htwg.se.nmm.persistence.hibernate;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "game_session")
public class PersistentGameSession implements Serializable {

    @Id
    @Column(name = "id")
    private String id;

    @OneToOne
    private PersistentBoard board;

    @OneToOne
    private PersistentPlayer playerWhite;

    @OneToOne
    private PersistentPlayer playerBlack;

    @OneToOne
    private PersistentPlayer currentPlayer;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PersistentBoard getBoard() {
        return board;
    }

    public void setBoard(PersistentBoard board) {
        this.board = board;
    }

    public PersistentPlayer getPlayerWhite() {
        return playerWhite;
    }

    public void setPlayerWhite(PersistentPlayer playerWhite) {
        this.playerWhite = playerWhite;
    }

    public PersistentPlayer getPlayerBlack() {
        return playerBlack;
    }

    public void setPlayerBlack(PersistentPlayer playerBlack) {
        this.playerBlack = playerBlack;
    }

    public PersistentPlayer getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(PersistentPlayer currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
}
