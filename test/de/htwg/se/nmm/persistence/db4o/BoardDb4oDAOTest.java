package de.htwg.se.nmm.persistence.db4o;

import de.htwg.se.nmm.model.IBoard;
import de.htwg.se.nmm.model.IGameSession;
import de.htwg.se.nmm.model.IJunction;
import de.htwg.se.nmm.model.IPlayer;
import de.htwg.se.nmm.model.impl.Board;
import de.htwg.se.nmm.model.impl.GameSession;
import de.htwg.se.nmm.model.impl.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by funkemarkus on 05.04.17.
 * TODO: Test Generisch mit Dependency Injection
 */
public class BoardDb4oDAOTest {
    private static final String SESSION_ID = "foo";
    private IGameSession session;
    IPlayer curPlayer = new Player("2", IPlayer.Man.WHITE);

    @Before
    public void setUp() throws Exception {
        session = new GameSession(SESSION_ID, new Board(), new Player("1", IPlayer.Man.BLACK),
                curPlayer, curPlayer);
    }

    @After
    public void tearDown() throws Exception {
        //GameSessionDb4oDAO.getInstance().closeDb();
    }

    @Test
    public void saveBoard() throws Exception {
        GameSessionDb4oDAO db = GameSessionDb4oDAO.getInstance();
        db.saveSession(session);

        List<IGameSession> dbSessions = db.getAllSessions();
        Map<String, IJunction> dbBoardMap = dbSessions.get(0).getBoard().getBoardMap();

        assertEquals(dbBoardMap.toString(), session.getBoard().getBoardMap().toString());
        assertEquals(session.getId(), db.getSession(session.getId()).getId());
    }

    @Test
    public void deleteBoard() throws Exception {
        IGameSession tmpSession = new GameSession("tmp", new Board(), new Player("1", IPlayer.Man.BLACK),
                curPlayer, curPlayer);
        GameSessionDb4oDAO db = GameSessionDb4oDAO.getInstance();
        db.saveSession(tmpSession);

        assertTrue(db.containsSession(tmpSession));

        db.deleteSession(tmpSession);

        assertFalse(db.containsSession(tmpSession));
        assertNull(db.getSession("tmp"));
    }

    @Test
    public void closeDb() throws Exception {

    }
}