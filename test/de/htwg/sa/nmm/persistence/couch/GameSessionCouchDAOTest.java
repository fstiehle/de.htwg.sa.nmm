package de.htwg.sa.nmm.persistence.couch;

import de.htwg.sa.nmm.model.impl.GameSession;
import de.htwg.sa.nmm.model.impl.Player;
import de.htwg.sa.nmm.model.IPlayer;
import de.htwg.sa.nmm.model.impl.Board;
import de.htwg.sa.nmm.persistence.IGameSessionDAO;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

/**
 * Created by fabianstiehle on 26.05.17.
 * ad66d1381fc666b038629fa1a1000cfa
 */
@Ignore
public class GameSessionCouchDAOTest {

    @Test
    public void completeSessionHandling() throws Exception {
        UUID whiteID = UUID.randomUUID();
        UUID blackID = UUID.randomUUID();
        UUID sessID = UUID.randomUUID();

        IGameSessionDAO dao = new GameSessionCouchDAO();
        Board board = new Board();

        IPlayer curPlayer = new Player("player1", IPlayer.Man.WHITE);
        curPlayer.setUserID(whiteID);
        IPlayer black = new Player("player2", IPlayer.Man.BLACK);
        black.setUserID(blackID);

        dao.saveSession(new GameSession(sessID, "testSession", board, black, curPlayer, curPlayer));
        ArrayList<HashMap<String, String>> data = new ArrayList<>();
        HashMap<String, String> sessData = new HashMap<>();
        sessData.put("name", "testSession");
        sessData.put("id", sessID.toString());
        data.add(sessData);

        ArrayList<HashMap<String, String>> dbData = dao.getData(blackID, whiteID);
        assertArrayEquals(data.toArray(), dbData.toArray());
    }

}