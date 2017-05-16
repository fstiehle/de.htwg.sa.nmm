package de.htwg.se.nmm.persistence;

import de.htwg.se.nmm.persistence.hibernate.PersistentJunction;
import de.htwg.se.nmm.persistence.hibernate.PersistentPlayer;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by fabianstiehle on 17.05.17.
 */
public interface IPersistentGameSession {

    int getId();

    Map<String, PersistentJunction> getBoardMap();

    void setBoardMap(Map<String, PersistentJunction> boardMap);

    PersistentPlayer getPlayerWhite();

    void setPlayerWhite(PersistentPlayer playerWhite);

    PersistentPlayer getPlayerBlack();

    void setPlayerBlack(PersistentPlayer playerBlack);

    PersistentPlayer getCurrentPlayer();

    void setCurrentPlayer(PersistentPlayer currentPlayer);

}
