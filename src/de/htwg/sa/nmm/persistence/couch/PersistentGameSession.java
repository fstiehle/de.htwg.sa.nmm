package de.htwg.sa.nmm.persistence.couch;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.htwg.sa.nmm.model.IGameSession;
import de.htwg.sa.nmm.persistence.IPersistentGameSession;
import de.htwg.sa.nmm.persistence.IPersistentJunction;
import de.htwg.sa.nmm.model.IJunction;
import de.htwg.sa.nmm.persistence.IPersistentPlayer;
import org.ektorp.support.CouchDbDocument;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PersistentGameSession extends CouchDbDocument implements IPersistentGameSession {

    private UUID id;

    private String sessionName;

    private Map<String, PersistentJunction> boardMap;

    private PersistentPlayer playerWhite;

    private PersistentPlayer playerBlack;

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
    public void setSessionID(UUID sessionID) {
        this.id = sessionID;
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
    @JsonIgnore
    public void setPlayerWhite(IPersistentPlayer playerWhite) {
        this.playerWhite = (PersistentPlayer) playerWhite;
    }

    @Override
    public IPersistentPlayer getPlayerBlack() {
        return playerBlack;
    }

    @Override
    @JsonIgnore
    public void setPlayerBlack(IPersistentPlayer playerBlack) {
        this.playerBlack = (PersistentPlayer) playerBlack;
    }

    @Override
    public IPersistentPlayer getCurrentPlayer() {
        return currentPlayer;
    }

    @Override
    @JsonIgnore
    public void setCurrentPlayer(IPersistentPlayer currentPlayer) {
        this.currentPlayer = (PersistentPlayer) currentPlayer;
    }

    @Override
    @JsonIgnore
    public Map<String, IPersistentJunction> getBoardMap() {
        Map<String, IPersistentJunction> tmp = new HashMap<>();
        boardMap.entrySet().stream().forEach((e) -> tmp.put(e.getKey(), e.getValue()));
        return tmp;
    }

    /* Setters for Ektorp */
    /* ---------------------------------------------*/

    @JsonProperty("boardMap")
    public Map<String, PersistentJunction> getJsonBoardMap() {
        return boardMap;
    }

    @JsonProperty("boardMap")
    public void setBoardMap(Map<String, PersistentJunction> boardMap) {
        this.boardMap = boardMap;
    }

    @JsonProperty("playerWhite")
    public void setPlayerWhite(PersistentPlayer playerWhite) {
        this.playerWhite = playerWhite;
    }

    @JsonProperty("playerBlack")
    public void setPlayerBlack(PersistentPlayer playerBlack) {
        this.playerBlack = playerBlack;
    }

    @JsonProperty("currentPlayer")
    public void setCurrentPlayer(PersistentPlayer currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public String getId() {
        return id.toString();
    }

    public void setId(String s) {
        id = UUID.fromString(s);
    }
}
