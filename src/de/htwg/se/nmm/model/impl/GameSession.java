package de.htwg.se.nmm.model.impl;

import de.htwg.se.nmm.model.IBoard;
import de.htwg.se.nmm.model.IGameSession;

/**
 * Created by funkemarkus on 05.04.17.
 */
public class GameSession implements IGameSession {

    private final String id;
    private IBoard board;

    public GameSession(String ID, IBoard board) {
        id = ID;
        this.board = board;
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
}
