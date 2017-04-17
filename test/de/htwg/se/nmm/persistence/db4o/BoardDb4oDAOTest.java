package de.htwg.se.nmm.persistence.db4o;

import de.htwg.se.nmm.model.IBoard;
import de.htwg.se.nmm.model.IGameSession;
import de.htwg.se.nmm.model.IJunction;
import de.htwg.se.nmm.model.impl.Board;
import de.htwg.se.nmm.model.impl.GameSession;
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

    @Before
    public void setUp() throws Exception {
        session = new GameSession(new Board(), SESSION_ID);
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
    }

    @Test
    public void deleteBoard() throws Exception {

    }

    @Test
    public void closeDb() throws Exception {

    }
}