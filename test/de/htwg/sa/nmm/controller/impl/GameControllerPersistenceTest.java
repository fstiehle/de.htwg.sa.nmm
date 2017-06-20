package de.htwg.sa.nmm.controller.impl;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.sun.java.swing.plaf.motif.MotifLookAndFeel;
import de.htwg.sa.nmm.GameModule;
import de.htwg.sa.nmm.controller.IGameController;
import de.htwg.sa.nmm.model.IJunction;
import de.htwg.sa.nmm.model.IPlayer;
import de.htwg.sa.nmm.model.impl.Board;
import de.htwg.sa.nmm.model.impl.Junction;
import de.htwg.sa.nmm.persistence.IGameSessionDAO;
import de.htwg.sa.nmm.persistence.couch.GameSessionCouchDAO;
import de.htwg.sa.nmm.persistence.db4o.GameSessionDb4oDAO;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Map;
import java.util.UUID;

import static org.junit.Assert.*;

/**
 * Created by fabianstiehle on 19.06.17.
 */
@Ignore
public class GameControllerPersistenceTest {

    GameController controller;
    Injector injector;
    IGameSessionDAO db;

    @Before
    public void setUp() {
        injector = Guice.createInjector(new GameModule());
        db = injector.getInstance(IGameSessionDAO.class);
        controller = new GameController(new Board(), db);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void persistence() throws Exception {
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        controller.getPlayer(IPlayer.Man.BLACK).setUserID(id2);
        controller.getPlayer(IPlayer.Man.WHITE).setUserID(id1);

        controller.saveGame("test");
        controller.loadGame(UUID.nameUUIDFromBytes(id1.toString().concat(id2.toString()).concat("test").getBytes()));

        assertEquals(id1, controller.getPlayer(IPlayer.Man.WHITE).getUserID());
    }

}