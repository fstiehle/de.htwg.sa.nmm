package de.htwg.sa.nmm.persistence.couch;

import de.htwg.sa.nmm.model.IGameSession;
import de.htwg.sa.nmm.model.impl.GameSession;
import de.htwg.sa.nmm.model.impl.Player;
import de.htwg.sa.nmm.model.IPlayer;
import de.htwg.sa.nmm.model.impl.Board;
import de.htwg.sa.nmm.persistence.IGameSessionDAO;
import org.junit.Test;

/**
 * Created by fabianstiehle on 26.05.17.
 * ad66d1381fc666b038629fa1a1000cfa
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

    @Test
    public void getSession() throws Exception {
        IGameSessionDAO dao = new GameSessionCouchDAO();
        IGameSession g = dao.getSession("f81de584a2da7416f2c1fbc5af001e0b");
    }

}