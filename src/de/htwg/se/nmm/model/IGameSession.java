package de.htwg.se.nmm.model;

/**
 * Created by funkemarkus on 05.04.17.
 */
public interface IGameSession {
    String getId();

    IBoard getBoard();

    void setBoard(IBoard board);
}
