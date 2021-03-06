package de.htwg.sa.nmm.persistence.db4o;

import de.htwg.sa.nmm.model.IGameSession;
import de.htwg.sa.nmm.model.impl.GameSession;
import de.htwg.sa.nmm.model.impl.Player;
import de.htwg.sa.nmm.model.IJunction;
import de.htwg.sa.nmm.model.IPlayer;
import de.htwg.sa.nmm.model.impl.Board;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.Assert.*;

/**
 * Created by funkemarkus on 05.04.17.
 * TODO: Test Generisch mit Dependency Injection
 */
@Ignore
public class BoardDb4oDAOTest {
    private static final String SESSION_NAME = "foo";
    private static final UUID SESSION_ID = UUID.randomUUID();
    private IGameSession session;
    IPlayer curPlayer = new Player("2", IPlayer.Man.WHITE);

    @Before
    public void setUp() throws Exception {
        session = new GameSession(SESSION_ID, SESSION_NAME, new Board(), new Player("1", IPlayer.Man.BLACK),
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
        assertEquals(session.getSessionID().toString(), db.getSession(session.getSessionID()));
    }

    @Test
    public void deleteBoard() throws Exception {
        IGameSession tmpSession = new GameSession(SESSION_ID, SESSION_NAME, new Board(), new Player("1", IPlayer.Man.BLACK),
                curPlayer, curPlayer);
        GameSessionDb4oDAO db = GameSessionDb4oDAO.getInstance();
        db.saveSession(tmpSession);

        assertTrue(db.containsSession(tmpSession));

        db.deleteSession(tmpSession);

        assertFalse(db.containsSession(tmpSession));
        assertNull(db.getSession(SESSION_ID));
    }

    @Test
    public void closeDb() throws Exception {

    }
}