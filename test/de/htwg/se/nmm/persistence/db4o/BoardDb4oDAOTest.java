package de.htwg.se.nmm.persistence.db4o;

import de.htwg.se.nmm.model.IBoard;
import de.htwg.se.nmm.model.IJunction;
import de.htwg.se.nmm.model.impl.Board;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by funkemarkus on 05.04.17.
 */
public class BoardDb4oDAOTest {
    private IBoard board;

    @Before
    public void setUp() throws Exception {
        board = new Board();
    }

    @After
    public void tearDown() throws Exception {
        //BoardDb4oDAO.getInstance().closeDb();
    }

    @Test
    public void saveBoard() throws Exception {
        BoardDb4oDAO db = BoardDb4oDAO.getInstance();
        db.saveBoard(board);

        List<IBoard> dbBoards = db.getAllBoards();
        Map<String, IJunction> dbBoardMap = dbBoards.get(0).getBoardMap();

        assertEquals(dbBoardMap.toString(), board.getBoardMap().toString());
    }

    @Test
    public void deleteBoard() throws Exception {

    }

    @Test
    public void closeDb() throws Exception {

    }
}