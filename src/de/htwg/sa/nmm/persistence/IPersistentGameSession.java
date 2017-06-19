package de.htwg.sa.nmm.persistence;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by fabianstiehle on 17.05.17.
 */
public interface IPersistentGameSession {

    UUID getSessionID();

    void setSessionID(UUID id);

    IPersistentPlayer getPlayerWhite();

    void setPlayerWhite(IPersistentPlayer playerWhite);

    IPersistentPlayer getPlayerBlack();

    void setPlayerBlack(IPersistentPlayer playerBlack);

    IPersistentPlayer getCurrentPlayer();

    void setCurrentPlayer(IPersistentPlayer currentPlayer);

    Map<String, IPersistentJunction> getBoardMap();

    // TODO: create createPersistentGameSession in util class

}
