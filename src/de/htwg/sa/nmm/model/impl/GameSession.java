package de.htwg.sa.nmm.model.impl;

import de.htwg.sa.nmm.model.*;
import de.htwg.sa.nmm.persistence.IPersistentGameSession;
import de.htwg.sa.nmm.persistence.IPersistentJunction;

import java.util.Map;

/**
 * Created by funkemarkus on 05.04.17.
 */
public class GameSession implements IGameSession {

    private String id;
    private IBoard board;
    private IPlayer playerBlack;
    private IPlayer playerWhite;
    private IPlayer playerCurrent;

    public GameSession(String id,
                       IBoard board,
                       IPlayer playerBlack,
                       IPlayer playerWhite,
                       IPlayer playerCurrent) {

        this.board = board;
        this.id = id;
        this.playerBlack = playerBlack;
        this.playerWhite = playerWhite;
        this.playerCurrent = playerCurrent;
    }

    public GameSession(IPersistentGameSession persGameSession) {
        IPlayer white = new Player(persGameSession.getPlayerWhite());
        IPlayer black = new Player(persGameSession.getPlayerBlack());

        playerWhite = white;
        playerBlack = black;

        if (persGameSession.getCurrentPlayer().equals(white)) {
            playerCurrent = white;
        } else {
            playerCurrent = black;
        }

        IBoard tmpBoard = new Board();
        // we only need to load information about the puck on the junction from DB
        for (Map.Entry<String, IPersistentJunction> entry : persGameSession.getBoardMap().entrySet()) {
            IJunction j = tmpBoard.getBoardMap().get(entry.getKey());
            IPersistentJunction jP = entry.getValue();
            IPuck puck = new Puck();

            if (jP.getPuck() != null) {
                if (jP.getPuck().getPlayer().equals(white)) {
                    puck.setPlayer(white);
                } else {
                    puck.setPlayer(black);
                }
                j.setPuck(puck);
            }

            tmpBoard.getBoardMap().put(entry.getKey(), j);
        }
        board = tmpBoard;
    }

    @Override
    public IBoard getBoard() {
        return board;
    }

    @Override
    public void setBoard(IBoard board) {
        this.board = board;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public IPlayer getPlayerBlack() {
        return playerBlack;
    }

    @Override
    public void setPlayerBlack(IPlayer playerBlack) {
        this.playerBlack = playerBlack;
    }

    @Override
    public IPlayer getPlayerWhite() {
        return playerWhite;
    }

    @Override
    public void setPlayerWhite(IPlayer playerWhite) {
        this.playerWhite = playerWhite;
    }

    @Override
    public IPlayer getPlayerCurrent() {
        return playerCurrent;
    }

    @Override
    public void setPlayerCurrent(IPlayer playerCurrent) {
        this.playerCurrent = playerCurrent;
    }
}
