package de.htwg.sa.nmm.persistence.hibernate;

import de.htwg.sa.nmm.model.IGameSession;
import de.htwg.sa.nmm.model.IJunction;
import de.htwg.sa.nmm.persistence.IPersistentGameSession;
import de.htwg.sa.nmm.persistence.IPersistentJunction;
import de.htwg.sa.nmm.persistence.IPersistentPlayer;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name = "game_session")
    public class PersistentGameSession implements IPersistentGameSession {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String sessionName;

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

    public void createPersistentGameSession(IGameSession gameSession) {
        setSessionID(gameSession.getSessionID());
        setSessionName(gameSession.getSessionName());
        Map<String, PersistentJunction> persBoardMap = new HashMap<>();
        for (Map.Entry<String, IJunction> entry : gameSession.getBoard().getBoardMap().entrySet()) {
            PersistentJunction persJunction = new PersistentJunction(entry.getKey(), entry.getValue());
            persBoardMap.put(entry.getKey(), persJunction);
        }
        this.boardMap = persBoardMap;

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
    public UUID getSessionID() {
        return id;
    }

    @Override
    public void setSessionID(UUID id) {
        this.id = id;
    }

    @Override
    public String getSessionName() {
        return sessionName;
    }

    @Override
    public void setSessionName(String name) {
        sessionName = name;
    }

    @Override
    public IPersistentPlayer getPlayerWhite() {
        return playerWhite;
    }

    @Override
    public void setPlayerWhite(IPersistentPlayer playerWhite) {
        this.playerWhite = (PersistentPlayer) playerWhite;
    }

    @Override
    public IPersistentPlayer getPlayerBlack() {
        return playerBlack;
    }

    @Override
    public void setPlayerBlack(IPersistentPlayer playerBlack) {
        this.playerBlack = (PersistentPlayer) playerBlack;
    }

    @Override
    public IPersistentPlayer getCurrentPlayer() {
        return currentPlayer;
    }

    @Override
    public void setCurrentPlayer(IPersistentPlayer currentPlayer) {
        this.currentPlayer = (PersistentPlayer) currentPlayer;
    }

    @Override
    public Map<String, IPersistentJunction> getBoardMap() {
        return boardMap.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> (IPersistentJunction) e));
    }
}
