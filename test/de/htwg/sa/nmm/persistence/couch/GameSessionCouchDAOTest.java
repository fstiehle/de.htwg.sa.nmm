package de.htwg.sa.nmm.persistence.couch;

import de.htwg.sa.nmm.model.IGameSession;
import de.htwg.sa.nmm.model.impl.GameSession;
import de.htwg.sa.nmm.model.impl.Player;
import de.htwg.sa.nmm.model.IPlayer;
import de.htwg.sa.nmm.model.impl.Board;
import de.htwg.sa.nmm.persistence.IGameSessionDAO;
import org.junit.Ignore;
import org.junit.Test;

import java.util.UUID;

/**
 * Created by fabianstiehle on 26.05.17.
 * ad66d1381fc666b038629fa1a1000cfa
 */
@Ignore
public class GameSessionCouchDAOTest {

    @Test
    public void saveSession() throws Exception {
        IGameSessionDAO dao = new GameSessionCouchDAO();
        Board board = new Board();
        IPlayer curPlayer = new Player("player1", IPlayer.Man.WHITE);
        dao.saveSession(new GameSession(UUID.randomUUID(), "hallo", board, new Player("player2", IPlayer.Man.BLACK),
                curPlayer, curPlayer));
    }

    @Test
    public void getSession() throws Exception {
        IGameSessionDAO dao = new GameSessionCouchDAO();
        dao.getAllSessions();
    }

}