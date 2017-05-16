package de.htwg.se.nmm.persistence;

import de.htwg.se.nmm.persistence.hibernate.PersistentJunction;
import de.htwg.se.nmm.persistence.hibernate.PersistentPlayer;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by fabianstiehle on 17.05.17.
 */
public abstract class APersistentGameSession {

    public abstract int getId();

    public abstract Map<String, PersistentJunction> getBoardMap();

    public abstract void setBoardMap(Map<String, PersistentJunction> boardMap);

    public abstract PersistentPlayer getPlayerWhite();

    public abstract void setPlayerWhite(PersistentPlayer playerWhite);

    public abstract PersistentPlayer getPlayerBlack();

    public abstract void setPlayerBlack(PersistentPlayer playerBlack);

    public abstract PersistentPlayer getCurrentPlayer();

    public abstract void setCurrentPlayer(PersistentPlayer currentPlayer);

}
