package de.htwg.se.nmm.persistence.couch;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.htwg.se.nmm.model.IGameSession;
import de.htwg.se.nmm.model.IJunction;
import de.htwg.se.nmm.persistence.IPersistentGameSession;
import de.htwg.se.nmm.persistence.IPersistentJunction;
import de.htwg.se.nmm.persistence.IPersistentPlayer;
import org.ektorp.support.CouchDbDocument;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class PersistentGameSession extends CouchDbDocument implements IPersistentGameSession {

    private Integer id = 155;

    private Map<String, PersistentJunction> DBboardMap;

    private PersistentPlayer playerWhite;

    private PersistentPlayer playerBlack;

    private PersistentPlayer currentPlayer;

    public PersistentGameSession(IGameSession gameSession) {
        createPersistentGameSession(gameSession);
    }

    public PersistentGameSession() {
    }

    public void createPersistentGameSession(IGameSession gameSession) {
        Map<String, PersistentJunction> persBoardMap = new HashMap<>();
        for (Map.Entry<String, IJunction> entry : gameSession.getBoard().getBoardMap().entrySet()) {
            PersistentJunction persJunction = new PersistentJunction(entry.getKey(), entry.getValue());
            persBoardMap.put(entry.getKey(), persJunction);
        }
        this.DBboardMap = persBoardMap;

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
    public int getSessionID() {
        return id;
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
    @JsonIgnore
    public Map<String, IPersistentJunction> getBoardMap() {
        return DBboardMap.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> (IPersistentJunction) e));
    }

    public Map<String, PersistentJunction> getDBBoardMap() {
        return DBboardMap;
    }
}
