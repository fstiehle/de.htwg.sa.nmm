package de.htwg.sa.nmm.persistence;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by fabianstiehle on 17.05.17.
 */
public interface IPersistentGameSession {

    int getSessionID();

    IPersistentPlayer getPlayerWhite();

    void setPlayerWhite(IPersistentPlayer playerWhite);

    IPersistentPlayer getPlayerBlack();

    void setPlayerBlack(IPersistentPlayer playerBlack);

    IPersistentPlayer getCurrentPlayer();

    void setCurrentPlayer(IPersistentPlayer currentPlayer);

    Map<String, IPersistentJunction> getBoardMap();

    // TODO: create createPersistentGameSession in util class

}
