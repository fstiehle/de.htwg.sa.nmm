package de.htwg.sa.nmm.model;

import java.util.UUID;

/**
 * Created by funkemarkus on 05.05.17.
 */
public interface IGameSession {

    IBoard getBoard();

    void setBoard(IBoard board);

    IPlayer getPlayerBlack();

    void setPlayerBlack(IPlayer playerBlack);

    IPlayer getPlayerWhite();

    void setPlayerWhite(IPlayer playerWhite);

    IPlayer getPlayerCurrent();

    void setPlayerCurrent(IPlayer playerCurrent);

    void setSessionID(UUID id);

    UUID getSessionID();
}
