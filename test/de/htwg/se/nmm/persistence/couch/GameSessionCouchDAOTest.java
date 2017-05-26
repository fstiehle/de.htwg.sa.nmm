package de.htwg.se.nmm.persistence.couch;

import de.htwg.se.nmm.model.IPlayer;
import de.htwg.se.nmm.model.impl.Board;
import de.htwg.se.nmm.model.impl.GameSession;
import de.htwg.se.nmm.model.impl.Player;
import de.htwg.se.nmm.persistence.IGameSessionDAO;
import de.htwg.se.nmm.persistence.hibernate.GameSessionHibernateDAO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by fabianstiehle on 26.05.17.
 */
public class GameSessionCouchDAOTest {
    @Test
    public void saveSession() throws Exception {
        IGameSessionDAO dao = new GameSessionCouchDAO();
        Board board = new Board();
        IPlayer curPlayer = new Player("player1", IPlayer.Man.WHITE);
        dao.saveSession(new GameSession("1", board, new Player("player2", IPlayer.Man.BLACK),
                curPlayer, curPlayer));
    }
}