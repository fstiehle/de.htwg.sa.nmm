package de.htwg.sa.nmm.persistence.hibernate;

import de.htwg.sa.nmm.model.IPlayer;
import de.htwg.sa.nmm.model.impl.Board;
import de.htwg.sa.nmm.model.impl.GameSession;
import de.htwg.sa.nmm.model.impl.Player;
import de.htwg.sa.nmm.persistence.IGameSessionDAO;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by funkemarkus on 18.04.17.
 */
@Ignore
public class GameSessionHibernateDAOTest {
    @Test
    public void saveSession() throws Exception {
        IGameSessionDAO dao = new GameSessionHibernateDAO();
        Board board = new Board();
        IPlayer curPlayer = new Player("player1", IPlayer.Man.WHITE);
        dao.saveSession(new GameSession(board, new Player("player2", IPlayer.Man.BLACK),
                curPlayer, curPlayer));
    }

}