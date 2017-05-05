package de.htwg.se.nmm.model.impl;

import de.htwg.se.nmm.controller.IGameController;
import de.htwg.se.nmm.model.IBoard;
import de.htwg.se.nmm.model.IGameSession;
import de.htwg.se.nmm.model.IPlayer;

/**
 * Created by funkemarkus on 05.04.17.
 */
public class GameSession implements IGameSession {

    private final String id;

    private IBoard board;

    private IPlayer playerBlack;

    private IPlayer playerWhite;

    private IPlayer playerCurrent;

    public GameSession(String id, IBoard board, IPlayer playerBlack, IPlayer playerWhite,
                       IPlayer playerCurrent) {
        this.board = board;
        this.id = id;
        this.playerBlack = playerBlack;
        this.playerWhite = playerWhite;
        this.playerCurrent = playerCurrent;
    }

    @Override
    public String getId() {
        return id;
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
