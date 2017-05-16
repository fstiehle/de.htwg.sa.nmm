package de.htwg.se.nmm.persistence.hibernate;

import de.htwg.se.nmm.model.IGameSession;
import de.htwg.se.nmm.model.IJunction;
import de.htwg.se.nmm.persistence.IPersistentGameSession;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "game_session")
public class PersistentGameSession implements Serializable, IPersistentGameSession {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(
            name = "boards",
            inverseJoinColumns = @JoinColumn(name = "junction_id"),
            joinColumns = @JoinColumn(name = "game_session_id"))
    @MapKey(name = "name")
    private Map<String, PersistentJunction> boardMap;

    @OneToOne(cascade = CascadeType.ALL)
    private PersistentPlayer playerWhite;

    @OneToOne(cascade = CascadeType.ALL)
    private PersistentPlayer playerBlack;

    @OneToOne(cascade = CascadeType.ALL)
    private PersistentPlayer currentPlayer;

    public PersistentGameSession(IGameSession gameSession) {
        createPersistentGameSession(gameSession);
    }

    public PersistentGameSession() {
    }

    private void createPersistentGameSession(IGameSession gameSession) {
        Map<String, PersistentJunction> persBoardMap = new HashMap<>();
        for (Map.Entry<String, IJunction> entry : gameSession.getBoard().getBoardMap().entrySet()) {
            PersistentJunction persJunction = new PersistentJunction(entry.getKey(), entry.getValue());
            persBoardMap.put(entry.getKey(), persJunction);
        }
        this.setBoardMap(persBoardMap);

        PersistentPlayer white = new PersistentPlayer(gameSession.getPlayerWhite());
        PersistentPlayer black = new PersistentPlayer(gameSession.getPlayerBlack());

        setPlayerBlack(black);
        setPlayerWhite(white);

        if (gameSession.getPlayerCurrent().equals(white)) {
            setCurrentPlayer(white);
        } else {
            setCurrentPlayer(black);
        }
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public Map<String, PersistentJunction> getBoardMap() {
        return boardMap;
    }

    @Override
    public void setBoardMap(Map<String, PersistentJunction> boardMap) {
        this.boardMap = boardMap;
    }

    @Override
    public PersistentPlayer getPlayerWhite() {
        return playerWhite;
    }

    @Override
    public void setPlayerWhite(PersistentPlayer playerWhite) {
        this.playerWhite = playerWhite;
    }

    @Override
    public PersistentPlayer getPlayerBlack() {
        return playerBlack;
    }

    @Override
    public void setPlayerBlack(PersistentPlayer playerBlack) {
        this.playerBlack = playerBlack;
    }

    @Override
    public PersistentPlayer getCurrentPlayer() {
        return currentPlayer;
    }

    @Override
    public void setCurrentPlayer(PersistentPlayer currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
}
