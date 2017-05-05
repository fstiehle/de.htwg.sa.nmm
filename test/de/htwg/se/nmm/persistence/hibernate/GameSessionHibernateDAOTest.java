package de.htwg.se.nmm.persistence.hibernate;

import de.htwg.se.nmm.controller.IGameController;
import de.htwg.se.nmm.model.IPlayer;
import de.htwg.se.nmm.model.impl.Board;
import de.htwg.se.nmm.model.impl.GameSession;
import de.htwg.se.nmm.model.impl.Player;
import de.htwg.se.nmm.persistence.IGameSessionDAO;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by funkemarkus on 18.04.17.
 */
public class GameSessionHibernateDAOTest {
    @Test
    public void saveSession() throws Exception {
        IGameSessionDAO dao = new GameSessionHibernateDAO();
        Board board = new Board();
        IPlayer curPlayer = new Player("player1", IPlayer.Man.WHITE);
        dao.saveSession(new GameSession("1", board, new Player("player2", IPlayer.Man.BLACK),
                curPlayer, curPlayer));
    }

}