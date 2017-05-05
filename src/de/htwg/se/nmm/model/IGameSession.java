package de.htwg.se.nmm.model;

/**
 * Created by funkemarkus on 05.05.17.
 */
public interface IGameSession {
    String getId();

    IBoard getBoard();

    void setBoard(IBoard board);

    IPlayer getPlayerBlack();

    void setPlayerBlack(IPlayer playerBlack);

    IPlayer getPlayerWhite();

    void setPlayerWhite(IPlayer playerWhite);

    IPlayer getPlayerCurrent();

    void setPlayerCurrent(IPlayer playerCurrent);
}
